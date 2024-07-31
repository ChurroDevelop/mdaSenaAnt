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
    // Instancia de un nuevo MonitorDao para el manejo de la base de datos
    MonitorDAO mDao = new MonitorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Crear una nueva session para el manejo de datos en otras vistas
        HttpSession sesion = request.getSession();
        
        // Tomar el id del instructor para visualizar que monitores tiene asignado
        String id = request.getParameter("txtIdInstructor");
        List<Perfil> monitores = mDao.obtenerMonitores(id); // Lista de los monitores que asigno dicho instructor
        sesion.setAttribute("listMonitores", monitores); // Se sobre escribe la sesion
        response.sendRedirect("views/instructor/asignarMonitor.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
