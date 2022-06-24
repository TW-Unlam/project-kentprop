package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropietario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioPropietarioDefault implements ServicioPropietario {

    private final RepositorioPropietario repositorioPropietario;

    @Autowired
    public ServicioPropietarioDefault(RepositorioPropietario repositorioPropietario){
        this.repositorioPropietario = repositorioPropietario;
    }

    @Override
    public List<Publicacion> obtenePublicacionesDelPropietario(Long PropietarioId) {
        return repositorioPropietario.obtenePublicacionesDelPropietario(PropietarioId);
    }

}
