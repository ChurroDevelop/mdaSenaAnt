<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "MDA SENA - Ingreso");
%>
<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %>

    <body class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5 bg-background2 bg-cover">
        <!-- Ingrese aquí la estrucuta de la página -->

        <!-- Filtro imagen -->
        <div class="absolute top-0 left-0 min-h-screen w-full bg-mdaBlack_600"></div>

        <!-- Contenedor de login -->
        <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96 z-10">
            <!-- Texto informativo -->
            <h3 class="text-mdaBlack text-xl text-center mb-2.5">
                Ingrese a nuestra plataforma!
            </h3>
            <!-- Logo Sena y nombre del proyecto -->
            <div class="flex flex-row w-full h-20 mb-2.5">
                <!-- Logo -->
                <div class="grid flex-grow place-items-center justify-end w-full">
                    <img class="w-12" src="images/LogoNegro.svg" alt="">
                </div>
                <!-- HR -->
                <div class="divider divider-horizontal"></div>
                <!-- Nombre -->
                <div class="grid flex-grow place-items-center justify-start w-full">
                    <h1 class="text-2xl text-mdaBlack leading-none">
                        <span class="text-xl text-mdaGreen">MDA</span> <br> Sena
                    </h1>
                </div>
            </div>
            <form class="validarFormulario grid" method="POST" id="formularioLogin">
                <div>
                    <label id="labelCorreo" class="validarLabelInput bg-white text-mdaBlack input input-bordered flex items-center gap-2">
                        <i class="fa-solid fa-envelope text-gray-400"></i>
                        <input name="txtCorreo" id="inputCorreo" type="text" class="validarCorreo grow text-mdaBlack" placeholder="Correo institucional" autocomplete="off" autofocus />
                    </label>
                </div>
                <div>
                    <label id="labelClave" class="validarLabelContrasena bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4">
                        <i class="fa-solid fa-lock text-gray-400"></i>
                        <input name="txtClave" id="inputClave" type="password" class="validarContrasena grow text-mdaBlack" placeholder="Contraseña" autocomplete="off" />
                    </label>
                </div>
                <p class="text-sm text-right mt-1 text-mdaGreen hover:underline">
                    <a href="correoRecuperacion.jsp">¿Olvidó su contraseña?</a>
                </p>
                <button id="btnForm" type="submit" class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
                    Ingresar
                </button>
            </form>
            <div class="flex justify-center gap-2 text-xs">
                <p class="text-sm text-center mt-1 text-mdaBlack">
                    ¿Aún no posee una cuenta?
                    <a class="text-mdaGreen hover:underline" href="registro.jsp">Regístrese</a>
                </p>
            </div>
            <div id="resultado"></div>
        </section>
        <!-- Enlace para manejo del DOM -->
        <script type="module" src="scripts/validacionFormularios/login.js"></script>
    </body>

</html>