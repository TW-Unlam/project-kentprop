package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
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

    private ServicioLogin servicioUsuario;

    @Autowired
    public ControladorDetallePublicacion(ServicioPregunta servicioPregunta, ServicioPublicaciones servicioPublicaciones, ServicioLogin servicioUsuario) {
        this.servicioConsultas = servicioPregunta;
        this.servicioPublicaciones = servicioPublicaciones;
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/detalle-publicacion",method = RequestMethod.GET)
    public ModelAndView verDetallePublicacion(Integer id) {
        ModelMap modelo = new ModelMap();
        Publicacion publicaciones = null;
        List<Pregunta> consultasHechas = null;
        List<Imagen> imagenes = null;

        publicaciones = servicioPublicaciones.verDetallePublicacion(id);
        consultasHechas = servicioConsultas.buscarConsultasDePublicacion(id);
        imagenes = servicioPublicaciones.traerImagenesPorId(id);

        modelo.put("imagenes", imagenes);
        modelo.put("datosPregunta", new DatosPregunta());

        if(consultasHechas.isEmpty()){
            modelo.put("msg_sin_preguntas","Por el momento no se realizaron preguntas ¡Sé el primero!");
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
    public ModelAndView hacerPregunta(@ModelAttribute("datosPregunta") DatosPregunta datosPregunta , HttpServletRequest request){
        ModelMap modelo = new ModelMap();
        Object usuarioId = request.getSession().getAttribute("id");

        if(usuarioId!=null) {

           Publicacion publicacion = servicioConsultas.buscarPublicacionPorId(datosPregunta.getPublicacionId());
           Usuario usuario = servicioUsuario.obterneUsuario((Integer) usuarioId);


            return new ModelAndView("redirect:/detalle-publicacion?id=" + datosPregunta.getPublicacionId(), modelo);
        }else{
            return new ModelAndView("redirect:/loginConId?id="+datosPregunta.getPublicacionId());
        }
    }
    /**/
    @RequestMapping(value = "responder-pregunta-publicacion", method = RequestMethod.POST)
    public ModelAndView responderPregunta(@ModelAttribute("datosPregunta") DatosPregunta datosPregunta){
            ModelMap modelo = new ModelMap();
            Pregunta preguntaAresponder=servicioConsultas.buscarLaPregunta(datosPregunta.getPublicacionId());
            preguntaAresponder.setRespuesta(datosPregunta.getDescripcion());
            servicioConsultas.responderPregunta(preguntaAresponder);
            return new ModelAndView("redirect:/detalle-publicacion?id=" + preguntaAresponder.getPublicacion().getId());
        }

    }
