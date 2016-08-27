package pe.egcc.jsf.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Sucursal;
import pe.egcc.app.model.CuentaModel;

@ManagedBean
@SessionScoped
public class Ejemplo3Bean {

	// Variables
	private String sucursal;
	private String cuenta;
	private List<Sucursal> sucursales;
	private List<String> cuentas;
	private List<Map<String,Object>> movimientos;
	private CuentaModel cuentaModel;
	
	@PostConstruct
	public void initBean(){
		try {
			cuentaModel = new CuentaModel();
			Connection cn = AccesoDB.getConnection();
			sucursales = cuentaModel.traerSucursales(); 
    } catch (Exception e) {
    }
	}


	public String getSucursal() {
		return sucursal;
	}


	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}


	public String getCuenta() {
		return cuenta;
	}


	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}


	public List<Sucursal> getSucursales() {
		return sucursales;
	}


	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}


	public List<String> getCuentas() {
		return cuentas;
	}


	public void setCuentas(List<String> cuentas) {
		this.cuentas = cuentas;
	}


	public List<Map<String, Object>> getMovimientos() {
		return movimientos;
	}


	public void setMovimientos(List<Map<String, Object>> movimientos) {
		this.movimientos = movimientos;
	}


	public CuentaModel getCuentaModel() {
		return cuentaModel;
	}


	public void setCuentaModel(CuentaModel cuentaModel) {
		this.cuentaModel = cuentaModel;
	}
	
	public void cuentasListener(AjaxBehaviorEvent event) {
		try {
			cuentas = cuentaModel.traerCuentas(sucursal);
			movimientos.clear(); // = new ArrayList<Map<String,Object>>();
    } catch (Exception e) {
    }
	}
	
	public void movimientosListener(AjaxBehaviorEvent event) {
		try {
			movimientos = cuentaModel.traerMov(cuenta);
		} catch (Exception e) {
		}
	}
	
}
