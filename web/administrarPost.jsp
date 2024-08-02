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
                            <%                                
                                for (Post post : posts) {
                            %>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <!--                                <td type="hidden" data-id="" id="idPost"></td>-->
                                <td><%= post.getFechaPost()%></td>
                                <td><%= post.getNombreUsuario()%></td>
                                <td><%= post.getTitulo()%></td>
                                <td>
                                    <button id="cantidadArchivos" data-id="<%= post.getId()%>">
                                        <%= post.getContador()%>
                                    </button>
                                </td>
                                <td>
                                    <button id="rechazarPost">
                                        <i class="fa-solid fa-square-xmark text-mdaRed text-lg"></i>
                                    </button>
                                    <button id="aceptarPost" data-id='<%= post.getId()%>'>
                                        <i class="fa-solid fa-square-check text-mdaGreen text-lg ml-2"></i>
                                    </button>
                                </td>
                                        <div id="documentosContainer">
                                            <%
                                                List<Archivo> archivos = null;
                                                try {
                                                    archivos = aDao.listarArchivosPorPostId(post.getId());
                                                } catch (Exception e) {
                                                }
                                    
                                                if (archivos != null && !archivos.isEmpty()) {
                                                    for (Archivo arch : archivos) {
                                            %>
                                            <div class="hidden" data-id='<%= post.getId() %>' id="divPost">
                                                    <a href='/descargarArchivo?id=<%= arch.getIdDocumento() %>'>
                                                        <i class="fa-solid fa-arrow-down"></i> <%= arch.getNombreDocumento()%>
                                                    </a>
                                                </div>
                                            <%
                                                }
                                            }
                                            %>
                                        </div>
                        
                            </tr>
                            
                            <%
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
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td>08-01-2024</td>
                                <td>Daniel Fernando Gómez Zayas</td>
                                <td>¿Qúé es el internet de las cosas?</td>
                                <td>
                                    <button id="cantidadArchivos">
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
                                <td>08-01-2024</td>
                                <td>Daniel Fernando Gómez Zayas</td>
                                <td>¿Qúé es el internet de las cosas?</td>
                                <td>
                                    <button id="cantidadArchivos">
                                        1
                                    </button>
                                </td>
                                <td class="truncate max-w-36" title="La mala pirobo, aprenda a redactar skdjskjdskdj, hola churro Xde">
                                    <button id="observacion">
                                        La mala pirobo, aprenda a redactar skdjskjdskdj, hola churro Xde
                                    </button>
                                </td>
                                <td>
                                    <button id="rechazarPost">
                                        <i class="fa-solid fa-square-xmark text-mdaRed text-lg"></i>
                                    </button>
                                    <button id="aceptarPost">
                                        <i class="fa-solid fa-square-check text-mdaGreen text-lg ml-2"></i>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>

    <script src="scripts/dashboard.js">
    </script>

</html>
