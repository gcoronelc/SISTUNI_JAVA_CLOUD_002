package jsf.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import jsf.bean.Demo02a;

/**
 *
 * @author Gustavo Coronel
 * @blog   http://gcoronelc.blogspot.com
 */
public class ProductoValueListenerA implements ValueChangeListener {

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
		Demo02a demo02 = (Demo02a) FacesContext.getCurrentInstance().
						getExternalContext().getSessionMap().get("demo02a");
		demo02.setPrecio(precio);
	}
}
