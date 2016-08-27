package pe.egcc.jsf.validadores.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Gustavo Coronel
 */
@ManagedBean
@RequestScoped
public class Demo01Controller {

  private int num1;
  private double num2;
  private String cadena;

  public int getNum1() {
    return num1;
  }

  public void setNum1(int num1) {
    this.num1 = num1;
  }

  public double getNum2() {
    return num2;
  }

  public void setNum2(double num2) {
    this.num2 = num2;
  }

  public String getCadena() {
    return cadena;
  }

  public void setCadena(String cadena) {
    this.cadena = cadena;
  }
  
}
