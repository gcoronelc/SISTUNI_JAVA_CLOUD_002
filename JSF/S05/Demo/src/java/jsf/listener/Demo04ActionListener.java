package jsf.listener;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import jsf.bean.Demo04;

/**
 *
 * @author Gustavo Coronel
 * @blog http://gcoronelc.blogspot.com
 */
@ManagedBean
public class Demo04ActionListener implements ActionListener {

  @ManagedProperty(value = "#{demo04}")
  private Demo04 demo04;

  public void setDemo04(Demo04 demo04) {
    this.demo04 = demo04;
  }

  @Override
  public void processAction(ActionEvent event) throws AbortProcessingException {
    String destino = "demo04_resultado";
    if (demo04.getPrecio() <= 0 || demo04.getCant() <= 0) {
      FacesMessage msg = new FacesMessage("Los valores no pueden ser negativos.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
      destino = null;
    }
    demo04.setDestino(destino);
  }

}
