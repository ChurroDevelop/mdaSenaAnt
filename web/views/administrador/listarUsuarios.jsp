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
    if ((sesion == null || sesion.getAttribute("dataUser") == null)) {
        System.out.println("Error en la vista de editar perfil");
        response.sendRedirect("login.jsp");
        return;
    }

    Usuario user = (Usuario) sesion.getAttribute("dataUser");
    Perfil perfil = (Perfil) sesion.getAttribute("dataPerfil");
    List<Usuario> listUsers = (List<Usuario>) sesion.getAttribute("listaUsers");
%>
<%
    request.setAttribute("pageTitle", "MDA SENA - Listar usuarios");
%>
<!DOCTYPE html>
<html>
    <%@ include file="../../partials/roles/head.jsp" %>
    
    <style>
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
    </style>
    
    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <div class="hidden md:block">
            <%@ include file="../../partials/roles/nav.jsp" %>            
        </div>

        <div class="block md:hidden">
            <%@ include file="../../partials/roles/navMobile.jsp" %>            
        </div>
        
        <section class="container p-4 w-9/12 min-h-screen m-auto">
            <h1 class="text-black text-xl mb-4 font-bold">
                Listado de usuarios
            </h1>
            
            <div class="tabs text-black">
                
                <ul class="flex border-b">
                    <li class="mr-1">
                        <a href="#tab1" class="tab-link rounded-t-lg bg-white py-2 px-4 inline-block text-mdaBlack cursor-pointer">Usuarios activos</a>
                    </li>
                    <li class="mr-1">
                        <a href="#tab2" class="tab-link py-2 px-4 inline-block text-mdaBlack cursor-pointer">Usuarios deshabilitados</a>
                    </li>
                </ul>

                
                <div id="tab1" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        
                        <input type="hidden" value="<%= user.getId_usuario() %>" id="idAdmin">
                        
                        <thead>
                            <tr class="text-black text-md text-center">
                                <th>Nombre usuario</th>
                                <th>Estado</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <%
                                for (Usuario us : listUsers) {
                                    if (us.getEstadoUser()) {
                            %>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td><%= us.getNombreUser() %></td>
                                <td><%= us.getEstadoUser() ? "Habilitado" : "Deshabilitado" %></td>
                                <td><%= us.getNombreRol() %></td>
                                <td>
                                    <button id="deshabilitarUser" data-id="<%= us.getId_usuario() %>" class="p-2 bg-mdaGreen text-white rounded-xl text-xs ease-linear hover:bg-green-700">
                                        Deshabilitar
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
                
                <div id="tab2" class="overflow-x-auto tab-content">
                    <table class="table overflow-hidden bg-white rounded-ss-none shadow-lg">
                        <!-- head -->
                        <thead>
                            
                            <tr class="text-black text-md text-center">
                                <th>Nombre usuario</th>
                                <th>Estado</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Usuario us : listUsers) {
                                    if (!us.getEstadoUser()) {
                            %>
                            <tr class="text-black hover:bg-mdaGreen_400 cursor-pointer text-md text-center">
                                <td><%= us.getNombreUser() %></td>
                                <td><%= !us.getEstadoUser() ? "Deshabilitado" : "Habilitado"%></td>
                                <td><%= us.getNombreRol() %></td>
                                <td>
                                    <button id="habilitarUser" data-id="<%= us.getId_usuario() %>" class="p-2 bg-mdaGreen text-white rounded-xl text-xs ease-linear hover:bg-green-700">
                                        Habilitar
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
        </section>
    </body>
    <script src="../../scripts/dashboard.js"></script>
</html>
