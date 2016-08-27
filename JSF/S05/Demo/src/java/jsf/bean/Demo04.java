
package jsf.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Gustavo Coronel
 * @blog   http://gcoronelc.blogspot.com
 */
@ManagedBean
@RequestScoped
public class Demo04 implements Serializable{

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

  public void setDestino(String destino) {
    this.destino = destino;
  }
  
  
}
