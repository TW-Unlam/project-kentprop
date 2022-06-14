package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPublicacion {

    private ServicioPublicaciones servicioPublicacion;
    private ServicioEmail servicioEmail;

    private ServicioConsulta servicioConsultas;

    @Autowired
    public ControladorPublicacion(ServicioPublicaciones servicioPublicacion, ServicioEmail servicioEmail, ServicioConsulta servicioConsulta){
        this.servicioPublicacion = servicioPublicacion;
        this.servicioEmail=servicioEmail;
        this.servicioConsultas = servicioConsulta;
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAHome()
    {
        ModelMap modelo = new ModelMap();
        modelo.put("datosBusqueda", new DatosBusqueda());
        modelo.put("tipoPropiedades", TipoPropiedad.values());
        modelo.put("tipoAcciones", Accion.values());
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path="/buscar-publicaciones", method = RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute("datosBusqueda") DatosBusqueda datosBusqueda) {
        ModelMap modelo = new ModelMap();
        List<Publicacion> resultado = null;
        resultado = servicioPublicacion.buscarPublicacion(datosBusqueda.getTipoAccion(),
                    datosBusqueda.getTipoPropiedad(),
                    datosBusqueda.getUbicacion());
        if(resultado.isEmpty()){
            modelo.put("msg-error", "No se encontraron publicaciones con estos datos");
        }else{
            modelo.put("publicaciones", resultado);
        }
        return new ModelAndView("lista-publicaciones", modelo);
    }
    @RequestMapping(path = "/detalle-publicacion",method = RequestMethod.GET)
    public ModelAndView verDetallePublicacion(Integer id) {
        ModelMap modelo = new ModelMap();
        Publicacion publicaciones = null;
        List<Consulta> consultasHechas = null;

        publicaciones = servicioPublicacion.verDetallePublicacion(id);
        consultasHechas = servicioConsultas.buscarConsultasDePublicacion(id);


        if(publicaciones != null){
            modelo.put("detalle", publicaciones);
        }else{
            modelo.put("msg-error", "Pagina inexistente");
        }
        return new ModelAndView("detalle-publicacion", modelo);
    }

    @RequestMapping(value = "/hacer-pregunta-publicacion/", method = RequestMethod.GET)
    public ModelAndView hacerPregunta(@RequestParam("publicacionId") Integer publicacionId){
        Integer publicacionId2 = 2;
        ModelMap modelo = new ModelMap();
        Consulta resultado = null;

        //Usuario usuario = servicioConsultas.buscarUsuarioPorId(usuarioId);
        //Publicacion publicacion = servicioConsultas.buscarPublicacionPorId(publicacionId);
        //servicioConsultas.hacerPregunta(new Consulta(descripcion,usuario,publicacion));

        modelo.put("preguntas","Hola");
        return new ModelAndView("redirect:/detalle-publicacion?id=publicacionId2",modelo);
    }

    @RequestMapping(path = "/lista-publicaciones")
    public ModelAndView irAListaDePublicaciones() {

        return new ModelAndView("lista-publicaciones");
    }
    @RequestMapping(path = "/enviar-consulta-privada",method = RequestMethod.POST)
    public ModelAndView enviarConsulta(DatosConsultaPrivada datosConsultaPrivada, Integer propiedadId) {
        ModelMap modelo = new ModelMap();
        Usuario resultado = null;
        try{
            resultado = servicioEmail.enviarConsultaPrivada(
                    datosConsultaPrivada.getEmail(),
                    datosConsultaPrivada.getTelefono(),
                    datosConsultaPrivada.getMensaje(),
                    propiedadId);
        }catch(Exception e){
            modelo.put("msg-error", "Propietario inexistente");
        }
        modelo.put("msg", "Mensaje_Enviado_Correctamente");
        modelo.put("usuario", resultado);

        return new ModelAndView("detalle-publicacion",modelo);
    }
}
