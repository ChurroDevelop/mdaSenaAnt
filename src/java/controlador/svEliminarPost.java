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

    PostDAO pDao = new PostDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        String idPost = request.getParameter("txtIdPost");
        String idInstructor = request.getParameter("txtIdInstructor");

        boolean estado = pDao.eliminarPost(idPost);

        if (estado) {
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            response.sendRedirect("adminisitrarPost.jsp");
        }
    }
}
