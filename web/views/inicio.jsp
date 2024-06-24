<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Inicio</title>
    </head>
    <body>
        <h1>Bienvenido a la página de inicio</h1>
        <%
            // Recuperar el correo del usuario desde la sesión
            HttpSession sessionInicio = request.getSession(false);
            if (sessionInicio != null) {
                String userEmail = (String) sessionInicio.getAttribute("userEmail");
                if (userEmail != null) {
                    out.println("<p>Correo del usuario: " + userEmail + "</p>");
                } else {
                    out.println("<p>No hay un usuario logueado.</p>");
                }
            } else {
                out.println("<p>No hay una sesión activa.</p>");
            }
        %>
    </body>
</html>
