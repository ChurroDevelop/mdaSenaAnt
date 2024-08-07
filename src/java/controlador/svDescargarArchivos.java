package controlador;

import modelo.ArchivoDAO;
import modelo.objetos.Archivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@WebServlet("/descargarArchivo")
public class svDescargarArchivos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del documento desde la solicitud
        String idDocumentoStr = request.getParameter("id");
        
        // Verifica si el id esta presente o esta nulo
        if (idDocumentoStr == null || idDocumentoStr.isEmpty()) {
            
            // Envia error 400 (BAD REQUEST) con un mensaje personalizado
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del documento no proporcionado");
            
            // Rompe de una vez la ejecucion del servlet
            return;
        }

        // Declaracion de variable del documento
        int idDocumento;
        try {
            // Intenta convertir el dato entrante en entero
            idDocumento = Integer.parseInt(idDocumentoStr);
            
            // Captura el error si la conversion fue invalida
        } catch (NumberFormatException e) {
            
            // Envia error 400 (BAD REQUEST) con un mensaje personalizado
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del documento inválido");
            
            // Rompe de una vez la ejecucion del servlet
            return;
        }

        // Instancia de un nuevo ArchivoDAO para obtener los archivos de la base de datos
        ArchivoDAO archivoDAO = new ArchivoDAO();
        
        // Instancia de un nuevo objeto tipo Archiv que se le asigna el valor de null
        Archivo archivo = null;
        try {
            // Al objeto archivo se le asigna el valor del archivo que retorna la funcion del objeto archivoDAO
            archivo = archivoDAO.obtenerArchivoPorId(idDocumento);
        } catch (SQLException e) {
            
            // Envia error 400 (BAD REQUEST) con un mensaje personalizado
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el archivo");
            
            // Rompe de una vez la ejecucion del servlet
            return;
        }

        // Si el archivo sigue siendo nulo
        if (archivo == null) {
            
            // Envia error 400 (BAD REQUEST) con un mensaje personalizado
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Archivo no encontrado");
            
            // Rompe de una vez la ejecucion del servlet
            return;
        }

        // Configurar la respuesta HTTP para la descarga del archivo
        
        // Configura el tipo de contenido que retornara la respuesta, que indica el contenido del archivo en binario
        response.setContentType("application/octet-stream");
        
        // Configura la cabecera para establecer que se debe descargar como archvio adjunto con el nombre establecido
        response.setHeader("Content-Disposition", "attachment; filename=" + archivo.getNombreDocumento());
        
        // Configura la longitud del contendio de respuesta en tamaño de bytes
        response.setContentLength(archivo.getDocumento().length);

        // Enviar el archivo al cliente
        try (OutputStream out = response.getOutputStream()) {
            
            // Escribe el archivo en la vista al cliente
            out.write(archivo.getDocumento());
        }
    }
}
