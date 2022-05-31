package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Detalle;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.excepciones.PropiedadNoEncontrada;
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
    private Detalle detallePropiedad;
    private static final Integer ID_PROPIEDAD = 1;

    @Before
    public void init(){
        detallePropiedad = mock(Detalle.class);
        datosBusqueda = mock(DatosBusqueda.class);
        repositorio = mock(RepositorioPropiedades.class);
        servicioPropiedades = new ServicioPropiedadesDefault(repositorio);
    }

    @Test
    public void queAlBuscarUnaPropiedadPorDatosDeBusquedaDeberaTraerObjetoPropiedad() throws PropiedadNoEncontrada{
        dadoQueExisteUnaListaDePropiedades(3);

        List<Propiedad> busqueda = cuandoBuscoUnaPropiedad(datosBusqueda);

        entoncesSeObtieneTodasLasPropiedades(busqueda,3);

    }

    private void entoncesSeObtieneTodasLasPropiedades(List<Propiedad> busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);

    }

    private List<Propiedad> cuandoBuscoUnaPropiedad(DatosBusqueda datosBusqueda) throws PropiedadNoEncontrada {
        return servicioPropiedades.buscarPropiedadPorUbicacion(datosBusqueda);
    }

    private void dadoQueExisteUnaListaDePropiedades(int cantidad) {
        List<Propiedad> lista = givenExistenPropiedades(cantidad);

        when(repositorio.buscarPropiedad(datosBusqueda)).thenReturn(lista);

    }

    private List<Propiedad> givenExistenPropiedades(int cantidad){
        List<Propiedad> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Propiedad());
        }
        return lista;
    }

    @Test(expected = PropiedadNoEncontrada.class)
    public void alBuscarUnaPropiedadQueNoExisteDeberanDeArrojarUnaExcepcion() throws PropiedadNoEncontrada {

        dadoQueNoExisteUnaListaDePropiedades();
        cuandoBuscoUnaPropiedad(datosBusqueda);

    }

    private void dadoQueNoExisteUnaListaDePropiedades() {
    }
    
    @Test
    public void solicitarDetalleDePropiedadMedianteNumeroId(){
        
        dadoQueExisteUnaPropiedad();
        
        Detalle resultado = cuandoSolicitoVerDetalle();

        entoncesSeObtieneElDetalleDeLaPropiedad(resultado);
        
    }

    private void entoncesSeObtieneElDetalleDeLaPropiedad(Detalle resultado) {
        assertThat(resultado.getId()).isEqualTo(ID_PROPIEDAD);
    }

    private Detalle cuandoSolicitoVerDetalle() {
        return servicioPropiedades.verDetallePropiedad(ID_PROPIEDAD);

    }

    private void dadoQueExisteUnaPropiedad() {
        Detalle detalle = new Detalle();
        when(repositorio.buscarDetallePropiedad(ID_PROPIEDAD)).thenReturn(detalle);
    }


}
