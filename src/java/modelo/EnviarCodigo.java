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
 * Clase para gestionar el envío de códigos de verificación por correo electrónico.
 */
public class EnviarCodigo {

    /**
     * Genera un código de verificación aleatorio de 6 dígitos.
     * 
     * @return El código de verificación en formato de 6 dígitos, como una cadena.
     */
    public String getRandom() {
        // Crea una instancia de Random para generar un número aleatorio
        Random r = new Random();
        // Genera un número aleatorio entre 0 y 999999
        int numero = r.nextInt(999999);
        // Formatea el número para que siempre tenga 6 dígitos, agregando ceros a la izquierda si es necesario
        return String.format("%06d", numero);
    }

    /**
     * Envía un correo electrónico con el código de verificación al usuario especificado.
     * 
     * @param user El objeto {@link Usuario} que contiene la dirección de correo y el código de verificación.
     * @throws AddressException Si la dirección de correo electrónico del destinatario es inválida.
     */
    public static void enviarEmail(Usuario user) throws AddressException {
        try {
            // Dirección de correo del destinatario
            String destinatario = user.getCorreoInst();
            // Dirección de correo del remitente
            String remitente = "mdasena00@outlook.com";

            // Credenciales del correo remitente
            final String usuario = "mdasena00@outlook.com";
            final String password = "ijjbcdmrvpekpvtn";

            // Servidor SMTP para el envío de correos
            final String host = "smtp-mail.outlook.com";

            // Configuración de propiedades para la conexión SMTP
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.auth", "true"); // Habilita la autenticación SMTP
            propiedades.put("mail.smtp.starttls.enable", "true"); // Habilita STARTTLS para encriptar la conexión
            propiedades.put("mail.smtp.host", host); // Establece el servidor SMTP
            propiedades.put("mail.smtp.port", "587"); // Establece el puerto para la conexión SMTP

            // Crea una sesión con autenticación utilizando las credenciales proporcionadas
            Session session = Session.getInstance(propiedades, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, password);
                }
            });

            // Crea un nuevo mensaje de correo electrónico
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente)); // Establece el remitente del correo
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); // Establece el destinatario

            // Asunto y cuerpo del correo
            mensaje.setSubject("Código de verificación de la plataforma MdaSena");
            mensaje.setText("El código de verificación es: " + user.getCodigo());

            // Envía el correo electrónico
            Transport.send(mensaje);
        } catch (Exception e) {
            // Manejo de errores durante el proceso de envío del correo
            System.out.println("Error enviando el correo electrónico: " + e.getMessage());
        }
    }
}
