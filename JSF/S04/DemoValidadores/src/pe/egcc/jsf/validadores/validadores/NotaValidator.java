package pe.egcc.jsf.validadores.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Gustavo Coronel
 */
@FacesValidator(value = "notaValidator")
public class NotaValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component,
	    Object value) throws ValidatorException {
		FacesMessage msg = null;
		try {
			int nota = Integer.parseInt(value.toString());
			if (nota < 0 || nota > 20) { 
				msg = new FacesMessage("Dato no es correcto.","La nota debe estar en el rango de 0 a 20.");
			}
		} catch (Exception e) {
			msg = new FacesMessage("Dato no es correcto.", "La nota es un dato entero.");
		}
		if(msg != null){
			throw new ValidatorException(msg);
		}

	}
}
