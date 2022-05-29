package ar.edu.unlam.tallerweb1.controladores;

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
    public static  DatosBusqueda datosBusqueda;
    private ControladorPropiedades controladorPropiedades;

    private ServicioPropiedades servicioPropiedades;

    @Before
    public void init(){
        datosBusqueda = new DatosBusqueda();
        servicioPropiedades = mock(ServicioPropiedades.class);
        controladorPropiedades = new ControladorPropiedades(servicioPropiedades);
    }

    @Test
    public void alBuscarUnaUbicacionExistenteDeberiaDevolvermeLaListaDePropiedades(){

        //Preparacion
        datosBusqueda.setTipoPropiedad("Departamento");
        datosBusqueda.setTipoAccion("Alquilar");
        datosBusqueda.setUbicacion("Ramos");
        dadoQueTenemosUnaListaDePropiedades(10);

        //Ejecucion
        ModelAndView mav = cuandoBuscoUnaPropiedadDeLa(datosBusqueda);

        entoncesEncuentro(mav, 10);
        entoncesMeLLevaALaVista(VISTA_LISTA_PROPIEDADES, mav.getViewName());
    }

    @Test
    public void alBuscarUnaUbicacionInexistenteDeberiaDevolvermeMensajeDeError(){
        when(servicioPropiedades.buscarPropiedadPorUbicacion(datosBusqueda)).thenThrow(new RuntimeException());

        ModelAndView mav = cuandoBuscoUnaPropiedadDeLa(datosBusqueda);

        entoncesMeLLevaALaVista(VISTA_LISTA_PROPIEDADES, mav.getViewName());
        entoncesSeRecibeMensaje(MENSAJE_TIPO_INVALIDO, mav.getModel());
   }

    private void dadoQueTenemosUnaListaDePropiedades(int cantidad) {
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
