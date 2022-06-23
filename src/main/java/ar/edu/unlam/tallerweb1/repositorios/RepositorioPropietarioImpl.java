package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPropietarioImpl implements RepositorioPropietario{
    @Override
    public List<Publicacion> obtenePublicacionesDelPropietario(Integer propietarioId) {
        return null;
    }
}
