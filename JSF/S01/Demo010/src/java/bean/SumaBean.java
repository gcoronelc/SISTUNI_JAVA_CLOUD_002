package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "panchito")
@RequestScoped
public class SumaBean {

	private Integer num1;
	private Integer num2;
	private Integer suma;

	public SumaBean() {
		num1 = 0;
		num2 = 0;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public Integer getSuma() {
		return this.suma;
	}

	public String doProcesar(){
		this.suma = this.getNum1() + this.getNum2();
		return "resultado";
	}

	public String doNuevo(){
		this.num1 = 0;
		this.num2 = 0;
		this.suma = 0;
		return "index";
	}
}
