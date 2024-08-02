/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author Propietario
 */
@WebServlet(name = "svListarPosts", urlPatterns = {"/svListarPosts"})
public class svListarPosts extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // Atrapar sesion
        HttpSession sesion = request.getSession();
        
        // Atrapar el id del instructor para listar esos posts
        String id = request.getParameter("txtIdInstructor");
        
        // Instancia de un postDao
        PostDAO pDao = new PostDAO();
        
        // Lista de los post de ese instructor
        List<Post> posts = pDao.listarPostsUser(id);
        
        sesion.setAttribute("listaPosts", posts);
        response.sendRedirect("administrarPost.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
