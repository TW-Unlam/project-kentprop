package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "TABLA_DETALLE_PROPIEDAD")
public class Detalle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double metrosCuadrados; //Propiedad
    private Integer cantidadAmbientes; //Propiedad
    private Boolean cochera; //Propiedad



    public Detalle(Integer id, Double metrosCuadrados, Integer cantidadAmbientes, Boolean cochera) {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.cantidadAmbientes = cantidadAmbientes;
        this.cochera = cochera;

    }

    public Detalle() {

    }


    public Detalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(Double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public Integer getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(Integer cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

}
