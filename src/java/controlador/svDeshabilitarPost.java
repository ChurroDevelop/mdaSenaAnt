package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;

@WebServlet(name = "svDeshabilitarPost", urlPatterns = {"/svDeshabilitarPost"})
public class svDeshabilitarPost extends HttpServlet {
    // Instancia de un nuevo PostDao para manejo del CRUD
    PostDAO pDao = new PostDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer una nueva sesion
        HttpSession sesion = request.getSession();
        
        // Tomar el id del instructor y el id del post
        String idPost = request.getParameter("txtIdPost");
        String idInstructor = request.getParameter("txtIdInstructor");
        
        // Manejo de estado para saber si el metodo fue true o false
        boolean estado = pDao.deshabilitarPost(idPost);
        
        if (estado) {
            // Setea una nueva session con el valor de un array
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            
            // Redirije a la vista de administrar post por parte del instructor
            response.sendRedirect("administrarPost.jsp");
        }
        
        /*
            Este servlet no se esta utilizando
        */
    }
}
