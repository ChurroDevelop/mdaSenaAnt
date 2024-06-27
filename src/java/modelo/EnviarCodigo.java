package modelo;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.objetos.Usuario;

public class EnviarCodigo {
    
    public String getRandom(){
        Random r = new Random();
        int numero = r.nextInt(999999);
        return String.format("%06d", numero);
    }
    
    public static void enviarEmail(Usuario user) throws AddressException{
        try {
            // Correo de verificacion
            String destinatario = user.getCorreoInst(); // Correo destinatario
            String remitente = "mdasena00@outlook.com"; // Correo remitente
            
            // Datos del proveedor
            final String usuario = "mdasena00@outlook.com";
            final String password = "ijjbcdmrvpekpvtn";
            
            // Host para el envio del email
            final String host = "smtp-mail.outlook.com";
            
            // Configuracion para el envio del correo
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.auth", "true");
            propiedades.put("mail.smtp.starttls.enable", "true");
            propiedades.put("mail.smtp.host", host);
            propiedades.put("mail.smtp.port", "587");
            
            // Creador de la nueva session
            Session session = Session.getInstance(propiedades,
                    new Authenticator(){
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication(){
                            return new PasswordAuthentication(usuario, password);
                        }
                    });
            
            // Creacion del mensaje a enviar por Email
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            
            // Asuntos y cuerpo del correo
            mensaje.setSubject("Codigo de verificacion de la plataforma MdaSena");
            mensaje.setText("El codigo de verificacion es: " + user.getCodigo());
            
            // Enviar el menasje a email
            Transport.send(mensaje);
        } catch (Exception e) {
        }
        
        
        
    }
    
}
