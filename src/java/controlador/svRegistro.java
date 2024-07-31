package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.RolDAO;
import modelo.UsuarioDao;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {
    // Instancia de un nuevo UsuarioDao para manejo de base de datos
    UsuarioDao userDao = new UsuarioDao();
    
    // Instancia de un nuevo RolDao para manejo de base de datos
    RolDAO rolDao = new RolDAO();
    
    // Instancia de un nuevo objeto Rol
    Rol rol = new Rol();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionUser = request.getSession();
        HttpSession sesionId = request.getSession();
        request.setCharacterEncoding("UTF-8");

        Usuario user = (Usuario) sesionUser.getAttribute("autenticacion");
        String autenticacion = request.getParameter("txtCodigo");

        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b";

        final Pattern pAprendiz = Pattern.compile(expAprendiz);
        final Pattern pInstructor = Pattern.compile(expInstructor);

        Matcher mAprendiz = pAprendiz.matcher(user.getCorreoInst());
        Matcher mInstructor = pInstructor.matcher(user.getCorreoInst());

        int id_rol;
        boolean insertado;
        int idUser;

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            if (autenticacion.equals(user.getCodigo())) {
                if (mAprendiz.matches()) {
                    id_rol = 1;
                    insertado = userDao.registrarUsuario(user, id_rol);
                    idUser = userDao.obtenerId(user.getCorreoInst());
                    user.setId_usuario(idUser);
                    rol = rolDao.getIdRol(user);
                    user.setId_rol_fk(rol);
                    sesionId.setAttribute("UsuarioAprendiz", user);

                    if (insertado) {
                        out.print("success");
                    } else {
                        out.print("error");
                    }
                } else if (mInstructor.matches()) {
                    id_rol = 2;
                    insertado = userDao.registrarUsuario(user, id_rol);
                    idUser = userDao.obtenerId(user.getCorreoInst());
                    user.setId_usuario(idUser);
                    rol = rolDao.getIdRol(user);
                    user.setId_rol_fk(rol);
                    sesionId.setAttribute("UsuarioInstructor", user);

                    if (insertado) {
                        out.print("success");
                    } else {
                        out.print("error");
                    }
                } else {
                    out.print("invalid_email");
                }
            } else {
                out.print("code_mismatch");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            out.print("error");
        }

        out.flush();
    }
}
