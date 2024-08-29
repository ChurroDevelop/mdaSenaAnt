package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.AdminDAO;
import modelo.objetos.Post;

/**
 * Servlet para listar todas las publicaciones disponibles en el sistema.
 * Este servlet maneja la solicitud para obtener una lista de todas las publicaciones desde la base de datos,
 * guarda esta lista en la sesión y redirige a la vista que muestra todas las publicaciones.
 */
@WebServlet(name = "svListarPublicaciones", urlPatterns = {"/svListarPublicaciones"})
public class svListarPublicaciones extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para listar todas las publicaciones.
     * 
     * @param request La solicitud HTTP del cliente, que no necesita parámetros adicionales.
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();
        
        // Instancia de un AdminDAO para manejar la base de datos de publicaciones
        AdminDAO adminDao = new AdminDAO();
        
        // Obtiene la lista de todas las publicaciones desde la base de datos
        List<Post> allPosts = adminDao.listarTodosLosPosts();
        
        // Guarda la lista de publicaciones en la sesión para que esté disponible en la vista
        sesion.setAttribute("listaPublicaciones", allPosts);
        
        // Redirige a la vista que muestra todas las publicaciones
        response.sendRedirect("views/administrador/listarPublicaciones.jsp");
    }
}
