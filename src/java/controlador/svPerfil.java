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
    Usuario user = new Usuario(); // Instancia de un nuevo usuario
    Perfil profile = new Perfil(); // Instancia de un nuevo perfil
    PerfilDAO profileDao = new PerfilDAO(); // Instancia de un nuevo perfilDao que manejara los procesos CRUD
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUser = request.getSession(); // Session para el usuario
        request.setCharacterEncoding("UTF-8"); // Cotejamiento para los acentos en la base de datos
        
        String nombre = request.getParameter("txtNombre"); // Toma el nombre del formualario
        String apellidos = request.getParameter("txtApellidos"); // Toma el apellido del formulario
        String documento = request.getParameter("txtDocumento"); // Toma el documento del formularip
        String centro = request.getParameter("txtCentro"); // Toma el centro de formacioon del formulario
        
        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion"); // Se castea la sesion que se obtiene del registro
        
        profile.setNombre_usuario(nombre); // Se setea el atributo nombre de la clase perfil
        profile.setApellido_usuario(apellidos); // Se setea el atributo apellido de la clase perfil
        profile.setNum_documento(documento); // Se setea el atributo documento de la clase perfil
        profile.setCentro_formacion(centro); // Se setea el atributo centro de formacion de la clase perfil
        
        try { // Manejo de errores
            profileDao.registroPerfil(profile, user.getId_usuario()); // Se registra el perfil y se le pasa el ID del usuario que se seteo
            System.out.println("Se registro el perfil"); // Muestra mensaje por consola que se pudo registar el nuevo perfil asociado al usuario
            response.sendRedirect("login.jsp"); // Redirije a la vista que se va a manejar
        } catch (Exception e) {
            System.out.println("Error en el Perfil DAO: " +e.getMessage());
        }
        
        
    }
}
