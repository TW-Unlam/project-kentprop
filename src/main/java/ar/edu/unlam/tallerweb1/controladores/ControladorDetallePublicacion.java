package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioPregunta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Controller
public class ControladorDetallePublicacion {

    private ServicioPregunta servicioConsultas;
    private ServicioPublicaciones servicioPublicaciones;

    @Autowired
    public ControladorDetallePublicacion(ServicioPregunta servicioPregunta, ServicioPublicaciones servicioPublicaciones) {
        this.servicioConsultas = servicioPregunta;
        this.servicioPublicaciones = servicioPublicaciones;
    }

    @RequestMapping(path = "/detalle-publicacion",method = RequestMethod.GET)
    public ModelAndView verDetallePublicacion(Integer id) {
        ModelMap modelo = new ModelMap();
        Publicacion publicaciones = null;
        List<Pregunta> consultasHechas = null;

        publicaciones = servicioPublicaciones.verDetallePublicacion(id);
        consultasHechas = servicioConsultas.buscarConsultasDePublicacion(id);

        modelo.put("datosPregunta", new DatosPregunta());

        if(consultasHechas.isEmpty()){
            modelo.put("msg_vacio","Todavia no hay preguntas hechas, se el primero en hacer una!");
        }else{
            modelo.put("preguntas_hechas",consultasHechas);
        }
        if(publicaciones != null){
            modelo.put("detalle", publicaciones);
        }else{
            modelo.put("msg_error", "Pagina inexistente");
        }
        return new ModelAndView("detalle-publicacion", modelo);
    }

    @RequestMapping(value = "hacer-pregunta-publicacion", method = RequestMethod.POST)
    public ModelAndView hacerPregunta(@ModelAttribute("datosPregunta") DatosPregunta datosPregunta ,
                                      HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        if(request.getSession().getAttribute("id")!=null) {
            Boolean seHizo = false;

            Publicacion publicacion = servicioConsultas.buscarPublicacionPorId(datosPregunta.getId());
            seHizo = servicioConsultas.hacerPregunta(new Pregunta(datosPregunta.getDescripcion(), publicacion));

            modelo.put("pregunta_hecha", seHizo);

            return new ModelAndView("redirect:/detalle-publicacion?id=" + datosPregunta.getId(), modelo);
        }
        return new ModelAndView("redirect:/loginConId?id="+datosPregunta.getId());
    }
}
