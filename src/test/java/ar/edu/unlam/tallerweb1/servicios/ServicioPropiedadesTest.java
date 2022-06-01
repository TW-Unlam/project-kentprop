package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosBusqueda;
import ar.edu.unlam.tallerweb1.modelo.Detalle;
import ar.edu.unlam.tallerweb1.excepciones.PropiedadNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
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

        List<Publicacion> busqueda = cuandoBuscoUnaPropiedad(datosBusqueda);

        entoncesSeObtieneTodasLasPropiedades(busqueda,3);

    }

    private List<Publicacion> givenExistenPropiedades(int cantidad){
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Publicacion());
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
        
        Publicacion resultado = cuandoSolicitoVerDetalle();

        entoncesSeObtieneElDetalleDeLaPropiedad(resultado);
        
    }

    private void entoncesSeObtieneElDetalleDeLaPropiedad(Publicacion resultado) {
        assertThat(resultado.getId()).isEqualTo(ID_PROPIEDAD);
    }

    private Publicacion cuandoSolicitoVerDetalle() {
        return servicioPropiedades.verDetallePublicacion(ID_PROPIEDAD);

    }

    private void dadoQueExisteUnaPropiedad() {
        Publicacion detalle = new Publicacion();
        when(repositorio.buscarDetallePropiedad(ID_PROPIEDAD)).thenReturn(detalle);
    }

    private void entoncesSeObtieneTodasLasPropiedades(List<Publicacion> busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);

    }

    private List<Publicacion> cuandoBuscoUnaPropiedad(DatosBusqueda datosBusqueda) throws PropiedadNoEncontrada {
        return servicioPropiedades.buscarPublicacion(datosBusqueda);
    }

    private void dadoQueExisteUnaListaDePropiedades(int cantidad) {
        List<Publicacion> lista = givenExistenPropiedades(cantidad);

        when(repositorio.buscarPropiedad(datosBusqueda)).thenReturn(lista);

    }


}
