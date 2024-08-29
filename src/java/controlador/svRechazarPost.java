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
 * Servlet para manejar el rechazo de un post. Permite agregar una observación
 * a un post y actualizar la lista de posts del instructor.
 */
@WebServlet(name = "svRechazarPost", urlPatterns = {"/svRechazarPost"})
public class svRechazarPost extends HttpServlet {
    // Instancia para los métodos a la base de datos
    PostDAO pDao = new PostDAO();

    /**
     * Procesa la solicitud POST para rechazar un post y agregar una observación.
     * 
     * @param request La solicitud HTTP del cliente.
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error en el procesamiento del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instanciar una nueva sesión
        HttpSession sesion = request.getSession();
        
        // Tomar los parámetros para realizar las consultas a la base de datos
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));
        String idInstructor = request.getParameter("txtIdInstructor");
        String observacion = request.getParameter("txtObservacion");

        // Manejo de estado para saber si se agregó o no la observación
        boolean estado = pDao.agregarObservacion(idPost, observacion);
        
        if (estado) {
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            System.out.println("SI SE PUDO MODIFICAR EL POST");
        }
        
        /**
         * Este servlet no se está utilizando
         */
    }
}
