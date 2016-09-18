
package uni.sistemas.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uni.sistemas.modelo.Venta;
import uni.sistemas.service.VentaService;

@ManagedBean
@SessionScoped
public class VentaController {
    List<Venta>lista=new ArrayList<>();
    Venta venta;
    
    @PostConstruct
    public void init(){
        venta=new Venta();
    }

    public List<Venta> getLista() {
        return lista;
    }

    public void setLista(List<Venta> lista) {
        this.lista = lista;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    public String dolistarVentas(){
        try {
            VentaService ser=new VentaService();
            lista=ser.listaVenta();
        } catch (Exception e) {
        }
        return "/vista/venta/Ventas.xhtml";
    }
    
    public void prepararNuevaVenta(){
        venta=new Venta();
    }
}
