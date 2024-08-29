package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;
import modelo.objetos.Post;

/**
 * Servlet para listar los posts asociados a un instructor específico.
 * Este servlet maneja la solicitud para obtener la lista de posts asociados a un instructor particular. Recupera el ID
 * del instructor desde la solicitud POST, obtiene la lista de posts desde la base de datos, guarda esta lista en la sesión
 * y redirige a la vista de administración de posts.
 */
@WebServlet(name = "svListarPosts", urlPatterns = {"/svListarPosts"})
public class svListarPosts extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para listar los posts de un instructor.
     * 
     * @param request La solicitud HTTP del cliente, que contiene el parámetro "txtIdInstructor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();
        
        // Recupera el ID del instructor desde la solicitud
        String id = request.getParameter("txtIdInstructor");
        
        // Instancia de un PostDAO para manejar la base de datos de posts
        PostDAO pDao = new PostDAO();
        
        // Obtiene la lista de posts del instructor con el ID proporcionado
        List<Post> posts = pDao.listarPostsUser(id);
        
        // Guarda la lista de posts en la sesión para que esté disponible en la vista
        sesion.setAttribute("listaPosts", posts);
        
        // Redirige a la vista de administración de posts para mostrar los resultados
        response.sendRedirect("administrarPost.jsp");
    }
}
