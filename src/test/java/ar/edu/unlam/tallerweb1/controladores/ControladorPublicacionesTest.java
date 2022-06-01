package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.PropiedadNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedades;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorPublicacionesTest {

    public static final String VISTA_LISTA_PUBLICACIONES = "lista-publicaciones";
    public static final String MENSAJE_TIPO_INVALIDO = "No se encontraron publicaciones con estos datos";
    private static final String VISTA_VER_DETALLE = "detalle-publicacion";
    private static final Integer PROPIEDAD_ID = 1;
    public static  DatosBusqueda datosBusqueda;
    private ControladorPublicacion controladorPublicacion;
    private ServicioPropiedades servicioPublicaciones;


    @Before
    public void init(){
        datosBusqueda = mock(DatosBusqueda.class);
        servicioPublicaciones = mock(ServicioPropiedades.class);
        controladorPublicacion = new ControladorPublicacion(servicioPublicaciones);
    }

    @Test
    public void alBuscarUnaPublicacionDeberiaDevolvermeUnaListaPublicaciones() throws PropiedadNoEncontrada{
        //Preparacion
        dadoQueTenemosUnaListaDePublicaciones(10);

        //Ejecucion
        ModelAndView mav = cuandoBuscoUnaPublicacion(datosBusqueda);

        entoncesEncuentro(mav, 10);
        entoncesMeLLevaALaVista(VISTA_LISTA_PUBLICACIONES, mav.getViewName());
    }

    @Test
    public void alBuscarUnaPublicacionInexistenteDeberiaDevolvermeMensajeDeError() throws PropiedadNoEncontrada{
        when(servicioPublicaciones.buscarPublicacion(datosBusqueda)).thenThrow(new PropiedadNoEncontrada());

        ModelAndView mav = cuandoBuscoUnaPublicacion(datosBusqueda);

        entoncesMeLLevaALaVista(VISTA_LISTA_PUBLICACIONES, mav.getViewName());
        entoncesSeRecibeMensaje(MENSAJE_TIPO_INVALIDO, mav.getModel());
   }

   @Test
   public void alSeleccionarVerDetalleMeTraeLaVistaDetalle(){

        dadoQueExisteUnaPropiedad();

        ModelAndView mav = cuandoSeleccionoVerDetalle();

        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());

   }

    private ModelAndView cuandoSeleccionoVerDetalle() {
        return controladorPublicacion.verDetallePublicacion(PROPIEDAD_ID);
    }

    private void dadoQueExisteUnaPropiedad() {
       Publicacion detalle = new Publicacion();
       when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
    }

    private void dadoQueTenemosUnaListaDePublicaciones(int cantidad) throws PropiedadNoEncontrada{
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++){
            lista.add(new Publicacion());
        }
        when(servicioPublicaciones.buscarPublicacion(datosBusqueda)).thenReturn(lista);
    }

    private ModelAndView cuandoBuscoUnaPublicacion(DatosBusqueda datosBusqueda) {
        return controladorPublicacion.buscar(datosBusqueda);
    }

    private void entoncesSeRecibeMensaje(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg-error")).isEqualTo(mensaje);
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav, int cantidadEsperada) {
        List<Publicacion> lista = (List<Publicacion>) mav.getModel().get("publicaciones");
        assertThat(lista).hasSize(cantidadEsperada);
    }
}
