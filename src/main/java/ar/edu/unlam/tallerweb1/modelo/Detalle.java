package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Table(name = "TABLA_DETALLE_PROPIEDAD")
public class Detalle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double metrosCuadrados; //Propiedad
    private Integer cantidadAmbientes; //Propiedad
    private Double precio; //
    private Boolean cochera; //Propiedad
    private String descripcion; //
    private LocalDate fechaPublicacion; //

    public Detalle(Integer id, Double metrosCuadrados, Integer cantidadAmbientes, Double precio, Boolean cochera, String descripcion, LocalDate fechaPublicacion) {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.cantidadAmbientes = cantidadAmbientes;
        this.precio = precio;
        this.cochera = cochera;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
