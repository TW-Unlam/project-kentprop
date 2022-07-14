package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {


	private ServicioLogin servicioLogin;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin){
		this.servicioLogin = servicioLogin;
	}


	@RequestMapping("/loginConId")
	public ModelAndView irALoginConId(@RequestParam(value="id")Integer id) {
		ModelMap modelo = new ModelMap();
		modelo.put("datosLogin", new DatosLogin());
		modelo.put("idp", id);
		return new ModelAndView("login", modelo);
	}

	@RequestMapping("/login")
	public ModelAndView irALogin() {
		ModelMap modelo = new ModelMap();
		modelo.put("datosLogin", new DatosLogin());
		return new ModelAndView("login", modelo);
	}

	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
		if(usuarioBuscado != null && datosLogin.getIdPublicacion() != null){
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			return new ModelAndView("redirect:/detalle-publicacion?id="+datosLogin.getIdPublicacion());
		}

		if(usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			return new ModelAndView("redirect:/");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "logout")
	public ModelAndView cerrarSesion(HttpServletRequest request) {

		if (request.getSession().getAttribute("id") != null) {
			request.getSession().setAttribute("id", null);
			request.getSession().setAttribute("ROL", null);
		}

		return new ModelAndView("redirect:/");
	}
}
