package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class RepositorioPublicacionesTest extends SpringTest {

    private final TipoPropiedad TIPO_EXISTENTE = TipoPropiedad.DEPARTAMENTO;
    private final Accion ACCION_EXISTENTE = Accion.ALQUILAR;
    private final String DESCRIPCION_EXISTENTE = "Ramos Mejia";
    private final TipoPropiedad TIPO_INEXISTENTE = TipoPropiedad.OFICINA;
    private final Accion ACCION_INEXISTENTE = Accion.EMPRENDER;
    private final String DESCRIPCION_INEXISTENTE = "Villa Luzuriaga";

    @Autowired
    private RepositorioPublicaciones repositorioPublicaciones;

    @Test @Transactional @Rollback
    public void busquedaPublicacionesConDevolucion(){

        dadoQueExisteUnaListaDePublicaciones();

        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE,
                TIPO_EXISTENTE,
                DESCRIPCION_EXISTENTE);

        entoncesMeDevuelveUnaListaDePublicacionesQueCoinciden(propiedades);
    }

    @Test @Transactional @Rollback
    public void busquedaPublicacionesSinDevolucion(){

        dadoQueNoExisteUnaListaDePublicaciones();
        
        List<Publicacion> publicaciones = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE,
                TIPO_EXISTENTE,
                DESCRIPCION_EXISTENTE);

        entoncesNoMeDevuelveNingunaPublicacion(publicaciones);

    }

    @Test @Transactional @Rollback
    public void realizarUnaBusquedaConDatosDeBusquedaInexistentes(){

        dadoQueExisteUnaListaDePublicaciones();

        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_INEXISTENTE,
                TIPO_INEXISTENTE,
                DESCRIPCION_INEXISTENTE);

        entoncesNoMeDevuelveNingunaPublicacion(propiedades);

    }

    @Test @Transactional @Rollback
    public void obtenerElObjetoPropiedadAlVerDetalleDeLaPublicacion(){
        dadoQueExisteUnaListaDePublicaciones();
        
        List<Publicacion> propiedades = repositorioPublicaciones.buscarPublicaciones(ACCION_EXISTENTE, TIPO_EXISTENTE, DESCRIPCION_EXISTENTE);
        Publicacion resultado = repositorioPublicaciones.buscarDetallePublicacion(propiedades.get(0).getId());

        entoncesMeDevuelveElDetalleDeLaPropiedadConId(propiedades.get(0).getId(), resultado.getId());
    }

    private void entoncesMeDevuelveElDetalleDeLaPropiedadConId(Integer idPropiedad, Integer idDetalle) {
        assertThat(idPropiedad).isEqualTo(idDetalle);
    }

    private void entoncesNoMeDevuelveNingunaPublicacion(List<Publicacion> propiedades) {
        assertThat(propiedades).hasSize(0);
    }

    private void dadoQueNoExisteUnaListaDePublicaciones() {
    }

    private void entoncesMeDevuelveUnaListaDePublicacionesQueCoinciden(List<Publicacion> propiedades) {
        assertThat(propiedades).hasSize(1);
    }

    private void dadoQueExisteUnaListaDePublicaciones() {

        Publicacion publicacionUno = new Publicacion();
        Publicacion publicacionDos = new Publicacion();

        Propiedad propiedadUno = new Propiedad();
        Propiedad propiedadDos = new Propiedad();

        Ubicacion ubicacionDos = new Ubicacion();
        ubicacionDos.setProvincia("Buenos Aires");
        ubicacionDos.setLocalidad("Ramos Mejia");

        Ubicacion ubicacionUno = new Ubicacion();
        ubicacionUno.setProvincia("Buenos Aires");
        ubicacionUno.setLocalidad("Ramos Mejia");

        propiedadUno.setUbicacion(ubicacionDos);
        propiedadUno.setTipoPropiedad(TipoPropiedad.CASA);
        propiedadDos.setUbicacion(ubicacionUno);
        propiedadDos.setTipoPropiedad(TipoPropiedad.DEPARTAMENTO);

        publicacionUno.setTipoAccion(ACCION_EXISTENTE);
        publicacionDos.setTipoAccion(ACCION_EXISTENTE);
        publicacionUno.setPropiedad(propiedadUno);
        publicacionDos.setPropiedad(propiedadDos);

        session().save(ubicacionUno);
        session().save(ubicacionDos);
        session().save(propiedadUno);
        session().save(propiedadDos);
        session().save(publicacionUno);
        session().save(publicacionDos);

    }


}
