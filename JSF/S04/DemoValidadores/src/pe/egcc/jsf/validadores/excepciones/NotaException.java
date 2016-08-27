package pe.egcc.jsf.validadores.excepciones;

/**
 *
 * @author Gustavo Coronel
 */
public class NotaException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

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
