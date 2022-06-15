package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorDetallePublicacionTest {
    private static final String VISTA_VER_DETALLE = "detalle-publicacion";
    private static final Integer PROPIEDAD_ID = 1;
    private static final String VISTA_REDIRECCION = "redirect:/detalle-publicacion?id=1";

    private static final String PREGUNTA_HECHA = "Descripcion random";
    private ControladorDetallePublicacion controladorDetallePublicacion;
    private ServicioPublicaciones servicioPublicaciones;
    private ServicioConsulta servicioConsulta;

    @Before
    public void init(){
        servicioConsulta = mock(ServicioConsulta.class);
        servicioPublicaciones = mock(ServicioPublicaciones.class);
        controladorDetallePublicacion = new ControladorDetallePublicacion(servicioConsulta, servicioPublicaciones);

    }

    @Test
    public void alSeleccionarVerDetalleMeTraeLaVistaDetalle(){

        dadoQueExisteUnaPropiedad();

        ModelAndView mav = cuandoSeleccionoVerDetalle();

        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());
    }

    @Test
    public void alEntrarEnVerDetalleDeberianDeCargarLaSeccionPreguntas(){
        dadoQueExisteUnaPropiedad();
        dadoQueExistenPreguntasEnUnaPublicacion(10);

        ModelAndView mav = cuandoSeleccionoVerDetalle();

        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());
        yMeCarganLasPreguntasYaHechas(10, mav);
    }

    @Test
    public void queAlCargarUnaPreguntaMeRedireccioneALaPaginaVerDetalle(){
        alRealizarUnaPregunta();

        ModelAndView mav = cuandoEnvioLaPregunta();

        entoncesMeRedirecciona(VISTA_REDIRECCION, mav.getViewName());
    }

    private void entoncesMeRedirecciona(String vistaRedireccion, String viewName) {
        assertThat(vistaRedireccion).isEqualTo(viewName);
    }

    private ModelAndView cuandoEnvioLaPregunta() {
        //return controladorDetallePublicacion.hacerPregunta(PROPIEDAD_ID, PREGUNTA_HECHA);
        return null;
    }

    private void alRealizarUnaPregunta() {
        Consulta consulta = new Consulta();
        when(servicioConsulta.hacerPregunta(consulta)).thenReturn(true);
    }

    private void yMeCarganLasPreguntasYaHechas(int cantidadEsperada, ModelAndView mav) {
        List<Consulta> lista = (List<Consulta>) mav.getModel().get("preguntas_hechas");
        assertThat(lista).hasSize(cantidadEsperada);
    }

    private void dadoQueExistenPreguntasEnUnaPublicacion(int cantidadPreguntas) {
        List<Consulta> consultasHechas = new LinkedList<Consulta>();
        for(int i = 0 ; i < cantidadPreguntas; i++){
            consultasHechas.add(new Consulta());
        }
        when(servicioConsulta.buscarConsultasDePublicacion(PROPIEDAD_ID)).thenReturn(consultasHechas);
    }

    private void dadoQueExisteUnaPropiedad() {
        Publicacion detalle = new Publicacion();
        when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
    }

    private ModelAndView cuandoSeleccionoVerDetalle() {
        return controladorDetallePublicacion.verDetallePublicacion(PROPIEDAD_ID);
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }
}
