package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Ubicacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String provincia;
    private String localidad;
    private Double latitud;
    private Double longitud;


    public Ubicacion(Integer id, String provincia, String localidad) {
        this.id = id;
        this.provincia = provincia;
        this.localidad = localidad;
        this.latitud = 0.0;
        this.longitud = 0.0;
    }

    public Ubicacion() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

}
