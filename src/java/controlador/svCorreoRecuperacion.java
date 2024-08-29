package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EnviarCodigoContrasena;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

/**
 * Servlet para manejar la recuperación de contraseñas enviando un código de
 * verificación por correo electrónico.
 */
@WebServlet(name = "svCorreoRecuperacion", urlPatterns = {"/svCorreoRecuperacion"})
public class svCorreoRecuperacion extends HttpServlet {

    // Instancia un nuevo objeto Usuario para manipular sus atributos
    Usuario user = new Usuario();
    
    // Instancia de UsuarioDao para realizar operaciones CRUD relacionadas con el usuario
    UsuarioDao userDao = new UsuarioDao();
    
    // Instancia de EnviarCodigoContrasena para generar y enviar el código de recuperación
    EnviarCodigoContrasena mensaje = new EnviarCodigoContrasena();

    /**
     * Maneja la solicitud HTTP POST para recuperar la contraseña.
     *
     * @param request La solicitud HTTP que contiene los parámetros del formulario.
     * @param response La respuesta HTTP que se envía de vuelta al cliente.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir en la respuesta.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtiene la sesión actual del usuario
        HttpSession sesion = request.getSession();
        
        // Configura la codificación de caracteres a UTF-8 para manejar caracteres especiales
        request.setCharacterEncoding("UTF-8");

        // Obtiene el correo electrónico ingresado por el usuario desde el formulario
        String correo = request.getParameter("txtCorreo");
        
        // Genera un código aleatorio para la recuperación de contraseña
        String codigo = mensaje.getRandom();

        // Expresiones regulares para validar correos electrónicos de aprendices e instructores
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b";

        // Compila las expresiones regulares en patrones
        final Pattern pAprendiz = Pattern.compile(expAprendiz);
        final Pattern pInstructor = Pattern.compile(expInstructor);

        // Crea matchers para verificar si el correo electrónico cumple con los patrones
        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);

        // Asigna el correo electrónico y el código al objeto Usuario
        user.setCorreoInst(correo);
        user.setCodigo(codigo);

        // Verifica si el usuario existe en la base de datos
        boolean encontrado = userDao.buscarUser(user);

        // Establece el tipo de respuesta como texto plano
        response.setContentType("text/plain");

        // Verifica si el correo electrónico es válido y si el usuario existe en la base de datos
        if (mInstructor.matches() || mAprendiz.matches()) {
            if (encontrado) {
                System.out.println("Correo: " + correo);
                try {
                    // Envía el código de recuperación al usuario por correo electrónico
                    mensaje.enviarCodigoRecuperacion(user);
                    System.out.println("Se le envió el código");
                    System.out.println("Código: " + codigo);

                    // Establece el objeto usuario en la sesión
                    sesion.setAttribute("autenticacion", user);
                    // Envía una respuesta de éxito
                    response.getWriter().write("success");
                } catch (AddressException ex) {
                    // Registra cualquier excepción relacionada con el correo electrónico
                    Logger.getLogger(svCorreoRecuperacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Informa que el usuario no existe en la base de datos
                System.out.println("El usuario no existe");
                response.getWriter().write("user_not_found");
            }
        } else {
            // Informa que la dirección de correo electrónico no es válida
            System.out.println("La extensión del correo no es válida");
            response.getWriter().write("invalid_email");
        }
    }
}