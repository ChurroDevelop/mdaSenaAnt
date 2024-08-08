<%@page import="modelo.objetos.Perfil"%>
<%@page import="modelo.objetos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    HttpSession sesion = request.getSession(false);
    HttpSession sesionPerfil = request.getSession(false);
    if ((sesion == null || sesion.getAttribute("dataUser") == null)) {
        System.out.println("Error en la vista de editar perfil");
        response.sendRedirect("login.jsp");
        return;
    }
    Perfil perfil = (Perfil) sesionPerfil.getAttribute("dataPerfil");
    Usuario user = (Usuario) sesion.getAttribute("dataUser");
%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Editar perfil");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %>

    <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
        <!-- Ingrese aquí la estrucuta de la página -->

        <div class="hidden md:block">
            <%@ include file="partials/nav.jsp" %>            
        </div>

        <div class="block md:hidden">
            <%@ include file="partials/navMobile.jsp" %>            
        </div>

        <!-- Contenedor para los artículos -->
        <section class="m-auto flex justify-center items-center min-h-screen">
            <!-- Artículo -->
            <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96">
                <!-- Texto informativo -->
                <h3 class="text-mdaBlack text-2xl text-center mb-1">Perfil</h3>
                <p class="text-mdaBlack text-center font-thin mb-4">
                    Actualiza tus datos personales
                </p>
                <!-- Foto de perfil -->
                <!--      <div class="flex items-center flex-col mb-4">
                        <h2 class="text-mdaBlack text-center mb-1">Foto de perfil</h2>
                        <img class="w-24" src="../../images/fotoPerfil.svg" alt="" />
                      </div>-->
                <form action="svModificar" class="grid w-full" method="POST" id="formularioEditarPerfil">

                    <!--<p class="text-center text-mdaBlack mb-4">Datos básicos del perfil</p>-->
                    <!-- Id del perfil -->
                    <input type="number" name="txtIdPerfil" class="hidden" value="<%= perfil.getId_perfil()%>">
                    <!-- Nombre -->
                    <div>
                        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2">
                            <i class="fa-solid fa-user text-gray-400"></i>
                            <input id="inputNombre" type="text" class="grow text-mdaBlack" name="txtNombre" placeholder="Nombre" value="<%= perfil.getNombre_usuario()%>"/>
                        </label>
                    </div>
                    <!-- Apellidos -->
                    <div>
                        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4">
                            <i class="fa-solid fa-user text-gray-400"></i>
                            <input id="inputApellido" type="text" class="grow text-mdaBlack" name="txtApellido" placeholder="Apellidos" value="<%= perfil.getApellido_usuario()%>"/>
                        </label>
                    </div>
                    <!-- Número de documento -->
                    <div>
                        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4">
                            <i class="fa-solid fa-id-card text-gray-400"></i>
                            <input id="inputDocumento" type="text" class="grow text-mdaBlack" name="txtDocumento" placeholder="Número de documento" value="<%= perfil.getNum_documento()%>"/>
                        </label>
                    </div>
                    <!-- Centro de formación -->        
                    <div>
                        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4">
                            <i class="fa-solid fa-hospital text-gray-400"></i>
                            <input id="inputCentro" type="text" class="grow text-mdaBlack" name="txtCentro" placeholder="Centro de formación" value="<%= perfil.getCentro_formacion()%>"/>
                        </label>
                    </div>
                    <button class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
                        Modificar datos
                    </button>
                </form>
                <a id="btnCerrar" href="login.jsp" class="btn bg-transparent border border-mdaRed text-mdaRed mt-2 hover:bg-mdaRed hover:text-mdaWhite hover:border-mdaRed">
                    Cerrar session
                </a>
            </section>
        </section>
        <!-- Indicador de rol -->
        <button class="bg-white btn btn-sm border-none text-mdaBlack absolute top-0 right-0 m-2.5 hover:bg-white">
            <i class="fa-solid fa-user"></i> <%= user.getId_rol_fk().getNombre_rol()%>
        </button>

        <!-- Enlace para manejo del DOM -->
        <script type="module" src="scripts/validacionFormularios/editarPerfil.js"></script>
        <script src="scripts/cerrar.js"></script>
        <script src="scripts/buscador.js"></script>
        <script src="scripts/buscarMobile.js"></script>
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
