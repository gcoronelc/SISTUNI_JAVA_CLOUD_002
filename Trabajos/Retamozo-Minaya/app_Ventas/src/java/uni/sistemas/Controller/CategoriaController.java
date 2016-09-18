package uni.sistemas.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import uni.sistemas.modelo.Categoria;
import uni.sistemas.service.CategoriaService;

@ManagedBean
@SessionScoped
public class CategoriaController {

    private Categoria categoria;
    private List<Categoria> listaCategoria;
    private List<Categoria> lista_Eliminados=new ArrayList<>();
    private String cod;
    
    @PostConstruct
    public void init() {
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    
    
    
    public String listarCategoria() {
        try {
            CategoriaService ser = new CategoriaService();
            listaCategoria = ser.listaCategoria(categoria);
        } catch (Exception e) {
        }
        return "/vista/categoria/Categorias.xhtml";
    }

    public void prepararNuevaCategoria() {
        categoria = new Categoria();
    }

    public void registrarCategoria() {
        try {
            CategoriaService ser = new CategoriaService();
            categoria.setIdCategoria(ser.obtenerId());
            ser.insertarCategoria(categoria);
        } catch (Exception e) {
        }
        listarCategoria();
    }

    public void actualizar(RowEditEvent event) {
        try {
            CategoriaService ser=new CategoriaService();
            categoria = (Categoria) event.getObject();
            ser.ActualizarCategoria(categoria);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizacion del Producto "+categoria.getIdCategoria()));
        } catch (Exception e) {
        }

    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizacion Cancelada"));
    }
    
    public void eliminarCategoria(){
        CategoriaService ser=new CategoriaService();
        try {
            //el objeto c de la clase Categoria va a tomar todos los datos de la lista
            for(Categoria c : listaCategoria){
                //pasar todos los datos seleccionados a la lista de eliminados
                if(c.isSeleccionar()){
                    lista_Eliminados.add(c);
                }
            }
            //Si la lista eliminados no es vacia
            if(!lista_Eliminados.isEmpty()){
                    categoria.setIdCategoria(lista_Eliminados.get(0).getIdCategoria());
                    ser.eliminarCategoria(categoria);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Eliminados"));
                }
            } catch (Exception e) {
        }
        listarCategoria();
    }

}
