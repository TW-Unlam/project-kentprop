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


public class RepositorioDetalleTest extends SpringTest {
   /* private final Session session= session();*/
    @Autowired
    private RepositorioPregunta repositorioDeConsultas;
    
    @Test
    @Transactional @Rollback
    public void alGuardarConsutalDelUsuarioParaUnaPublicacionExistente(){
        Pregunta pregunta = dadoQueExisteUnaPublicacionYUsuarioLogueado();

        repositorioDeConsultas.guardarConsulta(pregunta);

        entoncesSeEstableceElIDDelGuardado(pregunta);
    }

    @Test
    @Transactional @Rollback
    public void alBuscarLaConsultaDeUsuarioDevuelvelaExistente(){ //Lo mismo
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
    public void alBuscarlasConsultaDeUnaPublicacionExistenteDevuelveLaLista(){
        ///Cantida
        Publicacion publicacion = dadoQueExisteUnaPublicacionConPreguntas(3);

        List<Pregunta> pregunta1Busqueda =repositorioDeConsultas.buscarConsultasDePublicacion(publicacion.getId());
        assertThat(pregunta1Busqueda).hasSize(3);

    }

    private void entoncesSeEstableceElIDDelGuardado(Pregunta pregunta) {
        assertThat(pregunta.getId()).isNotNull();
    }

    private Pregunta dadoQueExisteUnaPublicacionYUsuarioLogueado() {
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

    private Publicacion dadoQueExisteUnaPublicacionConPreguntas(int cantidadDePreguntas ) {
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);

        for (int i=0;i<cantidadDePreguntas;i++){
            Pregunta pregunta1 =new Pregunta();
            pregunta1.setPregunta("Tiempo de Aquiler minimo?");
            Usuario usuario1=new Usuario();
            usuario1.setEmail("sullca@gmail.com");

            pregunta1.setUsuario(usuario1);
            pregunta1.setPublicacion(publicacion);
            session().save(usuario1);
            session().save(pregunta1);
        }
            session().save(publicacion);

        return publicacion;
    }
}
