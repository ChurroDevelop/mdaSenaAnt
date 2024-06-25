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
import javax.servlet.http.HttpSession;
import modelo.Conexion;
import modelo.PasswordEncryptionUtil;
import modelo.UsuarioDAO;
import modelo.objetos.Usuario;

@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {
    UsuarioDAO userDao = new UsuarioDAO();
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
        
        HttpSession sesion = request.getSession();
        String correo = request.getParameter("txtCorreo");
        String clave = request.getParameter("txtPass");
        String confirm = request.getParameter("txtConfirm");
        
        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@misena\\.edu\\.co\\b";
        
        final Pattern pAprendiz = Pattern.compile(expAprendiz);
        final Pattern pInstructor = Pattern.compile(expInstructor);
        
        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);

        int rol;
        boolean insertado;
        
        if (clave.equals(confirm)) {
            System.out.println("Las contraseñas coinciden");
            String encript = PasswordEncryptionUtil.encriptar(clave);
            u.setCorreoInst(correo);
            u.setPassword(encript);
            try {
                if (mAprendiz.matches()) {
                    rol = 1;
                    insertado = userDao.registrarUsuario(u, rol);
                    System.out.println("Se mando a crear un nuevo aprendiz");
                    sesion.setAttribute("UsuarioAprendiz", u.getCorreoInst());
                    response.sendRedirect("views/crearPerfil.jsp");
                }
                else{
                    if (mInstructor.matches()) {
                        rol = 2;
                        insertado = userDao.registrarUsuario(u, rol);
                        System.out.println("Se mando a crear un nuevo instructor");
                    } else {
                        System.out.println("No se permiten otros correos");
                        response.sendRedirect("views/registro.jsp");
                    }
                }
            } catch (Exception e) {
            }
        }
        else{
            System.out.println("Las contraseñas no coinciden");
            response.sendRedirect("views/registro.jsp");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
