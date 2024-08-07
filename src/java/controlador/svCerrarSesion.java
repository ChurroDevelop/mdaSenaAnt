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
 */
@WebServlet(name = "svCerrarSesion", urlPatterns = {"/svCerrarSesion"})
public class svCerrarSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual, si existe
        HttpSession sesion = request.getSession(false);
        
        // Imprime la sesión actual en la consola para depuración
        System.out.println(sesion); 

        // Si hay una sesión activa, la invalida para cerrar la sesión del usuario
        if (sesion != null) {
            sesion.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión después de cerrar la sesión
        response.sendRedirect("login.jsp");
        
        /*
            No se esta utilizando este Servlet, ya que se hace por medio de JS
        */
    }
}
