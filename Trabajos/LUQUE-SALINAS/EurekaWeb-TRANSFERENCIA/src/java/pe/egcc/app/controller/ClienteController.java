package pe.egcc.app.controller;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pe.egcc.app.domain.Cliente;
import pe.egcc.app.service.ClienteService;

@ManagedBean
@RequestScoped
public class ClienteController {
    
    private Cliente cliente;
    ClienteService clienteService;
    public List<Cliente> listaClientes;
    private String codigo;
    private String paterno;
    private String materno;
    private String nombre;
    private String dni;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String mensaje;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
      @PostConstruct
    public void init(){
    cliente = new Cliente();
    
    try {
            Cliente iniciovacio = new Cliente();
            ClienteService service = new ClienteService();
            listaClientes = service.traerLista(iniciovacio);
        }
         catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error en el proceso." + e.getMessage(), e.getMessage());
                FacesContext.getCurrentInstance().addMessage(e.getMessage(), msg);
        } 
    }

    public List<Cliente> traerClientes(Cliente bean) {
        return clienteService.traerLista(bean);
    }
    
    public List<Cliente> consultarNomCliente(String nombre) {
        return clienteService.consultarNomCliente(nombre);
    }
    
    public void doConsulta(){
        try {
            ClienteService service = new ClienteService();
            listaClientes = service.traerLista(cliente);
        }
         catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error en el proceso." + e.getMessage(), e.getMessage());
                FacesContext.getCurrentInstance().addMessage(e.getMessage(), msg);
        } 
    }
    
    public String doInsertar(){
        FacesMessage msg;
         
        try {
            Cliente bean = new Cliente();
            bean.setPaterno(paterno);
            bean.setMaterno(materno);
            bean.setNombre(nombre);
            bean.setDni(dni);
            bean.setCiudad(ciudad);
            bean.setDireccion(direccion);
            bean.setTelefono(telefono);
            bean.setEmail(email);
            
            ClienteService service = new ClienteService();
            if(service.validarDatos(bean)){
                service.insertar(bean);
                bean = null;
                
                this.setMensaje("Se ha registrado el nuevo cliente " + getNombre() + " " + getPaterno() + " " + getMaterno()
                    + ".");
                return "clienteMsg";
            }
            else{
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error al registrar al cliente", getNombre() + " " + getPaterno() + " " + getMaterno()
                    + ".");
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
        ClienteService service = new ClienteService();
        Cliente bean = service.traerPorCodigo(codigo);
        //Enviar datos
        this.codigo = codigo;
        this.paterno = bean.getPaterno();
        this.materno = bean.getMaterno();
        this.nombre = bean.getNombre();
        this.dni = bean.getDni();
        this.ciudad = bean.getCiudad();
        this.direccion = bean.getDireccion();
        this.telefono = bean.getTelefono();
        this.email = bean.getEmail();
        
       return "ModificarCliente";
    }
    
    public String doActualizar(){
        FacesMessage msg;
         
        try {
            Cliente bean = new Cliente();
            bean.setCodigo(this.codigo);
            bean.setPaterno(this.paterno);
            bean.setMaterno(this.materno);
            bean.setNombre(this.nombre);
            bean.setDni(this.dni);
            bean.setCiudad(this.ciudad);
            bean.setDireccion(this.direccion);
            bean.setTelefono(this.telefono);
            bean.setEmail(this.email);
            
            ClienteService service = new ClienteService();
            if(service.validarDatos(bean)){
                service.actualizar(bean);
           
                this.setMensaje("Los datos del cliente "
                    + "se modificaron satisfactorialmente.");
                return "clienteMsg";
            }
            else{
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error al actualizar al cliente ", getNombre() + " " + getPaterno() + " " + getMaterno()
                    + ".");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.setMensaje(e.getMessage());
            return "clienteMsg";
        }
    }
    
    public String doCancelar(){
        return "main";
    }
    
    public void doEliminar(String codigo){
        FacesMessage msg;
         
        try {            
            ClienteService service = new ClienteService();
            service.eliminar(codigo);
            
            Cliente bean = new Cliente();
            listaClientes = service.traerLista(bean);

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Proceso terminado.", "Se eliminó el cliente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}