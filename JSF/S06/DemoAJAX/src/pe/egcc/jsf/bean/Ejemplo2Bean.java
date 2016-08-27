package pe.egcc.jsf.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import pe.egcc.app.model.CuentaModel;

@ManagedBean
public class Ejemplo2Bean {

	private List<Map<String, Object>> movimientos;
	private String cuenta;

	public List<Map<String, Object>> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Map<String, Object>> movimientos) {
		this.movimientos = movimientos;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public void countListener(AjaxBehaviorEvent event) {
		try {
			CuentaModel model = new CuentaModel();
			movimientos = model.traerMov(cuenta);
    } catch (Exception e) {
    	movimientos = new ArrayList<Map<String,Object>>();
    }
	}
}
