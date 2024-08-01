package modelo;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.objetos.Usuario;

/**
 * Clase para enviar códigos de verificación por correo electrónico.
 */
public class EnviarCodigo {

    /**
     * Genera un código de verificación aleatorio de 6 dígitos.
     *
     * @return El código de verificación en formato de 6 dígitos.
     */
    public String getRandom() {
        Random r = new Random();
        int numero = r.nextInt(999999);
        return String.format("%06d", numero);
    }

    /**
     * Envía un correo electrónico con el código de verificación al usuario.
     *
     * @param user El objeto Usuario que contiene el correo y el código de
     * verificación.
     * @throws AddressException Si la dirección de correo es inválida.
     */
    public static void enviarEmail(Usuario user) throws AddressException {
        try {
            // Correo destinatario
            String destinatario = user.getCorreoInst();
            // Correo remitente
            String remitente = "mdasena00@outlook.com";

            // Credenciales del correo remitente
            final String usuario = "mdasena00@outlook.com";
            final String password = "ijjbcdmrvpekpvtn";

            // Host para el envío del email
            final String host = "smtp-mail.outlook.com";

            // Configuración para el envío del correo
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.auth", "true");
            propiedades.put("mail.smtp.starttls.enable", "true");
            propiedades.put("mail.smtp.host", host);
            propiedades.put("mail.smtp.port", "587");

            // Creador de la nueva sesión
            Session session = Session.getInstance(propiedades, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, password);
                }
            });

            // Creación del mensaje a enviar por email
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            // Asunto y cuerpo del correo
            mensaje.setSubject("Código de verificación de la plataforma MdaSena");
            mensaje.setText("El código de verificación es: " + user.getCodigo());

            // Enviar el mensaje a email
            Transport.send(mensaje);
        } catch (Exception e) {
            System.out.println("Error mandando email: " + e.getMessage());
        }
    }
}
