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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service("ServicioEmail") @Transactional
public class ServicioEmailDefault implements ServicioEmail {
    private final RepositorioPublicaciones repositorioDePublicaciones;
    private final RepositorioUsuario repositorioDeUsuarios;


    private String PropietarioEmail = "sullcafernando18@gmail.com";
    private String MensajeIngresada = "Este Es un Email Informativo Sobre las Preguntas sobre su Publicaciones";
    private String EmailIngresado = "sullcafernando18@gmail.com";
    private Integer TelefonoIngresado;
    private String NombreIngresada;

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
//           sendMail();
//           enviarMailDeConsultaPrivadasEnPublicacion();
           return  propiedad.getPropietario();
//         return  repositorioDeUsuarios.obterneUsuario(propiedad.getPropietario().getId());
//         return  repositorioDePublicaciones.buscarPropietarioDeLaPropiedad(propiedadId);
       }
    }


    public void enviarMail(String mensaje,String asunto, String email) {
        String username="Kent-Propiedades";
        String password="Aa123456.";

        String emailOrg = "sullcafernando18";
        String passwordOrg = "37841788";
        Properties props =new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");//465
        props.put("mail.smtp.port", "465");//465
//        props.put("mail.smtp.user",username);
        props.put("mail.smtp.user", emailOrg);
//        props.put("mail.smtp.password",password);
        props.put("mail.smtp.password", passwordOrg);
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.mail.sender",username+"@gmail.com");
        props.put("mail.smtp.mail.sender", emailOrg+"@gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        try {
            Session session =Session.getDefaultInstance(props);
            MimeMessage msj=new MimeMessage(session);
//          msj.setFrom(new InternetAddress(username));
            msj.setFrom(new InternetAddress(emailOrg));
            msj.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            msj.setSubject(asunto);
            msj.setContent(mensaje, "text/html");
            Transport transport=session.getTransport("smtp");
            transport.connect("smtp.gmail.com",emailOrg,passwordOrg);
            transport.sendMessage(msj,msj.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new MailNoEnviado();
        }

    }

    @Override
    public void enviarMailDeConsultaPrivadasEnPublicacion(String emailConsultante, String nombreConsultante, Integer telefonoConsultante, String mensajeConsultante,String emailPropietario) {
        String asunto="Consulta Sobre Su publicacion en ";
        String mensaje= "<h2>¡Gracias Por Usar Nuestros Servicios. Tiene Nuevas Consultas!</h2>\n"
                + "<p>------------------------</p>\n"
                + "<h4>-"+mensajeConsultante+"-</h4><br>"
                + "<p>------------------------</p>\n"
                +"<p>Datos de Contacto del interesado</p><br>"
                +"<p>email: "+emailConsultante+"</p><br> <br>"
                +"<p>nombre: "+nombreConsultante+"</p><br> <br>"
                +"<p>telefono: "+telefonoConsultante+"</p><br> <br>"
                +"<p>: Puede volver a ver su publicacion desde el siguiente enlace</p><br> <br>"
//                +"<a href='http://localhost:8080/project-kenprop_war_exploded/detalle-de-publicacion?id="+propiedadId+" >IR A Publicacion</a>"
                +"<br>";
//        enviarMail(mensaje,asunto,this.PropietarioEmail);
        enviarMail(mensaje,asunto,"sullcafernando18@gmail.com");
    }


////////////////***************************************///////////////////////////

    /* Email email = EmailBuilder.startingBlank()
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
         m.sendMail(email);
     }
 */
    ////////////****************************/////////////////
}