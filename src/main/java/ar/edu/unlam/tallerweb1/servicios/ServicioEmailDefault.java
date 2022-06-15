package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEmailDefault implements ServicioEmail {
    private RepositorioPublicaciones repositorioDePublicaciones;
    private RepositorioUsuario repositorioDeUsuarios;

    @Autowired
    public ServicioEmailDefault(RepositorioPublicaciones repositorioPublicaciones, RepositorioUsuario repositorioUsuarios) {
        repositorioDePublicaciones=repositorioPublicaciones;
        repositorioDeUsuarios=repositorioUsuarios;
    }

    @Override
    @Transactional
    public Usuario enviarConsultaPrivada(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente {
        Propiedad propiedad=  repositorioDePublicaciones.buscarPropiedadConPropietario(propiedadId);
       if(propiedad==null)
       {
           throw new UsuarioInexistente();
       }
       else{
           /////Operacionde mensajeria
           //String email, String nombre, Integer telefono, String mensaje propiedad.getPropietario().getEmail()
           //retorna los datos del propietario a moo de informacion extra
           /*return  repositorioDeUsuarios.obterneUsuario(propiedad.getPropietario().getId());*/
           return  propiedad.getPropietario();
       }
    }
}
