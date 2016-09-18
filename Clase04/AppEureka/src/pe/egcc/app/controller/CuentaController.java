package pe.egcc.app.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import pe.egcc.app.service.CuentaService;

@ManagedBean
public class CuentaController {

  private String cuenta;
  private double importe;
  private String moneda = "None";
  private String clave;
  private double saldo;
  
  
  @ManagedProperty("#{logonController}")
  private LogonController logonController;
  
  public double getSaldo() {
    return saldo;
  }
  
  public void setLogonController(LogonController logonController) {
    this.logonController = logonController;
  }
  
  public void setClave(String clave) {
    this.clave = clave;
  }
  
  public String getClave() {
    return clave;
  }
  
  public String getMoneda() {
    return moneda;
  }

  public String getCuenta() {
    return cuenta;
  }

  public void setCuenta(String cuenta) {
    this.cuenta = cuenta;
  }

  public double getImporte() {
    return importe;
  }

  public void setImporte(double importe) {
    this.importe = importe;
  }

  public void doProcDeposito(){
    FacesMessage msg;
    try {
      CuentaService service = new CuentaService();
      service.procDeposito(cuenta, importe, "0004");
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso ok.", "El depóiso se realizò con éxito.");
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el proceso.", e.getMessage());
    }
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
  
  public void doProcRetiro(){
    FacesMessage msg;
    try {
      CuentaService service = new CuentaService();
      String codEmp = logonController.getEmpleado().getCodigo();
      service.procRetiro(cuenta, importe, codEmp, clave);
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso ok.", "El retiro se realizó con éxito.");
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el proceso.", e.getMessage());
    }
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
  
  public void doConsultarCuenta(){
    FacesMessage msg;
    try {
      CuentaService service = new CuentaService();
      saldo = service.conSaldoCuenta(cuenta);
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proceso ok.", "El retiro se realizó con éxito.");
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el proceso.", e.getMessage());
    }
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
  
}
