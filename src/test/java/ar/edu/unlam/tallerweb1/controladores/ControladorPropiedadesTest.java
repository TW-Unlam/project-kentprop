package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.PropiedadNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Detalle;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
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

public class ControladorPropiedadesTest {

    public static final String VISTA_LISTA_PROPIEDADES = "lista-propiedades";
    public static final String MENSAJE_TIPO_INVALIDO = "No se encontraron propiedades para esta ubicacion";
    private static final String VISTA_VER_DETALLE = "detalle-propiedad";

    private static final Integer PROPIEDAD_ID = 1;
    public static  DatosBusqueda datosBusqueda;
    private ControladorPropiedades controladorPropiedades;

    private ServicioPropiedades servicioPropiedades;
    private Detalle detallePropiedad;

    @Before
    public void init(){
        detallePropiedad = mock(Detalle.class);
        datosBusqueda = mock(DatosBusqueda.class);
        servicioPropiedades = mock(ServicioPropiedades.class);
        controladorPropiedades = new ControladorPropiedades(servicioPropiedades);
    }

    @Test
    public void alBuscarUnaUbicacionExistenteDeberiaDevolvermeLaListaDePropiedades() throws PropiedadNoEncontrada{

        //Preparacion

        dadoQueTenemosUnaListaDePropiedades(10);

        //Ejecucion
        ModelAndView mav = cuandoBuscoUnaPropiedadDeLa(datosBusqueda);

        entoncesEncuentro(mav, 10);
        entoncesMeLLevaALaVista(VISTA_LISTA_PROPIEDADES, mav.getViewName());
    }

    @Test
    public void alBuscarUnaUbicacionInexistenteDeberiaDevolvermeMensajeDeError() throws PropiedadNoEncontrada{
        when(servicioPropiedades.buscarPropiedadPorUbicacion(datosBusqueda)).thenThrow(new PropiedadNoEncontrada());

        ModelAndView mav = cuandoBuscoUnaPropiedadDeLa(datosBusqueda);

        entoncesMeLLevaALaVista(VISTA_LISTA_PROPIEDADES, mav.getViewName());
        entoncesSeRecibeMensaje(MENSAJE_TIPO_INVALIDO, mav.getModel());
   }

   @Test
   public void alSeleccionarVerDetalleMeTraeLaVistaDetalle(){

        dadoQueExisteUnaPropiedad();

        ModelAndView mav = cuandoSeleccionoVerDetalle();

        entoncesMeLLevaALaVista(VISTA_VER_DETALLE, mav.getViewName());

   }

    private ModelAndView cuandoSeleccionoVerDetalle() {

        return controladorPropiedades.verDetalle(PROPIEDAD_ID);
    }

    private void dadoQueExisteUnaPropiedad() {
       Propiedad propiedad = new Propiedad(PROPIEDAD_ID,detallePropiedad);
       when(servicioPropiedades.verDetallePropiedad(PROPIEDAD_ID)).thenReturn(propiedad);
    }

    private void dadoQueTenemosUnaListaDePropiedades(int cantidad) throws PropiedadNoEncontrada{
        datosBusqueda.setTipoPropiedad("Departamento");
        datosBusqueda.setTipoAccion("Alquilar");
        datosBusqueda.setUbicacion("Ramos");
        List<Propiedad> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++){
            lista.add(new Propiedad());
        }
        when(servicioPropiedades.buscarPropiedadPorUbicacion(datosBusqueda)).thenReturn(lista);
    }

    private ModelAndView cuandoBuscoUnaPropiedadDeLa(DatosBusqueda datosBusqueda) {
        return controladorPropiedades.buscar(datosBusqueda);
    }

    private void entoncesSeRecibeMensaje(String mensaje, Map<String, Object> model) {
        assertThat(model.get("msg-error")).isEqualTo(mensaje);
    }

    private void entoncesMeLLevaALaVista(String vistaEsperada, String vistaRecibida) {
        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }

    private void entoncesEncuentro(ModelAndView mav, int cantidadEsperada) {
        List<Propiedad> lista = (List<Propiedad>) mav.getModel().get("propiedades");
        assertThat(lista).hasSize(cantidadEsperada);
    }
}
