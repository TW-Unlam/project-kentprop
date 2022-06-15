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

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class RepositorioConsultasTest extends SpringTest {
   /* private final Session session= session();*/
    @Autowired
    private RepositorioPregunta repositorioDeConsultas;
    
    @Test
    @Transactional @Rollback
    public void alGuardarConsutaldelUsuarioParaUnaPublicacionExistente(){
        Pregunta pregunta = dadoQueExisteUnaPublicacionPropiedadYUsuarioLogueado();

        repositorioDeConsultas.guardarConsulta(pregunta);

        entoncesSeEstableceElIDDelGuardado(pregunta);
    }


    @Test
    @Transactional @Rollback
    public void alBuscarlaConsultaDeUsuarioDEvuelvelaExistente(){
        Usuario usuario=dadoQueExisteUnaConsultaDeUnUsuario();

        List<Pregunta> pregunta1Busqueda =repositorioDeConsultas.buscarConsultasDeUsuario(usuario.getId());
        assertThat(pregunta1Busqueda).hasSize(1);

    }
    @Test
    @Transactional @Rollback
    public void alBuscarConsultaDeUsuarioDarQueNoexisten(){
        Usuario usuario=dadoQueExisteUnaConsultaDeUnUsuario();

        List<Pregunta> pregunta1Busqueda =repositorioDeConsultas.buscarConsultasDeUsuario(usuario.getId()+1);
        assertThat(pregunta1Busqueda).hasSize(0);

    }

    @Test
    @Transactional @Rollback
    public void alBuscarlasConsultaDeUnaPublicacionExistenteDevuelveLALista(){
        Publicacion publicacion = dadoQueExistelistaConsultaDeUnaPublicacion();

        List<Pregunta> pregunta1Busqueda =repositorioDeConsultas.buscarConsultasDePublicacion(publicacion.getId());
        assertThat(pregunta1Busqueda).hasSize(3);

    }


    private void entoncesSeEstableceElIDDelGuardado(Pregunta pregunta) {
        assertThat(pregunta.getId()).isNotNull();
    }

    private Pregunta dadoQueExisteUnaPublicacionPropiedadYUsuarioLogueado() {
        Pregunta pregunta =new Pregunta();
        pregunta.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario=new Usuario();
        usuario.setEmail("sullca@gmail.com");
        usuario.setId(1L);
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);
        pregunta.setUsuario(usuario);
        pregunta.setPublicacion(publicacion);
        session().save(usuario);
        session().save(publicacion);
        return pregunta;
    }

    private Usuario dadoQueExisteUnaConsultaDeUnUsuario() {
        Pregunta pregunta =new Pregunta();
        pregunta.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario=new Usuario();
        usuario.setEmail("sullca@gmail.com");
        usuario.setId(1L);
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);
        pregunta.setUsuario(usuario);
        pregunta.setPublicacion(publicacion);
        session().save(usuario);
        session().save(publicacion);
        session().save(pregunta);
        return usuario;
    }

    private Publicacion dadoQueExistelistaConsultaDeUnaPublicacion() {
        Pregunta pregunta1 =new Pregunta();
        pregunta1.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario1=new Usuario();
        usuario1.setEmail("sullca@gmail.com");
        usuario1.setId(1L);
        Pregunta pregunta2 =new Pregunta();
        pregunta2.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario2=new Usuario();
        usuario2.setEmail("sullca@gmail.com");
        usuario2.setId(2L);
        Pregunta pregunta3 =new Pregunta();
        pregunta3.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario3=new Usuario();
        usuario3.setEmail("sullca@gmail.com");
        usuario3.setId(3L);
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);

        pregunta1.setUsuario(usuario1);
        pregunta2.setUsuario(usuario1);
        pregunta3.setUsuario(usuario1);
        pregunta1.setPublicacion(publicacion);
        pregunta2.setPublicacion(publicacion);
        pregunta3.setPublicacion(publicacion);

        session().save(usuario1);
        session().save(usuario2);
        session().save(usuario3);
        session().save(publicacion);
        session().save(pregunta1);
        session().save(pregunta2);
        session().save(pregunta3);
        return publicacion;
    }
}
