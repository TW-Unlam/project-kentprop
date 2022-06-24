package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPropietarioImpl implements RepositorioPropietario{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPropietarioImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Publicacion> obtenePublicacionesDelPropietario(Long propietarioId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .createAlias("propiedad", "prop")
                .createAlias("prop.propietario", "userPropietario")
                .add(Restrictions.eq("userPropietario.id", propietarioId))
                .list();
    }
}
