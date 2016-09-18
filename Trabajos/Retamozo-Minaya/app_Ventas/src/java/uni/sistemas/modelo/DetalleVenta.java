
package uni.sistemas.modelo;

public class DetalleVenta {
    
    private Venta venta;
    private Producto producto;
    private double cantidad;
    private double costo;
    private double precio;
    private double total;

    public DetalleVenta(Venta venta, Producto producto, double cantidad, double costo, double precio, double total) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.costo = costo;
        this.precio = precio;
        this.total = total;
    }

    public DetalleVenta() {
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
