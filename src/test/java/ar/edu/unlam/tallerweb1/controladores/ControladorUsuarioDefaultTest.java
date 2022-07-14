package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPregunta;

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

public class ControladorUsuarioDefaultTest {
    private static final Integer USUARIO_ID = 1;
    private static final String ROL_CORRECTO = "USUARIO";
    private static final String ROL_INCORRECTO = "PROPIETARIO";
    private static final Integer CANTIDAD_DE_PREGUNTAS = 3;
    private ServicioPregunta servicioPregunta;

    private static final String VISTA_LOGIN = "redirect:/login";
    private static final String VISTA_HOME="redirect:/";

    private static final String VISTA_MIS_PREGUNTAS="mis-preguntas";
    private HttpServletRequest request;
    private HttpSession session;

    private static final String MENSAJE_LISTA_VACIA = "Todavía no has hecho ninguna pregunta...";

    private ControladorUsuarioDefault controladorUsuarioDefault;

    @Before
    public void init(){
        servicioPregunta = mock(ServicioPregunta.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        controladorUsuarioDefault = new ControladorUsuarioDefault(servicioPregunta);
    }

    @Test
    public void queAlIrAMisPreguntasSinEstarLogueadoMeRedireccioneALaPaginaLogin(){
        request = dadoQueNoExisteUnUsuarioLogueado();

        ModelAndView mav = cuandoBuscoLasPreguntasDelUsuario(request);

        entoncesMeLLevaALaVista(VISTA_LOGIN, mav.getViewName());
    }

    @Test
    public void queAlIrAMisPublicacionesLogeadoSinSerPropietarioMeRedireccioneALaPaginaHome(){
        request = dadoQueExisteUnUsuarioSinRolUsuarioDefault();

        ModelAndView mav = cuandoBuscoLasPreguntasDelUsuario(request);

        entoncesMeLLevaALaVista(VISTA_HOME, mav.getViewName());
    }

    @Test
    public void AlBuscarLasPreguntasExistentesDelUsuarioMeDevuelveUnaLista(){
        request = dadoQueExisteUnUsuarioLogueadoConElRol(USUARIO_ID,ROL_CORRECTO);
        dadoQueExisteUnaListaDePreguntasParaElUsuario(USUARIO_ID, CANTIDAD_DE_PREGUNTAS);

        ModelAndView mav = cuandoBuscoLasPreguntasDelUsuario(request);

        entoncesObtengoLaListaDePreguntas(mav, CANTIDAD_DE_PREGUNTAS);
        entoncesMeLLevaALaVista(VISTA_MIS_PREGUNTAS, mav.getViewName());
    }

    @Test
    public void AlBuscarLasPreguntasInexistentesDelUsuarioMeDevuelveUnaListaVacia(){
        request = dadoQueExisteUnUsuarioLogueadoConElRol(USUARIO_ID,ROL_CORRECTO);
        dadoQueExisteUnaListaVaciaDePreguntasParaElUsuario(USUARIO_ID);

        ModelAndView mav = cuandoBuscoLasPreguntasDelUsuario(request);

        entoncesObtengoUnMensajeDeListaDePreguntasVacia(mav, MENSAJE_LISTA_VACIA);
        entoncesMeLLevaALaVista(VISTA_MIS_PREGUNTAS, mav.getViewName());
    }

    private void dadoQueExisteUnaListaDePreguntasParaElUsuario(Integer usuarioId, Integer cantidadPreguntas) {
        List<Pregunta> listaPreguntas = new LinkedList<>();
        for(int i = 0 ; i < cantidadPreguntas; i++){
            listaPreguntas.add(new Pregunta());
        }
        when(servicioPregunta.buscarPreguntasPorIdDeUsuario(usuarioId) ).thenReturn(listaPreguntas);
    }

    private void dadoQueExisteUnaListaVaciaDePreguntasParaElUsuario(Integer usuarioId) {
        List<Pregunta> listaPreguntas = new LinkedList<>();

        when(servicioPregunta.buscarPreguntasPorIdDeUsuario(usuarioId) ).thenReturn(listaPreguntas);
    }

    private HttpServletRequest dadoQueExisteUnUsuarioLogueadoConElRol(Integer usuarioId, String rol) {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(usuarioId);
        when(request.getSession().getAttribute("ROL")).thenReturn(rol);
        return request;
    }

    private HttpServletRequest dadoQueNoExisteUnUsuarioLogueado() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(null);
        return request;
    }

    private HttpServletRequest dadoQueExisteUnUsuarioSinRolUsuarioDefault() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(1);
        when(request.getSession().getAttribute("ROL")).thenReturn(ROL_INCORRECTO);
        return request;
    }

    private ModelAndView cuandoBuscoLasPreguntasDelUsuario(HttpServletRequest request) {
        return controladorUsuarioDefault.verPreguntasDelUsuario(request);
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String viewName) {
        assertThat(vistaEsperada).isEqualTo(viewName);
    }

    private void entoncesObtengoLaListaDePreguntas(ModelAndView mav, Integer cantidadDePreguntas) {
        List<Pregunta> listaPreguntas = (List<Pregunta>) mav.getModel().get("preguntasRealizadas");
        assertThat(listaPreguntas).hasSize(cantidadDePreguntas);
    }
    private void entoncesObtengoUnMensajeDeListaDePreguntasVacia(ModelAndView mav, String mensajeEsperado) {
        String mensaje = (String) mav.getModel().get("msg_vacio");
        assertThat(mensaje).isEqualTo(mensajeEsperado);
    }
}
