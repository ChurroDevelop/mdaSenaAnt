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

@WebServlet(name = "svListarPublicaciones", urlPatterns = {"/svListarPublicaciones"})
public class svListarPublicaciones extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Atrapar sesion
        HttpSession sesion = request.getSession();
        
        // Instancia de un MonitorDao
        AdminDAO adminDao = new AdminDAO();
        
        // Lista de los post de ese instructor
        List<Post> allPosts = adminDao.listarTodosLosPosts();
        
        // Sobre escribir la sesion que tiene como valor los post de dicho monitr
        sesion.setAttribute("listaPublicaciones", allPosts);
        
        // Redirije a la vista
        response.sendRedirect("views/administrador/listarPublicaciones.jsp");
    }

}
