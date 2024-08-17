package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.AdminDAO;
@WebServlet(name = "svDeshabilitarUser", urlPatterns = {"/svDeshabilitarUser"})
public class svDeshabilitarUser extends HttpServlet {
    AdminDAO aDao = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        String idUser = request.getParameter("txtIdUser");
        String idAdmin = request.getParameter("txtIdAdmin");
        
        boolean estado = aDao.modificarEstado(idUser);
        if (estado) {
          sesion.setAttribute("listaUsers", aDao.listaUsuarios());
          response.sendRedirect("views/administrador/listarUsuarios.jsp");
        } 
        
        
    }
}
