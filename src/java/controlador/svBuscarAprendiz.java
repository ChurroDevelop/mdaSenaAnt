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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Toma el número de documento del usuario para buscar al aprendiz.
        String numero = request.getParameter("txtNumero");

        // JsonObject es lo que devuelve el método para buscar al aprendiz por el número de documento.
        JsonObject info = pDao.buscarAprendiz(numero);

        // Establece el tipo de contenido de la respuesta HTTP a JSON.
        response.setContentType("application/json");
        
        // Asegura que la codificación sea UTF-8 para caracteres especiales.
        response.setCharacterEncoding("UTF-8"); 

        // Escribe la respuesta HTTP en formato JSON
        response.getWriter().write(info.toString());
    }
}
