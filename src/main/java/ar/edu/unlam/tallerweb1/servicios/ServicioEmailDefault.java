package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

@Service
public class ServicioEmailDefault implements ServicioEmail {
    private RepositorioPublicaciones repositorioDePublicaciones;
    private RepositorioUsuario repositorioDeUsuarios;
    @Override
    public Usuario enviarConsultaPrivada(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente {
        Propiedad propiedad= repositorioDePublicaciones.buscarPrpiedadConPropietario(propiedadId);

       if(propiedad==null)
       {
           return null;
       }
       else{
           /////Operacionde mensajeria
           //String email, String nombre, Integer telefono, String mensaje propiedad.getPropietario().getEmail()
           //retorna los datos del propietario a moo de informacion extra
           return  propiedad.getPropietario();
       }
    }
}
