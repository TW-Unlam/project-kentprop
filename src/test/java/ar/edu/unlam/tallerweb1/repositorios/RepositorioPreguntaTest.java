package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Accion;
import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class RepositorioPreguntaTest extends SpringTest {
    private final Integer ID_PUBLICACION_INEXISTENTE = -1;
    private final Integer ID_USUARIO_INEXISTENTE = -1;
    @Autowired
    private RepositorioPregunta repositorioPregunta;

    // buscarConsultasDePublicacion

    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDePublicacionExistenteMeDevuelveSusPreguntas(){
        Publicacion publicacionBusq= dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId();

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(publicacionBusq.getId());

        entoncesMeDevuelveUnaListaDePreguntas(preguntas);
    }

    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDePublicacionInexistenteNoMeDevuelvePreguntas(){

        dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId();

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(ID_PUBLICACION_INEXISTENTE);

        entoncesMeDevuelveUnaListaDePreguntasVacia(preguntas);
    }

    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDeUsuarioExistenteMeDevuelveSusPreguntas(){

        Usuario usuarioBusq = dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeIdUsuario();

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDeUsuario(usuarioBusq.getId());

        entoncesMeDevuelveUnaListaDePreguntas(preguntas);
    }

    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDeUsuarioInexistenteNoMeDevuelvePreguntas(){

        dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId();

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDeUsuario(ID_USUARIO_INEXISTENTE);

        entoncesMeDevuelveUnaListaDePreguntasVacia(preguntas);
    }

    private Publicacion dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId() {
        Pregunta pregunta1 = new Pregunta();
        Pregunta pregunta2 = new Pregunta();

        Usuario usuario = new Usuario();

        Publicacion publicacion = new Publicacion();

        pregunta1.setPublicacion(publicacion);
        pregunta1.setUsuario(usuario);
        pregunta2.setPublicacion(publicacion);
        pregunta2.setUsuario(usuario);

        session().save(usuario);
        session().save(publicacion);
        session().save(pregunta1);
        session().save(pregunta2);

        return publicacion;
    }

    private Usuario dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeIdUsuario() {
        Pregunta pregunta1 = new Pregunta();
        Pregunta pregunta2 = new Pregunta();

        Usuario usuario = new Usuario();

        Publicacion publicacion = new Publicacion();

        pregunta1.setPublicacion(publicacion);
        pregunta1.setUsuario(usuario);
        pregunta2.setPublicacion(publicacion);
        pregunta2.setUsuario(usuario);

        session().save(usuario);
        session().save(publicacion);
        session().save(pregunta1);
        session().save(pregunta2);

        return usuario;
    }

    private void entoncesMeDevuelveUnaListaDePreguntasVacia(List<Pregunta> preguntas) {
        assertThat(preguntas).hasSize(0);
    }

    private void entoncesMeDevuelveUnaListaDePreguntas(List<Pregunta> preguntas) {
        assertThat(preguntas).hasSize(2);
    }

    // guardarConsulta


    //buscarPublicacionPorId
}
