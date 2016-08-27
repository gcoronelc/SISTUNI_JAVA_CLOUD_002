package pe.egcc.jsf.validadores.convertidores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import pe.egcc.jsf.validadores.excepciones.NotaException;

/**
 *
 * @author Gustavo Coronel
 */
@FacesConverter(value = "notaConverter")
public class NotaConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    Integer nota = null;
    if (value != null && value.length() > 0) {
      try {
        nota = Integer.parseInt(value);
        if (nota < 0 || nota > 20) {
          throw new NotaException();
        }
      } catch (NotaException e) {
        throw new ConverterException(new FacesMessage("Error en la nota.", e.getMessage()));
      } catch (Exception e) {
        throw new ConverterException(new FacesMessage("Error en la nota.", "Es un valor entero."));
      }
    } else {
      throw new ConverterException(new FacesMessage("Falta dato.", "Es necesario que ingreso un dato."));
    }
    return nota;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    String retorno = "";
    if(value != null){
      retorno = value.toString();
    }
    return retorno;
  }
}
