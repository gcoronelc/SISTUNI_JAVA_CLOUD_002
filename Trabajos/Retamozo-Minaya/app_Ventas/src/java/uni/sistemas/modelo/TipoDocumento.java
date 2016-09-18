
package uni.sistemas.modelo;


public class TipoDocumento {
    
    private int idTipoDocu;
    private String descripcion;
    private boolean selec;
    
    public TipoDocumento() {
    }

    public int getIdTipoDocu() {
        return idTipoDocu;
    }

    public TipoDocumento(int idTipoDocu, String descripcion) {
        this.idTipoDocu = idTipoDocu;
        this.descripcion = descripcion;
    }

    public void setIdTipoDocu(int idTipoDocu) {
        this.idTipoDocu = idTipoDocu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSelec() {
        return selec;
    }

    public void setSelec(boolean selec) {
        this.selec = selec;
    }
    
    
}
