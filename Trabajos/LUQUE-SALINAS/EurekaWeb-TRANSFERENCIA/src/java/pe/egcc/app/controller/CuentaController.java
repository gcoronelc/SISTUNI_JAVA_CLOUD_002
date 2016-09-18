package pe.egcc.app.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pe.egcc.app.service.CuentaService;
import pe.egcc.app.domain.Cuenta;

@ManagedBean(name = "cuentaController")
@RequestScoped

public class CuentaController {

    private Cuenta cuenta;
    private List<Cuenta> lista;

    //Cambios
    private String codigoemp;
    private String cuorigen;
    private String cudestino;
    private double monto;

    @ManagedProperty("#{logonController}")
    private LogonController logoncontroller;
    //

    @PostConstruct
    public void init() {
        cuenta = new Cuenta();
        codigoemp = logoncontroller.getEmpleado().getCodigo().toString();
    }

    public List<Cuenta> getLista() {
        return lista;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void doConsultar() {

        try {
            CuentaService service = new CuentaService();
            lista = service.traerCuenta(cuenta);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceo.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("+", msg);
        }
    }
    //Cambios añadido transferir

    public void doTransferir() {

        try {
            if (cuorigen.equals(cudestino) == true || cuorigen==cudestino) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "No se puede utilizar la misma cuenta para hacer la transferencia ", ".");
                FacesContext.getCurrentInstance().addMessage("+", msg);
            } else {
                CuentaService service = new CuentaService();
                service.transferir(codigoemp, cuorigen, cudestino, monto);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Transaccion Satisfactoria ", "ok");
                FacesContext.getCurrentInstance().addMessage("+", msg);
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso ", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("+", msg);
        }
    }
    //Cambios añadido transferir

    public String getCuorigen() {
        return cuorigen;
    }

    public void setCuorigen(String cuorigen) {
        this.cuorigen = cuorigen;
    }

    public String getCudestino() {
        return cudestino;
    }

    public void setCudestino(String cudestino) {
        this.cudestino = cudestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the codigoemp
     */
    public String getCodigoemp() {
        return codigoemp;
    }

    /**
     * @param codigoemp the codigoemp to set
     */
    public void setCodigoemp(String codigoemp) {
        this.codigoemp = codigoemp;
    }

    public String doCancelar() {
        return "main";
    }

    /**
     * @return the logoncontroller
     */
    public LogonController getLogoncontroller() {
        return logoncontroller;
    }

    /**
     * @param logoncontroller the logoncontroller to set
     */
    public void setLogoncontroller(LogonController logoncontroller) {
        this.logoncontroller = logoncontroller;
    }

}
