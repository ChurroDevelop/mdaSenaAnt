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

/**
 * Servlet para listar archivos asociados a un post específico.
 * Este servlet maneja la solicitud para listar los archivos relacionados con un post. Recibe el ID del post a través
 * de una solicitud POST, obtiene la lista de archivos asociados a dicho post desde la base de datos y envía la lista
 * en formato JSON como respuesta.
 */
@WebServlet("/svListarArchivos")
public class svListarArchivos extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para listar archivos asociados a un post.
     * 
     * @param request La solicitud HTTP del cliente, que contiene el parámetro "txtIdPost".
     * @param response La respuesta HTTP al cliente.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException Si ocurre un error al leer o escribir datos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Captura el ID del post y lo convierte a entero
        int idPost = Integer.parseInt(request.getParameter("txtIdPost"));

        // Instancia de un ArchivoDAO para listar los archivos de dicho post
        ArchivoDAO aDao = new ArchivoDAO();
        
        // Lista de archivos con valor inicial nulo
        List<Archivo> listaArchivos = null;
        
        // Manejo de errores
        try {
            // Asigna a la lista los archivos asociados al ID del post capturado
            listaArchivos = aDao.listarArchivosPorPostId(idPost);
        } catch (SQLException ex) {
            // Registro de errores en caso de fallo en la consulta SQL
            Logger.getLogger(svListarArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Convierte la lista de archivos a JSON y la envía como respuesta
        String json = new Gson().toJson(listaArchivos);
        
        // Configura el tipo de contenido que será visible al cliente
        response.setContentType("application/json");
    
        // Configura la codificación de la respuesta
        response.setCharacterEncoding("UTF-8");
        
        // Escribe los datos JSON en la respuesta
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
