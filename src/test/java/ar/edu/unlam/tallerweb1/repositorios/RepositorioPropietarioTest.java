package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioPropietarioTest extends SpringTest {

    private final long PROPIETARIO_ID_VALIDO=1;

    @Autowired
    private RepositorioPropietario repositorioPropietario;


    @Test
    @Transactional @Rollback
    public void ALBuscarLasPublicacionesDevuelveListaNula(){

        Usuario Propietario=dadoQueNoExisteUnaListaDePublicaciones();

        List<Publicacion> publicaciones = repositorioPropietario.obtenePublicacionesDelPropietario(Propietario.getId());

        entoncesMeDevuelveUnaListaDePublicacionesVacia(publicaciones);

    }

    private Usuario dadoQueNoExisteUnaListaDePublicaciones() {
        Usuario Propietario=new Usuario();
        session().save(Propietario);
        return  Propietario;
    }

    private void entoncesMeDevuelveUnaListaDePublicacionesVacia(List<Publicacion> publicaciones) {
        assertThat(publicaciones).isNotNull();
        assertThat(publicaciones).hasSize(0);
    }


    @Test
    @Transactional @Rollback
    public void ALBuscarLasPublicacionesDevuelveLista(){

        Usuario Propietario=dadoQueExisteUnaListaDePublicaciones(10);

        List<Publicacion> publicaciones = repositorioPropietario.obtenePublicacionesDelPropietario(Propietario.getId());

        entoncesMeDevuelveUnaListaDePublicacionesQueCoinciden(publicaciones);
        entoncesMeDevuelveUnaListaDePublicaciones(10,publicaciones);
    }

    private void entoncesMeDevuelveUnaListaDePublicaciones(int cantidadEsperada, List<Publicacion> publicaciones) {
        assertThat(publicaciones).hasSize(cantidadEsperada);
    }

    private void entoncesMeDevuelveUnaListaDePublicacionesQueCoinciden(List<Publicacion> publicaciones) {
        assertThat(publicaciones).isNotNull();
    }

    private Usuario dadoQueExisteUnaListaDePublicaciones(int cantidad) {
        Usuario Propietario=new Usuario();

        for (int i=0;i<cantidad;i++){

            Propiedad propiedad=new Propiedad();
            propiedad.setPropietario(Propietario);

            Publicacion publicacionN = new Publicacion();

            publicacionN.setPropiedad(propiedad);

            session().save(propiedad);
            session().save(publicacionN);
        }

        session().save(Propietario);
        return  Propietario;
    }


}
