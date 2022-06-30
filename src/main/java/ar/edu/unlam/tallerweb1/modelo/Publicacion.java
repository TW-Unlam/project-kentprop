package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double precio;
    private String descripcion;
    private LocalDate fechaPublicacion;
    private boolean isActivo;
    private Accion tipoAccion;
    private Estatus estatus;
    private boolean destacada;
    @OneToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    public Publicacion(Double precio, String descripcion, LocalDate fechaPublicacion, Propiedad propiedad) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.propiedad = propiedad;
    }

    public Publicacion() {

    }
    public Publicacion(Integer id) {
        this.id = id;
    }


    public Boolean getDestacada() {
        return destacada;
    }

    public void setDestacada(Boolean destacada) {
        this.destacada = destacada;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
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

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }
}
