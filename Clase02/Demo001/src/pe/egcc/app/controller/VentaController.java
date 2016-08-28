package pe.egcc.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import pe.egcc.app.model.ProductoBean;

@ManagedBean
public class VentaController {

  private String codigo;
  private String codSelect = "NONE";
  private List<SelectItem> productos;
  private HashSet<SelectItem> productos2;
  private List<ProductoBean> productos3;
  
  @PostConstruct
  public void init(){
    // Productos 1
    productos = new ArrayList<>();
    productos.add(new SelectItem("P500", "Producto 500"));
    productos.add(new SelectItem("P501", "Producto 501"));
    productos.add(new SelectItem("P502", "Producto 502"));
    productos.add(new SelectItem("P503", "Producto 503"));
    productos.add(new SelectItem("P504", "Producto 504"));
    productos.add(new SelectItem("P505", "Producto 505"));
    // Productos 2
    productos2 = new HashSet<>();
    productos2.add(new SelectItem("P600", "Producto 600"));
    productos2.add(new SelectItem("P601", "Producto 601"));
    productos2.add(new SelectItem("P602", "Producto 602"));
    productos2.add(new SelectItem("P603", "Producto 603"));
    productos2.add(new SelectItem("P604", "Producto 604"));
    productos2.add(new SelectItem("P605", "Producto 605"));
    // Productos 3
    productos3 = new ArrayList<>();
    productos3.add(new ProductoBean("P701", "Producto 701", 123.45, 400));
    productos3.add(new ProductoBean("P702", "Producto 702", 540.45, 100));
    productos3.add(new ProductoBean("P703", "Producto 703", 750.30, 400));
    productos3.add(new ProductoBean("P704", "Producto 704", 760.54, 300));
    productos3.add(new ProductoBean("P705", "Producto 705", 321.78, 500));
    productos3.add(new ProductoBean("P706", "Producto 706", 698.34, 600));
    productos3.add(new ProductoBean("P707", "Producto 707", 920.30, 400));
  }
  
  public List<SelectItem> getProductos() {
    return productos;
  }
  
  public HashSet<SelectItem> getProductos2() {
    return productos2;
  }
  
  public List<ProductoBean> getProductos3() {
    return productos3;
  }
  
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }
  
  public String getCodigo() {
    return codigo;
  }
  
  public String getCodSelect() {
    return codSelect;
  }
  
  public void doProcesar1(){
    codSelect = codigo;
  }
  
  
  
}
