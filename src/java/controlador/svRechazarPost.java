package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;

@WebServlet(name = "svRechazarPost", urlPatterns = {"/svRechazarPost"})
public class svRechazarPost extends HttpServlet {
    // Instancia para los metodos a la base de datos
    PostDAO pDao = new PostDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instanciar una nueva sesion
        HttpSession sesion = request.getSession();
        
        // Tomar los parametros para realizar las consultas a la base de datos
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));
        String idInstructor = request.getParameter("txtIdInstructor");
        String observacion = request.getParameter("txtObservacion");

        // Manejo de estado para saber si se agrego o no la obsrvacion
        boolean estado = pDao.agregarObservacion(idPost, observacion);
        
        if (estado) {
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            System.out.println("SI SE PUDO MODIFICAR EL POST");
        }
        
        /**
         * Este servlet no se esta utilizando
         */
    }

}
