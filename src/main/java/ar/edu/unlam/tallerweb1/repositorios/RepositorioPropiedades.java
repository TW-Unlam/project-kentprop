package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Detalle;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;

import java.util.List;

public interface RepositorioPropiedades {

    List<Publicacion> buscarPropiedad(DatosBusqueda datos);

    Publicacion buscarDetallePropiedad(Integer id);

}
