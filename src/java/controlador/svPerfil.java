package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PerfilDAO;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;

/**
 * Servlet para manejar el registro de perfiles de usuario. Permite actualizar o
 * crear un perfil asociado al usuario autenticado.
 */
@WebServlet(name = "svPerfil", urlPatterns = {"/svPerfil"})
public class svPerfil extends HttpServlet {

    private final PerfilDAO perfilDao = new PerfilDAO();  // DAO para operaciones CRUD relacionadas con perfiles

    /**
     * Maneja las solicitudes POST para registrar o actualizar el perfil de un
     * usuario.
     *
     * @param request Solicitud HTTP que contiene la información del perfil del
     * usuario.
     * @param response Respuesta HTTP que redirige al usuario después del
     * registro.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUser = request.getSession();  // Obtiene la sesión del usuario autenticado
        request.setCharacterEncoding("UTF-8");  // Configura la codificación de caracteres para manejar acentos y caracteres especiales

        // Recupera los parámetros del formulario de perfil
        String nombre = request.getParameter("txtNombre");
        String apellidos = request.getParameter("txtApellidos");
        String documento = request.getParameter("txtDocumento");
        String centro = request.getParameter("txtCentro");

        // Recupera el objeto Usuario de la sesión
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");

        // Crea y configura el objeto Perfil con la información del formulario
        Perfil perfil = new Perfil();
        perfil.setNombre_usuario(nombre);
        perfil.setApellido_usuario(apellidos);
        perfil.setNum_documento(documento);
        perfil.setCentro_formacion(centro);

        try {
            // Registra o actualiza el perfil en la base de datos
            perfilDao.registroPerfil(perfil, user.getId_usuario());
            System.out.println("Se registró el perfil exitosamente");  // Mensaje en consola para confirmación
            response.sendRedirect("login.jsp");  // Redirige al usuario a la página de login
        } catch (Exception e) {
            // Manejo de errores en caso de excepción
            System.out.println("Error en el Perfil DAO: " + e.getMessage());
        }
    }
}
