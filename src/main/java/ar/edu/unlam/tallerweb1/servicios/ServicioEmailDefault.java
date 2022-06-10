package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ServicioEmailDefault implements ServicioEmail {

    @Override
    public Usuario enviarConsultaPrivada(String email, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente {
        return null;
    }
}
