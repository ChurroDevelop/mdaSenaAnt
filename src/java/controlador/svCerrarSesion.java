package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet para manejar el cierre de sesión del usuario.
 * Invalida la sesión actual y redirige al usuario a la página de inicio de sesión.
 */
@WebServlet(name = "svCerrarSesion", urlPatterns = {"/svCerrarSesion"})
public class svCerrarSesion extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para cerrar la sesión del usuario.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtiene la sesión actual, si existe; de lo contrario, no crea una nueva sesión.
        HttpSession sesion = request.getSession(false);
        
        // Imprime la sesión actual en la consola para propósitos de depuración.
        System.out.println(sesion); 

        // Si hay una sesión activa, la invalida para cerrar la sesión del usuario.
        if (sesion != null) {
            sesion.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión después de cerrar la sesión.
        response.sendRedirect("login.jsp");
        
        /*
         * Este Servlet no está siendo utilizado, ya que el cierre de sesión se realiza por medio de JavaScript.
         */
    }
}
