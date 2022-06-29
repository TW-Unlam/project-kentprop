package ar.edu.unlam.tallerweb1.controladores;

public class DatosPregunta {
    private Integer publicacionId;

    private String descripcion;

    public DatosPregunta(Integer publicacionId, String descripcion) {
        this.publicacionId = publicacionId;
        this.descripcion = descripcion;
    }

    public DatosPregunta(){

    }

    public Integer getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Integer publicacionId) {
        this.publicacionId = publicacionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
