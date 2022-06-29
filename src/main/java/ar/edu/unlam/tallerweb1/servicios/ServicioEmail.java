package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface ServicioEmail {

	Usuario enviarConsultaPrivada(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente;

	void enviarMail(String mensaje,String asunto, String email);
	void enviarMailDeConsultaPrivadasEnPublicacion(String emailConsultante, String nombreConsultante, Integer telefonoConsultante, String mensajeConsultante,String emailPropietario);

	/*public void sendMail();

	public Mailer getmailer();*/
}
