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
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

@WebServlet(name = "svCodigo", urlPatterns = {"/svCodigo"})
public class svCodigo extends HttpServlet {
    Usuario user = new Usuario(); // Instanciando un nuevo usuario para colocarle sus atributos
    UsuarioDao userDao = new UsuarioDao(); // instancia de un usuarioDao que manejara los procesos CRUD
    EnviarCodigo mensaje = new EnviarCodigo(); // Instancia de enviarCodigo para el manejo de los codigos de verificacion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(); // Sesion para atrapar la session
        request.setCharacterEncoding("UTF-8");
        
        String correo = request.getParameter("txtCorreo"); // Tomar el correo del formulario
        String clave = request.getParameter("txtPass"); // Tomar la contraseña del formulario
        String confirm = request.getParameter("txtConfirm"); // Tomar la confirmacion de la contraseña del formulario
        String codigo = mensaje.getRandom(); // Metodo de mensaje para poder obtener el numero random que sera el codigo para la autenticacion
        
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b"; // Regex para el instructor
        
        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compilador para el regex del aprendiz
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compilador para el regex del instructor
        
        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);
        
        if (clave.equals(confirm)) { // Si las contraseñas son iguales
            
            String encript = EncriptarContraseña.encriptar(clave); // Encripta la contraseña
            
            user.setCorreoInst(correo); // Se setea el correo institucional
            user.setPassword(encript); // Se setea la contraseña encriptada
            user.setCodigo(codigo); // Se setea el codigo aleatorio
            System.out.println(codigo);
            
            boolean encontrado = userDao.buscarUser(user); // Hace la validacion si existe o no un usuario en la base de datos
            
            try {
                if (encontrado != true) { // Si no s eencuentra un usuario con ese correo electronicp creado en la base de datos
                    if (mAprendiz.matches()) { // Si cumple con las condiciones del regex del aprendiz
                        mensaje.enviarEmail(user); // Envia el codigo de verificacion al correo que registro el usuario
                        sesion.setAttribute("autenticacion", user); // Atrapa la session que se le coloca como valor el objeto user
                        System.out.println("Es un Aprendiz");
                        response.sendRedirect("autenticacion.jsp"); // Redirije a la autenticacion del codigo enviado por email
                    }
                    else{
                        if (mInstructor.matches()) { // Si cumple con las condiciones del regex del instructor
                            mensaje.enviarEmail(user); // Envia el codigo de verificacion al correo que registro el usuario
                            sesion.setAttribute("autenticacion", user); // Atrapa la session que se le colocara como valor el objeto user
                            System.out.println("Es un Instructor");
                            response.sendRedirect("autenticacion.jsp"); // Redirije a la autenticacion del codigo enviado por email
                        }
                        else{
                            System.out.println("No se puede registrar ningun correo diferente al institucional"); // Debe registrar un correo institucional
                            response.sendRedirect("registro.jsp"); // Lo redirije a que haga otra vez el registro
                        }
                    }
                }
                else{
                    System.out.println("No se puede crear nuevo usuario");
                    response.sendRedirect("registro.jsp");
                }
            } 
            catch (AddressException ex) {
                Logger.getLogger(svCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("Las contraseñas no coinciden"); //  Si las contraseñas no coinciden
            response.sendRedirect("registro.jsp"); // Lo manda a que realize otra vez el registro
        }
    }
}
