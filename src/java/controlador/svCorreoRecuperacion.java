package controlador;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "svCorreoRecuperacion", urlPatterns = {"/svCorreoRecuperacion"})
public class svCorreoRecuperacion extends HttpServlet {

    Usuario user = new Usuario(); // Instanciando un nuevo usuario para colocarle sus atributos
    UsuarioDao userDao = new UsuarioDao(); // instancia de un usuarioDao que manejara los procesos CRUD
    EnviarCodigoContrasena mensaje = new EnviarCodigoContrasena();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(); // Obtiene la sesión actual
        request.setCharacterEncoding("UTF-8"); // Configura la codificación de caracteres para evitar problemas con acentos y caracteres especiales

        String correo = request.getParameter("txtCorreo"); // Obtiene el correo electrónico ingresado por el usuario en el formulario
        String codigo = mensaje.getRandom(); // Genera un código aleatorio para la autenticación

        // Expresiones regulares para validar correos de aprendices e instructores
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el correo de aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b"; // Regex para el correo de instructor

        // Compiladores para las expresiones regulares
        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compila el regex para aprendiz
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compila el regex para instructor

        // Matchers para comprobar si el correo cumple con los patrones definidos
        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);

        // Configura el correo y el código en el objeto usuario
        user.setCorreoInst(correo);
        user.setCodigo(codigo); // Establece el código aleatorio en el usuario

        // Verifica si el usuario existe en la base de datos
        boolean encontrado = userDao.buscarUser(user); // Hace la validación si existe o no un usuario en la base de datos

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
                    sesion.setAttribute("autenticacion", user); // Guarda el usuario en la sesión
                    response.getWriter().write("success"); // Escribe 'success' en la respuesta
                } catch (AddressException ex) {
                    Logger.getLogger(svCorreoRecuperacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("El usuario no existe"); // Mensaje si el usuario no se encuentra en la base de datos
                response.getWriter().write("user_not_found"); // Escribe 'user_not_found' en la respuesta
            }
        } else {
            System.out.println("La extensión del correo no es válida"); // Mensaje si el correo no tiene una extensión válida
            // response.getWriter().write("invalid_email"); // Escribe 'invalid_email' en la respuesta
        }
    }
}
