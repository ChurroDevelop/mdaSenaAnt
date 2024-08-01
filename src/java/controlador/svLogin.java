package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EncriptarContraseña;
import modelo.PerfilDAO;
import modelo.UsuarioDao;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;

/**
 * Servlet para manejar el inicio de sesión de usuarios. Valida las credenciales
 * proporcionadas por el usuario y establece la sesión correspondiente.
 */
public class svLogin extends HttpServlet {

    // Instancias de los DAOs y objetos necesarios para la autenticación
    private final UsuarioDao userDao = new UsuarioDao(); // DAO para manejar operaciones de usuario
    private final PerfilDAO profileDao = new PerfilDAO(); // DAO para manejar operaciones de perfil
    private final Usuario u = new Usuario(); // Objeto Usuario para almacenar credenciales
    private Perfil profile = new Perfil(); // Objeto Perfil para almacenar la información del perfil del usuario

    /**
     * Maneja las solicitudes POST para autenticar al usuario y establecer la
     * sesión.
     *
     * @param request Solicitud HTTP que contiene las credenciales del usuario.
     * @param response Respuesta HTTP que indica el resultado de la
     * autenticación.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtiene la sesión actual para almacenar los datos del usuario y del perfil
        HttpSession sesion = request.getSession();
        HttpSession sesionPerfil = request.getSession();

        // Configura la codificación de caracteres para manejar acentos y caracteres especiales
        request.setCharacterEncoding("UTF-8");

        // Recupera el correo y la contraseña proporcionados por el usuario en el formulario
        String correo = request.getParameter("txtCorreo");
        String password = request.getParameter("txtClave");

        // Encripta la contraseña para compararla con la almacenada en la base de datos
        String encript = EncriptarContraseña.encriptar(password);

        // Configura el objeto Usuario con el correo y la contraseña encriptada
        u.setCorreoInst(correo);
        u.setPassword(encript);

        System.out.println("Usuario inicial: " + u.getCorreoInst());

        // Obtiene la información del usuario desde la base de datos
        Usuario newUser = userDao.getDataUser(u);
        profile = profileDao.dataPerfil(newUser);

        // Configura el tipo de respuesta como texto plano
        response.setContentType("text/plain");

        // Verifica las credenciales del usuario
        if (userDao.autenticacion(u)) {
            // Si la autenticación es exitosa, almacena los datos del usuario y del perfil en la sesión
            sesion.setAttribute("dataUser", newUser);
            sesionPerfil.setAttribute("dataPerfil", profile);
            response.getWriter().write("success"); // Escribe 'success' en la respuesta
        } else {
            response.getWriter().write("error"); // Escribe 'error' en la respuesta si las credenciales son incorrectas
        }
    }
}
