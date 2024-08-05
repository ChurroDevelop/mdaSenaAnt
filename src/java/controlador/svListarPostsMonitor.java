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
import modelo.MonitorDAO;
import modelo.PostDAO;
import modelo.objetos.Post;

@WebServlet(name = "svListarPostsMonitor", urlPatterns = {"/svListarPostsMonitor"})
public class svListarPostsMonitor extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("txtIdMonitor")) ;
        
        // Instancia de un MonitorDao
        MonitorDAO mDao = new MonitorDAO();
        
        // Lista de los post de ese instructor
        List<Post> posts = mDao.listaPostMonitor(id);
        
        sesion.setAttribute("listaPostsMonitor", posts);
        response.sendRedirect("administrarPost.jsp");
    }
}
