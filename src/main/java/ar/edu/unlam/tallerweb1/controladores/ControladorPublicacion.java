package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPublicacion {

    private ServicioPublicaciones servicioPublicacion;
    private ServicioEmail servicioEmail;

    @Autowired
    public ControladorPublicacion(ServicioPublicaciones servicioPublicacion, ServicioEmail servicioEmail){
        this.servicioPublicacion = servicioPublicacion;
        this.servicioEmail=servicioEmail;
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
        try{
            resultado = servicioPublicacion.buscarPublicacion(datosBusqueda.getTipoAccion(),
                    datosBusqueda.getTipoPropiedad(),
                    datosBusqueda.getUbicacion());
        } catch(Exception e) {
            modelo.put("msg_error", "No se encontraron publicaciones con estos datos");
        }
        modelo.put("publicaciones", resultado);

        return new ModelAndView("lista-publicaciones", modelo);
    }
    @RequestMapping(path = "/detalle-publicacion",method = RequestMethod.GET)
    public ModelAndView verDetallePublicacion(Integer id) {
        ModelMap modelo = new ModelMap();
        Publicacion resultado = null;
        try{
            resultado = servicioPublicacion.verDetallePublicacion(id);
        }catch(Exception e){
            modelo.put("msg-error", "Pagina inexistente");
        }
     /*   modelo.put("datosConsulta", new DatosConsulta());*/
        modelo.put("detalle", resultado);
        return new ModelAndView("detalle-publicacion", modelo);
    }

    @RequestMapping(path = "/lista-publicaciones")
    public ModelAndView irAListaDePublicaciones() {

        return new ModelAndView("lista-publicaciones");
    }

}
