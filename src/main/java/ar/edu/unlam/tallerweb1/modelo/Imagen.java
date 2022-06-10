package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
@Entity
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String urlImagen;
    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

    public Imagen(Integer id, String urlImagen, Publicacion publicacion) {
        this.id = id;
        this.urlImagen = urlImagen;
        this.publicacion = publicacion;
    }

    public Imagen() {

    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
