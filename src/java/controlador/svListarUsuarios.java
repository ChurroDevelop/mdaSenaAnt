package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svListarUsuarios", urlPatterns = {"/svListarUsuarios"})
public class svListarUsuarios extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Atrapar una nueva sesion
        HttpSession sesion = request.getSession();
        
        // Atrapar el id del administrados
        String idAdministrador = request.getParameter("txtIdAdministrador");
        System.out.println("El id del administrador es: " + idAdministrador);
        response.sendRedirect("views/administrador/listarPublicaciones.jsp");

    }

}
