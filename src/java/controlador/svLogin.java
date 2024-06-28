/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EncriptarContraseña;
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

public class svLogin extends HttpServlet {
    UsuarioDao userDao = new UsuarioDao();
    Usuario u = new Usuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        request.setCharacterEncoding("UTF-8");
        
        String correo = request.getParameter("txtCorreo");
        String password = request.getParameter("txtClave");
        String encript = EncriptarContraseña.encriptar(password);
        
        u.setCorreoInst(correo);
        u.setPassword(encript);
        
        if (userDao.autenticacion(u) == true) {
            sesion.setAttribute("userEmail", u.getId_usuario());
            response.sendRedirect("inicio.jsp");
        }
        else{
            System.out.println("No se encuentra");
            // Redireccionar a una página de error o mostrar un mensaje de error
            response.sendRedirect("error.jsp");
        }
    }

}
