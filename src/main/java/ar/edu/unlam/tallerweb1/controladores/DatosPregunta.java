package ar.edu.unlam.tallerweb1.controladores;

public class DatosPregunta {

    private Integer id;

    private String descripcion;

    public DatosPregunta(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public DatosPregunta(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
