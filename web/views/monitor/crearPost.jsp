<%@page import="modelo.objetos.Perfil"%>
<%@page import="modelo.objetos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    HttpSession sesion = request.getSession(false);
    HttpSession sesionPerfil = request.getSession(false);
    if (sesion == null || sesion.getAttribute("dataUser") == null) {
        response.sendRedirect("../../login.jsp");
        return;
    }
    Perfil perfil = (Perfil) sesionPerfil.getAttribute("dataPerfil");
    Usuario user = (Usuario) sesion.getAttribute("dataUser");
%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Crear Post");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="../../partials/roles/head.jsp" %>

    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Incluir la navegación -->
        <!-- Incluir la navegación -->
        <div class="hidden md:block">
            <%@ include file="../../partials/roles/nav.jsp" %>            
        </div>

        <div class="block md:hidden">
            <%@ include file="../../partials/roles/navMobile.jsp" %>            
        </div>

        <div id="modal-2__background" class="hidden bg-mdaBlack_400 w-full min-h-screen absolute"></div>
        <!-- Contenedor para los artículos -->
        <section class="m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-center">
            <!-- Contenedor -->
            <article class="bg-white w-full max-w-2xl shadow-md rounded-lg p-5 flex flex-col justify-between gap-5">
                <!-- Crear post -->
                <div class="flex w-full justify-between">
                    <h3 class="text-mdaBlack text-xl">Crear post</h3>
                </div>
                <!-- Post -->
                <form action="/svCrearPost" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="idUsuario" value="<%= perfil.getId_perfil()%>"> 
                    <label class="input input-bordered flex items-center gap-2 bg-white mb-4">
                        <i class="fa-solid fa-heading"></i>
                        <input type="text" class="grow text-mdaBlack" placeholder="Titulo del post" id="titulo" name="titulo"> 
                    </label>
                    <label class="input input-bordered flex items-center gap-2 bg-white mb-4">
                        <i class="fa-solid fa-file-upload"></i> 
                        <input type="file" class="grow text-mdaBlack" id="archivo" name="archivo" accept=".pdf,.doc,.docx,.py" multiple> 
                    </label>
                    <button type="submit" class="btn w-full bg-mdaGreen border-none text-white hover:bg-mdaGreen">
                        Cargar post
                    </button>
                </form>
            </article>
        </section>
        <!-- Indicador de rol -->
        <button class="bg-white btn btn-sm border-none text-mdaBlack absolute top-0 right-0 m-2.5 hover:bg-white">
            <i class="fa-solid fa-user"></i>Monitor
        </button>

        <!-- Enlace para manejo del DOM -->
        <script src="../../scripts/monitor.js"></script>
        <script src="../../scripts/buscador.js"></script>
        <script src="../../scripts/buscarMobile.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const abrirNavegacionBtn = document.getElementById("abrirNavegacion");
                const modalNavegacion = document.getElementById("modalNavegacion");

                abrirNavegacionBtn.addEventListener("click", function () {
                    // Toggle visibility of the navigation
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
