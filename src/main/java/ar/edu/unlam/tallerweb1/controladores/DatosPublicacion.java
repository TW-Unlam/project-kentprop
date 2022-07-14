package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;

public class DatosPublicacion {
    private Publicacion publicacion;
    private Imagen imagen;

    public DatosPublicacion(Publicacion publicacion, Imagen imagen) {
        this.publicacion = publicacion;
        this.imagen = imagen;
    }

    public DatosPublicacion(){

    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
}
