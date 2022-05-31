package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Detalle;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.excepciones.PropiedadNoEncontrada;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class ServicioPropiedadesDefault implements ServicioPropiedades {

    private final RepositorioPropiedades repositorioPropiedades;

    @Autowired
    public ServicioPropiedadesDefault(RepositorioPropiedades repositorioPropiedades){
        this.repositorioPropiedades = repositorioPropiedades;
    }

    @Override
    public List<Propiedad> buscarPropiedadPorUbicacion(DatosBusqueda datosBusqueda) throws PropiedadNoEncontrada{
        List<Propiedad> lista = repositorioPropiedades.buscarPropiedad(datosBusqueda);
        if(lista.isEmpty()){
            throw new PropiedadNoEncontrada();
        }else{
            return lista;
        }
    }

    @Override
    public Detalle verDetallePropiedad(Integer id) {
        Detalle resultado = repositorioPropiedades.buscarDetallePropiedad(id);
        return resultado;
    }
}
