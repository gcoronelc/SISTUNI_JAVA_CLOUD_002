
package jsf.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Gustavo Coronel
 * @blog   http://gcoronelc.blogspot.com
 */
@ManagedBean
@RequestScoped
public class Demo03 implements Serializable{

	private static final long serialVersionUID = 1L;
	private double precio;
	private int cant;
	private double total;
	private String destino;

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public String procesar(){
		total = precio * cant;
		return destino;
	}
	
	public void escuchar(ActionEvent e){
		destino = "demo03_resultado";
		if(precio <= 0 || cant <= 0){
			FacesMessage msg = new FacesMessage("Los valores no pueden ser negativos.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			destino = null;
		}
	}
}
