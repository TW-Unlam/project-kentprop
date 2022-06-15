package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorBuscadorHomeTest {

    public static final String VISTA_LISTA_PUBLICACIONES = "lista-publicaciones";
    public static final String MENSAJE_TIPO_INVALIDO = "No se encontraron publicaciones con estos datos";

    public static  DatosBusqueda datosBusqueda;
    private ControladorBuscadorHome controladorBuscadorHome;
    private ServicioPublicaciones servicioPublicaciones;

    @Before
    public void init(){
        datosBusqueda = mock(DatosBusqueda.class);
        servicioPublicaciones = mock(ServicioPublicaciones.class);
        controladorBuscadorHome = new ControladorBuscadorHome(servicioPublicaciones);
    }

    @Test
    public void alBuscarUnaPublicacionDeberiaDevolvermeUnaListaPublicaciones(){
        //Preparacion
        dadoQueTenemosUnaListaDePublicaciones(10);

        //Ejecucion
        ModelAndView mav = cuandoBuscoUnaPublicacion(datosBusqueda);

        entoncesEncuentro(mav, 10);
        entoncesMeLLevaALaVista(VISTA_LISTA_PUBLICACIONES, mav.getViewName());
    }

    @Test
    public void alBuscarUnaPublicacionInexistenteDeberiaDevolvermeMensajeDeError(){

        dadoQueNoExistePublicaciones();

        ModelAndView mav = cuandoBuscoUnaPublicacion(datosBusqueda);

        entoncesMeLLevaALaVista(VISTA_LISTA_PUBLICACIONES, mav.getViewName());
        entoncesSeRecibeMensajeError(MENSAJE_TIPO_INVALIDO, mav.getModel());
   }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav, int cantidadEsperada) {
        List<Publicacion> lista = (List<Publicacion>) mav.getModel().get("publicaciones");
        assertThat(lista).hasSize(cantidadEsperada);
    }

    private void dadoQueNoExistePublicaciones(){
        List<Publicacion> lista = new LinkedList<>();
        when(servicioPublicaciones.buscarPublicacion(datosBusqueda.getTipoAccion(),
                datosBusqueda.getTipoPropiedad(),
                datosBusqueda.getUbicacion()
        )).thenReturn(lista);
    }

    private ModelAndView cuandoBuscoUnaPublicacion(DatosBusqueda datosBusqueda) {
        return controladorBuscadorHome.buscar(datosBusqueda);
    }

    private void entoncesSeRecibeMensajeError(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg_error")).isEqualTo(mensaje);
    }

    private void dadoQueTenemosUnaListaDePublicaciones(int cantidad){
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++){
            lista.add(new Publicacion());
        }
        when(servicioPublicaciones.buscarPublicacion(datosBusqueda.getTipoAccion(),
                datosBusqueda.getTipoPropiedad(),
                datosBusqueda.getUbicacion())).thenReturn(lista);
    }

}
