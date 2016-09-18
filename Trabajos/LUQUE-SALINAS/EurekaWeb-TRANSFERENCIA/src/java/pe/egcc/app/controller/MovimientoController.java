package pe.egcc.app.controller;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.egcc.app.domain.Movimiento;
import pe.egcc.app.service.MovimientoService;

@ManagedBean
@ViewScoped
public class MovimientoController {

  private Movimiento movimiento;
  private List<Movimiento> lista;
  
  @PostConstruct
  public void init(){
	  movimiento = new Movimiento();
  }
  
  public List<Movimiento> getLista() {
    return lista;
  }
  
  public Movimiento getMovimiento() {
    return movimiento;
  }
  
  public void setMovimiento(Movimiento movimiento) {
    this.movimiento = movimiento;
  }
  
  public void doConsultar(){
    
    try {
      MovimientoService service = new MovimientoService();
      lista = service.traerMovimientos(movimiento);
    } catch (Exception e) {

    }
    
  }  
}
