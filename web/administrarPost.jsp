<%@page import="modelo.objetos.Perfil"%>
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
    Perfil perfil = (Perfil) sesion.getAttribute("dataPerfil");
    PostDAO pDao = new PostDAO();
    ArchivoDAO aDao = new ArchivoDAO();
    List<Post> posts = (List<Post>) sesion.getAttribute("listaPosts");
%>
<%
    request.setAttribute("pageTitle", "MDA SENA - Administrar post");
%>
<!DOCTYPE html>
<html>

    <%@ include file="partials/head.jsp" %>

    <style>
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
    </style>

    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Incluir la navegación -->
        <%@ include file="partials/nav.jsp" %>
        <%            if (user.getId_rol_fk().getNombre_rol().equals("Instructor")) {
        %>
        <div class="container mx-auto p-4 w-9/12">
            <!-- Contenedor de pestañas -->
            <div class="tabs text-black">
                <ul class="flex border-b">
                    <li class="mr-1">
                        <a href="#tab1" class="tab-link rounded-t-lg bg-white py-2 px-4 inline-block text-mdaBlack cursor-pointer">Pendientes</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab2" class="tab-link py-2 px-4 inline-block text-mdaBlack cursor-pointer">Aceptados</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab3" class="tab-link py-2 px-4 inline-block text-mdaBlack cursor-pointer">Rechazados</a>
                    </li>
                </ul>
                <div id="tab1" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad archivos</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                        <input type="hidden" value="<%= user.getId_usuario()%>" id="idInstructor">
                        <!--ESTO ES PARA VER LOS POSTS QUE ESTAN PENDIENTES-->
                        <%
                            for (Post post : posts) {
                                if (!post.getEstado() && !post.getValidacion()) {
                        %>
                        <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                            <td><%= post.getFechaPost()%></td>
                            <td><%= post.getNombreUsuario()%></td>
                            <td><%= post.getTitulo()%></td>
                            <td>
                                <button id="cantidadArchivos" data-id="<%= post.getId()%>">
                                    <%= post.getContador()%>
                                </button>
                            </td>
                            <td>
                                <button id="rechazarPost" data-id="<%= post.getId()%>">
                                    <i class="fa-solid fa-square-xmark text-mdaRed text-lg"></i>
                                </button>
                                <button id="aceptarPost" data-id='<%= post.getId()%>'>
                                    <i class="fa-solid fa-square-check text-mdaGreen text-lg ml-2"></i>
                                </button>
                            </td>
                        <div class="hidden flex bg-[#1D1D1D60] fixed top-0 left-0 min-h-screen w-full justify-center items-center z-10" data-id='<%= post.getId()%>' id="divPost">
                            <%
                                List<Archivo> archivos = null;
                                try {
                                    archivos = aDao.listarArchivosPorPostId(post.getId());
                                } catch (Exception e) {
                                }

                                if (archivos != null && !archivos.isEmpty()) {
                                    for (Archivo arch : archivos) {
                            %>
                            <div class="bg-white relative w-96 rounded-lg p-5 text-center flex gap-5 flex-col items-center">
                                <p>Cantidad de archivos del post: <%= post.getContador()%></p>
                                <button id="cerrarCantidadPost" class="absolute top-2 right-2 bg-red-500 text-white rounded-full w-6 h-6 flex items-center justify-center">
                                    X
                                </button>
                                <div>
                                    <a href='/descargarArchivo?id=<%= arch.getIdDocumento()%>'>
                                        <i class="fa-solid fa-arrow-down"></i> <%= arch.getNombreDocumento()%>
                                    </a>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <div id="tab2" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad archivos</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--ESTA ES PARA VER LOS POST QUE ESTAN ACEPTADOS-->
                            <%
                                for (Post post : posts) {
                                    if (post.getEstado() && post.getValidacion()) {
                            %>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td><%= post.getFechaPost()%></td>
                                <td><%= post.getNombreUsuario()%></td>
                                <td><%= post.getTitulo()%></td>
                                <td>
                                    <button id="cantidadArchivos" data-id="<%= post.getId()%>">
                                        <%= post.getContador()%>
                                    </button>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <div id="tab3" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad archivos</th>
                                <th>Observación</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--ESTE ES PARA VER LA PESTAÑA DE LOS POSTS QUE ESTAN RECHAZADOS-->
                            <%
                                for (Post post : posts) {
                                    if (!post.getEstado() && post.getValidacion()) {
                            %>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td><%= post.getFechaPost()%></td>
                                <td><%= post.getNombreUsuario()%></td>
                                <td><%= post.getTitulo()%></td>
                                <td>
                                    <button id="cantidadArchivos">
                                        <%= post.getContador()%>
                                    </button>
                                </td>
                                <td class="truncate max-w-36" title="La mala pirobo, aprenda a redactar skdjskjdskdj, hola churro Xde">
                                    <button id="observacion">
                                        <%= post.getObservacion()%>
                                    </button>
                                </td>
                                <td>
                                    <button id="rechazarPost" data-id="<%= post.getId()%>">
                                        <i class="fa-solid fa-square-xmark text-mdaRed text-lg"></i>
                                    </button>
                                    <button id="aceptarPost" data-id="<%= post.getId()%>">
                                        <i class="fa-solid fa-square-check text-mdaGreen text-lg ml-2"></i>
                                    </button>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <% }
            if (user.getId_rol_fk().getNombre_rol().equals("Monitor")) {
        %>
        <div class="container mx-auto p-4 w-9/12">
            <!-- Contenedor de pestañas -->
            <h1 class="text-black text-xl mb-4 font-bold">Dashboard de <%= perfil.getNombre_usuario() + " " + perfil.getApellido_usuario()%></h1>
            <div class="tabs text-black">
                <ul class="flex border-b">
                    <li class="mr-1">
                        <a href="#tab1" class="tab-link rounded-t-lg bg-white py-2 px-4 inline-block text-mdaBlack cursor-pointer">Posts creados</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab2" class="tab-link py-2 px-4 inline-block text-mdaBlack cursor-pointer">Aceptados</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab3" class="tab-link py-2 px-4 inline-block text-mdaBlack cursor-pointer">Rechazados</a>
                    </li>
                </ul>
                <div id="tab1" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad archivos</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                        <input type="hidden" value="<%= user.getId_usuario()%>" id="idMonitor">
                        <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                            <td>Fecha del post</td>
                            <td>Nombre del usuario</td>
                            <td>Titulo del post</td>
                            <td>
                                <button id="cantidadArchivos" data-id="">
                                    1
                                </button>
                            </td>
                            <td>
                                <button id="rechazarPost" data-id="">
                                    <i class="fa-solid fa-square-xmark text-mdaRed text-lg"></i>
                                </button>
                                <button id="aceptarPost" data-id=''>
                                    <i class="fa-solid fa-square-check text-mdaGreen text-lg ml-2"></i>
                                </button>
                            </td>
                        <div id="documentosContainer">
                            <div class="hidden" data-id='' id="divPost">
                                <a href=''>
                                    <i class="fa-solid fa-arrow-down"></i> 
                                </a>
                            </div>
                        </div>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div id="tab2" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad archivos</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td>Fecha del post</td>
                                <td>Nombre del usuario</td>
                                <td>Titulo del post</td>
                                <td>
                                    <button id="cantidadArchivos" data-id="">
                                        1
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="tab3" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad archivos</th>
                                <th>Observación</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td>Fecha del post</td>
                                <td>Nombre del usuario</td>
                                <td>Titulo del post</td>
                                <td>
                                    <button id="cantidadArchivos">
                                        1
                                    </button>
                                </td>
                                <td class="truncate max-w-36" title="La mala pirobo, aprenda a redactar skdjskjdskdj, hola churro Xde">
                                    <button id="observacion">
                                        Algo tranqui
                                    </button>
                                </td>
                                <td>
                                    <button id="rechazarPost" data-id="">
                                        <i class="fa-solid fa-square-xmark text-mdaRed text-lg"></i>
                                    </button>
                                    <button id="aceptarPost" data-id="">
                                        <i class="fa-solid fa-square-check text-mdaGreen text-lg ml-2"></i>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <% }%>
    </body>

    <script src="scripts/dashboard.js">
    </script>

</html>
