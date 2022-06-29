package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPropietario;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPropietarioTest {

    private RepositorioPropietario repositorioPropietarios;
    private ServicioPropietarioDefault servicioPropietarios;

    private final Integer PROPIETARIO_ID_VALIDO=1;
    private final static int CANTIDAD_PUBLICACIONES = 10;

    @Before
    public void init(){
        repositorioPropietarios = mock(RepositorioPropietario.class);
        servicioPropietarios = new ServicioPropietarioDefault(repositorioPropietarios);
    }

    @Test
    public void AlPedirLasPublicacionesDeLPropietarioMeDevuelveLAListaDePublicaciones(){
        //Preparacion

        DadoQueExisteUnaListaDePublicacionesConSuPropietario(CANTIDAD_PUBLICACIONES);
        //Ejecucion
        List<Publicacion> busqueda = cuandoBuscoLasPublicacionesDelPropietario();
        //Validacion
        entoncesSeObtieneLasPublicaciones(busqueda,CANTIDAD_PUBLICACIONES);
    }

    @Test
    public void AlPedirLasPublicacionesDeLPropietarioMeDevuelveUnaListaVacia(){
        //Preparacion
        DadoQueExisteNOExiteUnaListaDePublicacionesConSuPropietario();
        //Ejecucion
        List<Publicacion> busqueda = cuandoBuscoLasPublicacionesDelPropietario();
        //Validacion
        entoncesSeObtieneLasPublicaciones(busqueda,0);
    }

    private void DadoQueExisteNOExiteUnaListaDePublicacionesConSuPropietario() {
        List<Publicacion> lista = new LinkedList<>();
        when(repositorioPropietarios.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO)).thenReturn(lista);
    }

    private void DadoQueExisteUnaListaDePublicacionesConSuPropietario(int cantidad_publicaciones) {
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad_publicaciones; i++) {
            lista.add(new Publicacion());
        }
        when(repositorioPropietarios.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO)).thenReturn(lista);
    }

    private List<Publicacion> cuandoBuscoLasPublicacionesDelPropietario() {
        return servicioPropietarios.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO);
    }

    private void entoncesSeObtieneLasPublicaciones(List<Publicacion> busqueda, int cantidadEsperada) {
        assertThat(busqueda).hasSize(cantidadEsperada);
    }

}
