package uni.sistemas.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;
import uni.sistemas.modelo.Empleado;
import uni.sistemas.modelo.Usuario;
import uni.sistemas.service.EmpleadoService;

@ManagedBean
@SessionScoped
public class EmpleadoController {

    private String usuario;
    private String clave;
    private Empleado empleado;
    private List<Empleado> lista;
    private List<SelectItem> listaUsuario;
    private List<Empleado> listaEliminados=new ArrayList<>();
    private String contraseña2;

    @PostConstruct
    public void init() {
        empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Empleado> getLista() {
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }

    public List<SelectItem> getListaUsuario() {
        this.listaUsuario = new ArrayList<SelectItem>();
        EmpleadoService ser = new EmpleadoService();
        List<Usuario> TipU = ser.listaTipoUsuarios();
        listaUsuario.clear();

        for (Usuario u : TipU) {
            SelectItem TipoUrem = new SelectItem(u.getId(), u.getDescripcion());
            this.listaUsuario.add(TipoUrem);
        }
        return listaUsuario;
    }

    public void prepararNuevoEmpleado() {
        empleado = new Empleado();
    }

    public String doLogonProcesar() {
        String destino = "Menu";
        try {
            EmpleadoService service = new EmpleadoService();
            empleado = service.validar(usuario, clave);
        } catch (Exception e) {
            destino = "index";
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return destino;
    }

    public String doListarEmpleados() {
        try {
            EmpleadoService ser = new EmpleadoService();
            lista = ser.listaEmpleado();
        } catch (Exception e) {
        }
        return "/vista/empleado/Empleado.xhtml";
    }

    public void insertarEmpleado() {
        try {
            EmpleadoService ser = new EmpleadoService();
            empleado.setIdEmpleado(ser.obtenerId());
            ser.actualizarEmpleado(empleado);
        } catch (Exception e) {
        }
    }

    public void actualizarEmpleado(RowEditEvent event) {
        try {
            EmpleadoService ser = new EmpleadoService();
            empleado = (Empleado) event.getObject();
            ser.actualizarEmpleado(empleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizacion del Empleado " + empleado.getIdEmpleado()));
        } catch (Exception e) {
        }
    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizacion Cancelada"));
    }
    
    public void eliminarEmpleado(){
        EmpleadoService ser=new EmpleadoService();
        try {
            //el objeto c de la clase Categoria va a tomar todos los datos de la lista
            for(Empleado e : lista){
                //pasar todos los datos seleccionados a la lista de eliminados
                if(e.isSeleccionar()){
                    listaEliminados.add(e);
                }
            }
            //Si la lista eliminados no es vacia
            if(!listaEliminados.isEmpty()){
                    empleado.setIdEmpleado(listaEliminados.get(0).getIdEmpleado());
                    ser.eliminarEmpleado(empleado);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Eliminados"));
                }
            } catch (Exception e) {
        }
        doListarEmpleados();
    }
}
