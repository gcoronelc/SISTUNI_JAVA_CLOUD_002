package jsf.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gustavo Coronel
 * @blog   http://gcoronelc.blogspot.com
 */
@ManagedBean
@SessionScoped
public class Demo02b implements Serializable {

	private static final long serialVersionUID = 1L;
	private String idart;
	private double precio;

	public void setIdart(String idart) {
		this.idart = idart;
	}

	public String getIdart() {
		return idart;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}
}
