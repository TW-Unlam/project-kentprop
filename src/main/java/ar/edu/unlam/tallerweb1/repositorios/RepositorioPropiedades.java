package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;

import java.util.List;

public interface RepositorioPropiedades {

    List<Propiedad> buscarPropiedad(DatosBusqueda datos);

    Propiedad buscarDetallePropiedad(Integer id);


}
