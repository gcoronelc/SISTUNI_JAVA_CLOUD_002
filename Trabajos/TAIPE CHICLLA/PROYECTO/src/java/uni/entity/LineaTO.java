/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.entity;

/**
 *
 * @author Alumno
 */
public class LineaTO {
    //atributos
    private int idlinea;
    private String nombre;
    
    //constructor

    public LineaTO() {
    }

    public LineaTO(int idlinea, String nombre) {
        this.idlinea = idlinea;
        this.nombre = nombre;
    }
    
    //metodos get y set

    public int getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(int idlinea) {
        this.idlinea = idlinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
