package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.controladores.Propiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service @Transactional
public class ServicioPropiedadesDefault implements ServicioPropiedades {


    private final RepositorioPropiedades repositorioPropiedades;

    @Autowired
    public ServicioPropiedadesDefault(RepositorioPropiedades repositorioPropiedades){
        this.repositorioPropiedades = repositorioPropiedades;

    }
    @Override
    public List<Propiedad> buscarPropiedadPorUbicacion(DatosBusqueda datosBusqueda) {
        List<Propiedad> lista = new LinkedList<>();
        for(int i = 0 ; i < 3; i++){
            lista.add(new Propiedad());
        }
        return lista;
    }
}
