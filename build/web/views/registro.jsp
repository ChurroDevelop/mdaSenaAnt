<%-- 
    Document   : registro
    Created on : 18 jun 2024, 13:55:13
    Author     : Propietario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- Enlace para estilos personalizados -->
  <link rel="stylesheet" href="../styles/style.css">
  <!-- Enlace con la librería Tailwind -->
  <script src="https://cdn.tailwindcss.com"></script>
  <!-- Enlace para personalización de colores en Tailwind -->
  <script src="../scripts/tailwind.js"></script>
  <!-- Enlace con la libería DaisyUI -->
  <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.2/dist/full.min.css" rel="stylesheet" type="text/css" />
  <title>MDA Sena - Registro</title>
</head>

<body class="bg-mdaWhite grid content-center justify-center min-h-screen w-full p-2.5">
  <!-- Ingrese aquí la estrucuta de la página -->

  <!-- Contenedor de login -->
  <section class="grid bg-white p-7 rounded-lg shadow-md w-full">
    <!--  -->
    <h3 class="text-mdaBlack text-xl text-center mb-2.5">
      Registrese a nuestra plataforma!
    </h3>
    <!-- Logo Sena y nombre del proyecto -->
    <div class="flex flex-row w-full h-20 mb-2.5">
      <!-- Logo -->
      <div class="grid flex-grow place-items-center justify-end w-full">
        <img class="w-12" src="../images/LogoNegro.svg" alt="">
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
    <form action="viewsMonitor/inicio.jsp" class="grid">
      <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="w-4 h-4 opacity-70">
          <path
            d="M2.5 3A1.5 1.5 0 0 0 1 4.5v.793c.026.009.051.02.076.032L7.674 8.51c.206.1.446.1.652 0l6.598-3.185A.755.755 0 0 1 15 5.293V4.5A1.5 1.5 0 0 0 13.5 3h-11Z" />
          <path
            d="M15 6.954 8.978 9.86a2.25 2.25 0 0 1-1.956 0L1 6.954V11.5A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5V6.954Z" />
        </svg>
        <input type="text" class="grow text-mdaBlack" placeholder="Correo institucional" />
      </label>
      <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="w-4 h-4 opacity-70">
          <path fill-rule="evenodd"
            d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
            clip-rule="evenodd" />
        </svg>
        <input type="password" class="grow text-mdaBlack" placeholder="Contraseña" />
      </label>
      <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="w-4 h-4 opacity-70">
          <path fill-rule="evenodd"
            d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
            clip-rule="evenodd" />
        </svg>
        <input type="password" class="grow text-mdaBlack" placeholder="Confirmar contraseña" />
      </label>
      <button class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
        Registrar
      </button>
    </form>
    <div class="flex justify-center gap-2 text-xs">
      <p class="text-sm text-center mt-1 text-mdaBlack">
        ¿Ya posee una cuenta?
        <a class="text-mdaGreen hover:underline" href="login.jsp">Ingrese</a>
      </p>
  </section>
  <!-- Enlace para manejo del DOM -->
  <script src="../scripts/script.js"></script>
</body>

</html>
