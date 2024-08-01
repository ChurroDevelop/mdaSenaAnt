<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    request.setAttribute("pageTitle", "MDA SENA - Código de recuperación");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %>

    <body id="body" class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5 bg-background3 bg-cover">
        <!-- Filtro imagen -->
        <div class="absolute top-0 left-0 min-h-screen w-full bg-mdaBlack_600"></div>

        <!-- Ingrese aquí la estrucuta de la página -->
        <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96 z-10">
            <h3 class="text-mdaBlack text-xl text-center mb-1.5">
                Recuperación de contraseña
            </h3>
            <p class="text-center flex justify-center mb-2.5">
                Ingrese el código enviado a su correo
            </p>
            <form id="formularioCodigoContrasena" action="svVerificarCodigoContrasena" class="grid" method="POST">
                <div>
                    <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2">
                        <i class="fa-solid fa-lock text-gray-400"></i>
                        <input name="txtCodigo" id="inputCodigo" type="text" class="grow text-mdaBlack" placeholder="Codigo de recuperacion" />
                    </label>
                </div>
                <button type="submit" class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
                    Verificar
                </button>
            </form>
            <div id="resultado"></div>
        </section>
        <!-- Enlace para manejo del DOM -->
        <script type="module" src="scripts/validacionFormularios/codigoContrasena.js"></script>
    </body>

</html>