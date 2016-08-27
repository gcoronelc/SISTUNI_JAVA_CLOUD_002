package pe.egcc.jsf.validadores.controller;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Gustavo Coronel
 */
@ManagedBean
@RequestScoped
public class Demo02Controller {

  private Date fechaNac;
  private Date fechaIng;

  public Date getFechaNac() {
    return fechaNac;
  }

  public void setFechaNac(Date fechaNac) {
    this.fechaNac = fechaNac;
  }

  public Date getFechaIng() {
    return fechaIng;
  }

  public void setFechaIng(Date fechaIng) {
    this.fechaIng = fechaIng;
  }
}
