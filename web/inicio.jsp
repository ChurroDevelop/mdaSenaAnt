<%@page import="modelo.objetos.Usuario"%>
<%@page import="modelo.PostDAO"%>
<%@page import="modelo.ArchivoDAO"%>
<%@page import="modelo.objetos.Post"%>
<%@page import="modelo.objetos.Archivo"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("dataUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Usuario user = (Usuario) sesion.getAttribute("dataUser");

    PostDAO postDAO = new PostDAO();
    ArchivoDAO archivoDAO = new ArchivoDAO();
    List<Post> posts = null;
    try {
        posts = postDAO.listarPosts();
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar el error según sea necesario
    }
%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Inicio");
%>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="partials/head.jsp" %>
    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Incluir la navegación -->
        <%@ include file="partials/nav.jsp" %>

        <!-- Contenedor para los posts -->
        <section class="m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-start">
            <% if (posts != null && !posts.isEmpty()) { %>
            <% for (Post post : posts) {%>
            <article class="bg-white w-full max-w-2xl h-auto shadow-md rounded-lg p-5 flex flex-col justify-between">
                <!-- Nombre del creador del post -->
                <div class="text-mdaBlack text-sm">
                    <p><b><%= post.getNombreUsuario()%></b></p>
                </div>
                <div class="text-mdaGreen p-2">
                    <!-- Titulo del post -->
                    <h2 class="text-4xl mb-2 truncate" title="<%= post.getTitulo()%>">
                        <b><%= post.getTitulo()%></b>
                    </h2>
                    <!-- Archivos anexados al post -->
                    <div class="max-h-44 overflow-scroll">
                        <%
                            List<Archivo> archivos = null;
                            try {
                                archivos = archivoDAO.listarArchivosPorPostId(post.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                                // Manejar el error según sea necesario
                            }
                            if (archivos != null && !archivos.isEmpty()) {
                                for (Archivo archivo : archivos) {
                        %>
                        <a class="text-base hover:underline" href="/descargarArchivo?id=<%= archivo.getIdDocumento()%>">
                            <i class="fa-solid fa-arrow-down"></i> <%= archivo.getNombreDocumento()%>
                        </a><br/>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
            </article>
            <% } %>
            <% } else { %>
            <p>No hay posts disponibles.</p>
            <% }%>
        </section>

        <!-- Indicador de rol (sin cambios) -->
        <!-- Indicador de rol -->
        <button class="bg-white btn btn-sm border-none text-mdaBlack fixed top-0 right-0 m-2.5 hover:bg-white">
            <i class="fa-solid fa-user"></i> <%= user.getId_rol_fk().getNombre_rol()%>
        </button>

        <!-- Enlace para manejo del DOM -->
        <script src="scripts/inicio.js"></script>
    </body>
</html>
