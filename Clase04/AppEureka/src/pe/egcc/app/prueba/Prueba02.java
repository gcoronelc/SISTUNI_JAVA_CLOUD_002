package pe.egcc.app.prueba;

import pe.egcc.app.service.CuentaService;

public class Prueba02 {

  public static void main(String[] args) {
    try {
      CuentaService service = new CuentaService();
      double saldo = service.conSaldoCuenta("00100001");
      System.out.println("Saldo: " + saldo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
