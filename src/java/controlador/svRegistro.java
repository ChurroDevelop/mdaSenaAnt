/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import modelo.PasswordEncryptionUtil;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author Propietario
 */
@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {
    UsuarioDAO usuDao = new UsuarioDAO();
    Usuario u = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b"; // Regex para el aprendiz
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@misena\\.edu\\.co\\b"; // Regex para el instructor
        final Pattern pAprendiz = Pattern.compile(expAprendiz); // Compilador de los regex
        final Pattern pInstructor = Pattern.compile(expInstructor); // Compilador de los regex
        
        String correo = request.getParameter("txtCorreo"); // Toma el correo entrante
        String pass = request.getParameter("txtPass"); // Toma la contraseña entrante
        String confirm = request.getParameter("txtConfirm"); // Toma la confirmacion de la contraseña entrante
        
        Matcher mAprendiz = pAprendiz.matcher(correo); // Se valida si coincide con el correo del aprendiz
        Matcher mInstructor = pInstructor.matcher(correo); // Se valida si coincide con el correo del instructor
        
        int rol = 0;
        
        if (pass.equals(confirm)) {
            System.out.println("Las contraseñas coinciden");
            String encriptPassword = PasswordEncryptionUtil.encriptar(pass);
            try {
                if (mAprendiz.matches()) {
                    rol = 1;
                    
                    u.setCorreoInstitucional(correo);
                    u.setContrasena(encriptPassword);
//                    u.setId_rol_fk();
                    usuDao.registro(u);
//                    boolean ola = usuDao.registroUsuario(correo, pass, rol);
//                    usuDao.registro(user);
//                    System.out.println(ola);
                    System.out.println("El correo registrado es del aprendiz");
                }
                else{
                    if (mInstructor.matches()) {
                        rol = 2;
                        System.out.println("El correo registrado es del instructor");
                    }
                    else{
                        System.out.println("No se permiten correos aparte de los institucionales");
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println("Las contraseñas no coinciden");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
