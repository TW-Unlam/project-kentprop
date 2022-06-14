package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ControladorDetallePublicacion {

    private ServicioConsulta servicioConsultas;
    private ServicioPublicaciones servicioPublicaciones;

    @Autowired
    public ControladorDetallePublicacion(ServicioConsulta servicioConsulta, ServicioPublicaciones servicioPublicaciones) {
        this.servicioConsultas = servicioConsulta;
        this.servicioPublicaciones = servicioPublicaciones;
    }

    @RequestMapping(path = "/detalle-publicacion",method = RequestMethod.GET)
    public ModelAndView verDetallePublicacion(Integer id) {
        ModelMap modelo = new ModelMap();
        Publicacion publicaciones = null;
        List<Consulta> consultasHechas = null;

        publicaciones = servicioPublicaciones.verDetallePublicacion(id);
        consultasHechas = servicioConsultas.buscarConsultasDePublicacion(id);

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

    @RequestMapping(value = "/hacer-pregunta-publicacion", method = RequestMethod.POST)
    public ModelAndView hacerPregunta(Integer publicacionId, String descripcion){
        ModelMap modelo = new ModelMap();
        Boolean seHizo = false;

        Publicacion publicacion = servicioConsultas.buscarPublicacionPorId(publicacionId);
        seHizo = servicioConsultas.hacerPregunta(new Consulta(descripcion,publicacion));

        modelo.put("pregunta_hecha", seHizo);

        return new ModelAndView("redirect:/detalle-publicacion?id="+publicacionId,modelo);
    }
}
