package pe.egcc.demo001.controller;

import javax.faces.bean.ManagedBean;

import pe.egcc.demo001.service.SumaService;

@ManagedBean
public class SumaController {

  private int num1;
  private int num2;
  private int suma;
  
  public int getNum1() {
    return num1;
  }
  
  public void setNum1(int num1) {
    this.num1 = num1;
  }
  
  public int getNum2() {
    return num2;
  }
  
  public void setNum2(int num2) {
    this.num2 = num2;
  }
  
  public int getSuma() {
    return suma;
  }
  
  public void setSuma(int suma) {
    this.suma = suma;
  }
  
  public void doProcesar(){
    SumaService service = new SumaService();
    suma = service.sumar(num1, num2);
  }
  
}
