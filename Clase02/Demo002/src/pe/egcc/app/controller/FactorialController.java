package pe.egcc.app.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FactorialController {

  private int num;
  private long fact;
  
  public int getNum() {
    return num;
  }
  
  public void setNum(int num) {
    this.num = num;
  }
  
  public long getFact() {
    return fact;
  }
  
  public void setFact(long fact) {
    this.fact = fact;
  }
  
  public String doCalcular(int n){
    num = n;
    fact = 1;
    for(int i = 2; i <= n; fact*=i, i++);
    return "pagina3";
  }
  
}
