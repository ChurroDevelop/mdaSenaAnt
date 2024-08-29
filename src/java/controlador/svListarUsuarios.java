package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.AdminDAO;

/**
 * Servlet para listar todos los usuarios del sistema.
 * Este servlet maneja la solicitud para obtener una lista de todos los usuarios desde la base de datos,
 * guarda esta lista en la sesión y redirige a la vista que muestra todos los usuarios.
 */
@WebServlet(name = "svListarUsuarios", urlPatterns = {"/svListarUsuarios"})
public class svListarUsuarios extends HttpServlet {
    // Instancia del AdminDAO para manejar la base de datos de usuarios
    private final AdminDAO aDao = new AdminDAO();

    /**
     * Maneja las solicitudes HTTP POST para listar todos los usuarios.
     * 
     * @param request La solicitud HTTP del cliente, que debe contener el ID del administrador.
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();
        
        // Recupera el ID del administrador desde la solicitud (aunque no se usa en este caso)
        String idAdministrador = request.getParameter("txtIdAdministrador");
        
        // Obtiene la lista de usuarios desde la base de datos
        sesion.setAttribute("listaUsers", aDao.listaUsuarios());
        
        // Redirige a la vista que muestra todos los usuarios
        response.sendRedirect("views/administrador/listarUsuarios.jsp");
    }
}
