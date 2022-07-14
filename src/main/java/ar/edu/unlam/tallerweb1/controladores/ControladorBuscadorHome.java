package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ControladorBuscadorHome {

    private ServicioPublicaciones servicioPublicacion;

    @Autowired
    public ControladorBuscadorHome(ServicioPublicaciones servicioPublicacion) {
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        ModelMap modelo = new ModelMap();
        modelo.put("datosBusqueda", new DatosBusqueda());
        modelo.put("tipoPropiedades", TipoPropiedad.values());
        modelo.put("tipoAcciones", Accion.values());
        return new ModelAndView("home", modelo);
    }

    @RequestMapping(path = "/buscar-publicaciones", method = RequestMethod.POST)
    public ModelAndView buscar(@ModelAttribute("datosBusqueda") DatosBusqueda datosBusqueda) {
        ModelMap modelo = new ModelMap();

        List<Publicacion> publicaciones = realizarBusqueda(datosBusqueda);
        List<Publicacion> resultadoDestacadas = servicioPublicacion.obtenerPublicacionesDestacadas();
        modelo.put("destacadas", completarConImagenes(resultadoDestacadas));

        if (publicaciones.isEmpty()) {
            modelo.put("msg_error", "No se encontraron publicaciones con estos datos");
            return new ModelAndView("lista-publicaciones", modelo);
        }
        modelo.put("publicaciones", completarConImagenes(publicaciones));
        return new ModelAndView("lista-publicaciones", modelo);
    }

    private List<Publicacion> realizarBusqueda(DatosBusqueda datosBusqueda) {
        return servicioPublicacion.buscarPublicacion(datosBusqueda.getTipoAccion(),
                datosBusqueda.getTipoPropiedad(),
                datosBusqueda.getUbicacion());
    }

    @RequestMapping(path = "/lista-publicaciones")
    public ModelAndView irAListaDePublicaciones() {

        return new ModelAndView("lista-publicaciones");
    }

    private List<DatosPublicacion> completarConImagenes(List<Publicacion> publicaciones){
        List<DatosPublicacion> resultado = new LinkedList<>();
        for (Publicacion publicacionUni : publicaciones) {
            DatosPublicacion datos = new DatosPublicacion();
            datos.setPublicacion(publicacionUni);
            datos.setImagen(servicioPublicacion.traerImagenesPorId(publicacionUni.getId()).get(0));
            resultado.add(datos);
        }
        return resultado;
    }


}
