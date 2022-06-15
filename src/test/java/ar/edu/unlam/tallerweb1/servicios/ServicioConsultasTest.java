package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioConsultasTest {

    private RepositorioPublicaciones repositorioPublicaciones;
    private ServicioEmailDefault servicioEmails;
    private RepositorioUsuario repositorioUsuarios;
    private Integer propiedadId=0;

    @Before
    public void init(){
        repositorioPublicaciones = mock(RepositorioPublicaciones.class);
        repositorioUsuarios=mock(RepositorioUsuario.class);
        servicioEmails = new ServicioEmailDefault(repositorioPublicaciones,repositorioUsuarios);
    }

    @Test
    public void alEnviarConsultaDeEmailDevuelveElUsuarioPropietario() throws UsuarioInexistente {
        dadoQueExisteUnaPublicacion();

        Usuario busqueda = cuandoBuscoUnUsuario(0);

        entoncesSeObtieneUnUsuarioValido(busqueda);

    }
    @Test
            (expected = UsuarioInexistente.class)
    public void alEnviarConsultaDeEmailDevuelveElUsuarioPropietarioNoexiste() throws UsuarioInexistente {
        dadoQueExisteUnaPublicacion();

        Usuario busqueda = cuandoBuscoUnUsuario(2);

        entoncesSeObtieneUnUsuarioNOValido(busqueda);

    }

    private void entoncesSeObtieneUnUsuarioNOValido(Usuario busqueda) {
        assertThat(busqueda).isNull();
    }

    private void entoncesSeObtieneUnUsuarioValido(Usuario busqueda) {
        assertThat(busqueda).isNotNull();
    }

    private Usuario cuandoBuscoUnUsuario(int idPropiedad) throws UsuarioInexistente {
        return servicioEmails.enviarConsultaPrivada("sullca@gmail.com","fernando",111111111,"mensaje Predeterminado", idPropiedad) ;
    }

    private void dadoQueExisteUnaPublicacion() {
        Publicacion detalle = new Publicacion();
        detalle.setId(1);
        detalle.setPropiedad(new Propiedad());
        detalle.getPropiedad().setPropietario(new Usuario());
        detalle.getPropiedad().getPropietario().setEmail("sullca@gmail.com");
        when(repositorioPublicaciones.buscarPropiedadConPropietario(propiedadId)).thenReturn( detalle.getPropiedad());
    }

    private List<Publicacion> givenExistenPropiedades(int cantidad){
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Publicacion());
        }
        return lista;
    }
}
