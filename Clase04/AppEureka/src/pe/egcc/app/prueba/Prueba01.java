package pe.egcc.app.prueba;

import pe.egcc.app.service.CuentaService;

public class Prueba01 {

  public static void main(String[] args) {
    try {
      
      CuentaService service = new CuentaService();
      service.procRetiro("00100001", 200.0, "0004", "123456");
      System.out.println("Proceso ok");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
