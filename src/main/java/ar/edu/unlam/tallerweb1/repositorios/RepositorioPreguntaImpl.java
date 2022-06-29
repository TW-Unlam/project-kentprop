package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RepositorioPreguntaImpl implements RepositorioPregunta {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPreguntaImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Pregunta> buscarConsultasDePublicacion(Integer idPublicacion) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Pregunta.class)
                .createAlias("publicacion", "publ")
                .createAlias("usuario", "usu")
                .add(Restrictions.eq("publ.id", idPublicacion))
                .list();
    }

    @Override
    public List<Pregunta> buscarConsultasDeUsuario(Integer idUsuario) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Pregunta.class)
                .createAlias("publicacion", "publ") //Chequear
                .createAlias("usuario", "usu")
                .add(Restrictions.eq("usu.id", idUsuario))
                .list();
    }

    @Override
    public void guardarConsulta(Pregunta pregunta) {
        sessionFactory.getCurrentSession().save(pregunta);
    }

    @Override
    public Publicacion buscarPublicacionPorId(Integer publicacionId) {
        return sessionFactory.getCurrentSession().get(Publicacion.class, publicacionId);
    }

    @Override
    public void guardarRespuesta(Pregunta respuesta) {
        sessionFactory.getCurrentSession().update( respuesta);

    }

    @Override
    public Pregunta ObtenerPregunta(Integer id_pregunta) {
        return sessionFactory.getCurrentSession().get(Pregunta.class, id_pregunta);
    }
}
