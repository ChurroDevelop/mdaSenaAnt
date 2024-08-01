package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.RolDAO;
import modelo.UsuarioDao;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

/**
 * Servlet para manejar el registro de usuarios, que verifica el código de
 * autenticación y asigna roles a los usuarios.
 */
@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {

    private final UsuarioDao userDao = new UsuarioDao();  // DAO para operaciones relacionadas con usuarios
    private final RolDAO rolDao = new RolDAO();  // DAO para operaciones relacionadas con roles
    private Rol rol = new Rol();  // Objeto Rol para almacenar información del rol

    /**
     * Maneja las solicitudes POST para registrar un nuevo usuario y asignar un
     * rol basado en el correo electrónico.
     *
     * @param request Solicitud HTTP que contiene el código de autenticación y
     * datos del usuario.
     * @param response Respuesta HTTP que indica el resultado del registro.
     * @throws ServletException Si ocurre un error durante el procesamiento de
     * la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUser = request.getSession();  // Obtiene la sesión del usuario autenticado
        HttpSession sesionId = request.getSession();  // Obtiene la sesión para almacenar el usuario registrado
        request.setCharacterEncoding("UTF-8");  // Configura la codificación de caracteres para manejar caracteres especiales

        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");  // Recupera el objeto Usuario de la sesión
        String autenticacion = request.getParameter("txtCodigo");  // Obtiene el código de verificación enviado por el usuario

        // Expresiones regulares para validar los correos electrónicos de aprendiz e instructor
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b";

        // Compilación de las expresiones regulares
        final Pattern pAprendiz = Pattern.compile(expAprendiz);
        final Pattern pInstructor = Pattern.compile(expInstructor);

        // Creación de matchers para verificar el correo electrónico del usuario
        Matcher mAprendiz = pAprendiz.matcher(user.getCorreoInst());
        Matcher mInstructor = pInstructor.matcher(user.getCorreoInst());

        int id_rol;  // Variable para almacenar el ID del rol
        boolean insertado;  // Resultado de la operación de registro
        int idUser;  // ID del usuario registrado

        response.setContentType("text/plain");  // Establece el tipo de contenido de la respuesta como texto plano
        PrintWriter out = response.getWriter();  // Obtiene el escritor para enviar la respuesta

        try {
            // Verifica si el código ingresado por el usuario coincide con el código almacenado en la sesión
            if (autenticacion.equals(user.getCodigo())) {
                // Verifica el tipo de usuario basado en el correo electrónico
                if (mAprendiz.matches()) {
                    id_rol = 1;  // ID del rol para aprendiz
                    insertado = userDao.registrarUsuario(user, id_rol);  // Registra al usuario con el rol de aprendiz
                    idUser = userDao.obtenerId(user.getCorreoInst());  // Obtiene el ID del usuario registrado
                    user.setId_usuario(idUser);  // Asigna el ID al objeto Usuario
                    rol = rolDao.getIdRol(user);  // Obtiene el rol del usuario
                    user.setId_rol_fk(rol);  // Asigna el rol al objeto Usuario
                    sesionId.setAttribute("UsuarioAprendiz", user);  // Almacena el usuario en la sesión como aprendiz

                    if (insertado) {
                        out.print("success");  // Respuesta exitosa si el registro fue exitoso
                    } else {
                        out.print("error");  // Respuesta de error si el registro falló
                    }
                } else if (mInstructor.matches()) {
                    id_rol = 2;  // ID del rol para instructor
                    insertado = userDao.registrarUsuario(user, id_rol);  // Registra al usuario con el rol de instructor
                    idUser = userDao.obtenerId(user.getCorreoInst());  // Obtiene el ID del usuario registrado
                    user.setId_usuario(idUser);  // Asigna el ID al objeto Usuario
                    rol = rolDao.getIdRol(user);  // Obtiene el rol del usuario
                    user.setId_rol_fk(rol);  // Asigna el rol al objeto Usuario
                    sesionId.setAttribute("UsuarioInstructor", user);  // Almacena el usuario en la sesión como instructor

                    if (insertado) {
                        out.print("success");  // Respuesta exitosa si el registro fue exitoso
                    } else {
                        out.print("error");  // Respuesta de error si el registro falló
                    }
                } else {
                    out.print("invalid_email");  // Respuesta de error si el correo electrónico no coincide con ningún rol esperado
                }
            } else {
                out.print("code_mismatch");  // Respuesta de error si el código de autenticación no coincide
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());  // Imprime el mensaje de error en la consola
            e.printStackTrace();  // Imprime el traza del error en la consola
            out.print("error");  // Respuesta de error en caso de excepción
        }

        out.flush();  // Asegura que todos los datos se envíen a la respuesta
    }
}
