
package uni.sistemas.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import uni.sistemas.modelo.DetalleVenta;
import uni.sistemas.service.DetalleVentaService;

@ManagedBean
public class DetalleVentaController {
    List<DetalleVenta>lista;
    DetalleVenta deta;

    @PostConstruct
    public void init(){
        deta=new DetalleVenta();
    }
    
    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }

    public DetalleVenta getDeta() {
        return deta;
    }

    public void setDeta(DetalleVenta deta) {
        this.deta = deta;
    }
    
    public String doListarDetalleVenta(){
        try {
            DetalleVentaService ser=new DetalleVentaService();
            lista=ser.listaDetalle();
        } catch (Exception e) {
        }
        return "DetalleVentas.xhtml";
    }
}
