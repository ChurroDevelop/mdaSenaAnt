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
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author Propietario
 */
@WebServlet(name = "svUsuario", urlPatterns = {"/svUsuario"})
public class svUsuario extends HttpServlet {

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
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                            verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarSession(request,response);
                    default:
                        response.sendRedirect("views/login.jsp");
                }
            }
            else{
                response.sendRedirect("views/login.jsp");
            }
        } catch (Exception e) {
            System.out.println("Error 2: " + e.getMessage());
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error 3: " + e.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion;
        UsuarioDAO dao;
        Usuario usuario;
        usuario = this.obtenerUsuario(request);
        
        dao = new UsuarioDAO();
        usuario = dao.identificar(usuario);
        if (usuario != null && usuario.getId_rol_fk().getNombre_rol().equals("Aprendiz")) {
             sesion = request.getSession();
             sesion.setAttribute("Aprendiz", usuario);
             request.setAttribute("Hola", "Bienvenido al sistema");
             response.sendRedirect("views/viewsAprendiz/inicio.jsp");
             this.getServletConfig().getServletContext().getRequestDispatcher("/views/viewsAprendiz/inicio.jsp").forward(request, response);
        }
        else{
            if (usuario != null && usuario.getId_rol_fk().getNombre_rol().equals("Instructor")) {
                sesion = request.getSession();
                sesion.setAttribute("Instructor", usuario);
                this.getServletConfig().getServletContext().getRequestDispatcher("/views/viewsInstructor/inicio.jsp").forward(request, response);
            }
            else{
                if (usuario != null && usuario.getId_rol_fk().getNombre_rol().equals("Monitor")) {
                    sesion = request.getSession();
                    sesion.setAttribute("Monitor", usuario);
                    response.sendRedirect("views/viewsMonitor/inicio.jsp");
                    this.getServletConfig().getServletContext().getRequestDispatcher("/views/viewsMonitor/inicio.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("mensaje", "Credenciales incorrectas");
                    request.getRequestDispatcher("views/login.jsp").forward(request, response);
                }
            }
        }
    }

    private void cerrarSession(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession sesion = request.getSession();
        sesion.setAttribute("Aprendiz", null);
        sesion.invalidate();
        response.sendRedirect("views/login.jsp");
    }

    private Usuario obtenerUsuario(HttpServletRequest request) {
//        final String regexAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
//        final String regexInstructor = "\\b[A-Za-z0-9._%+-]+@misena\\.edu\\.co\\b";
//        Pattern pAprendiz = Pattern.compile(regexAprendiz);
//        Pattern pInstructor = Pattern.compile(regexInstructor);

        Usuario u = new Usuario();
        u.setCorreoInstitucional(request.getParameter("txtCorreo"));
        u.setContraseña(request.getParameter("txtClave"));
        
//        Matcher mAprendiz = pAprendiz.matcher(u.getCorreoInstitucional());
//        Matcher mInstructor = pInstructor.matcher(u.getCorreoInstitucional());
//        
//        try {
//            if (mAprendiz.matches()) {
//                System.out.println("Es aprendiz");
//            }
//            else{
//                if (mInstructor.matches()) {
//                    System.out.println("Es instructor");
//                }
//            }
//        } catch (Exception e) {
//            
//        }
        return u;
    }

}
