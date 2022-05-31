package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Detalle;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        return sessionFactory.getCurrentSession()
                .createCriteria(Propiedad.class)
                .createAlias("ubicacion", "ubi")
                .add(Restrictions.or(
                        Restrictions.like("ubi.localidad",datosBusqueda.getUbicacion()),
                        Restrictions.like("ubi.provincia",datosBusqueda.getUbicacion()))
                )
                .add(Restrictions.eq("tipoPropiedad",datosBusqueda.getTipoPropiedad()))
                .add(Restrictions.eq("tipoAccion", datosBusqueda.getTipoAccion()))
                .list();


    }
    @Override
    public Detalle buscarDetallePropiedad(Integer id) {
        return sessionFactory.getCurrentSession().get(Detalle.class, id);
    }
}
