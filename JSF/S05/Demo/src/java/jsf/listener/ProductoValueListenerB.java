package jsf.listener;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import jsf.bean.Demo02b;

/**
 *
 * @author Gustavo Coronel
 * @blog   http://gcoronelc.blogspot.com
 */
@ManagedBean
public class ProductoValueListenerB implements ValueChangeListener {

  @ManagedProperty(value="#{demo02b}")
	private Demo02b demo02b;

  public void setDemo02b(Demo02b demo02b) {
    this.demo02b = demo02b;
  }
  
	@Override
	public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
		// Variables
		String codigo;
		double precio;
		// Dato seleccionado
		codigo = event.getNewValue().toString();
		// Proceso
		precio = 0.0;
		if(codigo.equals("A01")){
			precio = 456;
		} else if(codigo.equals("A02")){
			precio = 768;
		} else if(codigo.equals("A03")){
			precio = 234;
		} else if(codigo.equals("A04")){
			precio = 987;
		}
		// Mostrar precio
		demo02b.setPrecio(precio);
	}
}
