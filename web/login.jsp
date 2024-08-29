<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("pageTitle", "MDA SENA - Ingreso");
%>
<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %> <!-- Incluye el archivo de encabezado común -->

    <body class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5 bg-background2 bg-cover">
        <!-- Fondo de pantalla negro con filtro para la página -->
        <div class="absolute top-0 left-0 min-h-screen w-full bg-mdaBlack_600"></div>

        <!-- Contenedor principal del formulario de inicio de sesión -->
        <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96 z-10">
            <!-- Encabezado de la sección de inicio de sesión -->
            <h3 class="text-mdaBlack text-xl text-center mb-2.5">
                Ingrese a nuestra plataforma!
            </h3>
            <!-- Contenedor para el logo y nombre del proyecto -->
            <div class="flex flex-row w-full h-20 mb-2.5">
                <!-- Contenedor del logo -->
                <div class="grid flex-grow place-items-center justify-end w-full">
                    <img class="w-12" src="images/LogoNegro.svg" alt="">
                </div>
                <!-- Línea divisoria horizontal -->
                <div class="divider divider-horizontal"></div>
                <!-- Contenedor del nombre del proyecto -->
                <div class="grid flex-grow place-items-center justify-start w-full">
                    <h1 class="text-2xl text-mdaBlack leading-none">
                        <span class="text-xl text-mdaGreen">MDA</span> <br> Sena
                    </h1>
                </div>
            </div>
            <!-- Formulario de inicio de sesión -->
            <form class="validarFormulario grid" method="POST" id="formularioLogin">
                <!-- Campo para ingresar el correo institucional -->
                <div>
                    <label id="labelCorreo" class="validarLabelInput bg-white text-mdaBlack input input-bordered flex items-center gap-2">
                        <i class="fa-solid fa-envelope text-gray-400"></i>
                        <input name="txtCorreo" id="inputCorreo" type="text" class="validarCorreo grow text-mdaBlack" placeholder="Correo institucional" autocomplete="off" autofocus />
                    </label>
                </div>
                <!-- Campo para ingresar la contraseña -->
                <div>
                    <label id="labelClave" class="validarLabelContrasena bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4">
                        <i class="fa-solid fa-lock text-gray-400"></i>
                        <input name="txtClave" id="inputClave" type="password" class="validarContrasena grow text-mdaBlack" placeholder="Contraseña" autocomplete="off" />
                    </label>
                </div>
                <!-- Enlace para recuperación de contraseña -->
                <p class="text-sm text-right mt-1 text-mdaGreen hover:underline">
                    <a href="correoRecuperacion.jsp">¿Olvidó su contraseña?</a>
                </p>
                <!-- Botón para enviar el formulario de inicio de sesión -->
                <button id="btnForm" type="submit" class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
                    Ingresar
                </button>
            </form>
            <!-- Enlace para usuarios que no tienen cuenta -->
            <div class="flex justify-center gap-2 text-xs">
                <p class="text-sm text-center mt-1 text-mdaBlack">
                    ¿Aún no posee una cuenta?
                    <a class="text-mdaGreen hover:underline" href="registro.jsp">Regístrese</a>
                </p>
            </div>
            <!-- Contenedor para mensajes de resultado o errores -->
            <div id="resultado"></div>
        </section>
        <!-- Archivo JavaScript para la validación del formulario de inicio de sesión -->
        <script type="module" src="scripts/validacionFormularios/login.js"></script>
    </body>

</html>
