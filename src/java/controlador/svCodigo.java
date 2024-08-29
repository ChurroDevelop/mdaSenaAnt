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
import modelo.EncriptarContraseña;
import modelo.EnviarCodigo;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

/**
 * Servlet para manejar el registro de usuarios enviando un código de verificación por correo electrónico.
 */
@WebServlet(name = "svCodigo", urlPatterns = {"/svCodigo"})
public class svCodigo extends HttpServlet {

    // Instancias de Usuario, UsuarioDao y EnviarCodigo para manejar los procesos de registro y envío de códigos
    Usuario user = new Usuario();
    UsuarioDao userDao = new UsuarioDao();
    EnviarCodigo mensaje = new EnviarCodigo();

    /**
     * Método que maneja las solicitudes HTTP POST para el registro de usuarios.
     * Envía un código de verificación al correo del usuario si la validación es exitosa.
     *
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();
        
        // Configura la codificación de caracteres para evitar problemas con acentos y caracteres especiales
        request.setCharacterEncoding("UTF-8");

        // Obtiene los datos ingresados por el usuario en el formulario, como correo, clave y la confirmación de la clave
        String correo = request.getParameter("txtCorreo");
        String clave = request.getParameter("txtPass");
        String confirm = request.getParameter("txtConfirm");
        
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

        // Configura el tipo de contenido de la respuesta
        response.setContentType("text/plain");
        
        // Obtiene el escritor para enviar la respuesta al cliente
        PrintWriter out = response.getWriter();

        // Verifica si la contraseña y su confirmación coinciden
        if (clave.equals(confirm)) {
            
            // Encripta la contraseña
            String encript = EncriptarContraseña.encriptar(clave);

            // Setea los datos a los atributos de la nueva instancia del objeto Usuario
            user.setCorreoInst(correo);
            user.setPassword(encript);
            user.setCodigo(codigo);
            
            // Depuración del código de verificación por si algo falla
            System.out.println(codigo);

            // Verifica si el usuario ya existe en la base de datos
            boolean encontrado = userDao.buscarUser(user);

            try {
                if (!encontrado) {
                    
                    // Verifica si el correo cumple con el patrón de aprendiz
                    if (mAprendiz.matches()) {
                        
                        // Envía el mensaje de verificación por correo electrónico
                        mensaje.enviarEmail(user);
                        
                        // Setea una nueva sesión para utilizarla en las vistas
                        sesion.setAttribute("autenticacion", user);
                        
                        // Devuelve al cliente una respuesta indicando éxito
                        out.print("success");
                        
                    // Verifica si el correo cumple con el patrón de instructor
                    } else if (mInstructor.matches()) {
                        
                        // Envía el mensaje de verificación por correo electrónico
                        mensaje.enviarEmail(user);
                        
                        // Setea una nueva sesión para utilizarla en las vistas
                        sesion.setAttribute("autenticacion", user);
                        
                        // Devuelve al cliente una respuesta indicando éxito
                        out.print("success");
                        
                    // Si el correo no cumple con ningún patrón
                    } else {
                        out.print("invalid_email");
                    }
                // Si el usuario ya existe en la base de datos
                } else {
                    out.print("email_exists");
                }
            // Maneja excepciones de dirección de correo inválida
            } catch (AddressException ex) {
                Logger.getLogger(svCodigo.class.getName()).log(Level.SEVERE, null, ex);
                out.print("error");
            }
        // Si las contraseñas no coinciden
        } else {
            out.print("password_mismatch");
        }

        // Vacía el contenido del escritor
        out.flush();
    }
}
