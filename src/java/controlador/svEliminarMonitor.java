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

@WebServlet(name = "svEliminarMonitor", urlPatterns = {"/svEliminarMonitor"})
public class svEliminarMonitor extends HttpServlet {
    // Instancia de un nuevo MonitorDao para el manejo de la base de datos
    MonitorDAO mDao = new MonitorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer una nueva session para visualizar los datos en una vista
        HttpSession sesion = request.getSession();
        
        // Tomar el id del usuario y el id del instructor para hacer la asignacion de monitor
        String idUser = request.getParameter("txtIdMonitor");
        String idInstructor = request.getParameter("idInstructorTxt");
        
        // Si se cumple el cambio de rol del dicho usuario entonces
        if (mDao.eliminarMonitor(idUser)) {
            List<Perfil> monitores = mDao.obtenerMonitores(idInstructor); // Lista de los monitores que tiene dicho instructor
            sesion.setAttribute("listMonitores", monitores); // Sobre escribe la sesion que se ha llamado
            response.sendRedirect("views/instructor/asignarMonitor.jsp");
        } else {
            System.out.println("ERROR AL REMOVER EL ROL MONITOR DEL APRENDIZ: " + idUser);
        }
    }
}
