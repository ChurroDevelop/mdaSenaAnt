package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.MonitorDAO;
import modelo.PostDAO;
import modelo.objetos.Post;

/**
 * Servlet para listar los posts asociados a un monitor específico.
 * Este servlet maneja la solicitud para obtener la lista de posts asociados a un monitor particular. Recupera el ID del
 * monitor desde la solicitud POST, obtiene la lista de posts desde la base de datos, guarda esta lista en la sesión
 * y redirige a la vista de administración de posts.
 */
@WebServlet(name = "svListarPostsMonitor", urlPatterns = {"/svListarPostsMonitor"})
public class svListarPostsMonitor extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para listar los posts de un monitor.
     * 
     * @param request La solicitud HTTP del cliente, que contiene el parámetro "txtIdMonitor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();
        
        // Recupera el ID del monitor desde la solicitud y lo convierte a entero
        int id = Integer.parseInt(request.getParameter("txtIdMonitor"));
        
        // Instancia de un MonitorDAO para manejar la base de datos de monitores
        MonitorDAO mDao = new MonitorDAO();
        
        // Obtiene la lista de posts asociados al monitor con el ID proporcionado
        List<Post> posts = mDao.listaPostMonitor(id);
        
        // Guarda la lista de posts en la sesión para que esté disponible en la vista
        sesion.setAttribute("listaPostsMonitor", posts);
        
        // Redirige a la vista de administración de posts para mostrar los resultados
        response.sendRedirect("administrarPost.jsp");
    }
}
