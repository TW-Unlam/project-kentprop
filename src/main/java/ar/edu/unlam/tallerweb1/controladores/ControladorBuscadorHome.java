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
    public ControladorBuscadorHome(ServicioPublicaciones servicioPublicacion){
        this.servicioPublicacion = servicioPublicacion;
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
            modelo.put("msg_error", "No se encontraron publicaciones con estos datos");
        }else{
            modelo.put("publicaciones", resultado);
            List<Imagen> listaImagenes=new LinkedList<>();
            List<Imagen> listaImagenesDestacadas=new LinkedList<>();
            List<Imagen> imagenesBusqueda=null;
            List<Publicacion> resultadoDestacadas = null;

            resultadoDestacadas = servicioPublicacion.obtenerPublicacionesDestacadas();

            for (Publicacion publicacionUni :resultado)
            {
               imagenesBusqueda=servicioPublicacion.traerImagenesPorId(publicacionUni.getId());
//                System.out.println(imagenesBusqueda);

               if(imagenesBusqueda.size()>0){
//                   System.out.println(imagenesBusqueda.get(0));
                    listaImagenes.add(imagenesBusqueda.get(0));
                }

            }

            if(!resultadoDestacadas.isEmpty()){
                modelo.put("destacadas", resultadoDestacadas);
                for (Publicacion publicacionUni :resultadoDestacadas)
                {
                    imagenesBusqueda=servicioPublicacion.traerImagenesPorId(publicacionUni.getId());


                    if(imagenesBusqueda.size()>0){
                        listaImagenesDestacadas.add(imagenesBusqueda.get(0));
                    }

                }

            }

            modelo.put("listaDeImagenDePublicacionesDestacadas", listaImagenesDestacadas);
            modelo.put("listaDeImagenDePublicaciones",  listaImagenes);
        }
        return new ModelAndView("lista-publicaciones", modelo);
    }

    @RequestMapping(path = "/lista-publicaciones")
    public ModelAndView irAListaDePublicaciones() {

        return new ModelAndView("lista-publicaciones");
    }

    @RequestMapping(path = "/buscar-destacadas")
    public ModelAndView obtenerPublicacionesDestacadas(){
        ModelMap modelo = new ModelMap();
        List<Publicacion> resultado = null;

        resultado = servicioPublicacion.obtenerPublicacionesDestacadas();

        modelo.put("destacadas", resultado);
        return new ModelAndView("lista-publicaciones", modelo);
    }
}
