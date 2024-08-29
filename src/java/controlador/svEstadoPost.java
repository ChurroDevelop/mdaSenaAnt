package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;

/**
 * Servlet para modificar el estado de un post.
 * Este servlet maneja la solicitud para modificar el estado de un post específico. Recibe el ID del post y el ID del instructor
 * a través de una solicitud POST, modifica el estado del post en la base de datos, actualiza la lista de posts del instructor
 * en la sesión y realiza la depuración del código.
 */
@WebServlet("/svEstadoPost")
public class svEstadoPost extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para modificar el estado de un post.
     * 
     * @param request La solicitud HTTP del cliente, que contiene los parámetros "txtIdPost" y "txtIdInstructor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instanciar la nueva sesión
        HttpSession sesion = request.getSession();
        
        // Instancia de un PostDAO para manejar la base de datos
        PostDAO pDao = new PostDAO();
        
        // Tomar el ID del post y el ID del instructor desde la solicitud
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));
        String idInstructor = request.getParameter("txtIdInstructor");
        
        // Modificar el estado del post con el ID proporcionado
        boolean estado = pDao.modificarEstado(idPost);
        
        if (estado) {
            // Actualiza la sesión con la lista de posts del instructor
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            
            // Depuración del código
            System.out.println("Se modificó el post y ya es visible en la plataforma");
            
            /*
                Este servlet no se está utilizando.
            */
        }
    }
}
