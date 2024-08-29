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
 * Servlet para eliminar un monitor asignado a un instructor.
 * <p>
 * Este servlet maneja la solicitud para eliminar un monitor específico asignado a un instructor. Recibe el ID del monitor
 * y el ID del instructor a través de una solicitud POST, elimina el monitor de la base de datos, actualiza la lista de
 * monitores del instructor en la sesión y redirige al usuario a la vista de asignación de monitores.
 * </p>
 */
@WebServlet(name = "svEliminarMonitor", urlPatterns = {"/svEliminarMonitor"})
public class svEliminarMonitor extends HttpServlet {

    // Instancia del DAO para manejar la base de datos de monitores
    private final MonitorDAO mDao = new MonitorDAO();

    /**
     * Maneja las solicitudes HTTP POST para eliminar un monitor asignado a un instructor.
     * 
     * @param request La solicitud HTTP del cliente, que contiene los parámetros "txtIdMonitor" y "txtIdInstructor".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual
        HttpSession sesion = request.getSession();

        // Recupera el ID del monitor a eliminar y el ID del instructor desde la solicitud
        String idUser = request.getParameter("txtIdMonitor");
        String idInstructor = request.getParameter("txtIdInstructor");
        System.out.println("ID DEL INSTRUCTOR: " + idInstructor);

        // Intenta eliminar el monitor del instructor
        if (mDao.eliminarMonitor(idUser)) {
            // Si la eliminación fue exitosa, actualiza la lista de monitores del instructor
            List<Perfil> monitores = mDao.obtenerMonitores(idInstructor);
            
            // Guarda la lista actualizada en la sesión
            sesion.setAttribute("listMonitores", monitores); 
            
            // Redirige a la vista de asignación de monitores
            response.sendRedirect("views/instructor/asignarMonitor.jsp");
        } else {
            // Si hubo un error al eliminar el monitor, muestra un mensaje en la consola
            System.out.println("ERROR AL REMOVER EL ROL MONITOR DEL APRENDIZ: " + idUser);
        }
    }
}
