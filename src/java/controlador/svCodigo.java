package controlador;

import java.io.IOException;
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
import modelo.EncriptarContraseña;
import modelo.EnviarCodigo;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

@WebServlet(name = "svCodigo", urlPatterns = {"/svCodigo"})
public class svCodigo extends HttpServlet {
    Usuario user = new Usuario(); // Instanciando un nuevo usuario para colocarle sus atributos
    EnviarCodigo mensaje = new EnviarCodigo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(); // Sesion para atrapar la session
        
        String correo = request.getParameter("txtCorreo"); // Tomar el correo del formulario
        String clave = request.getParameter("txtPass"); // Tomar la contraseña del formulario
        String confirm = request.getParameter("txtConfirm"); // Tomar la confirmacion de la contraseña del formulario
        String codigo = mensaje.getRandom();
        
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@misena\\.edu\\.co\\b"; // Regex para el instructor
        
        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compilador para el regex del aprendiz
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compilador para el regex del instructor
        
        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);
        
        if (clave.equals(confirm)) {
            
            String encript = EncriptarContraseña.encriptar(clave);
            
            user.setCorreoInst(correo);
            user.setPassword(encript);
            user.setCodigo(codigo);
            try {
                if (mAprendiz.matches()) {
                    mensaje.enviarEmail(user);
                    sesion.setAttribute("autenticacion", user);
                    System.out.println("Es un Aprendiz");
                    response.sendRedirect("autenticacion.jsp");
                }
                else{
                    if (mInstructor.matches()) {
                        mensaje.enviarEmail(user);
                        sesion.setAttribute("autenticacion", user);
                        System.out.println("Es un instructor");
                        response.sendRedirect("autenticacion.jsp");
                    }
                    else{
                        System.out.println("No puedes ingresar ningun otro correo aparte del institucional");
                        response.sendRedirect("registro.jsp");
                    }
                }
            } catch (AddressException ex) {
                Logger.getLogger(svCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("Las contraseñas no coinciden");
            response.sendRedirect("registro.jsp");
        }
    }

}
