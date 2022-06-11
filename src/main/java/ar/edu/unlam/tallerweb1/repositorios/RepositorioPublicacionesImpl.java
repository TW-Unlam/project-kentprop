package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Accion;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.TipoPropiedad;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioPublicacionesImpl implements RepositorioPublicaciones {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPublicacionesImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Publicacion> buscarPublicaciones(Accion accion, TipoPropiedad tipo, String descripcion) {
              return sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .createAlias("propiedad", "prop")
                .createAlias("prop.ubicacion", "ubi")
                .add(Restrictions.or(
                        Restrictions.like("ubi.localidad", "%" + descripcion + "%"),
                        Restrictions.like("ubi.provincia", "%" + descripcion +"%" ))
                )
                .add(Restrictions.eq("prop.tipoPropiedad", tipo))
                .add(Restrictions.eq("tipoAccion", accion))
                .list();
    }

    @Override
    public Publicacion buscarDetallePublicacion(Integer id) {
        return sessionFactory.getCurrentSession().get(Publicacion.class, id);
    }

}