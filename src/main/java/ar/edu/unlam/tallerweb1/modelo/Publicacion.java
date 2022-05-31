package ar.edu.unlam.tallerweb1.modelo;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TABLA_PUBLICIDAD")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double precio;

    private String descripcion;

    private LocalDate fechaPublicacion;

    private boolean isActivo;

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

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }
}
