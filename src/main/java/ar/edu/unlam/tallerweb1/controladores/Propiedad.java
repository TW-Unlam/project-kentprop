package ar.edu.unlam.tallerweb1.controladores;

public class Propiedad {

    private String ubicacion;
    private Integer id;
    private String tipoPropiedad;
    private String tipoAccion;
    private String estatus;
    private String descripcion;


    public Propiedad(String ubicacion, Integer id, String tipoPropiedad, String tipoAccion, String estatus, String descripcion) {
        this.ubicacion = ubicacion;
        this.id = id;
        this.tipoPropiedad = tipoPropiedad;
        this.tipoAccion = tipoAccion;
        this.estatus = estatus;
        this.descripcion = descripcion;
    }

    public Propiedad(){

    }

    public String getUbicacion() {

        return ubicacion;
    }
}
