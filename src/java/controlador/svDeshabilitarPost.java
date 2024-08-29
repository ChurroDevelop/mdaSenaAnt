package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;

/**
 * Servlet para deshabilitar posts.
 * Este servlet maneja la solicitud de deshabilitar un post específico. Recibe el ID del post y del instructor
 * a través de una solicitud POST, deshabilita el post en la base de datos, actualiza la lista de posts del
 * instructor en la sesión y redirige al usuario a la vista de administración de posts.
 */
@WebServlet(name = "svDeshabilitarPost", urlPatterns = {"/svDeshabilitarPost"})
public class svDeshabilitarPost extends HttpServlet {
    // Instancia de un nuevo PostDAO para manejo del CRUD
    PostDAO pDao = new PostDAO();

    /**
     * Maneja las solicitudes HTTP POST para deshabilitar un post.
     * 
     * @param request La solicitud HTTP del cliente, que contiene los parámetros "txtIdPost" y "txtIdInstructor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer una nueva sesión
        HttpSession sesion = request.getSession();
        
        // Tomar el ID del post y el ID del instructor desde la solicitud
        String idPost = request.getParameter("txtIdPost");
        String idInstructor = request.getParameter("txtIdInstructor");
        
        // Deshabilitar el post con el ID proporcionado
        boolean estado = pDao.deshabilitarPost(idPost);
        
        if (estado) {
            // Actualiza la sesión con la lista de posts del instructor
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            
            // Redirige a la vista de administrar posts por parte del instructor
            response.sendRedirect("administrarPost.jsp");
        }
        
        /*
            Este servlet no se está utilizando.
        */
    }
}
