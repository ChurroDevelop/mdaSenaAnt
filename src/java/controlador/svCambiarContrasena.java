package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EncriptarContraseña;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

/**
 * Servlet para manejar el cambio de contraseña del usuario.
 */
@WebServlet(name = "svCambiarContrasena", urlPatterns = {"/svCambiarContrasena"})
public class svCambiarContrasena extends HttpServlet {

    // Instancia de UsuarioDao para manejar las operaciones de base de datos relacionadas con el usuario.
    UsuarioDao userDao = new UsuarioDao();

    /**
     * Maneja las solicitudes POST para cambiar la contraseña del usuario.
     *
     * @param request Solicitud HTTP que contiene la información de la solicitud
     * del cliente.
     * @param response Respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene la sesión del usuario.
        HttpSession sesionUser = request.getSession();
        // Configura la codificación de caracteres para evitar problemas con acentos y caracteres especiales.
        request.setCharacterEncoding("UTF-8");

        // Obtiene la nueva contraseña y la confirmación de la nueva contraseña ingresadas por el usuario en el formulario.
        String clave = request.getParameter("txtClave");
        String confirmarClave = request.getParameter("txtConfirmarClave");

        // Recupera el objeto Usuario de la sesión.
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");
        System.out.println("Usuario: " + user.getCorreoInst()); // Imprime en consola el correo del usuario autenticado.

        // Verifica si la nueva contraseña y su confirmación coinciden.
        if (clave.equals(confirmarClave)) {
            // Encripta la nueva contraseña.
            String encript = EncriptarContraseña.encriptar(confirmarClave);
            try {
                System.out.println("Las contraseñas coinciden"); // Mensaje de confirmación en consola.
                // Cambia la contraseña del usuario en la base de datos.
                userDao.cambiarContrasena(userDao.obtenerId(user.getCorreoInst()), encript);
                // Redirige al usuario a la página de inicio de sesión.
                response.sendRedirect("login.jsp");
            } catch (SQLException ex) {
                // Manejo de excepciones de SQL.
                Logger.getLogger(svCambiarContrasena.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Mensaje de error en consola si las contraseñas no coinciden.
            System.out.println("Las contraseñas no coinciden");
        }
    }
}
