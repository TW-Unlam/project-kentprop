package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ControladorConsulta {

    private ServicioConsulta servicioConsultas;

    @Autowired
    public ControladorConsulta(ServicioConsulta servicioConsulta) {
        this.servicioConsultas = servicioConsulta;
    }

    public ModelAndView cargarPreguntasDeUnaPublicacion(Integer publicacionId){
        ModelMap modelo = new ModelMap();
        List<Consulta> resultado = null;

        resultado = servicioConsultas.buscarConsultasDePublicacion(publicacionId);

        if(resultado.isEmpty()){
            modelo.put("msg-vacio","Todavia no hay preguntas hechas, se el primero en hacer una!");
        }else{
            modelo.put("preguntas",resultado);
        }
        return new ModelAndView("detalle-publicacion",modelo);
    }

    public ModelAndView hacerPregunta(String descripcion, Integer publicacionId, Integer usuarioId){
        ModelMap modelo = new ModelMap();
        Consulta resultado = null;

        Usuario usuario = servicioConsultas.buscarUsuarioPorId(usuarioId);
        Publicacion publicacion = servicioConsultas.buscarPublicacionPorId(publicacionId);
        servicioConsultas.hacerPregunta(new Consulta(descripcion,usuario,publicacion));
        modelo.put("preguntas",resultado);
        return new ModelAndView("detalle-publicacion?publicacionId");
    }

}
