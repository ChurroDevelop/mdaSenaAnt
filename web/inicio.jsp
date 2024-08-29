<%@page import="modelo.objetos.Usuario"%>
<%@page import="modelo.PostDAO"%>
<%@page import="modelo.ArchivoDAO"%>
<%@page import="modelo.objetos.Post"%>
<%@page import="modelo.objetos.Archivo"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    // Obtener la sesión actual. Redirigir al usuario al login si no hay sesión activa.
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("dataUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Obtener el usuario de la sesión
    Usuario user = (Usuario) sesion.getAttribute("dataUser");

    // Crear instancias de los DAOs
    PostDAO postDAO = new PostDAO();
    ArchivoDAO archivoDAO = new ArchivoDAO();
    List<Post> posts = null;
    try {
        // Obtener la lista de posts desde la base de datos
        posts = postDAO.listarPosts();
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar el error de manera adecuada (puede incluir un mensaje de error para el usuario)
    }
%>

<%
    // Configurar el título de la página
    request.setAttribute("pageTitle", "MDA SENA - Inicio");
%>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="partials/head.jsp" %> <!-- Incluir el archivo de encabezado común -->

    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Incluir la navegación -->
        <div class="hidden md:block">
            <%@ include file="partials/nav.jsp" %>            
        </div>

        <div class="block md:hidden">
            <%@ include file="partials/navMobile.jsp" %>            
        </div>

        <!-- Contenedor para los posts -->
        <section class="m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-start">
            <% if (posts != null && !posts.isEmpty()) { %>
            <% for (Post post : posts) {%>
            <article id="posts" class="bg-white w-full max-w-2xl h-auto shadow-md rounded-lg p-5 flex flex-col justify-between">
                <!-- Nombre del creador del post -->
                <div class="text-mdaBlack text-sm">
                    <p><b><%= post.getNombreUsuario()%></b></p>
                </div>
                <div class="text-mdaGreen p-2">
                    <!-- Titulo del post -->
                    <h2 class="text-4xl mb-2 truncate" title="<%= post.getTitulo()%>" id="title">
                        <b><%= post.getTitulo()%></b>
                    </h2>
                    <!-- Archivos anexados al post -->
                    <div class="max-h-12 overflow-scroll">
                        <%
                            // Obtener la lista de archivos asociados al post
                            List<Archivo> archivos = null;
                            try {
                                archivos = archivoDAO.listarArchivosPorPostId(post.getId());
                            } catch (SQLException e) {
                                e.printStackTrace();
                                // Manejar el error de manera adecuada (puede incluir un mensaje de error para el usuario)
                            }
                            // Mostrar los archivos si existen
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

        <!-- Indicador de rol del usuario (se muestra en la esquina superior derecha) -->
        <button class="bg-white btn btn-sm border-none text-mdaBlack fixed top-0 right-0 m-2.5 hover:bg-white">
            <i class="fa-solid fa-user"></i> <%= user.getId_rol_fk().getNombre_rol()%>
        </button>

        <!-- Archivos JavaScript para funcionalidades adicionales -->
        <script src="scripts/inicio.js"></script>
        <script src="scripts/buscar.js"></script>
        <script src="scripts/buscarMobile.js"></script>
        <script>
            // Script para manejar la visibilidad del menú de navegación en dispositivos móviles
            document.addEventListener("DOMContentLoaded", function () {
                const abrirNavegacionBtn = document.getElementById("abrirNavegacion");
                const modalNavegacion = document.getElementById("modalNavegacion");

                abrirNavegacionBtn.addEventListener("click", function () {
                    // Alternar la visibilidad de la navegación
                    if (modalNavegacion.classList.contains("hidden")) {
                        modalNavegacion.classList.remove("hidden");
                    } else {
                        modalNavegacion.classList.add("hidden");
                    }
                });
            });
        </script>

    </body>
</html>
