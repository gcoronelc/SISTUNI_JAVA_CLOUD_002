package pe.egcc.app.domain;

public class Sucursal {

	private String codigo;
	private String nombre;
	private String ciudad;
	private String direccion;
	private Integer contador;
	
	public Sucursal() {
  }

	public Sucursal(String codigo, String nombre, String ciudad,
      String direccion, Integer contador) {
	  super();
	  this.codigo = codigo;
	  this.nombre = nombre;
	  this.ciudad = ciudad;
	  this.direccion = direccion;
	  this.contador = contador;
  }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	
}
