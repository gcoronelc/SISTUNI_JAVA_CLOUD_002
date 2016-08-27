package jsf.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Gustavo Coronel
 * @blog   http://gcoronelc.blogspot.com
 */
@ManagedBean
@SessionScoped
public class Demo01 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String texto1;
	private String texto2;

	public Demo01() {
	}

	public String getTexto1() {
		return texto1;
	}

	public void setTexto1(String texto1) {
		this.texto1 = texto1;
	}

	public String getTexto2() {
		return texto2;
	}

	public void setTexto2(String texto2) {
		this.texto2 = texto2;
	}

	public void processValueChanged(ValueChangeEvent event) {
    texto2 = event.getNewValue().toString();
	}
}
