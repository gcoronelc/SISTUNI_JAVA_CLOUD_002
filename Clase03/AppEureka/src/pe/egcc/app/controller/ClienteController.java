package pe.egcc.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.egcc.app.model.Cliente;
import pe.egcc.app.service.ClienteService;

@ManagedBean
@ViewScoped
public class ClienteController {

  private Cliente cliente;
  private List<Cliente> lista;
  
  @PostConstruct
  public void init(){
    cliente = new Cliente();
  }
  
  public List<Cliente> getLista() {
    return lista;
  }
  
  public Cliente getCliente() {
    return cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public void doConsultar(){
    
    try {
      ClienteService service = new ClienteService();
      lista = service.traerClientes(cliente);
    } catch (Exception e) {

    }
    
  }
  
  
}
