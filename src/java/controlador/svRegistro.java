/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.EncriptarContraseña;
import modelo.EnviarCodigo;
import modelo.UsuarioDAO;
import modelo.objetos.Usuario;

@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {
    UsuarioDAO userDao = new UsuarioDAO(); // Instanciando un controlador del CRUD para el usuario
    Usuario u = new Usuario(); // Instanciando un nuevo usuario para colocarle sus atributos
    EnviarCodigo mensaje = new EnviarCodigo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(); // Sesion para atrapar la session
        String correo = request.getParameter("txtCorreo"); // Tomar el correo del formulario
        String clave = request.getParameter("txtPass"); // Tomar la contraseña del formulario
        String confirm = request.getParameter("txtConfirm"); // Tomar la confirmacion de la contraseña del formulario
        String codigo = mensaje.getRandom();
        
        if (clave.equals(confirm)) {
            u.setCorreoInst(correo);
            u.setPassword(clave);
            u.setCodigo(codigo);
            
            try {
                mensaje.enviarEmail(u);
                sesion.setAttribute("Verificacion", u);
                response.sendRedirect("verificacion.jsp");
            } catch (AddressException ex) {
                Logger.getLogger(svRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
