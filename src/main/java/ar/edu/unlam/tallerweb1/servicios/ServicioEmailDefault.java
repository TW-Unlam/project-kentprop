package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.MailerRegularBuilder;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioEmailDefault implements ServicioEmail {
    private RepositorioPublicaciones repositorioDePublicaciones;
    private RepositorioUsuario repositorioDeUsuarios;

    private final String passwordOrg = "";
    private final String organizacionEmail = "sullcafernando18@gmail.com";
    private String PropietarioEmail = "sullcafernando18@gmail.com";
    private String MensajeIngresada = "Este Es un Email Informativo Sobre las Preguntas sobre su Publicaciones";
    private String EmailIngresado = "sullcafernando18@gmail.com";
    private Integer TelefonoIngresado;
    private String NombreIngresada;
    Email email = EmailBuilder.startingBlank()
            .from("KentProp", organizacionEmail)
            .to("cliente", PropietarioEmail)
            .withSubject("Gracias por elegirnos")
            .withPlainText(MensajeIngresada)
            .buildEmail();

    @Autowired
    public ServicioEmailDefault(RepositorioPublicaciones repositorioPublicaciones, RepositorioUsuario repositorioUsuarios) {
        repositorioDePublicaciones=repositorioPublicaciones;
        repositorioDeUsuarios=repositorioUsuarios;
    }

    @Override
    @Transactional
    public Usuario enviarConsultaPrivada(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente {
        Propiedad propiedad=  repositorioDePublicaciones.buscarPropiedad(propiedadId);
       if(propiedad==null)
       {
           throw new UsuarioInexistente();
       }
       else{
           /////Operacionde mensajeria
           //String email, String nombre, Integer telefono, String mensaje propiedad.getPropietario().getEmail()
           //retorna los datos del propietario a moo de informacion extra
           this.EmailIngresado=email;
           this.NombreIngresada=nombre;
           this.TelefonoIngresado=telefono;
           this.MensajeIngresada=mensaje;
           this.PropietarioEmail=propiedad.getPropietario().getEmail();
//         senMail();
           return  propiedad.getPropietario();
//         return  repositorioDeUsuarios.obterneUsuario(propiedad.getPropietario().getId());
//         return  repositorioDePublicaciones.buscarPropietarioDeLaPropiedad(propiedadId);
       }
    }

    @Override
    public Mailer getmailer() {
        try {
            MailerRegularBuilder mb = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 465, organizacionEmail, passwordOrg)
                    .withTransportStrategy(TransportStrategy.SMTPS);
            Mailer mailer = mb.buildMailer();
            return mailer;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void sendMail() {
        Mailer m =  getmailer();
        m.sendMail(email);
    }

}
