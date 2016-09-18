
package uni.sistemas.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import uni.sistemas.modelo.Categoria;
import uni.sistemas.modelo.Producto;
import uni.sistemas.service.ClienteService;
import uni.sistemas.service.ProductoService;

@ManagedBean
@SessionScoped
public class ProductoController {
    List<Producto>lista;
    Producto producto;
    List<SelectItem>listaCategoria;
    
    @PostConstruct
    public void init(){
        producto=new Producto();
    }

    public List<Producto> getLista() {
        return lista;
    }

    public void setLista(List<Producto> lista) {
        this.lista = lista;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<SelectItem> getListaCategoria() {
        this.listaCategoria=new ArrayList<SelectItem>();
        ProductoService ser=new ProductoService();
        List<Categoria>cat=ser.listaCategoria();
        listaCategoria.clear();
        
        for(Categoria c: cat){
            SelectItem cateIrem=new SelectItem(c.getIdCategoria(),c.getDescripcion());
            this.listaCategoria.add(cateIrem);
        }
        return listaCategoria;
    }
    
    public String dolistarProducto(){
        try {
            ProductoService ser=new ProductoService();
            lista=ser.listaProductos();
        } catch (Exception e) {
        }
        return "/vista/producto/Productos.xhtml";
    }
    
    public void prepararNuevoProducto(){
        producto=new Producto();
    }
    
    public void registrarProducto(){
        try {
            ProductoService ser=new ProductoService();
            producto.setIdProducto(ser.obtenerIdProducto());
            ser.registrarProducto(producto);
        } catch (Exception e) {
        }
        dolistarProducto();
    }
    
    
    
    public void actualizarProducto(){
        try {
            ProductoService ser=new ProductoService();
            ser.actualizarProducto(producto);
            producto=new Producto();
        } catch (Exception e) {
        }
        dolistarProducto();
    }
    
    public void eliminarProducto(){
        try {
            ProductoService ser=new ProductoService();
            ser.eliminarProducto(producto);
        } catch (Exception e) {
        }
        dolistarProducto();
    }
}
