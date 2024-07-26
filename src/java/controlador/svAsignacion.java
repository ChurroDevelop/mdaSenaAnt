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
import modelo.UsuarioDao;

@WebServlet(name = "svAsignacion", urlPatterns = {"/svAsignacion"})
public class svAsignacion extends HttpServlet {
    // Instancia de un nuevo usuarioDao para todos los metodos a la base de datos
    UsuarioDao userDao = new UsuarioDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tomar el id al momento de dar click en asignar rol monitor
        String idAsignacion = request.getParameter("txtAsignacion");
        
        // Hacer la actualizacion en la base de datos, manejando metodo booleano para saber si se actualizo o no
        boolean actualizacion = userDao.asignarRolMonitor(idAsignacion);
        
        // Si actualizo redirije a la vista de asignar monitor, donde se vera el nuevo monitor
        if (actualizacion) {
            response.sendRedirect("views/instructor/asignarMonitor.jsp");
        } else {
            System.out.println("No se puedo hacer la modificacion");
            response.sendRedirect("views/instructor/asignarMonitor.jsp");
        }
    }
}
