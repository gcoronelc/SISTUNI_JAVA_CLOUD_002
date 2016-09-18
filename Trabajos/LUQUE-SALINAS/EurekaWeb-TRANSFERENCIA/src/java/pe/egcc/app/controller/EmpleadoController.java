package pe.egcc.app.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pe.egcc.app.domain.Empleado;
import pe.egcc.app.service.EmpleadoService;

@ManagedBean
@RequestScoped
public class EmpleadoController {

    EmpleadoService empleadoService;
    public List<Empleado> listaEmpleados;
    public List<Empleado> lista;

    private String codigo;
    private String paterno;
    private String materno;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String usuario;
    private String mensaje;
    private Empleado empleado;

    public List<Empleado> getLista() {
        return lista;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public EmpleadoService getClienteService() {
        return empleadoService;
    }

    public void setClienteService(EmpleadoService clienteService) {
        this.empleadoService = clienteService;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @PostConstruct
    public void initBean() {
        empleado = new Empleado();
        EmpleadoService service = new EmpleadoService();
        lista = service.traerLista(empleado);
    }

    public void doConsultar() {

        try {
            EmpleadoService service = new EmpleadoService();
            lista = service.traerEmpleados(empleado);
        } catch (Exception e) {

        }

    }

    public List<Empleado> traerEmpleados(Empleado bean) {
        return empleadoService.traerLista(bean);
    }

    public String doInsertar() {
        FacesMessage msg;

        try {
            Empleado bean = new Empleado();
            bean.setPaterno(paterno);
            bean.setMaterno(materno);
            bean.setNombre(nombre);
            bean.setCiudad(ciudad);
            bean.setDireccion(direccion);
            bean.setUsuario(usuario);

            EmpleadoService service = new EmpleadoService();
            if (service.validarDatos(bean)) {
                service.insertar(bean);
                bean = null;

                this.setMensaje("Se ha registrado el nuevo empleado " + getNombre() + " " + getPaterno() + " " + getMaterno()
                        + ".");
                return "empleadoMsg";
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error al registrar el empleado ", getNombre() + " " + getPaterno() + " " + getMaterno()
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

    public String doEditar(String codigo) {
        EmpleadoService service = new EmpleadoService();
        Empleado bean = service.traerPorCodigo(codigo);

        this.codigo = codigo;
        this.paterno = bean.getPaterno();
        this.materno = bean.getMaterno();
        this.nombre = bean.getNombre();
        this.ciudad = bean.getCiudad();
        this.direccion = bean.getDireccion();
        this.usuario = bean.getUsuario();

        return "ModificarEmpleado";
    }

    public String doActualizar() {
        FacesMessage msg;

        try {
            Empleado bean = new Empleado();
            bean.setCodigo(this.codigo);
            bean.setPaterno(this.paterno);
            bean.setMaterno(this.materno);
            bean.setNombre(this.nombre);
            bean.setCiudad(this.ciudad);
            bean.setDireccion(this.direccion);
            bean.setUsuario(this.usuario);

            EmpleadoService service = new EmpleadoService();
            if (service.validarDatos(bean)) {
                service.actualizar(bean);

                this.setMensaje("Los datos del empleado se modificaron satisfactoriamente");
                return "empleadoMsg";
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error al actualizar al empleado ", getNombre() + " " + getPaterno() + " " + getMaterno()
                        + ".");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.setMensaje(e.getMessage());
            return "empleadoMsg";
        }
    }

    public String doCancelar() {
        return "main";
    }

    public void doEliminar(String codigo) {
        FacesMessage msg;

        try {
            EmpleadoService service = new EmpleadoService();
            service.eliminar(codigo);

            Empleado bean = new Empleado();
            listaEmpleados = service.traerLista(bean);

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Proceso terminado", "Se eliminó el empleado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el proceso.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
