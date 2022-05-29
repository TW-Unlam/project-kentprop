package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioPropiedades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPropiedades {

    private ServicioPropiedades servicioPropiedades;

    @Autowired
    public ControladorPropiedades(ServicioPropiedades servicioPropiedades){
        this.servicioPropiedades = servicioPropiedades;
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAHome()
    {
        ModelMap modelo = new ModelMap();
        modelo.put("datosBusqueda", new DatosBusqueda());
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path="/buscar-propiedades", method = RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute("datosBusqueda") DatosBusqueda datosBusqueda) {
        ModelMap modelo = new ModelMap();
        List<Propiedad> resultado = null;
        try{
            resultado = servicioPropiedades.buscarPropiedadPorUbicacion(datosBusqueda);
        } catch(Exception e) {
            modelo.put("msg-error", "ubicacion inexistente");
        }
        modelo.put("propiedades", resultado);
        return new ModelAndView("lista-propiedades", modelo);
    }
}
