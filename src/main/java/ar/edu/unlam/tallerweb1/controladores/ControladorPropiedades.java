package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPropiedades {

    private ServicioPropiedades servicioPropiedades;

    @Autowired
    public ControladorPropiedades(ServicioPropiedades servicioPropiedades){
        this.servicioPropiedades = servicioPropiedades;
    }

    @RequestMapping(path="/listar-propiedades/{ubicacion}")
    public ModelAndView listar(@PathVariable("ubicacion") String ubicacion) {
        ModelMap model = new ModelMap();
        List<Propiedad> resultado = null;
        try{
            resultado = servicioPropiedades.buscarPropiedadPorUbicacion(ubicacion);
        } catch(Exception e) {
            model.put("msg-error", "ubicacion inexistente");
        }
        model.put("propiedades", resultado);
        return new ModelAndView("lista-propiedades", model);
    }

}
