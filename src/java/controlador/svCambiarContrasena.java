package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EncriptarContraseña;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

@WebServlet(name = "svCambiarContrasena", urlPatterns = {"/svCambiarContrasena"})
public class svCambiarContrasena extends HttpServlet {

    UsuarioDao userDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesionUser = request.getSession(); // Obtiene la sesión del usuario
        request.setCharacterEncoding("UTF-8"); // Configura la codificación de caracteres para evitar problemas con acentos y caracteres especiales

        String clave = request.getParameter("txtClave"); // Obtiene la nueva contraseña ingresada por el usuario en el formulario
        String confirmarClave = request.getParameter("txtConfirmarClave"); // Obtiene la confirmación de la nueva contraseña ingresada por el usuario

        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion"); // Recupera el objeto Usuario de la sesión
        System.out.println("Usuario: " + user.getCorreoInst()); // Imprime en consola el correo del usuario autenticado

        // Verifica si la nueva contraseña y su confirmación coinciden
        if (clave.equals(confirmarClave)) {
            // Encripta la nueva contraseña
            String encript = EncriptarContraseña.encriptar(confirmarClave);
            try {
                System.out.println("Las contraseñas coinciden"); // Mensaje de confirmación en consola
                // Cambia la contraseña del usuario en la base de datos
                userDao.cambiarContrasena(userDao.obtenerId(user.getCorreoInst()), encript);
            } catch (SQLException ex) {
                Logger.getLogger(svCambiarContrasena.class.getName()).log(Level.SEVERE, null, ex); // Manejo de excepciones de SQL
            }
        } else {
            System.out.println("Las contraseñas no coinciden"); // Mensaje de error en consola si las contraseñas no coinciden
        }
    }

}
