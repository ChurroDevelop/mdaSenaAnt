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
 * Servlet que maneja el cambio de contraseña del usuario.
 * Responde a solicitudes POST para actualizar la contraseña del usuario autenticado.
 */
@WebServlet(name = "svCambiarContrasena", urlPatterns = {"/svCambiarContrasena"})
public class svCambiarContrasena extends HttpServlet {

    // Instancia de UsuarioDao para manejar las operaciones de base de datos relacionadas con los usuarios.
    UsuarioDao userDao = new UsuarioDao();

    /**
     * Maneja las solicitudes HTTP POST para cambiar la contraseña de un usuario.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene la sesión actual del usuario.
        HttpSession sesionUser = request.getSession();
        
        // Configura la codificación de caracteres del request para manejar acentos y caracteres especiales.
        request.setCharacterEncoding("UTF-8");

        // Obtiene la nueva contraseña y la confirmación de la nueva contraseña del formulario.
        String clave = request.getParameter("txtClave");
        String confirmarClave = request.getParameter("txtConfirmarClave");

        // Recupera el objeto Usuario de la sesión, el cual contiene los datos del usuario autenticado.
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");
        
        // Imprime en la consola el correo del usuario autenticado para propósitos de depuración.
        System.out.println("Usuario: " + user.getCorreoInst());

        // Verifica si la nueva contraseña y la confirmación coinciden.
        if (clave.equals(confirmarClave)) {
            
            // Encripta la nueva contraseña antes de guardarla en la base de datos.
            String encript = EncriptarContraseña.encriptar(confirmarClave);
            
            try {
                // Imprime en la consola que las contraseñas coinciden.
                System.out.println("Las contraseñas coinciden");
                
                // Cambia la contraseña del usuario en la base de datos.
                userDao.cambiarContrasena(userDao.obtenerId(user.getCorreoInst()), encript);
                
                // Redirige al usuario a la página de inicio de sesión tras cambiar la contraseña.
                response.sendRedirect("login.jsp");
            } catch (SQLException ex) {
                // Maneja cualquier excepción de SQL y la registra en el log.
                Logger.getLogger(svCambiarContrasena.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Imprime en la consola que las contraseñas no coinciden si hay un error.
            System.out.println("Las contraseñas no coinciden");
        }
    }
}
