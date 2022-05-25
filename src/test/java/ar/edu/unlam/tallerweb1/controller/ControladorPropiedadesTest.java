package ar.edu.unlam.tallerweb1.controller;

import ar.edu.unlam.tallerweb1.controladores.Propiedad;
import ar.edu.unlam.tallerweb1.controladores.ControladorPropiedades;
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
    public static final String TIPO_INVALIDO = "FRUTA";
    public static final String MENSAJE_TIPO_INVALIDO = "ubicacion inexistente";
    private ControladorPropiedades controladorPropiedades;
    private ServicioPropiedades servicioPropiedades;

    @Before
    public void init(){
        servicioPropiedades = mock(ServicioPropiedades.class);
        controladorPropiedades = new ControladorPropiedades(servicioPropiedades);
    }

    @Test
    public void alPedirTodasLasBirrasDevuelveLaListaCompleta(){

        //Preparacion
        dadoQueTenemosUnaListaDePropiedades(10, "Ramos Mejia");

        //Ejecucion
        ModelAndView mav = cuandoBuscoUnaPropiedadDeLa("Ramos Mejia");

        entoncesEncuentro(mav, 10);
        entoncesMeLLevaALaVista(VISTA_LISTA_PROPIEDADES, mav.getViewName());
    }

    @Test
    public void alPedirUnTipoInvalidoLlevaAPantallaDeError(){
        when(servicioPropiedades.buscarPropiedadPorUbicacion(TIPO_INVALIDO)).thenThrow(new RuntimeException());

        ModelAndView mav = cuandoBuscoUnaPropiedadDeLa(TIPO_INVALIDO);

        entoncesMeLLevaALaVista(VISTA_LISTA_PROPIEDADES, mav.getViewName());
        entoncesSeRecibeMensaje(MENSAJE_TIPO_INVALIDO, mav.getModel());
    }

    private void dadoQueTenemosUnaListaDePropiedades(int cantidad, String ubicacion) {
        List<Propiedad> lista = new LinkedList<>();
        for(int i = 0 ; i < cantidad; i++){
            lista.add(new Propiedad(ubicacion));
        }
        when(servicioPropiedades.buscarPropiedadPorUbicacion(ubicacion)).thenReturn(lista);
    }

    private ModelAndView cuandoBuscoUnaPropiedadDeLa(String ubicacion) {
        return controladorPropiedades.listar(ubicacion);
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
