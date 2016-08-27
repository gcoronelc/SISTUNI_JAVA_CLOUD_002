package bean;

import dao.component.CuentaDAO;
import dao.design.ICuentaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "cuentaBean")
@RequestScoped
public class CuentaBean {

	String cuenta;
	String mensaje = "";

	public CuentaBean() {
		cuenta = null;
		mensaje = null;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Double getSaldo() {
		Double saldo = 0.0;
		this.mensaje = "";
		if (cuenta == null) {
			return saldo;
		}
		try {
			if (cuenta.isEmpty()) {
				throw new Exception("Error: Cuenta vacia.");
			}
			ICuentaDAO dao = new CuentaDAO();
			saldo = dao.consultarSaldo(getCuenta());
		} catch (Exception e) {
			this.mensaje = "Error: " + e.getMessage();
		}
		return saldo;
	}

	public String getMensaje() {
		return mensaje;
	}
}
