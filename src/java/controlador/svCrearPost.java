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

@WebServlet(name = "svCrearPost", urlPatterns = {"/svCrearPost"},
      initParams = {@WebInitParam(name = "disallowedFileTypes", value = "exe,bat")})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
  maxFileSize = 1024 * 1024 * 50,      // 50 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class svCrearPost extends HttpServlet {

  PostDAO postDAO = new PostDAO();
  ArchivoDAO archivoDAO = new ArchivoDAO();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    String titulo = request.getParameter("titulo");
    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

    // Crear un nuevo objeto Post
    Post post = new Post();
    post.setTitulo(titulo);
    post.setIdUsuarioFk(idUsuario);
    post.setEstado(true); // Asigna un valor por defecto, modificar según sea necesario
    post.setObservacion(""); // Asigna un valor por defecto, modificar según sea necesario

    int idPost = 0;
    try {
      idPost = postDAO.crearPost(post);
    } catch (SQLException e) {
      System.out.println("No se pudo crear el post: " + e.getMessage());
      response.sendRedirect("crearPost.jsp");
      return;
    }

    // Manejo de los archivos subidos
    Collection<Part> fileParts = request.getParts();
    for (Part filePart : fileParts) {
      if (filePart.getName().equals("archivo") && filePart.getSize() > 0) {
        String fileName = filePart.getSubmittedFileName();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        byte[] fileContent = inputStreamToByteArray(filePart.getInputStream());

        Archivo archivo = new Archivo(0, extension, fileContent, idPost);
        try {
          archivoDAO.crearArchivo(archivo);
        } catch (SQLException e) {
          System.out.println("No se pudo crear el archivo: " + e.getMessage());
          response.sendRedirect("crearPost.jsp");
          return;
        }
      }
    }

    response.sendRedirect("inicio.jsp");
  }

  private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[16384];
    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }
    buffer.flush();
    return buffer.toByteArray();
  }
}
