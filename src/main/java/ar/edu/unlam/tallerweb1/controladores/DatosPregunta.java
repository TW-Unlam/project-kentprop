package ar.edu.unlam.tallerweb1.controladores;

public class DatosPregunta {

    private Integer id;

    private String descripcion;

    private Integer id_usuario;

    public DatosPregunta(Integer id, String descripcion, Integer id_usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
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
    
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
