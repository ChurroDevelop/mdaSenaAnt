package controlador;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PerfilDAO;
import com.google.gson.JsonObject;

@WebServlet(name = "svBuscarAprendiz", urlPatterns = {"/svBuscarAprendiz"})
public class svBuscarAprendiz extends HttpServlet {
    // Instancia de un nuevo perfilDao para el manejo de metodos en la base de datos
    PerfilDAO pDao = new PerfilDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Toma el numero de documento del usuario para buscar el aprendiz
        String numero = request.getParameter("txtNumero");
        
        // JsonObject, es lo que devuelve el metodo para buscar el aprendiz por el numero de documento
        JsonObject info = pDao.buscarAprendiz(numero);
        
        // Establece el tipo de contenido de la respuesta HTTP
        response.setContentType("application/json");
        
        // Sirve para obtener los caracteres de html y poder pintarlos en la vista
        response.getWriter().write(info.toString());
        
        
    }
}
