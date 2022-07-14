package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPregunta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorDetallePublicacionTest {
    private static final String VISTA_VER_DETALLE = "detalle-publicacion";
    private static final Integer PROPIEDAD_ID = 1;
    private static final String VISTA_REDIRECCION_LOGUEADO = "redirect:/detalle-publicacion?id=1";
    private static final String VISTA_REDIRECCION_SIN_LOGUEO = "redirect:/loginConId?id=1";
    private static final Integer ID_USUARIO = 1;
    private ControladorDetallePublicacion controladorDetallePublicacion;
    private ServicioPublicaciones servicioPublicaciones;
    private ServicioPregunta servicioPregunta;
    private HttpServletRequest request;
    private HttpSession session;
    private DatosPregunta datosPregunta;
    private static final String PREGUNTAS_VACIAS = "Por el momento no se realizaron preguntas ¡Sé el primero!";

    @Before
    public void init(){
        datosPregunta = mock(DatosPregunta.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servicioPregunta = mock(ServicioPregunta.class);
        servicioPublicaciones = mock(ServicioPublicaciones.class);
        controladorDetallePublicacion = new ControladorDetallePublicacion(servicioPregunta, servicioPublicaciones);
    }

    private HttpServletRequest givenExisteUnUsuarioConId(Integer id) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(id);
        return request;
    }

    private HttpServletRequest givenNoExisteUnUsuarioLogueado() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(null);
        return request;
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

    // --------------------------------------------
    @Test
    public void alEntrarEnVerDetalleSiLaSeccionPreguntasEstaVaciaArrojarMensaje(){
        dadoQueExisteUnaPropiedad();
        dadoQueNoExistenPreguntasEnUnaPublicacion();

        ModelAndView mav = cuandoSeleccionoVerDetalle();

        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());
        yMeDevuelveElMensajeDePreguntasVacias(PREGUNTAS_VACIAS, mav);
    }

    @Test
    public void queAlCargarUnaPreguntaSinEstarLogeadoMeRedireccioneALaPaginaLoginConId(){
        request = givenNoExisteUnUsuarioLogueado();
        alRealizarUnaPregunta();

        ModelAndView mav = cuandoEnvioLaPregunta(datosPregunta,request);

        entoncesMeRedirecciona(VISTA_REDIRECCION_SIN_LOGUEO, mav.getViewName());
    }

    @Test
    public  void queAlResponderUnaPreguntaMeDirreecionAlDetalleDeLaPublicacion(){
        alrealizarUnaRespuesta();
        ModelAndView mav=cuandoEnvioLaRespuesta(datosPregunta);
        entoncesMeRedirecciona(VISTA_REDIRECCION_LOGUEADO, mav.getViewName());

    }

    private ModelAndView cuandoEnvioLaRespuesta(DatosPregunta datosPregunta) {
        return controladorDetallePublicacion.responderPregunta(datosPregunta);
    }

    private void alrealizarUnaRespuesta() {
        when(datosPregunta.getPublicacionId()).thenReturn(1);
        when(datosPregunta.getPublicacionId()).thenReturn(1);
        Pregunta pregunta = new Pregunta();
        Publicacion publicacion=new Publicacion();
        publicacion.setId(datosPregunta.getPublicacionId());
        pregunta.setPublicacion(publicacion);
        when(datosPregunta.getDescripcion()).thenReturn("5 Meses");

        when(servicioPregunta.responderPregunta(datosPregunta.getPreguntaId(),datosPregunta.getDescripcion())).thenReturn(pregunta.getPublicacion().getId());
    }


    @Test
    public void queAlCargarUnaPreguntaAlEstarLogeadoMeRedireccioneALaPaginaVerDetalle(){
        request = givenExisteUnUsuarioConId(ID_USUARIO);
        alRealizarUnaPregunta();

        ModelAndView mav = cuandoEnvioLaPregunta(datosPregunta,request);

        entoncesMeRedirecciona(VISTA_REDIRECCION_LOGUEADO, mav.getViewName());
    }

    @Test
    public void QueAlMarcarComoFavoritoUnaPublicacionSinEstarLogueadoIrALoguin()
    {   request = givenNoExisteUnUsuarioLogueado();
        Publicacion publicacion=dadoqueexisteLaPublicacionPAraMarcar();

        ModelAndView mav=cuandoMarcoComoFavorito(publicacion.getId(),request);
        entoncesMeRedirecciona(VISTA_REDIRECCION_SIN_LOGUEO, mav.getViewName());
    }

    @Test
    public void QueAlMarcarComoFavoritoUnaPublicacionIndiqueYSeMantengEnLaVistaDetalles()
    {    request = givenExisteUnUsuarioConId(ID_USUARIO);
        Publicacion publicacion=dadoqueexisteLaPublicacionPAraMarcar();
        ModelAndView mav=cuandoMarcoComoFavorito(publicacion.getId(),request);
        entoncesMeRedirecciona(VISTA_REDIRECCION_LOGUEADO, mav.getViewName() );
    }

    private Publicacion dadoqueexisteLaPublicacionPAraMarcar() {
        Publicacion publicacionAindicar=new Publicacion();
        publicacionAindicar.setId(1);
        return publicacionAindicar;
    }

    private ModelAndView cuandoMarcoComoFavorito(int idPublicacion, HttpServletRequest request) {
        return controladorDetallePublicacion.marcarComoFavorito(idPublicacion,request);
    }

    private void dadoqueexisteunaPublicacionYUsuarioLogueado() {
    }

    private void entoncesMeRedirecciona(String vistaRedireccion, String viewName) {
        assertThat(vistaRedireccion).isEqualTo(viewName);
    }

    private ModelAndView cuandoEnvioLaPregunta(DatosPregunta datosPregunta, HttpServletRequest request) {
        return controladorDetallePublicacion.hacerPregunta(datosPregunta, request);
    }

    private void alRealizarUnaPregunta() {
        when(datosPregunta.getPublicacionId()).thenReturn(1);
    }

    private void yMeCarganLasPreguntasYaHechas(int cantidadEsperada, ModelAndView mav) {
        List<Pregunta> lista = (List<Pregunta>) mav.getModel().get("preguntas_hechas");
        assertThat(lista).hasSize(cantidadEsperada);
    }

    private void yMeDevuelveElMensajeDePreguntasVacias(String i, ModelAndView mav) {
        String mensaje = (String) mav.getModel().get("msg_sin_preguntas");
        assertThat(mensaje).isEqualTo(i);
    }

    private void dadoQueExistenPreguntasEnUnaPublicacion(int cantidadPreguntas) {
        List<Pregunta> consultasHechas = new LinkedList<Pregunta>();
        for(int i = 0 ; i < cantidadPreguntas; i++){
            consultasHechas.add(new Pregunta());
        }
        when(servicioPregunta.buscarConsultasDePublicacion(PROPIEDAD_ID)).thenReturn(consultasHechas);
    }

    private void dadoQueNoExistenPreguntasEnUnaPublicacion() {
        List<Pregunta> consultasHechas = new LinkedList<Pregunta>();

        when(servicioPregunta.buscarConsultasDePublicacion(PROPIEDAD_ID)).thenReturn(consultasHechas);
    }

    private void dadoQueExisteUnaPropiedad() {
        Publicacion detalle = new Publicacion();
        when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
    }

    private ModelAndView cuandoSeleccionoVerDetalle() {
        return controladorDetallePublicacion.verDetallePublicacion(PROPIEDAD_ID,request );
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }
}
