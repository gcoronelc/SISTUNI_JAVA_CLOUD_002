/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.entity;
//atributos

/**
 *
 * @author Alumno
 */
public class ProductoTO {

    private String idproducto;
    private String descripcion;
    private int linea;
    private double preciocompra;
    private double precioventa;
    private int stock;

    public ProductoTO() {
    }

    public ProductoTO(String idproducto, String descripcion, int linea, double preciocompra, double precioventa, int stock) {
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.linea = linea;
        this.preciocompra = preciocompra;
        this.precioventa = precioventa;
        this.stock = stock;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    
    
}
