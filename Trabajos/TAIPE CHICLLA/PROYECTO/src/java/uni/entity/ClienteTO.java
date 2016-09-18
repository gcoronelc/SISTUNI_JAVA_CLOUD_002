
package uni.entity;

public class ClienteTO {
   //atributo
    private String idcliente;
    private String nombre;
    private String direccion;
    private String rucdni;
    private String telefono;
    //constructor

    public ClienteTO() {
    }

    public ClienteTO(String idcliente, String nombre, String direccion, String rucdni, String telefono) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.rucdni = rucdni;
        this.telefono = telefono;
    }
    // metodos get y set

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRucdni() {
        return rucdni;
    }

    public void setRucdni(String rucdni) {
        this.rucdni = rucdni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    // metodo adicional

    @Override
    public String toString() {
        return nombre;
    }
    
}
