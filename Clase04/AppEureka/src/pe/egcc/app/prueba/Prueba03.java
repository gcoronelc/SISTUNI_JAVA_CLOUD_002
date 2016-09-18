package pe.egcc.app.prueba;

import java.util.List;
import java.util.Map;

import pe.egcc.app.service.CuentaService;

public class Prueba03 {

  public static void main(String[] args) {
    try {
      CuentaService service = new CuentaService();
      List<Map<String, ?>> lista;
      lista = service.conMovimientos("00100001");
      for (Map<String, ?> r : lista) {
        String texto = r.get("MOVINUMERO").toString() +
            " | " + r.get("MOVIFECHA").toString() + 
            " | " + r.get("TIPONOMBRE").toString() + 
            " | " + r.get("TIPOACCION").toString() +
            " | " + r.get("MOVIIMPORTE").toString();
        System.out.println(texto);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
