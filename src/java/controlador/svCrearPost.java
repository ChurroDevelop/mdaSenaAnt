package controlador;

import modelo.PostDAO;
import modelo.ArchivoDAO;
import modelo.objetos.Post;
import modelo.objetos.Archivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Servlet para manejar la creación de posts y la carga de archivos asociados.
 * Configurado para manejar archivos de hasta 50 MB y solicitudes de hasta 100
 * MB.
 */
@WebServlet(name = "svCrearPost", urlPatterns = {"/svCrearPost"},
        initParams = {
            @WebInitParam(name = "disallowedFileTypes", value = "exe,bat")})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // Umbral para almacenar archivos temporalmente en la memoria (2 MB)
        maxFileSize = 1024 * 1024 * 50, // Tamaño máximo de archivo permitido (50 MB)
        maxRequestSize = 1024 * 1024 * 100 // Tamaño máximo de solicitud permitido (100 MB)
)
public class svCrearPost extends HttpServlet {

    private final PostDAO postDAO = new PostDAO();  // DAO para manejar operaciones con posts
    private final ArchivoDAO archivoDAO = new ArchivoDAO();  // DAO para manejar operaciones con archivos

    /**
     * Maneja las solicitudes POST para crear un nuevo post y cargar archivos
     * asociados.
     *
     * @param request Solicitud HTTP que contiene los datos del post y los
     * archivos.
     * @param response Respuesta HTTP para redirigir al usuario después de
     * procesar la solicitud.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // Establece la codificación de caracteres para la solicitud

        // Obtener los parámetros del post desde la solicitud
        String titulo = request.getParameter("titulo");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        // Crear un nuevo objeto Post y asignar valores
        Post post = new Post();
        post.setTitulo(titulo);
        post.setIdUsuarioFk(idUsuario);
        post.setEstado(false); // Asigna un valor por defecto, modificar según sea necesario
        post.setObservacion(""); // Asigna un valor por defecto, modificar según sea necesario

        int idPost = 0;
        try {
            // Crear el post en la base de datos
            idPost = postDAO.crearPost(post);
        } catch (SQLException e) {
            // Manejo de errores si no se puede crear el post
            System.out.println("No se pudo crear el post: " + e.getMessage());
            response.sendRedirect("crearPost.jsp");
            return;
        }

        // Manejo de los archivos subidos
        Collection<Part> fileParts = request.getParts();
        for (Part filePart : fileParts) {
            if (filePart.getName().equals("archivo") && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();  // Obtener el nombre del archivo
                String extension = fileName.substring(fileName.lastIndexOf('.') + 1);  // Obtener la extensión del archivo
                byte[] fileContent = inputStreamToByteArray(filePart.getInputStream());  // Convertir el contenido del archivo a bytes

                // Crear un nuevo objeto Archivo y asignar valores
                Archivo archivo = new Archivo(0, extension, fileContent, idPost);
                try {
                    // Crear el archivo en la base de datos
                    archivoDAO.crearArchivo(archivo);
                } catch (SQLException e) {
                    // Manejo de errores si no se puede crear el archivo
                    System.out.println("No se pudo crear el archivo: " + e.getMessage());
                    response.sendRedirect("crearPost.jsp");
                    return;
                }
            }
        }

        // Redirigir al usuario a la página de inicio después de procesar la solicitud
        response.sendRedirect("inicio.jsp");
    }

    /**
     * Convierte un InputStream en un array de bytes.
     *
     * @param inputStream InputStream que contiene el contenido del archivo.
     * @return Array de bytes que representa el contenido del archivo.
     * @throws IOException Si ocurre un error durante la lectura del
     * InputStream.
     */
    private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];  // Tamaño del buffer de lectura
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
