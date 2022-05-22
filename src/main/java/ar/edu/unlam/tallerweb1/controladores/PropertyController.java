package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PropertyController {
  private List<Property> lista = new LinkedList<>();

  public PropertyController() {
    lista.add(new Property(1,"Laprida"));
    lista.add(new Property(2,"Argentina"));
    lista.add(new Property(3,"Victoriano Loza"));
  }

  public ModelAndView listar(String ubicacion) {
    ModelMap model = new ModelMap();

    List<Property> resultado = buscarPropiedadPorUbicacion(ubicacion);

    if(resultado.isEmpty()){
      model.put("msg-error", "No hay propiedades en esa ubicación");
    } else {
      model.put("propiedades", resultado);
    }
    return new ModelAndView("lista-de-propiedades", model);
  }

  private List<Property> buscarPropiedadPorUbicacion(String ubicacion) {
    List<Property> resultado = new ArrayList<>();
    for(Property each: lista){
      if(each.getUbicacion().equals(ubicacion))
        resultado.add(each);
    }
    return resultado;
  }
}
