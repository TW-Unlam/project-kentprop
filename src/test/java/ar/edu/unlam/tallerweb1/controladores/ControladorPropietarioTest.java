package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropietario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorPropietarioTest {
    private static final String MENSAJE_PROPIEDADES_INEXISTENTES = "No tiene Propiedades publicadas...";
    private final String VISTA_MIS_PROPIEDADES="mis-publicaciones";

    private static final String VISTA_REDIRECCION_SIN_LOGUEO = "redirect:/login";
    private final Integer PROPIETARIO_ID_VALIDO = 1;
    private ServicioPropietario servicioPropietario;
    private ServicioPublicaciones servicioPublicaciones;
    private ControladorPropietario controladorPropietario;
    private HttpServletRequest request;
    private HttpSession session;
    private String VISTA_HOME = "/";;
    private String VISTA_REDIRECCION_HOME="redirect:/";
    private  DatosConsulta datosConsulta;
    @Before
    public void init(){
        servicioPropietario = mock(ServicioPropietario.class);
        servicioPublicaciones=mock(ServicioPublicaciones.class);
        datosConsulta=mock(DatosConsulta.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        controladorPropietario = new ControladorPropietario(servicioPublicaciones,servicioPropietario);
    }

    @Test
    public void queAlIrAMisPublicacionesSinEstarLogeadoMeRedireccioneALaPaginaLogin(){
        request = dadoQueNoExisteUnUsuarioLogueado();
        alintentarIrAMisPublicaciones();

        ModelAndView mav = cuandoBuscolasPublicacionDelPropietario(PROPIETARIO_ID_VALIDO,request);

        entoncesMeLLevaALaVista(VISTA_REDIRECCION_SIN_LOGUEO, mav.getViewName());
    }

    @Test
    public void queAlIrAMisPublicacionesLogeadoSinSerPropietarioMeRedireccioneALaPaginaHome(){
        request = dadoQueNoExisteUnUsuarioLogueadoPropietario();
        alintentarIrAMisPublicaciones();

        ModelAndView mav = cuandoBuscolasPublicacionDelPropietario(PROPIETARIO_ID_VALIDO,request);

        entoncesMeLLevaALaVista(VISTA_REDIRECCION_HOME, mav.getViewName());
    }



    @Test
    public void AlPedirLasPublicacionesDeLPropietarioMeDevuelveLAListaDePublicaciones(){
        //Preparacion
        request = dadoQueExisteUnUsuarioLogueado();
//        DadoQueExisteUnaListaDePublicacionesConSuPropietario(10);
        DadoQueExisteUnaListaDePublicacionesConSuPropietarioeImagenes(10);
        //Ejecucion
        ModelAndView mav = cuandoBuscolasPublicacionDelPropietario(PROPIETARIO_ID_VALIDO,request);
        //Validacion
        entoncesEncuentroPublicaciones(mav, 10);
        entoncesMeLLevaALaVista(VISTA_MIS_PROPIEDADES, mav.getViewName());
    }

    @Test
    public void AlPedirLasPublicacionesDeLPropietarioMeDevuelveLAListaVAcia(){
        request = dadoQueExisteUnUsuarioLogueado();
        dadoQueNoExistePublicaciones();

        ModelAndView mav = cuandoBuscolasPublicacionDelPropietario(PROPIETARIO_ID_VALIDO, request);

        entoncesSeRecibeMensajeListaVacia(MENSAJE_PROPIEDADES_INEXISTENTES, mav.getModel());
    }

    @Test
    public void AlPedirLasPublicacionesDeLPropietarioMeDevuelveLAListaDePublicacionesConSusImagenes(){
        //Preparacion
        request = dadoQueExisteUnUsuarioLogueado();
        DadoQueExisteUnaListaDePublicacionesConSuPropietarioeImagenes(10);
        //Ejecucion
        ModelAndView mav = cuandoBuscolasPublicacionDelPropietario((Integer)request.getSession().getAttribute("id"),request);
        //ModelAndView mav = cuandoBuscolasPublicacionDelPropietario(PROPIETARIO_ID_VALIDO,request);
        //Validacion
        entoncesEncuentroPublicaciones(mav, 10);
        entoncesEncuentroImagenes(mav, 10);
        entoncesMeLLevaALaVista(VISTA_MIS_PROPIEDADES, mav.getViewName());
    }

    ////////////////////////////////////////////////////////////////////
    private HttpServletRequest dadoQueNoExisteUnUsuarioLogueado() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(null);
        return request;
    }

    private HttpServletRequest dadoQueNoExisteUnUsuarioLogueadoPropietario() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(PROPIETARIO_ID_VALIDO);
        when(request.getSession().getAttribute("ROL")).thenReturn("Visitante");
        return request;
    }

    private HttpServletRequest dadoQueExisteUnUsuarioLogueado() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("id")).thenReturn(PROPIETARIO_ID_VALIDO);
        when(request.getSession().getAttribute("ROL")).thenReturn("PROPIETARIO");
        return request;
    }

    private void  alintentarIrAMisPublicaciones() {
        List<Publicacion> lista = new LinkedList<>();
        when(servicioPropietario.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO)).thenReturn(lista) ;
    }
    private void DadoQueExisteUnaListaDePublicacionesConSuPropietario(int cantidadPublicaciones) {
        List<Publicacion> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidadPublicaciones; i++){
            lista.add(new Publicacion());
        }
        when(servicioPropietario.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO)).thenReturn(lista);
    }

    private void DadoQueExisteUnaListaDePublicacionesConSuPropietarioeImagenes(int cantidadPublicaciones) {
        List<Publicacion> listaPublicaciones = new LinkedList<>();
        List<Imagen> listaImagenes = new LinkedList<>();
        for(int i = 0 ; i < cantidadPublicaciones; i++){
            Publicacion tmpP=new Publicacion();
            tmpP.setId(i);
            Imagen tmpI=new Imagen();
            tmpI.setPublicacion(tmpP);
            listaPublicaciones.add(tmpP);
            listaImagenes.add(tmpI);
            when(servicioPublicaciones.traerImagenesPorId(i)).thenReturn(listaImagenes);
        }
        when(servicioPropietario.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO)).thenReturn(listaPublicaciones);

    }

    private void dadoQueNoExistePublicaciones() {
        List<Publicacion> lista = new LinkedList<>();
        when(servicioPropietario.obtenePublicacionesDelPropietario(PROPIETARIO_ID_VALIDO)).thenReturn(lista);
    }

    private ModelAndView cuandoBuscolasPublicacionDelPropietario(Integer propietario_id_valido, HttpServletRequest request) {
        return controladorPropietario.verPublicacionDelPropietario(propietario_id_valido, request);
    }

    private void entoncesEncuentroImagenes(ModelAndView mav, int cantidadEsperada) {
        List<DatosPublicacion> lista = (List<DatosPublicacion>) mav.getModel().get("publicaciones");
        assertThat(lista).hasSize(cantidadEsperada);
        assertThat(lista.get(0).getImagen()).isNotNull();
    }

    private void entoncesEncuentroPublicaciones(ModelAndView mav, int cantidadEsperada) {
        List<Publicacion> lista = (List<Publicacion>) mav.getModel().get("publicaciones");
        assertThat(lista).hasSize(cantidadEsperada);
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String viewName) {
        assertThat(vistaEsperada).isEqualTo(viewName);
    }

    private void entoncesSeRecibeMensajeListaVacia(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg_vacio")).isEqualTo(mensaje);
    }



//////////////////////////////////////////////////////////////////


}
