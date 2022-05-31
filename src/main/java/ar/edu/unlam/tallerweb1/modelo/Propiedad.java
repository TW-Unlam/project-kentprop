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

    public Integer getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(Integer cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    private Accion tipoAccion; //publicidad?

    public Boolean getCochera() {
        return cochera;
    }

    public void setCochera(Boolean cochera) {
        this.cochera = cochera;
    }

    public Double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(Double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    private Estatus estatus;
    @OneToOne @JoinColumn(name = "detalle_id")
    private Detalle detalle;

    public Propiedad(Ubicacion ubicacion, Integer id, TipoPropiedad tipoPropiedad,Integer cantidadAmbientes, Accion tipoAccion, Estatus estatus, Detalle detalle,Double metrosCuadrados,Boolean cochera) {
        this.ubicacion = ubicacion;
        this.id = id;
        this.tipoPropiedad = tipoPropiedad;
        this.tipoAccion = tipoAccion;
        this.estatus = estatus;
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoPropiedad getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Accion getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(Accion tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
}
