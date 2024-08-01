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
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="styles/style.css" />
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="scripts/tailwind.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.2/dist/full.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>MDA Sena - Inicio</title>
    </head>
    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Barra lateral izquierda -->
        <nav class="bg-white p-7 shadow-md sticky top-0 h-screen z-10">
            <div class="grid gap-y-5">
                <!-- Logo Sena y nombre del proyecto -->
                <div class="flex flex-row w-full h-32">
                    <!-- Logo -->
                    <div class="grid flex-grow place-items-center w-full">
                        <img src="images/LogoNegro.svg" alt="" />
                    </div>
                    <!-- HR -->
                    <div class="divider divider-horizontal"></div>
                    <!-- Nombre -->
                    <div class="grid flex-grow place-items-center w-full">
                        <h1 class="text-4xl text-mdaBlack leading-none">
                            <span class="text-3xl text-mdaGreen">MDA</span> <br />
                            Sena
                        </h1>
                    </div>
                </div>
                <!-- HR -->
                <div class="flex flex-col w-full">
                    <div class="divider m-0 h-0"></div>
                </div>
                <!-- Input de búsqueda -->
                <form action="">
                    <label class="input input-bordered flex items-center gap-2 bg-white">
                        <i class="fa-solid fa-magnifying-glass text-gray-400"></i>
                        <input type="text" class="grow text-mdaBlack" placeholder="Search" />
                    </label>
                </form>
                <!-- HR -->
                <div class="flex flex-col w-full">
                    <div class="divider m-0 h-0"></div>
                </div>

                <%
                    if ("Aprendiz".equals(user.getId_rol_fk().getNombre_rol())) {
                %>

                <!-- Botones navegación -->
                <div>
                    <!-- Inicio -->
                    <a href="inicio.jsp">
                        <button
                            class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                            <i class="fa-solid fa-house"></i>
                            Inicio
                        </button>
                    </a>
                    <a href="editarPerfil.jsp">
                        <button
                            class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                            <i class="fa-regular fa-address-card"></i>
                            Perfil
                        </button>
                    </a>
                </div>
            </div>

            <%
            } else if ("Instructor".equals(user.getId_rol_fk().getNombre_rol())) {
            %>

            <!-- Botones navegación -->
            <div>
                <!-- Inicio -->
                <a href="inicio.jsp">
                    <button
                        class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                        <i class="fa-solid fa-house"></i>
                        Inicio
                    </button>
                </a>
                <!-- Asignar monitor -->
                <a href="">
                    <form action="/svListarMonitores" method="GET">
                        <input type="hidden" value="<%= user.getId_usuario()%> " name="txtIdInstructor">
                        <button
                            class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                            <i class="fa-solid fa-plus-minus"></i>
                            Asignar monitor
                        </button>
                    </form>
                </a>
                <a href="#">
                    <button id="showModal-2"
                            class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                        <i class="fa-solid fa-bell"></i>
                        Notificaciones
                    </button>
                </a>
                <a href="editarPerfil.jsp">
                    <button
                        class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                        <i class="fa-regular fa-address-card"></i>
                        Perfil
                    </button>
                </a>
            </div>
        </div>

        <%
        } else if ("Monitor".equals(user.getId_rol_fk().getNombre_rol())) {
        %>

        <!-- Botones navegación -->
        <div>
            <!-- Inicio -->
            <a href="inicio.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-house"></i>
                    Inicio
                </button>
            </a>
            <!-- Crear post -->
            <a href="views/monitor/crearPost.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-plus-minus"></i>
                    Crear post
                </button>
            </a>
            <a href="#">
                <button id="showModal-2"
                        class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-bell"></i>
                    Notificaciones
                </button>
            </a>
            <a href="editarPerfil.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-regular fa-address-card"></i>
                    Perfil
                </button>
            </a>
        </div>
    </div>

    <%
        }
    %>

    <!-- Barra notificaciones -->
    <nav id="modal-2" class="hidden bg-white p-7 shadow-md absolute top-0 left-full h-screen w-full">
        <div class="grid gap-y-5">
            <button id="closeModal-2" class="btn btn-sm btn-circle btn-ghost text-mdaGreen">
                <i class="fa-solid fa-chevron-left"></i>
            </button>
            <!-- HR -->
            <div class="flex flex-col w-full">
                <div class="divider m-0 h-0"></div>
            </div>
            <div>
                <p class="text-mdaBlack text-sm">
                    Daniel Acetaminofén, ha cargado una evidencia
                </p>
                <button class="btn btn-sm bg-mdaGreen border-none text-white mt-2 hover:bg-mdaGreen w-full">
                    Ver evidencia
                </button>
            </div>
        </div>
    </nav>
</nav>
<div id="modal-2__background" class="hidden bg-mdaBlack_400 w-full min-h-screen absolute"></div>

<!-- Contenedor para los posts -->
<section class="m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-start">
    <% if (posts != null && !posts.isEmpty()) { %>
    <% for (Post post : posts) {%>
    <article class="bg-white w-full max-w-2xl h-40 shadow-md rounded-lg p-5 flex flex-col justify-between">
        <!-- Nombre del creador del post -->
        <div class="text-mdaBlack text-sm">
            <p><b><%= post.getNombreUsuario()%></b></p>
        </div>
        <div class="text-mdaGreen">
            <!-- Titulo del post -->
            <h2 class="text-4xl mb-2 truncate" title="<%= post.getTitulo()%>">
                <b><%= post.getTitulo()%></b>
            </h2>
            <!-- Archivos anexados al post -->
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
            <a class="text-base hover:underline" href="<%= request.getContextPath()%>/descargarArchivo?id=<%= archivo.getIdDocumento()%>">
                <i class="fa-solid fa-arrow-down"></i> <%= archivo.getNombreDocumento()%>
            </a><br/>
            <%
                    }
                }
            %>
        </div>
    </article>
    <% } %>
    <% } else { %>
    <p>No hay posts disponibles.</p>
    <% }%>
</section>

<!-- Indicador de rol (sin cambios) -->

<!-- Enlace para manejo del DOM -->
<script src="scripts/inicio.js"></script>
</body>
</html>
