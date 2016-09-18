
package uni.sistemas.modelo;

public class Cliente {
  
    private int idCliente;
    private String nombre;
    private String ruc;
    private String dni;
    private String direccion;
    private String telefono;
    private String obs;
    private String usuario;
    private String contraseña;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String ruc, String dni, String direccion, String telefono, String obs, String usuario, String contraseña) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.ruc = ruc;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.obs = obs;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
