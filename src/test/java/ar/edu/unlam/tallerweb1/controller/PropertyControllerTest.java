package ar.edu.unlam.tallerweb1.controller;

import ar.edu.unlam.tallerweb1.controladores.Property;
import ar.edu.unlam.tallerweb1.controladores.PropertyController;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import static org.assertj.core.api.Assertions.*;


public class PropertyControllerTest {
  public static final String UBICACION = "Argentina";
  private static final Object VISTA_LISTADO_PROPIEDADES = "lista-de-propiedades" ;

  @Test
  public void alBuscarUnaUbicacionDevuelveListaDePropiedadesQueCoinciden (){
    dadoQueTenemosUnaListaDePropiedades();

    // ejecucion
    ModelAndView mav = cuandoBuscoPropiedadesPorUbicacion(UBICACION);

    // validacion
    entoncesEncuentro((List<Property>) mav.getModel().get("propiedades"));
    entoncesMeLLevaALaVista(VISTA_LISTADO_PROPIEDADES, mav.getViewName());
  }

  private void entoncesMeLLevaALaVista(Object vistaListadoPropiedades, String viewName) {
    assertThat(viewName).isEqualTo(vistaListadoPropiedades);
  }

  private void entoncesEncuentro(List<Property> listaPropiedades) {
    assertThat(listaPropiedades).hasSize(1);
  }

  private ModelAndView cuandoBuscoPropiedadesPorUbicacion(String ubicacion) {
    // agregamos el static, checkear porque!!!
    return PropertyController.listar(ubicacion);
  }

  private void dadoQueTenemosUnaListaDePropiedades() {
    PropertyController propertyController = new PropertyController();
  }
}
