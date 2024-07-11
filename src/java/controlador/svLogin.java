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
import modelo.PerfilDAO;
import modelo.UsuarioDao;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;

public class svLogin extends HttpServlet {
    UsuarioDao userDao = new UsuarioDao(); // Instancia de un usuarioDao que manejara los Procesos del CRUD
    Usuario u = new Usuario(); // Instancia de nuevo usuario para setearle sus atributos
    Perfil profile = new Perfil();
    PerfilDAO profileDao = new PerfilDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(); // Instancia para poder atrapar la sesion del usuario que se logea
        HttpSession sesionPerfil = request.getSession();
        request.setCharacterEncoding("UTF-8"); // Toma cotejamiento para poder obtener los acentos latinos
        
        String correo = request.getParameter("txtCorreo"); // Toma del formulario el correo que ingresa el usuario
        String password = request.getParameter("txtClave"); // Toma del formulario la clave que ingresa el usuario
        String encript = EncriptarContraseña.encriptar(password); // Encripta la contraseña para despues compararla con la contraseña que ingreso en el registro
        
        u.setCorreoInst(correo); // Se le setea el correo institucional al usuario
        u.setPassword(encript); // Se le setea la contraseña encriptada al usuario
        
        Usuario newUser = new Usuario(); // Se instancia un nuevo usuario para obtener todos los datos segun el registro
        newUser = userDao.getDataUser(u); // Se le asigna a newUser, el metodo ya que este metodo retorna un objeto usuario
        
        profile = profileDao.dataPerfil(newUser);
        System.out.println(profile.getNombre_usuario());
        
        if (userDao.autenticacion(u) == true) { // Si la autenticacion devuelve true
            sesion.setAttribute("dataUser", newUser); // Se atrapara el id del usuario en una sesion
            sesionPerfil.setAttribute("dataPerfil", profile); 
            System.out.println("Mandando al jsp"); // Mensaje para saber si se mando al inicio o no
            response.sendRedirect("inicio.jsp"); // Lo redirije al inicio
        }
        else{
            System.out.println("No se encuentra"); // Arroja mensaje por consola de que no se encuentra el usuario registrado
            response.sendRedirect("login.jsp");
        }
    }

}
