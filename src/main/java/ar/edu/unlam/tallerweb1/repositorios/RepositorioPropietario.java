package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;

import java.util.List;


public interface RepositorioPropietario {
    List<Publicacion> obtenePublicacionesDelPropietario(Long propietarioId);
}
