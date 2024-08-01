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

    // Instanciando un nuevo usuario para colocarle sus atributos
    Usuario user = new Usuario();
    // Instancia de un UsuarioDao que manejará los procesos CRUD
    UsuarioDao userDao = new UsuarioDao();
    // Instancia de EnviarCodigoContrasena para enviar el código de recuperación
    EnviarCodigoContrasena mensaje = new EnviarCodigoContrasena();

    /**
     * Maneja las solicitudes POST para enviar un código de recuperación al
     * correo electrónico del usuario.
     *
     * @param request Solicitud HTTP que contiene el correo electrónico del
     * usuario.
     * @param response Respuesta HTTP que indica el resultado del proceso.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();
        // Configura la codificación de caracteres para evitar problemas con acentos y caracteres especiales
        request.setCharacterEncoding("UTF-8");

        // Obtiene el correo electrónico ingresado por el usuario en el formulario
        String correo = request.getParameter("txtCorreo");
        // Genera un código aleatorio para la autenticación
        String codigo = mensaje.getRandom();

        // Expresiones regulares para validar correos de aprendices e instructores
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b";

        // Compiladores para las expresiones regulares
        final Pattern pAprendiz = Pattern.compile(expAprendiz);
        final Pattern pInstructor = Pattern.compile(expInstructor);

        // Matchers para comprobar si el correo cumple con los patrones definidos
        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);

        // Configura el correo y el código en el objeto usuario
        user.setCorreoInst(correo);
        user.setCodigo(codigo);

        // Verifica si el usuario existe en la base de datos
        boolean encontrado = userDao.buscarUser(user);

        response.setContentType("text/plain");

        // Si el correo cumple con alguno de los patrones y el usuario existe en la base de datos
        if (mInstructor.matches() || mAprendiz.matches()) {
            if (encontrado) {
                System.out.println("Correo: " + correo);
                try {
                    // Envía el código de recuperación al usuario
                    mensaje.enviarCodigoRecuperacion(user);
                    System.out.println("Se le envió el código");
                    System.out.println("Código: " + codigo);

                    // Establece el objeto usuario en la sesión
                    sesion.setAttribute("autenticacion", user);
                    response.getWriter().write("success");
                } catch (AddressException ex) {
                    Logger.getLogger(svCorreoRecuperacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("El usuario no existe");
                response.getWriter().write("user_not_found");
            }
        } else {
            System.out.println("La extensión del correo no es válida");
            response.getWriter().write("invalid_email");
        }
    }
}
