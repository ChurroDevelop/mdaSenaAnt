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
        fileSizeThreshold = 1024 * 1024 * 5, // Umbral para almacenar archivos temporalmente en la memoria (2 MB)
        maxFileSize = 1024 * 1024 * 50, // Tamaño máximo de archivo permitido (50 MB)
        maxRequestSize = 1024 * 1024 * 100 // Tamaño máximo de solicitud permitido (100 MB)
)
public class svCrearPost extends HttpServlet {

    // DAO para manejar operaciones con posts
    private final PostDAO postDAO = new PostDAO();  
    
    // DAO para manejar operaciones con archivos
    private final ArchivoDAO archivoDAO = new ArchivoDAO();  

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establece la codificación de caracteres para la solicitud
        request.setCharacterEncoding("UTF-8");  

        // Obtener los parámetros del post desde la solicitud del formulario
        String titulo = request.getParameter("titulo");
        
        // Castear el id del usuario a entero
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        // Crear un nuevo objeto Post y asignar valores por metodos setters
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
            
            // Retorna a la vista de crear post por parte del monitor
            response.sendRedirect("crearPost.jsp");
            
            // Rompe de una vez la ejecucion del servlet
            return;
        }

        // Manejo de los archivos subidos
        
        // Recupera todas las partes <de una solicitud multipart
        Collection<Part> fileParts = request.getParts();
        
        // Iterador sobre cada archivo subido
        for (Part filePart : fileParts) {
            
            // Filtra los archivos que tengan el nombre archivo, y que el tamaño sea mayor a 0
            if (filePart.getName().equals("archivo") && filePart.getSize() > 0) {
                
                // Obtener el nombre del archivo
                String fileName = filePart.getSubmittedFileName();
                
                // Mensaje de depuracion para saber si se tomo o no el archivo
                System.out.println(fileName);
                
                // Obtener la extensión del archivo, toma la extension despues de que encuentre un punto en su nombre
                String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
                
                // Convertir el contenido del archivo a bytes
                byte[] fileContent = convertirArrayBite(filePart.getInputStream());  

                // Crear un nuevo objeto Archivo y asignar valores
                Archivo archivo = new Archivo(0, extension, fileName, fileContent, idPost);
                
                // Manejo de errores
                try {
                    // Crear el archivo en la base de datos
                    archivoDAO.crearArchivo(archivo);
                } catch (SQLException e) {
                    // Manejo de errores si no se puede crear el archivo
                    System.out.println("No se pudo crear el archivo: " + e.getMessage());
                    
                    // Redirije a la vista de crear post por parte del monitor
                    response.sendRedirect("crearPost.jsp");
                    
                    // Rompe de una vez la ejecucion del servlet
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
    private byte[] convertirArrayBite(InputStream inputStream) throws IOException {
        
        // Crear un nuevo buffer para almacenar temporalmente los bytes leidos
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        
        // Variable para almacenar el numero de bytes leidos en cada iteracion
        int nRead;
        
        // Tamaño de 16 KB del buffer de lectura
        byte[] data = new byte[16384];
        
        // Bucle para leer byte por byte comenzando por el indice 0
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        
        // Al almacenar los datos en memoria, se hace un llamado para que los datos sean procesdos
        buffer.flush();
        
        // Retorna el arreglo de bytes, para subir el documento a la base de datos
        return buffer.toByteArray();
    }
}
