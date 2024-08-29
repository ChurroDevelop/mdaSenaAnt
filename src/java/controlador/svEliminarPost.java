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
 * Servlet para eliminar un post.
 * <p>
 * Este servlet maneja la solicitud para eliminar un post específico. Recibe el ID del post y el ID del instructor
 * a través de una solicitud POST, elimina el post de la base de datos, actualiza la lista de posts del instructor
 * en la sesión y redirige al usuario a la vista del panel de control.
 * </p>
 */
@WebServlet(name = "svEliminarPost", urlPatterns = {"/svEliminarPost"})
public class svEliminarPost extends HttpServlet {
    // Instancia de un nuevo PostDAO para manejo a la base de datos
    PostDAO pDao = new PostDAO();

    /**
     * Maneja las solicitudes HTTP GET. Este método no está implementado en el servlet.
     * 
     * @param request La solicitud HTTP del cliente.
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Maneja las solicitudes HTTP POST para eliminar un post.
     * 
     * @param request La solicitud HTTP del cliente, que contiene los parámetros "txtIdPost" y "txtIdInstructor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instanciar una nueva sesión
        HttpSession sesion = request.getSession();

        // Capturar el ID del post y el ID del instructor desde la solicitud
        String idPost = request.getParameter("txtIdPost");
        String idInstructor = request.getParameter("txtIdInstructor");

        // Eliminar el post con el ID proporcionado
        boolean estado = pDao.eliminarPost(idPost);

        if (estado) {
            // Actualiza la sesión con la lista de posts del instructor
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            
            // Redirige a la vista del panel de control
            response.sendRedirect("adminisitrarPost.jsp");
        }
    }
}
