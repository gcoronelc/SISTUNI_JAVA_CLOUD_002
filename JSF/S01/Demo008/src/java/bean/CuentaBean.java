package bean;

import dao.component.CuentaDAO;
import dao.design.ICuentaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "cuentaBean")
@RequestScoped
public class CuentaBean {

	private String cuenta; // RW
	private Double importe; // RW
	private String empleado; // RW
	private String mensaje; // R

	public CuentaBean() {
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void doDeposito() {
		try {
			ICuentaDAO dao = new CuentaDAO();
			dao.deposito(cuenta, importe, empleado);
			this.mensaje = "Proceso ok.";
			setCuenta("");
			setImporte(0.0);
			setEmpleado("");
		} catch (Exception e) {
			this.mensaje = "Error: " + e.getMessage();
		}
	}
}
