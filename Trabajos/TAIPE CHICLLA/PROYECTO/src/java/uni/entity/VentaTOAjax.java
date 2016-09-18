/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.entity;

/**
 *
 * @author Edison
 */
public class VentaTOAjax {
    //atributos
    private int idventa;
    private String idcliente;
    private String idempleado;
    private String tipodoc;
    private String nrodoc;
    private double total;    
    private String DesEmpleado;
    //constructor

    public VentaTOAjax() {
    }

    public VentaTOAjax(int idventa, String idcliente, String idempleado, String tipodoc, String nrodoc, double total, String DesEmpleado) {
        this.idventa = idventa;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.tipodoc = tipodoc;
        this.nrodoc = nrodoc;
        this.total = total;        
        this.DesEmpleado = DesEmpleado;
    }
    //metodos get y set

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(String idempleado) {
        this.idempleado = idempleado;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }    

    public String getDesEmpleado() {
        return DesEmpleado;
    }

    public void setDesEmpleado(String DesEmpleado) {
        this.DesEmpleado = DesEmpleado;
    }
    

}
