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

        <div class="container mx-auto p-4">
            <!-- Contenedor de pestañas -->
            <div class="tabs text-black">
                <ul class="flex border-b">
                    <li class="mr-1">
                        <a href="#tab1" class="tab-link bg-gray-200 py-2 px-4 inline-block text-gray-600 cursor-pointer">Pestaña 1</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab2" class="tab-link py-2 px-4 inline-block text-gray-600 cursor-pointer">Pestaña 2</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab3" class="tab-link py-2 px-4 inline-block text-gray-600 cursor-pointer">Pestaña 3</a>
                    </li>
                </ul>
                <div id="tab1" class="overflow-x-auto tab-content">
                    <table class="table">
                        <!-- head -->
                        <thead>
                            <tr class="text-black">
                                <th>Fecha</th>
                                <th>Nombre monitor</th>
                                <th>Título</th>
                                <th>Cantidad documentos</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="hover:bg-mdaGreen_400">
                                <th>08-01-2024</th>
                                <td>Daniel Fernando Gómez Zayas</td>
                                <td>¿Qúé es el internet de las cosas?</td>
                                <td>1</td>
                                <td>Eliminar</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="tab2" class="tab-content p-4">
                    <h2 class="text-xl font-bold">Contenido de la Pestaña 2</h2>
                    <p>Este es el contenido de la segunda pestaña.</p>
                </div>
                <div id="tab3" class="tab-content p-4">
                    <h2 class="text-xl font-bold">Contenido de la Pestaña 3</h2>
                    <p>Este es el contenido de la tercera pestaña.</p>
                </div>
            </div>
        </div>

    </body>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const tabLinks = document.querySelectorAll('.tab-link');
            const tabContents = document.querySelectorAll('.tab-content');

            tabLinks.forEach(link => {
                link.addEventListener('click', function (e) {
                    e.preventDefault();

                    // Remove active class from all links and hide all contents
                    tabLinks.forEach(link => link.classList.remove('bg-gray-200', 'text-gray-600'));
                    tabContents.forEach(content => content.classList.remove('active'));

                    // Add active class to the clicked link and show corresponding content
                    link.classList.add('bg-gray-200', 'text-gray-600');
                    const targetId = link.getAttribute('href').substring(1);
                    document.getElementById(targetId).classList.add('active');
                });
            });

            // Show the first tab by default
            tabLinks[0].click();
        });
    </script>

</html>
