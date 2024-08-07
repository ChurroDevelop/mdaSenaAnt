package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;
import modelo.objetos.Post;

@WebServlet(name = "svListarPosts", urlPatterns = {"/svListarPosts"})
public class svListarPosts extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Atrapar sesion
        HttpSession sesion = request.getSession();
        
        // Atrapar el id del instructor para listar esos posts
        String id = request.getParameter("txtIdInstructor");
        
        // Instancia de un postDao para manejo a la base de datos
        PostDAO pDao = new PostDAO();
        
        // Lista de los post de ese instructor
        List<Post> posts = pDao.listarPostsUser(id);
        
        // Asignar una nueva sesion con el arreglo de los post encontrados de dicho instructor
        sesion.setAttribute("listaPosts", posts);
        
        // Redirijir a la vista de administrar los post por parte del instructor
        response.sendRedirect("administrarPost.jsp");
    }
}
