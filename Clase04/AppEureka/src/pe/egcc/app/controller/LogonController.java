package pe.egcc.app.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pe.egcc.app.model.Empleado;
import pe.egcc.app.service.EmpleadoService;

@ManagedBean(name="logonController")
@SessionScoped
public class LogonController {
  
  private String usuario;
  private String clave;
  private Empleado empleado;
  
  public Empleado getEmpleado() {
    return empleado;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }
  
  public String doLogonProcesar(){
    String destino = "main";
    try {
      EmpleadoService service = new EmpleadoService();
      empleado = service.validar(usuario,clave);
    } catch (Exception e) {
      destino = "logon";
      FacesMessage msg; 
      msg  = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        "Error en el proceso.", e.getMessage()); 
      FacesContext.getCurrentInstance().addMessage(null, msg); 
    }
    return destino;
  }

}
