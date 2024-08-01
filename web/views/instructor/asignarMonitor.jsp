<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.objetos.Perfil"%>
<%@page import="modelo.objetos.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(false);
    if ((sesion == null || sesion.getAttribute("dataUser") == null)) {
        System.out.println("Error en la vista de editar perfil");
        response.sendRedirect("login.jsp");
        return;
    }
    Perfil perfil = (Perfil) sesion.getAttribute("dataPerfil");
    Usuario user = (Usuario) sesion.getAttribute("dataUser");
    List<Perfil> monitores = (List<Perfil>) sesion.getAttribute("listMonitores");
%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Asignar monitor");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="../../partials/roles/head.jsp" %>

    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Ingrese aquí la estrucuta de la página -->

        <!-- Incluir la navegación -->
        <%@ include file="../../partials/roles/nav.jsp" %>

        <div id="modal-2__background" class="hidden bg-mdaBlack_400 w-full min-h-screen absolute"></div>

        <!-- Modal agregar monitor -->
        <section id="modal-1"
                 class="hidden fixed m-auto flex w-full min-h-screen justify-center p-5 gap-5 flex-wrap content-center bg-mdaBlack_400 z-10">
            <article class="bg-white w-full max-w-2xl shadow-md rounded-lg p-5 flex flex-col justify-between gap-5">
                <div class="flex w-full justify-between">
                    <h3 class="text-mdaBlack text-xl">Agregar monitor</h3>
                    <button id="closeModal" class="btn btn-sm btn-circle btn-ghost text-mdaBlack">
                        ✕
                    </button>
                </div>
                <form action="" method="" id="buscarForm">
                    <label class="input input-bordered flex items-center gap-2 bg-white">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="w-4 h-4 opacity-70">
                        <path fill-rule="evenodd"
                              d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
                              clip-rule="evenodd" />
                        </svg>
                        <input type="text" class="grow text-mdaBlack" placeholder="Search" id="numAprendiz" name="txtNumero"/>
                    </label>
                    <button class="btn bg-mdaGreen border-none text-white hover:bg-mdaGreen w-full mt-4" type="submit">Agregar</button>
                </form>
                <div id="infoAprendiz" class="flex justify-around items-center">
                    <p id="detallesAprendiz" class="text-mdaBlack"></p>
                    <input type="hidden" id="idInstructor" value="<%= user.getId_usuario()%>" name="idInstructorTxt">
                </div>
            </article>
        </section>

        <!-- Contenedor para los artículos -->
        <section class="m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-center">
            <!-- Contenedor -->
            <article class="bg-white w-full max-w-2xl shadow-md rounded-lg p-5 flex flex-col justify-between gap-5">
                <!-- Agregar monitor -->
                <div class="flex w-full justify-between">
                    <h3 class="text-mdaBlack text-xl">Administrar monitores</h3>
                    <button id="showModal" class="btn bg-mdaGreen border-none text-white hover:bg-mdaGreen">
                        Agregar monitor
                    </button>
                </div>
                <!-- Buscar monitor -->
                <form action="" method="">
                    <label class="input input-bordered flex items-center gap-2 bg-white">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="w-4 h-4 opacity-70">
                        <path fill-rule="evenodd"
                              d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
                              clip-rule="evenodd" />
                        </svg>
                        <input type="text" class="grow text-mdaBlack" placeholder="Search"/>
                    </label>
                </form>
                <!-- HR -->
                <div class="flex flex-col w-full">
                    <div class="divider m-0 h-0"></div>
                </div>
                <!-- Quitar monitor -->
                <div class="w-full bg-mdaWhite rounded-lg p-1 flex flex-col gap-2">
                    <!-- Monitor #1 -->
                    <%
                        for (Perfil p : monitores) {
                    %>
                    <div class="flex justify-between items-center">
                        <div class="text-mdaBlack">
                            <i class="fa-solid fa-user-minus mx-2.5"></i>
                            <p class="inline-block"> <%= p.getNombre_usuario() + " " + p.getApellido_usuario()%> </p>
                        </div>
                        <form action="/svEliminarMonitor" method="POST">
                            <input type="hidden" value="<%= p.getId_perfil()%>" name="txtIdMonitor">
                            <button class="btn bg-mdaRed border-none text-white hover:bg-mdaRed">
                                Quitar monitor
                            </button>
                        </form>
                    </div>
                    <%
                        }
                    %>
                </div>
            </article>
        </section>
        <!-- Indicador de rol -->

        <!-- Enlace para manejo del DOM -->
        <script src="../../scripts/instructor.js"></script>
        <script src="../../scripts/script.js"></script>
    </body>

</html>
