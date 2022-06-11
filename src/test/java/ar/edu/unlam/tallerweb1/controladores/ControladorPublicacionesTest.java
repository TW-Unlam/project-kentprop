package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.PublicacionNoEncontrada;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
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

public class ControladorPublicacionesTest {

    public static final String VISTA_LISTA_PUBLICACIONES = "lista-publicaciones";
    public static final String MENSAJE_TIPO_INVALIDO = "No se encontraron publicaciones con estos datos";
    private static final String VISTA_VER_DETALLE = "detalle-publicacion";
    private static final Integer PROPIEDAD_ID = 1;
    public static  DatosBusqueda datosBusqueda;
    private ControladorPublicacion controladorPublicacion;
    private ServicioPublicaciones servicioPublicaciones;

    private ServicioEmail servicioEmail;
    private DatosConsulta datosConsulta;


    @Before
    public void init(){
        datosBusqueda = mock(DatosBusqueda.class);
        datosConsulta=mock(DatosConsulta.class);
        servicioPublicaciones = mock(ServicioPublicaciones.class);
        servicioEmail=mock(ServicioEmail.class);
        controladorPublicacion = new ControladorPublicacion(servicioPublicaciones,servicioEmail);
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


    @Test
   public void alSeleccionarVerDetalleMeTraeLaVistaDetalle(){

        dadoQueExisteUnaPropiedad();

        ModelAndView mav = cuandoSeleccionoVerDetalle();

        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());
   }

     @Test
    public void alEnviarUnMailDeUnaPublicacionEnDetalleDeberiaEnviarMensajeDeExito(){
        dadoQueExisteUnaPropiedad();
        ModelAndView mav= CuandoQuiereEnviarElMail();
        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());
        entoncesSeRecibeMensajeExito("Mensaje_Enviado_Correctamente", mav.getModel());

    }

    @Test
    public void alEnviarUnMailDeunaPublicacionEnDetalleAUsuarioInactivoDeberiaLanzarError() throws UsuarioInexistente {
        dadoQueExisteUnaPropiedadConUsuarioInactivo();
        EnviarElMailLanzaExcepcion();
        ModelAndView mav= CuandoQuiereEnviarElMail();
        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());
        entoncesSeRecibeMensajeError("Propietario inexistente", mav.getModel());
    }

    private  void EnviarElMailLanzaExcepcion() throws UsuarioInexistente{
        when(servicioEmail.enviarConsultaPrivada( datosConsulta.getEmail(),
                datosConsulta.getTelefono(),
                datosConsulta.getMensaje(),
                PROPIEDAD_ID
        )).thenThrow( new UsuarioInexistente());
    }

    private void dadoQueExisteUnaPropiedadConUsuarioInactivo() {
       /* Usuario propietario= new Usuario();
        propietario.setActivo(false);*/
        Publicacion detalle = new Publicacion();
       /* detalle.getPropiedad().setPropietario(propietario);*/
        when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
    }

    private void dadoQueNoExistePublicaciones(){
        List<Publicacion> lista = new LinkedList<>();
        when(servicioPublicaciones.buscarPublicacion(datosBusqueda.getTipoAccion(),
                datosBusqueda.getTipoPropiedad(),
                datosBusqueda.getUbicacion()
        )).thenReturn(lista);
    }

    private void dadoQueExisteUnaPropiedad() {
       Publicacion detalle = new Publicacion();
       when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
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


    private ModelAndView CuandoQuiereEnviarElMail() {
        return controladorPublicacion.enviarConsulta(datosConsulta,PROPIEDAD_ID);
    }

    private ModelAndView cuandoSeleccionoVerDetalle() {
        return controladorPublicacion.verDetallePublicacion(PROPIEDAD_ID);
    }


    private ModelAndView cuandoBuscoUnaPublicacion(DatosBusqueda datosBusqueda) {
        return controladorPublicacion.buscar(datosBusqueda);
    }

    private void entoncesSeRecibeMensajeError(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg-error")).isEqualTo(mensaje);
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav, int cantidadEsperada) {
        List<Publicacion> lista = (List<Publicacion>) mav.getModel().get("publicaciones");
        assertThat(lista).hasSize(cantidadEsperada);
    }

    private void entoncesSeRecibeMensajeExito(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg")).isEqualTo(mensaje);
    }
}
