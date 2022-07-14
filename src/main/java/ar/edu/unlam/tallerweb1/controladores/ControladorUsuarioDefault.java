package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.servicios.ServicioPregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorUsuarioDefault {

    private ServicioPregunta servicioPregunta;

    @Autowired
    public ControladorUsuarioDefault(ServicioPregunta servicioPregunta){
        this.servicioPregunta= servicioPregunta;
    }

    @RequestMapping(path="/mis-preguntas", method = RequestMethod.GET)
    public ModelAndView verPreguntasDelUsuario( HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        Integer usuarioId = (Integer) request.getSession().getAttribute("id");

        if(usuarioId ==null) {
            return new ModelAndView("redirect:/login");
        }

        if(!request.getSession().getAttribute("ROL").equals("USUARIO")) {
            return new ModelAndView("redirect:/");
        }

        List<Pregunta> preguntasDelUsuario = null;

        preguntasDelUsuario = servicioPregunta.buscarPreguntasPorIdDeUsuario(usuarioId);

        if(preguntasDelUsuario.isEmpty()){
            modelo.put("msg_vacio", "Todavía no has hecho ninguna pregunta...");
        } else {
            modelo.put("preguntasRealizadas", preguntasDelUsuario);
        }

        return new ModelAndView("mis-preguntas", modelo);
    }
}
