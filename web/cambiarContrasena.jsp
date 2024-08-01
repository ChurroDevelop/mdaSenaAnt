<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Cambiar contraseña");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %>

    <body id="body" class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5 bg-background3 bg-cover">
        <!-- Filtro imagen -->
        <div class="absolute top-0 left-0 min-h-screen w-full bg-mdaBlack_600"></div>

        <!-- Ingrese aquí la estrucuta de la página -->
        <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96 z-10">
            <!--  -->
            <h3 class="text-mdaBlack text-xl text-center mb-2.5">
                Establece una nueva contraseña
            </h3>
            <p class="text-center flex justify-center mb-2.5">Cambie su contraseña</p>
            <form id="formCambiarContrasena" action="svCambiarContrasena" method="POST" class="grid">
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2">
                        <i class="fa-solid fa-lock text-gray-400"></i>
                        <input name="txtClave" id="inputClave" type="password" class="grow text-mdaBlack" placeholder="Contraseña nueva" autocomplete="off" />
                    </label>
                </div>
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mt-4">
                        <i class="fa-solid fa-lock text-gray-400"></i>
                        <input name="txtConfirmarClave" id="inputConfirm" type="password" class="grow text-mdaBlack" placeholder="Confirmar contraseña" />
                    </label>
                </div>
                <button type="submit" class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
                    Cambiar contraseña
                </button>
            </form>
        </section>
        <!-- Enlace para manejo del DOM -->
        <script type="module" src="scripts/validacionFormularios/cambiarContrasena.js"></script>
    </body>

</html>