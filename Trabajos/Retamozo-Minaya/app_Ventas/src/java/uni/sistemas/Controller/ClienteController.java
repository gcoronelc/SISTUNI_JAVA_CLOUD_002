package uni.sistemas.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;

import uni.sistemas.modelo.Cliente;
import uni.sistemas.service.ClienteService;

@ManagedBean
@SessionScoped
public class ClienteController {

    private List<Cliente> lista;
    private Cliente cliente;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String doListar() {
        try {
            ClienteService ser = new ClienteService();
            lista = ser.listaCliente();
        } catch (Exception e) {
        }
        return "/vista/cliente/Clientes.xhtml";
    }

    public void prepararNuevoCliente() {
        cliente = new Cliente();
    }

    public void registrarCliente() {
        try {
            ClienteService ser = new ClienteService();
            cliente.setIdCliente(ser.obtenerIdCliente());
            ser.insertarCliente(cliente);
        } catch (Exception e) {
        }
        doListar();
    }

    public void actualizar() {
        try {
            ClienteService ser=new ClienteService();
            ser.actualizarClientes(cliente);
            cliente=new Cliente();
        } catch (Exception e) {
        }
        doListar();
    }

    public void eliminarCliente() {
        try {
            ClienteService ser = new ClienteService();
            ser.eliminarCliente(cliente);
            cliente = new Cliente();
        } catch (Exception e) {
        }
        doListar();
    }
}
