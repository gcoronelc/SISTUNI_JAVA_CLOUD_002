
package uni.sistemas.modelo;

import java.util.Date;

public class Venta {
    
    private int idVenta;
    private TipoDocumento tipoDocumento;
    private Cliente cliente;
    private Empleado empleado;
    private String serie;
    private String numero;
    private Date fecha;
    private double totalVenta;
    private double igv;
    private double totalPagar;
    private String estado;
    private boolean seleccionar;
    
    public Venta() {
    }

    public Venta(int idVenta, TipoDocumento tipoDocumento, Cliente cliente, Empleado empleado, String serie, String numero, Date fecha, double totalVenta, double igv, double totalPagar, String estado) {
        this.idVenta = idVenta;
        this.tipoDocumento = tipoDocumento;
        this.cliente = cliente;
        this.empleado = empleado;
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.totalVenta = totalVenta;
        this.igv = igv;
        this.totalPagar = totalPagar;
        this.estado = estado;
    }
    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
