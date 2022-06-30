package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorConsultasTest {
    private static final String VISTA_VER_DETALLE = "redirect:/detalle-publicacion";
    private static final String VISTA_REDIRRECCION_VER_DETALLE = "redirect:/detalle-publicacion?id=0";
    private static final Integer PROPIEDAD_ID = 0;
    private DatosConsulta datosConsulta;
    private ControladorConsultas controladorConsultas;
    private ServicioPublicaciones servicioPublicaciones;
    private ServicioEmail servicioEmail;

    @Before
    public void init(){
        datosConsulta=mock(DatosConsulta.class);
        servicioPublicaciones = mock(ServicioPublicaciones.class);
        servicioEmail=mock(ServicioEmail.class);
//      controladorPublicacion = new ControladorPublicacion(servicioPublicaciones,servicioEmail);
        controladorConsultas = new ControladorConsultas(servicioPublicaciones,servicioEmail);
    }

    @Test
    public void alEnviarUnMailDeUnaPublicacionEnDetalleDeberiaEnviarMensajeDeExito() throws UsuarioInexistente {
        dadoQueExisteUnaPropiedad();
        dadoquetengoPropietarioRegistrado();
        ModelAndView mav= CuandoQuiereEnviarElMail();
        entoncesMeLLevaALaVista(VISTA_REDIRRECCION_VER_DETALLE, mav.getViewName());
        entoncesSeRecibeMensajeExito("Mensaje Enviado Exitosamente", mav.getModel());

    }

    private void dadoquetengoPropietarioRegistrado() throws UsuarioInexistente {
        Usuario propietario=new Usuario();
        when(servicioEmail.enviarConsultaPrivada(datosConsulta.getEmail(),datosConsulta.getNombre(),datosConsulta.getTelefono(), datosConsulta.getMensaje(), datosConsulta.getPropiedadId())).thenReturn(propietario);}

//    @Test(expected =UsuarioInexistente.class)

//@Test(expected = UsuarioInexistente.class)
@Test
        public void alEnviarUnMailDeunaPublicacionEnDetalleAUsuarioInactivoDeberiaLanzarError() throws UsuarioInexistente {
        dadoQueExisteUnaPropiedadConUsuarioInactivo();
        EnviarElMailLanzaExcepcion();
        ModelAndView mav= CuandoQuiereEnviarElMail();
        entoncesMeLLevaALaVista(VISTA_REDIRRECCION_VER_DETALLE, mav.getViewName());
        entoncesSeRecibeMensajeError("Propietario inexistente", mav.getModel());
    }
    private void entoncesSeRecibeMensajeExito(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg")).isEqualTo(mensaje);
    }
    private void dadoQueExisteUnaPropiedad() {
        Publicacion detalle = new Publicacion();
        when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
    }

    private void dadoQueExisteUnaPropiedadConUsuarioInactivo() {
//        Usuario propietario= new Usuario();
//        propietario.setActivo(false);
//        Publicacion detalle = new Publicacion();
//        //detalle.getPropiedad().setPropietario(propietario);
//        when(servicioPublicaciones.verDetallePublicacion(PROPIEDAD_ID)).thenReturn(detalle);
        when(datosConsulta.getPropiedadId()).thenReturn(PROPIEDAD_ID);
    }

    private  void EnviarElMailLanzaExcepcion() throws UsuarioInexistente{

        when(servicioEmail.enviarConsultaPrivada( datosConsulta.getEmail(),
                datosConsulta.getNombre(), datosConsulta.getTelefono(),
                datosConsulta.getMensaje(),
                PROPIEDAD_ID
        )).thenThrow( new UsuarioInexistente());
    }

    private ModelAndView CuandoQuiereEnviarElMail() {

        return controladorConsultas.enviarConsulta(datosConsulta);
    }
    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }

    private void entoncesSeRecibeMensajeError(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg-error")).isEqualTo(mensaje);
    }
}
