<%
    // Configura el título de la página
    request.setAttribute("pageTitle", "MDA SENA - Autenticación");
%>

<!DOCTYPE html>
<html lang="en">

    <%@ include file="partials/head.jsp" %> <!-- Incluye el archivo de encabezado común -->

    <body class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5 bg-background3 bg-cover">
        <!-- Filtro imagen -->
        <div class="absolute top-0 left-0 min-h-screen w-full bg-mdaBlack_600"></div>

        <!-- Contenedor principal de la página -->
        <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96 z-10">
            <!-- Título de la sección -->
            <h3 class="text-mdaBlack text-xl text-center mb-1.5">
                Autenticación de cuenta
            </h3>
            <!-- Mensaje informativo -->
            <p class="text-center flex justify-center mb-2.5">
                Ingrese el código de verificación <br />
                enviado al correo registrado
            </p>
            <!-- Formulario para ingresar el código de verificación -->
            <form class="validarFormulario grid" method="POST" id="formularioCodigo">
                <div>
                    <label class="validarLabelCodigo bg-white text-mdaBlack input input-bordered flex items-center gap-2" id="labelCodigo">
                        <i class="fa-solid fa-unlock-keyhole text-gray-400"></i>
                        <input type="text" class="validarCodigo grow text-mdaBlack" placeholder="Código de verificación" autofocus
                               name="txtCodigo" id="inputCodigo" maxlength="6"/>
                    </label>
                </div>
                <button type="submit" class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
                    Verificar
                </button>
            </form>
            <div id="resultado"></div>
        </section>
        <!-- Enlace para manejo del DOM -->
        <script type="module" src="scripts/validacionFormularios/autenticacion.js"></script>
    </body>

</html>
