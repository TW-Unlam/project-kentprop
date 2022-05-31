package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity @Table(name = "TABLA_UBICACION")
public class Ubicacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String provincia;
    private String localidad;


    public Ubicacion(Integer id, String provincia, String localidad) {
        this.id = id;
        this.provincia = provincia;
        this.localidad = localidad;

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

}
