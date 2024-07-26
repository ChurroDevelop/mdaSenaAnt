package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "svCambiarContrasena", urlPatterns = {"/svCambiarContrasena"})
public class svCambiarContrasena extends HttpServlet {

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet svCambiarContrasena</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet svCambiarContrasena at " + request.getContextPath() + "</h1>");
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

        request.setCharacterEncoding("UTF-8"); // Cotejamiento para el tema de los acentos en la base de datos

        String clave = request.getParameter("txtClave");
        String confirmarClave = request.getParameter("txtConfirmarClave");

        if (clave.equals(confirmarClave)) {
            System.out.println("La contraseñas coinciden");
        } else {
            System.out.println("La contraseñas no coinciden");
        }

    }
}
