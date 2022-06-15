package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface RepositorioPublicaciones {

    List<Publicacion> buscarPublicaciones(Accion accion, TipoPropiedad tipo, String descripcion);

    List<Publicacion> buscarImagensPublicaciones(Accion accion, TipoPropiedad tipo, String descripcion);

    Publicacion buscarDetallePublicacion(Integer id);

    Propiedad buscarPropiedad(Integer id_propiedad);
    Usuario buscarPropietarioDeLaPropiedad(Integer id_propiedad);

}
