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

@WebServlet(name = "svEliminarPost", urlPatterns = {"/svEliminarPost"})
public class svEliminarPost extends HttpServlet {
    // Instancia de un nuevo PostDao para manejo a la base de datos
    PostDAO pDao = new PostDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instanciar una nueva sesion
        HttpSession sesion = request.getSession();

        // Capturar el id del post y el id del instructor
        String idPost = request.getParameter("txtIdPost");
        String idInstructor = request.getParameter("txtIdInstructor");

        // Manejo de estados para saber si el post se elimino o no
        boolean estado = pDao.eliminarPost(idPost);

        if (estado) {
            // Sobre escribe una nueva sesion donde estan los post de los monitores
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            
            // Redirije a la vista del panel de control
            response.sendRedirect("adminisitrarPost.jsp");
        }
    }
}
