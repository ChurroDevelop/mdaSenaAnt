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

/**
 * Servlet para manejar la modificación de un perfil de usuario. Permite
 * actualizar los datos del perfil del usuario en la base de datos.
 */
@WebServlet(name = "svModificar", urlPatterns = {"/svModificar"})
public class svModificar extends HttpServlet {

    // Instancia de PerfilDAO para manejar operaciones de actualización de perfil en la base de datos
    private final PerfilDAO pDao = new PerfilDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual para acceder a los datos del perfil del usuario
        HttpSession sesion = request.getSession();

        // Configura la codificación de caracteres para manejar acentos y caracteres especiales
        request.setCharacterEncoding("UTF-8");

        // Recupera el objeto Perfil desde la sesión
        Perfil p = (Perfil) sesion.getAttribute("dataPerfil");

        // Obtiene los datos del formulario para actualizar el perfil
        String idPerfil = request.getParameter("txtIdPerfil");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String numero = request.getParameter("txtDocumento");
        String centro = request.getParameter("txtCentro");

        // Convierte el ID del perfil a entero
        int idProfile = Integer.parseInt(idPerfil);

        // Configura el objeto Perfil con los datos actualizados
        p.setId_perfil(idProfile);
        p.setNombre_usuario(nombre);
        p.setApellido_usuario(apellido);
        p.setNum_documento(numero);
        p.setCentro_formacion(centro);

        // Intenta actualizar el perfil en la base de datos
        boolean isUpdate = pDao.actualizarPerfil(p);

        // Verifica si la actualización fue exitosa y redirige al usuario
        if (isUpdate) {
            System.out.println("Se modificó el perfil exitosamente");  // Mensaje en consola para confirmación
            response.sendRedirect("editarPerfil.jsp");  // Redirige al usuario a la página de edición de perfil
        } else {
            System.out.println("No se pudo actualizar el perfil");  // Mensaje en consola en caso de error
        }
    }
}
