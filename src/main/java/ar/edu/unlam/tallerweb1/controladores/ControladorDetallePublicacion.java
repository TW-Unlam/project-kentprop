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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
        List<Imagen> imagenes = null;

        publicaciones = servicioPublicaciones.verDetallePublicacion(id);
        consultasHechas = servicioConsultas.buscarConsultasDePublicacion(id);
        imagenes = servicioPublicaciones.traerImagenesPorId(id);

        modelo.put("datosReserva", new DatosReserva());

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
           servicioConsultas.hacerPregunta(datosPregunta.getDescripcion(), datosPregunta.getPublicacionId(),(Integer) usuarioId);

            return new ModelAndView("redirect:/detalle-publicacion?id=" + datosPregunta.getPublicacionId(), modelo);
        }else{
            return new ModelAndView("redirect:/loginConId?id="+datosPregunta.getPublicacionId());
        }
    }
    /**/
    @RequestMapping(value = "responder-pregunta-publicacion", method = RequestMethod.POST)
    public ModelAndView responderPregunta(@ModelAttribute("datosPregunta") DatosPregunta datosPregunta){
            ModelMap modelo = new ModelMap();

            int idPublicacion=servicioConsultas.responderPregunta(datosPregunta.getPreguntaId(),datosPregunta.getDescripcion());

            return new ModelAndView("redirect:/detalle-publicacion?id=" +idPublicacion);
        }
    //Presentacion o negocio?

    @RequestMapping(value="marcar-como-favorito",method = RequestMethod.GET)
    public ModelAndView marcarComoFavorito(@RequestParam(value= "idPublicacion")Integer idPublicacion, HttpServletRequest request){

        ModelMap modelo = new ModelMap();

        Object usuarioId = request.getSession().getAttribute("id");
        if(usuarioId==null) {
            return new ModelAndView("redirect:/loginConId?id="+ idPublicacion);
        }
        servicioPublicaciones.indicarPublicacionFavorita(idPublicacion,(Integer)usuarioId);
    return new ModelAndView("redirect:/detalle-publicacion?id=" + idPublicacion);
    }

    @RequestMapping(value="crear-reserva",method = RequestMethod.POST)
    public ModelAndView crearReserva(@ModelAttribute("datosReserva") DatosReserva datosReserva){
        return new ModelAndView("redirect:/detalle-publicacion?id="+datosReserva.getIdPublicacion());
    }
}
