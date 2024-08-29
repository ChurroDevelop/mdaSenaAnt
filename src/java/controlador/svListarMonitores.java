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
 * Este servlet maneja la solicitud para obtener la lista de monitores asignados a un instructor particular. Recupera el ID
 * del instructor desde la solicitud GET, obtiene la lista de monitores desde la base de datos, guarda esta lista en la sesión
 * y redirige a la vista de asignación de monitores.
 */
@WebServlet(name = "svListarMonitores", urlPatterns = {"/svListarMonitores"})
public class svListarMonitores extends HttpServlet {

    // Instancia del DAO para manejar la base de datos de monitores
    private final MonitorDAO mDao = new MonitorDAO();

    /**
     * Maneja las solicitudes HTTP GET para listar los monitores asignados a un instructor.
     * 
     * @param request La solicitud HTTP del cliente, que contiene el parámetro "txtIdInstructor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
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
