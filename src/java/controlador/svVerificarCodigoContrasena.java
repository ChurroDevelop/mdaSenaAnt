package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

@WebServlet(name = "svVerificarCodigoContrasena", urlPatterns = {"/svVerificarCodigoContrasena"})
public class svVerificarCodigoContrasena extends HttpServlet {

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

        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion"); // Recupera el objeto Usuario de la sesión
        String autenticacion = request.getParameter("txtCodigo"); // Obtiene el código de verificación enviado por el usuario en el formulario

        // Verifica si el código ingresado por el usuario coincide con el código almacenado en el objeto Usuario
        if (autenticacion.equals(user.getCodigo())) {
            System.out.println("El código coincide");
            sesionUser.setAttribute("autenticacion", user); // Actualiza la sesión con el objeto Usuario
            response.sendRedirect("cambiarContrasena.jsp"); // Redirige al usuario a la página para cambiar la contraseña
        } else {
            System.out.println("El código no coincide");
            response.sendRedirect("codigoContrasena.jsp"); // Redirige al usuario a la página de verificación del código
        }
    }

}
