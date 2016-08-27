package pe.egcc.jsf.validadores.excepciones;

/**
 *
 * @author Gustavo Coronel
 */
@SuppressWarnings("serial")
public class NotaException extends RuntimeException {
  
  public NotaException() {
    super("Nota fuera de rango.");
  }
  
  public NotaException(int nota) {
    super("Nota " + nota + " esta fuera de rango, debe ser entre 0 y 20.");
  }
  
  public NotaException(String mensaje) {
    super(mensaje);
  }
}
