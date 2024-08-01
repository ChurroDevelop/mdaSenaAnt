package servlets;

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
        if (idDocumentoStr == null || idDocumentoStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del documento no proporcionado");
            return;
        }

        int idDocumento;
        try {
            idDocumento = Integer.parseInt(idDocumentoStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del documento inválido");
            return;
        }

        // Obtener el archivo desde la base de datos
        ArchivoDAO archivoDAO = new ArchivoDAO();
        Archivo archivo = null;
        try {
            archivo = archivoDAO.obtenerArchivoPorId(idDocumento);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el archivo");
            return;
        }

        if (archivo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Archivo no encontrado");
            return;
        }

        // Configurar la respuesta HTTP para la descarga del archivo
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=documento." + archivo.getExtensionDocumento());
        response.setContentLength(archivo.getDocumento().length);

        // Enviar el archivo al cliente
        try (OutputStream out = response.getOutputStream()) {
            out.write(archivo.getDocumento());
        }
    }
}
