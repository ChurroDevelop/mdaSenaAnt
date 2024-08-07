package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import modelo.PostDAO;


@WebServlet("/svEstadoPost")
public class svEstadoPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Instanciar la nueva sesion
        HttpSession sesion = request.getSession();
        
        // Instancia de un postDao para manejo a la base de datos
        PostDAO pDao = new PostDAO();
        
        // Tomar el id del post y el id del instrucutor
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));
        String idInstructor = request.getParameter("txtIdInstructor");
        
        // Manejo del estado, para saber si se modifico o no el post
        boolean estado = pDao.modificarEstado(idPost);
        
        if (estado) {
            // Se sobre escribe la sesion con los post de los monitores
            sesion.setAttribute("listaPosts", pDao.listarPostsUser(idInstructor));
            
            // Depuracion del codigo
            System.out.println("Se modifico el post y ya es visible en la plataforma");
            
            /*
                Este servlet no se esta utilizando
            */
        }
        
        
    }

}
