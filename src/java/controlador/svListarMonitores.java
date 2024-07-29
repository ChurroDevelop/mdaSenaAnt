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
import modelo.objetos.Perfil;

@WebServlet(name = "svListarMonitores", urlPatterns = {"/svListarMonitores"})
public class svListarMonitores extends HttpServlet {
    MonitorDAO mDao = new MonitorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        System.out.println("METODO GET EN EL SERVLET");
        List<Perfil> monitores = mDao.obtenerMonitores();
        sesion.setAttribute("listMonitores", monitores);
        response.sendRedirect("views/instructor/asignarMonitor.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
