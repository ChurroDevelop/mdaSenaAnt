package controlador;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ArchivoDAO;
import modelo.objetos.Archivo;

@WebServlet("/svListarArchivos")
public class svListarArchivos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));
        System.out.println(idPost);

        ArchivoDAO aDao = new ArchivoDAO();
        List<Archivo> listaArchivos = null;
        try {
            listaArchivos = aDao.listarArchivosPorPostId(idPost);
        } catch (SQLException ex) {
            Logger.getLogger(svListarArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Convertir la lista de archivos a JSON y enviarla como respuesta
        String json = new Gson().toJson(listaArchivos);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
