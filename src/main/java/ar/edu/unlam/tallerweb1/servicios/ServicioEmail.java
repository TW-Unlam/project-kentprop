package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.simplejavamail.api.mailer.Mailer;
import org.springframework.stereotype.Service;

@Service
public interface ServicioEmail {

	Usuario enviarConsultaPrivada(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente;

	public void senMail();

	public Mailer getmailer();
}
