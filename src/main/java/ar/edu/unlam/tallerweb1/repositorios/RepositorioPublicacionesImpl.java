package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.*;
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
    public List<Imagen> buscarImagenesDeLaPublicacion(Integer publicacion_id) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Imagen.class)
                .createAlias("publicacion", "publ")
                .add(Restrictions.eq("publ.id",  publicacion_id))
                .list();
    }

    @Override
    public Publicacion buscarDetallePublicacion(Integer id) {
        return sessionFactory.getCurrentSession().get(Publicacion.class, id);
    }

    @Override
    public Propiedad buscarPropiedad(Integer id_propiedad) {
        return sessionFactory.getCurrentSession().get(Propiedad.class, id_propiedad);
    }

/** No se logro mandar/select una parte del join para que devuelva el usuario
 * por lo tanto se utiliza la funcion anterior
 * **/
    @Override
    public Usuario buscarPropietarioDeLaPropiedad(Integer id_propiedad) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Propiedad.class)
                .createAlias("propietario", "usuario")
                .add(Restrictions.eq("id", id_propiedad))
                .uniqueResult();
    }

    @Override
    public List<Publicacion> buscarPublicacionesDestacadas() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .add(Restrictions.eq("destacada", true))
                .list();
    }

}
