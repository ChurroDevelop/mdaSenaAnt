package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EnviarCodigoContrasena;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

@WebServlet(name = "svCorreoRecuperacion", urlPatterns = {"/svCorreoRecuperacion"})
public class svCorreoRecuperacion extends HttpServlet {

    Usuario user = new Usuario(); // Instanciando un nuevo usuario para colocarle sus atributos
    UsuarioDao userDao = new UsuarioDao(); // instancia de un usuarioDao que manejara los procesos CRUD
    EnviarCodigoContrasena mensaje = new EnviarCodigoContrasena();

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet svCorreoRecuperacion</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet svCorreoRecuperacion at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession sesion = request.getSession(); // Sesion para atrapar la session
        request.setCharacterEncoding("UTF-8");

        String correo = request.getParameter("txtCorreo"); // Tomar el correo del formulario
        String codigo = mensaje.getRandom(); // Metodo de mensaje para poder obtener el numero random que sera el codigo para la autenticacion

        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b"; // Regex para el instructor

        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compilador para el regex del aprendiz
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compilador para el regex del instructor

        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);

        user.setCorreoInst(correo);
        user.setCodigo(codigo); // Se setea el codigo aleatorio

        boolean encontrado = userDao.buscarUser(user); // Hace la validacion si existe o no un usuario en la base de datos

        if (mInstructor.matches() || mAprendiz.matches()) {
            if (encontrado == true) {
                System.out.println("Correo: " + correo);
                try {
                    mensaje.enviarCodigoRecuperacion(user);
                    System.out.println("Se le envió el código");
                    sesion.setAttribute("autenticacion", user); // Atrapa la session que se le coloca como valor el objeto user
                    response.sendRedirect("codigoContrasena.jsp"); // Redirije a la autenticacion del codigo enviado por email
                } catch (AddressException ex) {
                    Logger.getLogger(svCorreoRecuperacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("El usuario no existe");
            }
        } else {
            System.out.println("La extensión del correo no es válida");
        }

    }

}
