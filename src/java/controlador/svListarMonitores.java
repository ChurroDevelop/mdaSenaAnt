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
import modelo.objetos.Perfil;

/**
 * Servlet para listar los monitores asignados a un instructor específico.
 * Obtiene la lista de monitores desde la base de datos y la guarda en la
 * sesión.
 */
@WebServlet(name = "svListarMonitores", urlPatterns = {"/svListarMonitores"})
public class svListarMonitores extends HttpServlet {

    // Instancia del DAO para manejar la base de datos de monitores
    private final MonitorDAO mDao = new MonitorDAO();

    /**
     * Maneja las solicitudes GET para listar monitores asignados por un
     * instructor.
     *
     * @param request Solicitud HTTP que contiene el ID del instructor.
     * @param response Respuesta HTTP que redirige a la vista de asignación de
     * monitores.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual o crea una nueva si no existe
        HttpSession sesion = request.getSession();

        // Recupera el ID del instructor desde la solicitud
        String id = request.getParameter("txtIdInstructor");

        // Obtiene la lista de monitores asignados al instructor con el ID proporcionado
        List<Perfil> monitores = mDao.obtenerMonitores(id);

        // Guarda la lista de monitores en la sesión para que esté disponible en la vista
        sesion.setAttribute("listMonitores", monitores);

        // Redirige a la vista de asignación de monitores para mostrar los resultados
        response.sendRedirect("views/instructor/asignarMonitor.jsp");
    }

}
