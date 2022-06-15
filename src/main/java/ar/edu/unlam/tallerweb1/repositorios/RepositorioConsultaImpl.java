package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RepositorioConsultaImpl implements RepositorioConsulta{

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioConsultaImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Consulta> buscarConsultasDePublicacion(Integer idPublicacion) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Consulta.class)
                .createAlias("publicacion", "publ")
                .add(Restrictions.eq("publ.id", idPublicacion))
                .list();
    }

    @Override
    public List<Consulta> buscarConsultasDeUsuario(long idUsuario) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Consulta.class)
                .createAlias("publicacion", "publ") //Chequear
                .createAlias("usuario", "usu")
                .add(Restrictions.eq("usu.id", idUsuario))
                .list();
    }

    @Override
    public void guardarConsulta(Consulta consulta) {
        sessionFactory.getCurrentSession().save(consulta);
    }

    @Override
    public Publicacion buscarPublicacionPorId(Integer publicacionId) {
        return sessionFactory.getCurrentSession().get(Publicacion.class, publicacionId);
    }
}
