package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.AdminDAO;

/**
 * Servlet para deshabilitar usuarios.
 * Este servlet maneja la solicitud de deshabilitar un usuario específico. Recibe el ID del usuario y del administrador
 * a través de una solicitud POST, deshabilita el usuario en la base de datos, actualiza la lista de usuarios en la sesión
 * y redirige al usuario a la vista de administración de usuarios.
 */
@WebServlet(name = "svDeshabilitarUser", urlPatterns = {"/svDeshabilitarUser"})
public class svDeshabilitarUser extends HttpServlet {
    // Instancia de un nuevo AdminDAO para manejo del CRUD
    AdminDAO aDao = new AdminDAO();

    /**
     * Maneja las solicitudes HTTP POST para deshabilitar un usuario.
     * 
     * @param request La solicitud HTTP del cliente, que contiene los parámetros "txtIdUser" y "txtIdAdmin".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer una nueva sesión
        HttpSession sesion = request.getSession();
        
        // Tomar el ID del usuario y el ID del administrador desde la solicitud
        String idUser = request.getParameter("txtIdUser");
        String idAdmin = request.getParameter("txtIdAdmin");
        
        // Deshabilitar el usuario con el ID proporcionado
        boolean estado = aDao.modificarEstado(idUser);
        if (estado) {
            // Actualiza la sesión con la lista de usuarios
            sesion.setAttribute("listaUsers", aDao.listaUsuarios());
            
            // Redirige a la vista de listar usuarios
            response.sendRedirect("views/administrador/listarUsuarios.jsp");
        } 
    }
}
