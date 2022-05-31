package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class RepositorioPropiedadesTest extends SpringTest {

    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

    @Test @Transactional @Rollback
    public void busquedaPropiedadesConDevolucion(){

        dadoQueExisteUnaListaDePropiedades();

        DatosBusqueda datos = dadoqueExisteUnaBusqueda();
        List<Propiedad> propiedades = repositorioPropiedades.buscarPropiedad(datos);

        entoncesMeDevuelveUnaPropiedad(propiedades);
    }

    @Test @Transactional @Rollback
    public void busquedaPropiedadesSinDevolucion(){

        dadoQueNoExisteUnaListaDePropiedades();

        DatosBusqueda datos = dadoqueExisteUnaBusqueda();
        List<Propiedad> propiedades = repositorioPropiedades.buscarPropiedad(datos);

        entoncesNoMeDevuelveNingunaPropiedad(propiedades);

    }

    @Test @Transactional @Rollback
    public void realizarUnaBusquedaConDatosDeBusquedaInexistentes(){

        dadoQueExisteUnaListaDePropiedades();

        DatosBusqueda datos = dadoqueExisteUnaBusquedaInexistente();
        List<Propiedad> propiedades = repositorioPropiedades.buscarPropiedad(datos);

        entoncesNoMeDevuelveNingunaPropiedad(propiedades);

    }

    @Test @Transactional @Rollback
    public void a(){

    }

    private DatosBusqueda dadoqueExisteUnaBusquedaInexistente() {
        DatosBusqueda datos = new DatosBusqueda();
        datos.setTipoPropiedad(TipoPropiedad.DEPARTAMENTO);
        datos.setTipoAccion(Accion.EMPRENDER);
        datos.setUbicacion("Puerta de Hierro");
        return datos;
    }

    private void entoncesNoMeDevuelveNingunaPropiedad(List<Propiedad> propiedades) {
        assertThat(propiedades).hasSize(0);
    }

    private void dadoQueNoExisteUnaListaDePropiedades() {
    }

    private void entoncesMeDevuelveUnaPropiedad(List<Propiedad> propiedades) {
        assertThat(propiedades).hasSize(1);
    }

    private DatosBusqueda dadoqueExisteUnaBusqueda() {
        DatosBusqueda datos = new DatosBusqueda();
        datos.setTipoPropiedad(TipoPropiedad.DEPARTAMENTO);
        datos.setTipoAccion(Accion.ALQUILAR);
        datos.setUbicacion("Ramos Mejia");
        return datos;

    }

    private void dadoQueExisteUnaListaDePropiedades() {
        Propiedad propiedadUno = new Propiedad();
        Propiedad propiedadDos = new Propiedad();

        Ubicacion ubicacionDos = new Ubicacion();
        ubicacionDos.setProvincia("Buenos Aires");
        ubicacionDos.setLocalidad("Ramos Mejia");

        propiedadUno.setUbicacion(ubicacionDos);
        propiedadUno.setTipoPropiedad(TipoPropiedad.CASA);
        propiedadUno.setTipoAccion(Accion.COMPRAR);

        Ubicacion ubicacionUno = new Ubicacion();
        ubicacionUno.setProvincia("Buenos Aires");
        ubicacionUno.setLocalidad("Ramos Mejia");

        propiedadDos.setUbicacion(ubicacionUno);
        propiedadDos.setTipoPropiedad(TipoPropiedad.DEPARTAMENTO);
        propiedadDos.setTipoAccion(Accion.ALQUILAR);

        session().save(ubicacionUno);
        session().save(ubicacionDos);
        session().save(propiedadUno);
        session().save(propiedadDos);





    }


}
