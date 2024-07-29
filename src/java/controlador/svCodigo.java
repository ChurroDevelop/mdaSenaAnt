package controlador;

import java.io.IOException;
import java.io.PrintWriter;
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
import modelo.UsuarioDao;
import modelo.objetos.Usuario;

@WebServlet(name = "svCodigo", urlPatterns = {"/svCodigo"})
public class svCodigo extends HttpServlet {

    Usuario user = new Usuario();
    UsuarioDao userDao = new UsuarioDao();
    EnviarCodigo mensaje = new EnviarCodigo();

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
        String clave = request.getParameter("txtPass");
        String confirm = request.getParameter("txtConfirm");
        String codigo = mensaje.getRandom();

        final String expAprendiz = "\\b[A-Za-z0-9._%+-]+@soy\\.sena\\.edu\\.co\\b";
        final String expInstructor = "\\b[A-Za-z0-9._%+-]+@sena\\.edu\\.co\\b";

        final Pattern pAprendiz = Pattern.compile(expAprendiz);
        final Pattern pInstructor = Pattern.compile(expInstructor);

        Matcher mAprendiz = pAprendiz.matcher(correo);
        Matcher mInstructor = pInstructor.matcher(correo);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (clave.equals(confirm)) {

            String encript = EncriptarContraseña.encriptar(clave);

            user.setCorreoInst(correo);
            user.setPassword(encript);
            user.setCodigo(codigo);
            System.out.println(codigo);

            boolean encontrado = userDao.buscarUser(user);

            try {
                if (!encontrado) {
                    if (mAprendiz.matches()) {
                        mensaje.enviarEmail(user);
                        sesion.setAttribute("autenticacion", user);
                        System.out.println("Es un Aprendiz");
                        out.print("success");
                    } else if (mInstructor.matches()) {
                        mensaje.enviarEmail(user);
                        sesion.setAttribute("autenticacion", user);
                        System.out.println("Es un Instructor");
                        out.print("success");
                    } else {
                        out.print("invalid_email");
                    }
                } else {
                    out.print("email_exists");
                }
            } catch (AddressException ex) {
                Logger.getLogger(svCodigo.class.getName()).log(Level.SEVERE, null, ex);
                out.print("error");
            }
        } else {
            out.print("password_mismatch");
        }

        out.flush();
    }
}
