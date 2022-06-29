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
    @Autowired
    private RepositorioPregunta repositorioPregunta;

    // buscarConsultasDePublicacion
    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDePublicacionExistenteMeDevuelveSusPreguntas(){
        Integer idPublicacion = 10;
        Integer idUsuario = 2;

        dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId(idPublicacion, idUsuario);

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(idPublicacion);

        entoncesMeDevuelveUnaListaDePreguntas(preguntas);
    }

    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDePublicacionInexistenteNoMeDevuelvePreguntas(){
        Integer idPublicacionExistente = 10;
        Integer idPublicacionInexistente = 2;
        Integer idUsuario = 2;

        dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId(idPublicacionExistente, idUsuario);

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(idPublicacionInexistente);

        entoncesMeDevuelveUnaListaDePreguntasVacia(preguntas);
    }

    // buscarConsultasDeUsuario
    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDeUsuarioExistenteMeDevuelveSusPreguntas(){
        Integer idPublicacion = 10;
        Integer idUsuario = 10;

        dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId(idPublicacion,idUsuario);

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDeUsuario(idUsuario);

        entoncesMeDevuelveUnaListaDePreguntas(preguntas);
    }

    @Test
    @Transactional @Rollback
    public void alBuscarPorIdDeUsuarioInexistenteNoMeDevuelvePreguntas(){
        Integer idPublicacion = 10;
        Integer idUsuarioExistente = 2;
        Integer idUsuarioInexistente = 3;

        dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId(idPublicacion, idUsuarioExistente);

        List<Pregunta> preguntas = repositorioPregunta.buscarConsultasDePublicacion(idUsuarioInexistente);

        entoncesMeDevuelveUnaListaDePreguntasVacia(preguntas);
    }

    private void dadoQueExistenPreguntasParaLaPublicacionDeIdyUsuarioDeId(Integer idPublicacion, Integer idUsuario) {
        Pregunta pregunta1 = new Pregunta();
        pregunta1.setPregunta("¿Cuantos años de antiguedad tiene?");

        Pregunta pregunta2 = new Pregunta();
        pregunta2.setPregunta("¿Se aceptan mascotas?");

        Usuario usuario = new Usuario();
        usuario.setEmail("valPardo@mail");
        usuario.setPassword("1234");
        usuario.setRol("PROPIETARIO");
        usuario.setId(idUsuario);

        Publicacion publicacion = new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);
        publicacion.setId(idPublicacion);

        pregunta1.setPublicacion(publicacion);
        pregunta1.setUsuario(usuario);
        pregunta2.setPublicacion(publicacion);
        pregunta2.setUsuario(usuario);

        session().save(usuario);
        session().save(publicacion);
        session().save(pregunta1);
        session().save(pregunta2);
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
