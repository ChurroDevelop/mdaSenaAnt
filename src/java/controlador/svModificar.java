/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.PerfilDAO;
import modelo.objetos.Perfil;

@WebServlet(name = "svModificar", urlPatterns = {"/svModificar"})
public class svModificar extends HttpServlet {
    // Instancia de un nuevo perfilDao para hacer la actualizacion del perfil de dicho usuario
    PerfilDAO pDao = new PerfilDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Crear una nueva session para restablecer los datos del perfil desactualizado
        HttpSession sesion = request.getSession();
        
        // Cotejamiento de los caracteres para acentos y Ñ
        request.setCharacterEncoding("UTF-8");
        
        // Se castea la obtencion de los datos del perfil del usuario
        Perfil p = (Perfil) sesion.getAttribute("dataPerfil");
        
        // Obtener los datos del formulario para actualizarlos en la base de datos
        String idPerfil = request.getParameter("txtIdPerfil");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String numero = request.getParameter("txtDocumento");
        String centro = request.getParameter("txtCentro");
        
        // Convertir el idPerfil a entero
        int idProfile = Integer.parseInt(idPerfil);
        
        // Se setean los datos al objeto perfil
        p.setId_perfil(idProfile);
        p.setNombre_usuario(nombre);
        p.setApellido_usuario(apellido);
        p.setNum_documento(numero);
        p.setCentro_formacion(centro);
        
        // Dato boolean para saber si fue actualizado o no, que se le pasa el perfil seteado
        boolean isUpdate = pDao.actualizarPerfil(p);
        
        // Si fue actualizado sobreEscribe la session y no se pierde al momento de la vista
        if (isUpdate) {
            System.out.println("Se modifico el usuario");
            sesion.getAttribute("user");
            response.sendRedirect("editarPerfil.jsp");
        } else {
            System.out.println("No se envio al metodo de modificar");
        }
        
    }
}
