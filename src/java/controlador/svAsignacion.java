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
import modelo.UsuarioDao;
import modelo.objetos.Perfil;

@WebServlet(name = "svAsignacion", urlPatterns = {"/svAsignacion"})
public class svAsignacion extends HttpServlet {
    // Instancia de un nuevo usuarioDao para todos los metodos a la base de datos
    UsuarioDao userDao = new UsuarioDao();
    MonitorDAO mDao = new MonitorDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        // Tomar el id al momento de dar click en asignar rol monitor
        String idAsignacion = request.getParameter("txtAsignacion");
        String idInstructor = request.getParameter("txtIdInstructor");
        
        System.out.println("El id del instructor es: " + idInstructor);
        
        // Hacer la actualizacion en la base de datos, manejando metodo booleano para saber si se actualizo o no
        boolean actualizacion = userDao.asignarRolMonitor(idAsignacion, idInstructor);
        
        // Si actualizo redirije a la vista de asignar monitor, donde se vera el nuevo monitor
        if (actualizacion) {
            List<Perfil> monitores = mDao.obtenerMonitores();
            sesion.setAttribute("listMonitores", monitores);
            sesion.setAttribute("", request);
            response.sendRedirect("views/instructor/asignarMonitor.jsp");
        } else {
            System.out.println("No se puedo hacer la modificacion");
            response.sendRedirect("views/instructor/asignarMonitor.jsp");
        }
    }
}
