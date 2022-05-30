package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPropiedadesImpl implements RepositorioPropiedades{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPropiedadesImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Propiedad> buscarPropiedad(DatosBusqueda datosBusqueda) {
        return null;
    }

    @Override
    public Propiedad buscarDetallePropiedad(Integer id) {
        return null;
    }
}
