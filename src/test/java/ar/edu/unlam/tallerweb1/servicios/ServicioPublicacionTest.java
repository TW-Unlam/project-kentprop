package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.PublicacionNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Accion;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.TipoPropiedad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPublicacionTest {

    private RepositorioPublicaciones repositorio;
    private ServicioPublicaciones servicioPublicaciones;
    private static final Integer ID_PUBLICACION = 1;
    private final Accion ACCION = Accion.COMPRAR;
    private final TipoPropiedad TIPO = TipoPropiedad.CASA;
    private final String descripcion = "";

    @Before
    public void init(){
        repositorio = mock(RepositorioPublicaciones.class);
        servicioPublicaciones = new ServicioPublicacionesDefault(repositorio);
    }

    @Test
    public void queAlBuscarUnaPublicacionDeberaTraermeLaListaDePropiedades() throws PublicacionNoEncontrada {
        dadoQueExisteUnaListaDePublicaciones(3);

        List<Publicacion> busqueda = cuandoBuscoUnaPublicacion();

        entoncesSeObtieneLasPublicaciones(busqueda,3);

    }

    @Test(expected = PublicacionNoEncontrada.class)
    public void queAlBuscarUnaPublicacionDeberaTraermeUnaExcepcion() throws PublicacionNoEncontrada {

        dadoQueNoExisteUnaListaDePublicaciones();
        cuandoBuscoUnaPublicacion();

    }
    
    @Test
    public void solicitarDetalleDePropiedadMedianteNumeroId(){
        
        dadoQueExisteUnaPublicacion();
        
        Publicacion resultado = cuandoSeleccionoVerDetalle();

        entoncesSeObtieneElDetalleDeLaPublicacion(resultado);
        
    }

    private void dadoQueNoExisteUnaListaDePublicaciones() {
    }

    private List<Publicacion> givenExistenPropiedades(int cantidad){
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++) {
            lista.add(new Publicacion());
        }
        return lista;
    }

    private void entoncesSeObtieneElDetalleDeLaPublicacion(Publicacion resultado) {
        assertThat(resultado.getId()).isEqualTo(ID_PUBLICACION);
    }

    private Publicacion cuandoSeleccionoVerDetalle() {
        return servicioPublicaciones.verDetallePublicacion(ID_PUBLICACION);

    }

    private void dadoQueExisteUnaPublicacion() {
        Publicacion detalle = new Publicacion();
        detalle.setId(1);
        when(repositorio.buscarDetallePublicacion(ID_PUBLICACION)).thenReturn(detalle);
    }

    private void entoncesSeObtieneLasPublicaciones(List<Publicacion> busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);

    }

    private List<Publicacion> cuandoBuscoUnaPublicacion() throws PublicacionNoEncontrada {
        return servicioPublicaciones.buscarPublicacion(ACCION, TIPO, descripcion);
    }

    private void dadoQueExisteUnaListaDePublicaciones(int cantidad) {
        List<Publicacion> lista = givenExistenPropiedades(cantidad);

        when(repositorio.buscarPublicaciones(ACCION, TIPO, descripcion)).thenReturn(lista);

    }


}
