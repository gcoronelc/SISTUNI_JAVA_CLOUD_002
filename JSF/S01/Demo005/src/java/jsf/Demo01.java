package jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class Demo01 {

	public String buttonId;

	public void printIt(ActionEvent event) {

		//Get submit button id
		buttonId = event.getComponent().getClientId();

	}

	public String outcome() {
		return "result";
	}
}
