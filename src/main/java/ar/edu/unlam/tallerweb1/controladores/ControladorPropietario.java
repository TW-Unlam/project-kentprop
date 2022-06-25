package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Imagen;
import ar.edu.unlam.tallerweb1.modelo.Pregunta;
import ar.edu.unlam.tallerweb1.modelo.Publicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPropietario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ControladorPropietario {

    private ServicioPublicaciones servicioPublicaciones;
    private ServicioPropietario servicioPropietario;

    @Autowired
     public ControladorPropietario(ServicioPublicaciones servicioPubli1caciones, ServicioPropietario servicioPropietario) {
        this.servicioPublicaciones = servicioPubli1caciones;
        this.servicioPropietario = servicioPropietario;
    }

    @RequestMapping(path = "/mis-publicaciones",method = RequestMethod.GET)
    public ModelAndView verPublicacionDelPropietario(Long id, HttpServletRequest request) {
        ModelMap modelo = new ModelMap();
        System.out.println(request.getSession().getAttribute("id"));
        System.out.println(request.getSession().getAttribute("ROL"));
        if(request.getSession().getAttribute("id")==null) {
            return new ModelAndView("redirect:/login");
        }

        if(!request.getSession().getAttribute("ROL").equals("Propietario")) {
            return new ModelAndView("redirect:/");
        }

        List<Publicacion> publicaciones=null;
        publicaciones= servicioPropietario.obtenePublicacionesDelPropietario((Long) request.getSession().getAttribute("id"));
        System.out.println(publicaciones);
        if(publicaciones.isEmpty()){
            modelo.put("msg_vacio","No tiene Propiedades publicadas...");
        }else{
            modelo.put("listaDepublicaciones",publicaciones);
            List<Imagen> listaImagenes=new LinkedList<>();
            List<Imagen> imagenesBusqueda=null;
            for (Publicacion publicacionUni :publicaciones)
            {System.out.println(publicacionUni.getId());
                imagenesBusqueda=servicioPublicaciones.traerImagenesPorId(publicacionUni.getId());
                if(imagenesBusqueda.size()>0){
                    listaImagenes.add(imagenesBusqueda.get(0));
                    System.out.println(imagenesBusqueda.get(0).getUrlImagen());
                }else {
                    Imagen tmp=new Imagen();
                    tmp.setUrlImagen("images/PropiedadDefault.jpg");
                    tmp.setPublicacion(publicacionUni);
                    listaImagenes.add(tmp);
                }
            }
            System.out.println(listaImagenes);
            modelo.put("listaDeImagenesDePublicaciones",  listaImagenes);
        }
        return new ModelAndView("mis-publicaciones", modelo);
    }
}
