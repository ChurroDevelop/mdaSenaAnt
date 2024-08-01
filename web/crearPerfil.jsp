<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Crear perfil");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %>

    <body class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5 bg-background4 bg-cover">
        <!-- Ingrese aquí la estrucuta de la página -->

        <!-- Filtro imagen -->
        <div class="absolute top-0 left-0 min-h-screen w-full bg-mdaBlack_600"></div>

        <!-- Contenedor de login -->
        <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96 z-10">
            <!--  -->
            <h3 class="text-mdaBlack text-xl text-center mb-2.5">
                Bienvenido, ingrese los siguiente datos y cree su perfil
            </h3>
            <!-- Logo Sena y nombre del proyecto -->
            <div class="flex flex-row w-full h-20 mb-2.5">
                <!-- Logo -->
                <div class="grid flex-grow place-items-center justify-end w-full">
                    <img class="w-12" src="images/LogoNegro.svg" alt="" />
                </div>
                <!-- HR -->
                <div class="divider divider-horizontal"></div>
                <!-- Nombre -->
                <div class="grid flex-grow place-items-center justify-start w-full">
                    <h1 class="text-2xl text-mdaBlack leading-none">
                        <span class="text-xl text-mdaGreen">MDA</span> <br />
                        Sena
                    </h1>
                </div>
            </div>
            <form action="svPerfil" class="validarFormulario grid" method="POST" id="formularioCrearPerfil">
                <!-- Nombre -->
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2" id="labelName">
                        <i class="fa-solid fa-user text-gray-400"></i>
                        <input id="inputNombre" type="text" class="grow text-mdaBlack" placeholder="Nombre" autocomplete="off"
                               name="txtNombre"/>
                    </label>
                </div>
                <!-- Apellidos -->
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4" id="labelLast">
                        <i class="fa-solid fa-user text-gray-400"></i>
                        <input id="inputApellido" type="text" class="grow text-mdaBlack" placeholder="Apellidos" autocomplete="off"
                               name="txtApellidos"/>
                    </label>
                </div>
                <!-- Número de documento -->
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4" id="labelDocumento">
                        <i class="fa-solid fa-id-card text-gray-400"></i>
                        <input id="inputDocumento" type="text" class="grow text-mdaBlack" placeholder="Número de documento"
                               autocomplete="off" maxlength="20" name="txtDocumento"/>
                    </label>
                </div>
                <!-- Centro de formación -->
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4" id="labelCentro">
                        <i class="fa-solid fa-hospital text-gray-400"></i>
                        <input id="inputCentro" type="text" class="grow text-mdaBlack" placeholder="Centro de formación"
                               autocomplete="off" name="txtCentro"/>
                    </label>
                </div>
                <button class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen" type="submit" id="btnForm">
                    Crear perfil
                </button>
            </form>
        </section>
        <!-- Enlace para manejo del DOM -->
        <script type="module" src="scripts/validacionFormularios/crearPerfil.js"></script>
    </body>

</html>