package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;

import java.util.List;

public interface RepositorioPublicaciones {

    List<Publicacion> buscarPublicaciones(Accion accion, TipoPropiedad tipo, String descripcion);

    Publicacion buscarDetallePublicacion(Integer id);

    Propiedad buscarPrpiedadConPropietario(Integer id_propietario);

}
