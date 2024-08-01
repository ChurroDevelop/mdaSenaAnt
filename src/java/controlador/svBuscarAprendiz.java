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
 * Servlet para manejar la búsqueda de aprendices por número de documento.
 */
@WebServlet(name = "svBuscarAprendiz", urlPatterns = {"/svBuscarAprendiz"})
public class svBuscarAprendiz extends HttpServlet {

    // Instancia de un nuevo PerfilDAO para manejar las operaciones de base de datos relacionadas con el perfil.
    PerfilDAO pDao = new PerfilDAO();

    /**
     * Maneja las solicitudes POST para buscar un aprendiz por su número de
     * documento.
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

        // Toma el número de documento del usuario para buscar al aprendiz.
        String numero = request.getParameter("txtNumero");

        // JsonObject es lo que devuelve el método para buscar al aprendiz por el número de documento.
        JsonObject info = pDao.buscarAprendiz(numero);

        // Establece el tipo de contenido de la respuesta HTTP a JSON.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8"); // Asegura que la codificación sea UTF-8 para caracteres especiales.

        // Escribe el JsonObject en la respuesta HTTP.
        response.getWriter().write(info.toString());
    }
}
