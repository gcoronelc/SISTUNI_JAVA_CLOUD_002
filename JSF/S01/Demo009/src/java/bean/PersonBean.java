package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "personBean")
@RequestScoped
public class PersonBean {

	@NotNull
	@Size(min = 4, max = 15)
	private String nombre;

	public PersonBean() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
