package uni.entity;

public class DetalleTO {
    //atributos

    private String idproducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private double importe;

    //constructor
    public DetalleTO() {
    }

    public DetalleTO(String idproducto, String nombre, double precio, int cantidad, double importe) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.importe = importe;
    }
    //metodos get y set

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

}
