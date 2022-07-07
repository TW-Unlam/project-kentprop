package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.excepciones.MailNoEnviado;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEmail;
import ar.edu.unlam.tallerweb1.servicios.ServicioPublicaciones;
//import org.simplejavamail.api.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
public class ControladorConsultas {

    private ServicioPublicaciones servicioPublicacion;
    private ServicioEmail servicioEmail;
    @Autowired
    public ControladorConsultas(ServicioPublicaciones servicioPublicacion, ServicioEmail servicioEmail) {
        this.servicioPublicacion = servicioPublicacion;
        this.servicioEmail = servicioEmail;
    }

    @RequestMapping(path = "/enviar-consulta",method = RequestMethod.GET)
    public ModelAndView irAFormConsulta(Integer propiedadId) {
        ModelMap modelo = new ModelMap();
        modelo.put("datosConsulta", new DatosConsulta());
        modelo.put("idPropiedad",propiedadId);
        return new ModelAndView("consulta-privada",modelo);
    }

    @RequestMapping(path = "/enviar-consulta-privada",method = RequestMethod.POST)
    public ModelAndView enviarConsulta(@ModelAttribute("datosConsulta") DatosConsulta datosConsulta) {
        ModelMap modelo = new ModelMap();
        Usuario resultado = null;
        try{
            resultado = servicioEmail.enviarConsultaPrivada(
                    datosConsulta.getEmail(),
                    datosConsulta.getNombre(),
                    datosConsulta.getTelefono(),
                    datosConsulta.getMensaje(),
                    datosConsulta.getPropiedadId());
        }catch(UsuarioInexistente u){
            modelo.put("msg-error", "Propietario inexistente .El-Mail , no fue posible ser enviado");
            return new ModelAndView("redirect:/detalle-publicacion?id="+datosConsulta.getPropiedadId(),modelo);
        }
        modelo.put("msg","Mensaje Enviado Exitosamente");
        modelo.put("usuario", resultado);
        return new ModelAndView("redirect:/detalle-publicacion?id="+datosConsulta.getPropiedadId(),modelo);
    }
}
