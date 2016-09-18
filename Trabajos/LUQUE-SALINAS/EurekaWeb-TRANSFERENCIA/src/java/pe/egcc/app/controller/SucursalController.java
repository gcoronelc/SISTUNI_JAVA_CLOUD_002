package pe.egcc.app.controller;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pe.egcc.app.domain.Sucursal;
import pe.egcc.app.service.SucursalService;

@ManagedBean
@RequestScoped
public class SucursalController {

    private String codigo;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String mensaje;

    SucursalService sucursalService;
    public List<Sucursal> listaSucursales;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public SucursalService getSucursalService() {
        return sucursalService;
    }

    public void setSucursalService(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    public List<Sucursal> getListaSucursales() {
        return listaSucursales;
    }

    public void setListaSucursales(List<Sucursal> listaSucursales) {
        this.listaSucursales = listaSucursales;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @PostConstruct
    public void init() {
        
        try {
            Sucursal bean = new Sucursal();
            SucursalService service = new SucursalService();
            listaSucursales = service.traerLista(bean);
        } catch (Exception e) {
        }
    }

    public List<Sucursal> traerClientes(Sucursal bean) {
        return sucursalService.traerLista(bean);
    }
    
    public String doInsertar(){
        FacesMessage msg;
         
        try {
            Sucursal bean = new Sucursal();
            bean.setNombre(nombre);
            bean.setCiudad(ciudad);
            bean.setDireccion(direccion);
            
            SucursalService service = new SucursalService();
            if(service.validarDatos(bean)){
                service.insertar(bean);
                bean = null;
                
                this.setMensaje("Se registro la nueva sucursal " + getNombre() + ".");
                return "sucursalMsg";
            }
            else{
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Atención", "Error al registrar la sucursal");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            return null;
        }
    }
    
    public String doEditar(String codigo){
        SucursalService service = new SucursalService();
        Sucursal bean = service.traerPorCodigo(codigo);

        this.codigo = codigo;
        this.nombre = bean.getNombre();
        this.ciudad = bean.getCiudad();
        this.direccion = bean.getDireccion();
        
       return "ModificarSucursal";
    }
    
    public String doActualizar(){
        FacesMessage msg;
         
        try {
            Sucursal bean = new Sucursal();
            bean.setCodigo(this.codigo);
            bean.setNombre(this.nombre);
            bean.setCiudad(this.ciudad);
            bean.setDireccion(this.direccion);
            
            SucursalService service = new SucursalService();
            if(service.validarDatos(bean)){
                service.actualizar(bean);
                this.setMensaje("Se modifico la sucursal.");
                
                return "sucursalMsg";
            }
            else{
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error","No se modifico la sucursal");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.setMensaje(e.getMessage());
            return "sucursalMsg";
        }
    }
    
    public String doCancelar(){
        return "main";
    }
    
    public void doEliminar(String codigo){
        FacesMessage msg;
         
        try {            
            SucursalService service = new SucursalService();
            service.eliminar(codigo);
            
            Sucursal bean = new Sucursal();
            listaSucursales = service.traerLista(bean);

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Proceso terminado", "Se eliminó la sucursal.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}