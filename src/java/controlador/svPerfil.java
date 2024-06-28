package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.SOAPFault;
import modelo.PerfilDAO;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;

@WebServlet(name = "svPerfil", urlPatterns = {"/svPerfil"})
public class svPerfil extends HttpServlet {
    Usuario user = new Usuario();
    Perfil profile = new Perfil();
    PerfilDAO profileDao = new PerfilDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUser = request.getSession(); // Session para el usuario
        request.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("txtNombre");
        String apellidos = request.getParameter("txtApellidos");
        String documento = request.getParameter("txtDocumento");
        String centro = request.getParameter("txtCentro");
        
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");
        
        profile.setNombre_usuario(nombre);
        profile.setApellido_usuario(apellidos);
        profile.setNum_documento(documento);
        profile.setCentro_formacion(centro);
        
        try {
            profileDao.registroPerfil(profile, user.getId_usuario());
            System.out.println("Se registro el perfil");
        } catch (Exception e) {
            System.out.println("Error en el Perfil DAO: " +e.getMessage());
        }
        
        
    }
}
