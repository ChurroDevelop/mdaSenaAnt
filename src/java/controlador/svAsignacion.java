package controlador;

import java.io.IOException;
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

/**
 * Servlet que maneja la asignación del rol de monitor a un aprendiz.
 * Responde a solicitudes POST para realizar la asignación y redirige 
 * a la vista correspondiente tras la operación.
 */
@WebServlet(name = "svAsignacion", urlPatterns = {"/svAsignacion"})
public class svAsignacion extends HttpServlet {

    // Instancia de UsuarioDao para manejar las operaciones de base de datos relacionadas con los usuarios.
    private final UsuarioDao userDao = new UsuarioDao();

    // Instancia de MonitorDAO para manejar las operaciones de base de datos relacionadas con los monitores.
    private final MonitorDAO mDao = new MonitorDAO();

    /**
     * Maneja las solicitudes HTTP POST para asignar el rol de monitor a un aprendiz.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener la sesión actual o crear una nueva para almacenar datos entre vistas.
        HttpSession sesion = request.getSession();

        // Recuperar el ID del aprendiz y del instructor desde los parámetros del formulario.
        String idAsignacion = request.getParameter("txtAsignacion");
        String idInstructor = request.getParameter("txtIdInstructor");

        // Intentar asignar el rol de monitor al aprendiz en la base de datos.
        boolean actualizacion = userDao.asignarRolMonitor(idAsignacion, idInstructor);

        // Si la asignación fue exitosa, obtener la lista actualizada de monitores y guardarla en la sesión.
        if (actualizacion) {
            List<Perfil> monitores = mDao.obtenerMonitores(idInstructor);
            sesion.setAttribute("listMonitores", monitores);
        } else {
            // Imprimir un mensaje en la consola si la asignación no fue exitosa.
            System.out.println("No se pudo hacer la modificación");
        }
        
        // Redirigir al usuario a la vista de asignación de monitor.
        response.sendRedirect("views/instructor/asignarMonitor.jsp");
    }
}
