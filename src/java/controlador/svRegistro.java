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
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {
    UsuarioDao userDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUser = request.getSession(); // Sesion para el usuario
        HttpSession sesionId = request.getSession(); // Session para el id del usuario
        request.setCharacterEncoding("UTF-8"); // Cotejamiento para el tema de los acentos en la base de datos
        
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion"); // Atrapa el valor de lo que hay en la session de autenticacion
        
        String autenticacion = request.getParameter("txtCodigo"); // Atrapa el codigo de verificacion que se le dio al usuario en el formulario
        
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@misena\\.edu\\.co\\b"; // Regex para el instructor
        
        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compilador para el regex del aprendiz
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compilador para el regex del instructor
        
        Matcher mAprendiz = pAprendiz.matcher(user.getCorreoInst());
        Matcher mInstructor = pInstructor.matcher(user.getCorreoInst());
        
        int rol; // Setear el rol dependiendo del regex
        boolean insertado; // Setear el estado del usuario creado para poder redireccionarlo a las vistas
        int idUser; // Para obtener el id
        
        if (autenticacion.equals(user.getCodigo())) { // Si el codigo que ingresa el usuario es igual al del codigo que se le mando al correo
            
            try { // Manejo de errores
                if (mAprendiz.matches()) { // Si el correo coincide con el regex del aprendiz 
                    rol = 1; // Se le asignara el rol 1
                    insertado = userDao.registrarUsuario(user, rol); // Se mandara el usuario para el registro de usuarios
                    idUser = userDao.obtenerId(user.getCorreoInst()); // Se mandara el correo del usuario y se obtendra el id del usuario para poder asociarlo con el perfil
                    user.setId_usuario(idUser); // Se setea el id del usuario en la clase usuario
                    sesionId.setAttribute("UsuarioId", user); // Se atrapa el usuario con el nombre "UsuarioId"
                    
                    if (insertado != false) { // Verificacion si se creo el usuario
                        System.out.println("Se creo el usuario");
                        response.sendRedirect("crearPerfil.jsp");
                    }
                    else{ // Si no hubo error en el usuario DAO
                        System.out.println("Hubo problemas en el usuario DAO");
                    }
                }
                else{
                    if (mInstructor.matches()) {
                        rol = 2;
                        insertado = userDao.registrarUsuario(user, rol);
                        idUser = userDao.obtenerId(user.getCorreoInst());
                        user.setId_usuario(idUser);
                        sesionId.setAttribute("UsuarioId", user);
                        
                        if (insertado != false) {
                            System.out.println("Se creo el usuario instructor");
                            response.sendRedirect("crearPerfil.jps");
                        }
                        else{
                            System.out.println("Hubo problemas en el usuario DAO");
                        }
                    }
                    else {
                        response.sendRedirect("registro.jsp");
                        System.out.println("No se pudo crear el usuario ya que no cumple con las condiciones del correo institucional");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en el usuario DAO");
            }
            
        }
        else{
            System.out.println("El codigo de verificacion no coinciden");
            response.sendRedirect("autenticacion.jsp");
        }
    }

}
