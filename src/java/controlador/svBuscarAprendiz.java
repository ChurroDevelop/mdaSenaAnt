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
    PerfilDAO pDao = new PerfilDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numero = request.getParameter("txtNumero");
        System.out.println(numero);
//        JsonObject info = pDao.buscarAprendiz(numero);
//        response.setContentType("application/json");
//        response.getWriter().write(info.toString());
        
        
    }
}
