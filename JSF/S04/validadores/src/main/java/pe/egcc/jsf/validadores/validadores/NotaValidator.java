package pe.egcc.jsf.validadores.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
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
  public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {

    int nota = Integer.parseInt(value.toString());
    if (nota < 0 || nota > 20) {
      throw new ConverterException(new FacesMessage("Error en la nota.", "La nota debe estar en el rango de 0 a 20."));
    }

  }
}
