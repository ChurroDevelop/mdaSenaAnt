package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

/**
 * Servlet para verificar el código de autenticación ingresado por el usuario.
 * Se encarga de comparar el código proporcionado por el usuario con el código
 * almacenado en la sesión.
 */
@WebServlet(name = "svVerificarCodigoContrasena", urlPatterns = {"/svVerificarCodigoContrasena"})
public class svVerificarCodigoContrasena extends HttpServlet {

    private final UsuarioDao userDao = new UsuarioDao();  // Instancia de UsuarioDao para manejo de base de datos

    /**
     * Maneja las solicitudes POST para verificar el código de autenticación del
     * usuario.
     *
     * @param request Solicitud HTTP que contiene el código de verificación
     * enviado por el usuario.
     * @param response Respuesta HTTP que indica si el código es correcto o no.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesionUser = request.getSession();  // Obtiene la sesión del usuario
        request.setCharacterEncoding("UTF-8");  // Configura la codificación de caracteres para manejar acentos y caracteres especiales

        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");  // Recupera el objeto Usuario de la sesión
        String autenticacion = request.getParameter("txtCodigo");  // Obtiene el código de verificación ingresado por el usuario

        response.setContentType("text/plain");  // Establece el tipo de contenido de la respuesta como texto plano

        // Verifica si el código ingresado por el usuario coincide con el código almacenado en el objeto Usuario
        if (autenticacion != null && autenticacion.equals(user.getCodigo())) {
            System.out.println("El código coincide");
            response.getWriter().write("success");  // Envía 'success' como respuesta si el código es correcto
        } else {
            System.out.println("El código no coincide");
            response.getWriter().write("code_mismatch");  // Envía 'code_mismatch' como respuesta si el código no es correcto
        }
    }
}
