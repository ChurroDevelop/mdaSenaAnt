package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PerfilDAO;
import com.google.gson.JsonObject;

/**
 * Servlet que maneja la búsqueda de aprendices por número de documento.
 * Responde a solicitudes POST y devuelve la información del aprendiz en formato JSON.
 */
@WebServlet(name = "svBuscarAprendiz", urlPatterns = {"/svBuscarAprendiz"})
public class svBuscarAprendiz extends HttpServlet {

    // Instancia de PerfilDAO para manejar las operaciones de base de datos relacionadas con los perfiles.
    PerfilDAO pDao = new PerfilDAO();

    /**
     * Maneja las solicitudes HTTP POST para buscar un aprendiz por su número de documento.
     * 
     * @param request  El objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param response El objeto HttpServletResponse que contiene la respuesta que se enviará al cliente.
     * @throws ServletException Si ocurre un error específico del servlet.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el número de documento del usuario desde los parámetros del formulario.
        String numero = request.getParameter("txtNumero");

        // Busca al aprendiz en la base de datos utilizando el número de documento.
        JsonObject info = pDao.buscarAprendiz(numero);

        // Establece el tipo de contenido de la respuesta como JSON.
        response.setContentType("application/json");
        
        // Establece la codificación de la respuesta a UTF-8 para manejar caracteres especiales correctamente.
        response.setCharacterEncoding("UTF-8");

        // Escribe la respuesta en formato JSON y la envía al cliente.
        response.getWriter().write(info.toString());
    }
}
