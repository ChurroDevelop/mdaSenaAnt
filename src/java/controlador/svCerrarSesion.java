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

    /**
     * Maneja las solicitudes POST para cerrar la sesión del usuario.
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
        // Obtiene la sesión actual, si existe
        HttpSession sesion = request.getSession(false);
        System.out.println(sesion); // Imprime la sesión actual en la consola para depuración

        // Si hay una sesión activa, la invalida para cerrar la sesión del usuario
        if (sesion != null) {
            sesion.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión después de cerrar la sesión
        response.sendRedirect("login.jsp");
    }
}
