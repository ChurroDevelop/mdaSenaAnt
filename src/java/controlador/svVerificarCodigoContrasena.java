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

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet svVerificarCodigoContrasena</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet svVerificarCodigoContrasena at " + request.getContextPath() + "</h1>");
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

        HttpSession sesionUser = request.getSession(); // Sesion para el usuario
        request.setCharacterEncoding("UTF-8"); // Cotejamiento para el tema de los acentos en la base de datos

        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion"); // Atrapa el valor de lo que hay en la session de autenticacion
        String autenticacion = request.getParameter("txtCodigo"); // Atrapa el codigo de verificacion que se le dio al usuario en el formulario

        if (autenticacion.equals(user.getCodigo())) {
            System.out.println("El código coincide");
            sesionUser.setAttribute("autenticacion", user);
            response.sendRedirect("cambiarContrasena.jsp");
        } else {
            System.out.println("El código no coincide");
            response.sendRedirect("codigoContrasen.jsp");

        }

    }

}
