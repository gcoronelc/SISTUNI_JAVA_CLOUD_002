package pe.egcc.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class Ejemplo1Bean {

	private String nombre;
	
	public String getNombre() {
	  return nombre;
  }
	
	public void setNombre(String nombre) {
	  this.nombre = nombre;
  }
}
