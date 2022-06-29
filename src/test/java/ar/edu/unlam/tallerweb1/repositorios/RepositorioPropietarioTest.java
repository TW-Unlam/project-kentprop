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

    private final Integer PROPIETARIO_ID_VALIDO=1;

    @Autowired
    private RepositorioPropietario repositorioPropietario;


    @Test
    @Transactional @Rollback
    public void ALBuscarLasPublicacionesDevuelveListaNula(){

        Usuario propietario=dadoQueNoExisteUnaListaDePublicaciones();

        List<Publicacion> publicaciones = repositorioPropietario.obtenePublicacionesDelPropietario(propietario.getId());

        entoncesMeDevuelveUnaListaDePublicacionesVacia(publicaciones);

    }

    private Usuario dadoQueNoExisteUnaListaDePublicaciones() {
        Usuario propietario=new Usuario();
        session().save(propietario);
        return  propietario;
    }

    private void entoncesMeDevuelveUnaListaDePublicacionesVacia(List<Publicacion> publicaciones) {
        assertThat(publicaciones).isNotNull();
        assertThat(publicaciones).hasSize(0);
    }


    @Test
    @Transactional @Rollback
    public void ALBuscarLasPublicacionesDevuelveLista(){

        Usuario propietario=dadoQueExisteUnaListaDePublicaciones(10);

        List<Publicacion> publicaciones = repositorioPropietario.obtenePublicacionesDelPropietario(propietario.getId());

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
        Usuario propietario=new Usuario();

        for (int i=0;i<cantidad;i++){

            Propiedad propiedad=new Propiedad();
            propiedad.setPropietario(propietario);

            Publicacion publicacionN = new Publicacion();

            publicacionN.setPropiedad(propiedad);

            session().save(propiedad);
            session().save(publicacionN);
        }

        session().save(propietario);
        return  propietario;
    }


}
