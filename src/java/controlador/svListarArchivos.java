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
        // Atrapar el id del post y parsearlo a entero
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));

        // Instancia de un nuevo archivoDao para listar los archivos de dicho post
        ArchivoDAO aDao = new ArchivoDAO();
        
        // Nuevo arreglo de archivos con valor inicial de null
        List<Archivo> listaArchivos = null;
        
        // Manejo de errores
        try {
            // Se le asigna al arreglo los archivos que contengan el id capturado
            listaArchivos = aDao.listarArchivosPorPostId(idPost);
        } catch (SQLException ex) {
            // Erro por si algo sale malen al consulta SQL
            Logger.getLogger(svListarArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Convertir la lista de archivos a JSON y enviarla como respuesta
        String json = new Gson().toJson(listaArchivos);
        
        // Tipo de dato que sera visible al cliente
        response.setContentType("application/json");
    
        // Cotejamiento de la respuesta
        response.setCharacterEncoding("UTF-8");
        
        // Pintar los datos en la vista
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
