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
        HttpSession sesionUser = request.getSession();
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");
        
        String autenticacion = request.getParameter("txtCodigo");
        
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@misena\\.edu\\.co\\b"; // Regex para el instructor
        
        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compilador para el regex del aprendiz
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compilador para el regex del instructor
        
        Matcher mAprendiz = pAprendiz.matcher(user.getCorreoInst());
        Matcher mInstructor = pInstructor.matcher(user.getCorreoInst());
        
        int rol; // Setear el rol dependiendo del regex
        boolean insertado;
        
        if (autenticacion.equals(user.getCodigo())) {
            try {
                if (mAprendiz.matches()) {
                    rol = 1;
                    insertado = userDao.registrarUsuario(user, rol);
                    System.out.println("Aprendiz creado");
                    if (insertado != false) {
                        response.sendRedirect("crearPerfil.jsp");
                    }
                    else{
                        System.out.println("Hubo un error en el usuario DAO");
                    }
                }
                else{
                    if (mInstructor.matches()) {
                        rol = 2;
                        insertado = userDao.registrarUsuario(user, rol);
                        System.out.println("Instructor creado");
                        if (insertado != false) {
                            response.sendRedirect("crearPerfil.jsp");
                        }
                        else{
                            System.out.println("Hubo un error en el usuario DAO");
                        }
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
