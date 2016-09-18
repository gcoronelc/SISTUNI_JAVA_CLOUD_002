
package uni.sistemas.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import uni.sistemas.modelo.TipoDocumento;
import uni.sistemas.service.TipoDocumentoService;

@ManagedBean
@SessionScoped

public class TipoDocuController {
    private TipoDocumento tipoDocumento;
    private List<TipoDocumento> listaTipoDocu;
    private List<TipoDocumento> lista_Eliminados=new ArrayList<>();
    
    @PostConstruct
    public void init() {
        tipoDocumento = new TipoDocumento();
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<TipoDocumento> getListaTipoDocu() {
        return listaTipoDocu;
    }

    public void setListaTipoDocu(List<TipoDocumento> listaTipoDocu) {
        this.listaTipoDocu = listaTipoDocu;
    }

    public List<TipoDocumento> getLista_Eliminados() {
        return lista_Eliminados;
    }

    public void setLista_Eliminados(List<TipoDocumento> lista_Eliminados) {
        this.lista_Eliminados = lista_Eliminados;
    }

    public String listarTipoDocumentos() {
        try {
            TipoDocumentoService ser = new TipoDocumentoService();
            listaTipoDocu = ser.listaTipoDocumento();
        } catch (Exception e) {
        }
        return "/vista/tipoDocumento/TipoDocumento.xhtml";
    }

    public void prepararNuevoDocumento() {
        tipoDocumento = new TipoDocumento();
    }

    public void registrarCategoria() {
        try {
            TipoDocumentoService ser = new TipoDocumentoService();
            tipoDocumento.setIdTipoDocu(ser.obtenerId());
            ser.insertarTipoDocumento(tipoDocumento);
        } catch (Exception e) {
        }
        listarTipoDocumentos();
    }

    public void actualizar(RowEditEvent event) {
        try {
            TipoDocumentoService ser=new TipoDocumentoService();
            tipoDocumento = (TipoDocumento) event.getObject();
            ser.ActualizarTipoDocumento(tipoDocumento);
            FacesContext.getCurrentInstance().addMessage(null, new 
        FacesMessage("Actualizacion del Producto "+tipoDocumento.getIdTipoDocu()));
        } catch (Exception e) {
        }

    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizacion Cancelada"));
    }
    
    public void eliminarCategoria(){
        TipoDocumentoService ser=new TipoDocumentoService();
        try {
            //el objeto c de la clase Categoria va a tomar todos los datos de la lista
            for(TipoDocumento td : listaTipoDocu){
                //pasar todos los datos seleccionados a la lista de eliminados
                if(td.isSelec()){
                    lista_Eliminados.add(td);
                }
            }
            //Si la lista eliminados no es vacia
            if(!lista_Eliminados.isEmpty()){
                    tipoDocumento.setIdTipoDocu(lista_Eliminados.get(0).getIdTipoDocu());
                    ser.eliminarTipoDocumento(tipoDocumento);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Eliminados"));
                }
            } catch (Exception e) {
        }
        listarTipoDocumentos();
    }

}
