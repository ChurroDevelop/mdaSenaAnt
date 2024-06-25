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
import modelo.UsuarioDAO;
import modelo.objetos.Usuario;

public class svLogin extends HttpServlet {
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
        String password = request.getParameter("txtClave");
        String encript = PasswordEncryptionUtil.encriptar(password);
        
        u.setCorreoInst(correo);
        u.setPassword(encript);
        
        if (userDao.autenticacion(u) == true) {
            userDao.obtenerId(u);
            sesion.setAttribute("userEmail", u.getCorreoInst());
            response.sendRedirect("views/inicio.jsp");
        }
        else{
            System.out.println("No se encuentra");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
