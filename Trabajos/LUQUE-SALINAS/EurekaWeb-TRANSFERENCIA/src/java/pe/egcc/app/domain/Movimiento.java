package pe.egcc.app.domain;

public class Movimiento {

    private String cuencodigo;
    private String movinumero;
    private String movifecha;
    private String emplcodigo;
    private String tipocodigo;
    private String moviimporte;
    private String tipoaccion;
    private Integer cuenreferencia;

    public String getCuencodigo() {
        return cuencodigo;
    }

    public void setCuencodigo(String cuencodigo) {
        this.cuencodigo = cuencodigo;
    }

    public String getMovinumero() {
        return movinumero;
    }

    public void setMovinumero(String movinumero) {
        this.movinumero = movinumero;
    }

    public String getMovifecha() {
        return movifecha;
    }

    public void setMovifecha(String movifecha) {
        this.movifecha = movifecha;
    }

    public String getEmplcodigo() {
        return emplcodigo;
    }

    public void setEmplcodigo(String emplcodigo) {
        this.emplcodigo = emplcodigo;
    }

    public String getTipocodigo() {
        return tipocodigo;
    }

    public void setTipocodigo(String tipocodigo) {
        this.tipocodigo = tipocodigo;
    }

    public String getMoviimporte() {
        return moviimporte;
    }

    public void setMoviimporte(String moviimporte) {
        this.moviimporte = moviimporte;
    }

    public Integer getCuenreferencia() {
        return cuenreferencia;
    }

    public void setCuenreferencia(Integer cuenreferencia) {
        this.cuenreferencia = cuenreferencia;
    }

    /**
     * @return the tipoaccion
     */
    public String getTipoaccion() {
        return tipoaccion;
    }

    /**
     * @param tipoaccion the tipoaccion to set
     */
    public void setTipoaccion(String tipoaccion) {
        this.tipoaccion = tipoaccion;
    }

}
