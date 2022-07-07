package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.excepciones.MailNoEnviado;
import ar.edu.unlam.tallerweb1.excepciones.UsuarioInexistente;
import ar.edu.unlam.tallerweb1.modelo.Propiedad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPublicaciones;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

////Imports del servicio de Email
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
////Imports del servicio de Email
import java.time.LocalDate;

@Service("ServicioEmail")
@Transactional
public class ServicioEmailDefault implements ServicioEmail {
    private final RepositorioPublicaciones repositorioDePublicaciones;
    private final RepositorioUsuario repositorioDeUsuarios;

    private String MensajeIngresada = "Este Es un Email Informativo Sobre las Preguntas sobre su Publicaciones";

    @Autowired
    public ServicioEmailDefault(RepositorioPublicaciones repositorioPublicaciones, RepositorioUsuario repositorioUsuarios) {
        repositorioDePublicaciones=repositorioPublicaciones;
        repositorioDeUsuarios=repositorioUsuarios;
    }

    @Override
    public Usuario enviarConsultaPrivada(String email, String nombre, Integer telefono, String mensaje, Integer propiedadId) throws UsuarioInexistente {
        Propiedad propiedad=  repositorioDePublicaciones.buscarPropiedad(propiedadId);
       if(propiedad==null)
       {
           throw new UsuarioInexistente();
       }
       else{
           /////Operacionde mensajeria
           //String emailDelPropietarioreceptor, String nombre, Integer telefono, String mensajeConsultaArmado propiedad.getPropietario().getEmail()
           //retorna los datos del propietario a moo de informacion extra
           enviarMailDeConsultaPrivadasEnPublicacion(email,nombre,telefono,mensaje,propiedad.getPropietario().getEmail());
           return  propiedad.getPropietario();
//         return  repositorioDeUsuarios.obterneUsuario(propiedad.getPropietario().getId());
//         return  repositorioDePublicaciones.buscarPropietarioDeLaPropiedad(propiedadId);
       }
    }

    @Override
    public void enviarMailDeConsultaPrivadasEnPublicacion(String emailConsultante, String nombreConsultante, Integer telefonoConsultante, String mensajeConsultante,String emailPropietario) {
        String asuntoParaELConsultanteArmada="Consulta Sobre Su publicacion en ";
        String mensajeConsultaArmado= "<h2>¡Gracias Por Usar Nuestros Servicios. Tiene Nuevas Consultas!</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>-"+mensajeConsultante+"-</h4><br>"
                + "<p>------------------------</p>\n"
                +"<p>Datos de Contacto del interesado</p><br>"
                +"<p>email : "+emailConsultante+"</p> <br>"
                +"<p>nombre: "+nombreConsultante+"</p> <br>"
                +"<p>telefono: "+telefonoConsultante+"</p> <br>"
                +"<p>: Puede volver a ver su publicacion desde el siguiente enlace</p> <br>"
                +"<a href='http://localhost:8080/project_kentprop_war_exploded/mis-publicaciones'>IR A Mis Publicaciones</a>"
                +"<br>";
        enviarMail(mensajeConsultaArmado,asuntoParaELConsultanteArmada,emailPropietario);
//        enviarMail(mensaje,asunto,"sullcafernando18@gmail.com");
    }

    public void enviarMail(String mensajeConsultaArmado, String asuntoParaELConsultanteArmada, String emailDelPropietarioreceptor) {
        String username="Kent-Propiedades";
        String password="37841788";

        String emailOrg = "sullcafernando18";
        String passwordOrg = "hvqsnyjfcoodywai";
        Properties props =new Properties();
        /////////Verificar si era esto en test inicial/////
         props.put("mail.smtp.ssl.protocols", "TLSv1.3 TLSv1.2");
        // props.setProperty("mail.smtp.debug","true");
        /////////////////
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");//587///465
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        ////////////////
        Session session = Session.getInstance(props, new Authenticator() {
                   @Override
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(emailOrg+"@gmail.com", passwordOrg);
                   }
        });

     /*   props.put("mail.smtp.user", emailOrg);
//      props.put("mail.smtp.password",password);
        props.put("mail.smtp.password", passwordOrg);
//       props.put("mail.smtp.mail.sender",username+"@gmail.com");
        props.put("mail.smtp.mail.sender", emailOrg+"@gmail.com");
*/

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailOrg+"@gmail.com"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(emailDelPropietarioreceptor));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setSubject(asuntoParaELConsultanteArmada);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        try {
            mimeBodyPart.setContent(mensajeConsultaArmado, "text/html; charset=utf-8");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        Multipart multipart = new MimeMultipart();
        try {
            multipart.addBodyPart(mimeBodyPart);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            message.setContent(multipart);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

////////////////***************************************///////////////////////////

    /* Email emailDelPropietarioreceptor = EmailBuilder.startingBlank()
             .from("KentProp", organizacionEmail)
             .to("cliente", PropietarioEmail)
             .withSubject("Gracias por elegirnos")
             .withPlainText(MensajeIngresada)
             .buildEmail();

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
         m.sendMail(emailDelPropietarioreceptor);
     }
 */
    ////////////****************************/////////////////
}