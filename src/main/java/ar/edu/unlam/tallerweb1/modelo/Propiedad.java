package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity @Table(name = "TABLA_PROPIEDAD")
public class Propiedad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    private Double metrosCuadrados; //Propiedad

    private Boolean cochera; //Propiedad

    private Integer cantidadAmbientes; //Propiedad
    private TipoPropiedad tipoPropiedad;

    @OneToOne @JoinColumn(name = "detalle_id")
    private Detalle detalle;

    public Propiedad(Ubicacion ubicacion, Integer id, TipoPropiedad tipoPropiedad,
                     Integer cantidadAmbientes, Detalle detalle,
                     Double metrosCuadrados, Boolean cochera) {
        this.ubicacion = ubicacion;
        this.id = id;
        this.tipoPropiedad = tipoPropiedad;
        this.metrosCuadrados=metrosCuadrados;
        this.cantidadAmbientes = cantidadAmbientes;
        this.cochera=cochera;
        this.detalle = detalle;
    }

    public Propiedad(){

    }

    public Propiedad(Integer id , Detalle detalle){
        this.id = id;
        this.detalle =detalle;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(Double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    public Integer getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(Integer cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
}
