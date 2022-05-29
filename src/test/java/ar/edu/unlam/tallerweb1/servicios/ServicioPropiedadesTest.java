package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.controladores.Propiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropiedades;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPropiedadesTest {

    private  RepositorioPropiedades repositorio;
    private ServicioPropiedades servicioPropiedades;

    private DatosBusqueda datosBusqueda;
    private final String ubicacion = "Ramos Mejia";

    @Before
    public void init(){
        datosBusqueda = new DatosBusqueda();
        repositorio = mock(RepositorioPropiedades.class);
        servicioPropiedades = new ServicioPropiedadesDefault(repositorio);
    }

    @Test
    public void queAlBuscarUnaPropiedadPorDatosDeBusquedaDeberaTraerObjetoPropiedad(){
        datosBusqueda.setTipoPropiedad("Departamento");
        datosBusqueda.setTipoAccion("Alquilar");
        datosBusqueda.setUbicacion("Ramos");
        dadoQueExisteUnaListaDePropiedades(3, ubicacion);

        cuandoBuscoUnaPropiedad(datosBusqueda);

        entoncesSeObtieneTodasLasPropiedades();

    }

    private void entoncesSeObtieneTodasLasPropiedades() {

    }

    private void cuandoBuscoUnaPropiedad(DatosBusqueda datosBusqueda) {
        servicioPropiedades.buscarPropiedadPorUbicacion(datosBusqueda);
    }

    private void dadoQueExisteUnaListaDePropiedades(int cantidad, String ubicacion) {
        List<Propiedad> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Propiedad());
        }

        when(repositorio.buscarPropiedad(datosBusqueda)).thenReturn(lista);

    }


}
