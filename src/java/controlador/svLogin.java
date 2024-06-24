/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PasswordEncryptionUtil;
import modelo.UsuarioDao;

/**
 *
 * @author Propietario
 */
public class svLogin extends HttpServlet {

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
        
        String correo = request.getParameter("txtCorreo");
        String password = request.getParameter("txtClave");
        
        UsuarioDao userDao = new UsuarioDao();
        
        if (userDao.autenticacion(correo, password)) {
            // Si la autenticación es exitosa, guarda el correo en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", correo);
            response.sendRedirect("views/inicio.jsp");
        } else {
            System.out.println("No se encuentra");
            // Redireccionar a una página de error o mostrar un mensaje de error
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
