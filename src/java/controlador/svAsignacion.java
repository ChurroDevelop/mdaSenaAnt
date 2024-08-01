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
 * Servlet para manejar la asignación del rol de monitor a un aprendiz.
 */
@WebServlet(name = "svAsignacion", urlPatterns = {"/svAsignacion"})
public class svAsignacion extends HttpServlet {

    // Instancia de UsuarioDao para manejar las operaciones de base de datos relacionadas con los usuarios.
    private final UsuarioDao userDao = new UsuarioDao();

    // Instancia de MonitorDAO para manejar las operaciones de base de datos relacionadas con los monitores.
    private final MonitorDAO mDao = new MonitorDAO();

    /**
     * Maneja las solicitudes POST para asignar el rol de monitor a un aprendiz.
     *
     * @param request Solicitud HTTP que contiene la información de la solicitud
     * del cliente.
     * @param response Respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Establecer una nueva sesión para obtener los datos en otra vista.
        HttpSession sesion = request.getSession();

        // Tomar el id del aprendiz y del instructor desde el formulario.
        String idAsignacion = request.getParameter("txtAsignacion");
        String idInstructor = request.getParameter("txtIdInstructor");

        // Hacer la actualización en la base de datos y verificar si se realizó correctamente.
        boolean actualizacion = userDao.asignarRolMonitor(idAsignacion, idInstructor);

        // Redirigir a la vista de asignar monitor, donde se verá el nuevo monitor, según el resultado de la actualización.
        if (actualizacion) {
            List<Perfil> monitores = mDao.obtenerMonitores(idInstructor); // Obtener la lista de monitores asignados.
            sesion.setAttribute("listMonitores", monitores); // Guardar la lista de monitores en la sesión.
        } else {
            System.out.println("No se pudo hacer la modificación");
        }
        response.sendRedirect("views/instructor/asignarMonitor.jsp"); // Redirigir a la vista de asignar monitor.
    }
}
