package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropietario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropietarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPropietarioDefault implements ServicioPropietario {

    private final RepositorioPropietario repositorioPropietario;

    @Autowired
    public ServicioPropietarioDefault(RepositorioPropietario repositorioPropietario){
        this.repositorioPropietario = repositorioPropietario;
    }

    @Override
    public List<Publicacion> obtenePublicacionesDelPropietario(Integer PropietarioId) {
        return repositorioPropietario.obtenePublicacionesDelPropietario(PropietarioId);
    }

}
