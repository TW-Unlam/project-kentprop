package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Accion;
import ar.edu.unlam.tallerweb1.modelo.Consulta;
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
    private RepositorioConsulta repositorioDeConsultas;
    
    @Test
    @Transactional @Rollback
    public void alGuardarConsutaldelUsuarioParaUnaPublicacionExistente(){
        Consulta consulta = dadoQueExisteUnaPublicacionPropiedadYUsuarioLogueado();

        repositorioDeConsultas.GuardarConsulta(consulta);

        entoncesSeEstableceElIDDelGuardado(consulta);
    }


    @Test
    @Transactional @Rollback
    public void alBuscarlaConsultaDeUsuarioDEvuelvelaExistente(){
        Usuario usuario=dadoQueExisteUnaConsultaDeUnUsuario();

        List<Consulta> consulta1Busqueda=repositorioDeConsultas.buscarConsultasDeUsuario(usuario.getId());
        assertThat(consulta1Busqueda).hasSize(1);

    }
    @Test
    @Transactional @Rollback
    public void alBuscarConsultaDeUsuarioDarQueNoexisten(){
        Usuario usuario=dadoQueExisteUnaConsultaDeUnUsuario();

        List<Consulta> consulta1Busqueda=repositorioDeConsultas.buscarConsultasDeUsuario(usuario.getId()+1);
        assertThat(consulta1Busqueda).hasSize(0);

    }

    @Test
    @Transactional @Rollback
    public void alBuscarlasConsultaDeUnaPublicacionExistenteDevuelveLALista(){
        Publicacion publicacion = dadoQueExistelistaConsultaDeUnaPublicacion();

        List<Consulta> consulta1Busqueda=repositorioDeConsultas.buscarConsultasDePublicacion(publicacion.getId());
        assertThat(consulta1Busqueda).hasSize(3);

    }

    private void entoncesSeEstableceElIDDelGuardado(Consulta consulta) {
        assertThat(consulta.getId()).isNotNull();
    }

    private Consulta dadoQueExisteUnaPublicacionPropiedadYUsuarioLogueado() {
        Consulta consulta=new Consulta();
        consulta.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario=new Usuario();
        usuario.setEmail("sullca@gmail.com");
        usuario.setId(1L);
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);
        consulta.setUsuario(usuario);
        consulta.setPublicacion(publicacion);
        session().save(usuario);
        session().save(publicacion);
        return consulta;
    }

    private Usuario dadoQueExisteUnaConsultaDeUnUsuario() {
        Consulta consulta=new Consulta();
        consulta.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario=new Usuario();
        usuario.setEmail("sullca@gmail.com");
        usuario.setId(1L);
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);
        consulta.setUsuario(usuario);
        consulta.setPublicacion(publicacion);
        session().save(usuario);
        session().save(publicacion);
        session().save(consulta);
        return usuario;
    }

    private Publicacion dadoQueExistelistaConsultaDeUnaPublicacion() {
        Consulta consulta1=new Consulta();
        consulta1.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario1=new Usuario();
        usuario1.setEmail("sullca@gmail.com");
        usuario1.setId(1L);
        Consulta consulta2=new Consulta();
        consulta2.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario2=new Usuario();
        usuario2.setEmail("sullca@gmail.com");
        usuario2.setId(2L);
        Consulta consulta3=new Consulta();
        consulta3.setPregunta("Tiempo de Aquiler minimo?");
        Usuario usuario3=new Usuario();
        usuario3.setEmail("sullca@gmail.com");
        usuario3.setId(3L);
        Publicacion publicacion=new Publicacion();
        publicacion.setTipoAccion(Accion.ALQUILAR);

        consulta1.setUsuario(usuario1);
        consulta2.setUsuario(usuario1);
        consulta3.setUsuario(usuario1);
        consulta1.setPublicacion(publicacion);
        consulta2.setPublicacion(publicacion);
        consulta3.setPublicacion(publicacion);

        session().save(usuario1);
        session().save(usuario2);
        session().save(usuario3);
        session().save(publicacion);
        session().save(consulta1);
        session().save(consulta2);
        session().save(consulta3);
        return publicacion;
    }
}
