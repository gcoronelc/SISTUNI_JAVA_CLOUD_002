package pe.egcc.jsf.validadores.convertidores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Gustavo Coronel
 */
@FacesConverter(value = "toInteger")
public class ToInteger implements Converter{

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
    Integer nota = null;
    if (value != null && value.length() > 0) {
      try {
        nota = Integer.parseInt(value);
      } catch (Exception e) {
        throw new ConverterException(new FacesMessage("Dato incorrecto.", "No es un valor entero."));
      }
    } else {
      throw new ConverterException(new FacesMessage("No existe dato.", "Es necesario que ingreso un dato."));
    }
    return nota;
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
   return value.toString();
  }

}
