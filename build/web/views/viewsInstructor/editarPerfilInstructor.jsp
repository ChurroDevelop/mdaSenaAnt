<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Enlace para estilos personalizados -->
    <link rel="stylesheet" href="../../styles/style.css" />
    <!-- Enlace con la librería Tailwind -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Enlace para personalización de colores en Tailwind -->
    <script src="../../scripts/tailwind.js"></script>
    <!-- Enlace con la libería DaisyUI -->
    <link
      href="https://cdn.jsdelivr.net/npm/daisyui@4.12.2/dist/full.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <!-- Enlace para iconos de Font Awesome -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
      integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <title>MDA Sena - Inicio</title>
  </head>

  <body class="flex bg-mdaWhite bg-gradient-to-t from-mdaGreen_400 to-mdaWhite">
    <!-- Ingrese aquí la estrucuta de la página -->

    <!-- Barra lateral izquierda -->
    <nav class="bg-white p-7 shadow-md sticky top-0 h-screen z-10">
      <div class="grid gap-y-5">
        <!-- Logo Sena y nombre del proyecto -->
        <div class="flex flex-row w-full h-32">
          <!-- Logo -->
          <div class="grid flex-grow place-items-center w-full">
            <img src="../../images/LogoNegro.svg" alt="" />
          </div>
          <!-- HR -->
          <div class="divider divider-horizontal"></div>
          <!-- Nombre -->
          <div class="grid flex-grow place-items-center w-full">
            <h1 class="text-4xl text-mdaBlack leading-none">
              <span class="text-3xl text-mdaGreen">MDA</span> <br />
              Sena
            </h1>
          </div>
        </div>
        <!-- HR -->
        <div class="flex flex-col w-full">
          <div class="divider m-0 h-0"></div>
        </div>
        <!-- Input de búsqueda -->
        <form action="">
          <label class="input input-bordered flex items-center gap-2 bg-white">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 16 16"
              fill="currentColor"
              class="w-4 h-4 opacity-70"
            >
              <path
                fill-rule="evenodd"
                d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
                clip-rule="evenodd"
              />
            </svg>
            <input
              type="text"
              class="grow text-mdaBlack"
              placeholder="Search"
            />
          </label>
        </form>
        <!-- HR -->
        <div class="flex flex-col w-full">
          <div class="divider m-0 h-0"></div>
        </div>
        <!-- Botones navegación -->
        <div>
          <!-- Inicio -->
          <a href="inicio.jsp">
            <button
              class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start"
            >
              <i class="fa-solid fa-house"></i>
              Inicio
            </button>
          </a>
          <!-- Asignar monitor -->
          <a href="asignarMonitor.jsp">
            <button
              class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start"
            >
              <i class="fa-solid fa-user-plus"></i>
              Asignar monitor
            </button>
          </a>
          <a href="#">
            <button
              id="showModal-2"
              class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start"
            >
              <i class="fa-solid fa-bell"></i>
              Notificaciones
            </button>
          </a>
          <a href="editarPerfilInstructor.jsp">
            <button
              class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start"
            >
              <i class="fa-regular fa-address-card"></i>
              Perfil
            </button>
          </a>
        </div>
      </div>
      <!-- Barra notificaciones -->
      <nav
        id="modal-2"
        class="hidden bg-white p-7 shadow-md absolute top-0 left-full h-screen w-full"
      >
        <div class="grid gap-y-5">
          <button
            id="closeModal-2"
            class="btn btn-sm btn-circle btn-ghost text-mdaGreen"
          >
            <i class="fa-solid fa-chevron-left"></i>
          </button>
          <!-- HR -->
          <div class="flex flex-col w-full">
            <div class="divider m-0 h-0"></div>
          </div>
          <div>
            <p class="text-mdaBlack text-sm">
              Daniel Acetaminofén, ha cargado una evidencia
            </p>
            <button
              class="btn btn-sm bg-mdaGreen border-none text-white mt-2 hover:bg-mdaGreen w-full"
            >
              Ver evidencia
            </button>
          </div>
        </div>
      </nav>
    </nav>

    <div id="modal-2__background" class="hidden bg-mdaBlack_400 w-full min-h-screen absolute"></div>
    <!-- Contenedor para los artículos -->
    <section
      class="m-auto flex w-full max-w-screen-2xl min-h-screen justify-center p-5 gap-5 flex-wrap content-center"
    >
      <!-- Artículo -->
      <section class="grid bg-white p-7 rounded-lg shadow-md">
        <!--  -->
        <h3 class="text-mdaBlack text-2xl text-center mb-1">Perfil</h3>
        <p class="text-mdaBlack text-center font-thin mb-4">
          Actualiza tus datos personales
        </p>
        <!-- Foto de perfil -->
        <div class="flex items-center flex-col mb-4">
          <h2 class="text-mdaBlack text-center mb-1">Foto de perfil</h2>
          <img class="w-24" src="../../images/fotoPerfil.svg" alt="" />
        </div>
        <form action="../login.html" class="grid w-full md:w-80">
          <p class="text-center text-mdaBlack mb-4">Datos básicos del perfil</p>
          <!-- Nombre -->
          <label
            class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4"
          >
            <i class="fa-solid fa-user text-gray-400"></i>
            <input
              type="text"
              class="grow text-mdaBlack"
              placeholder="Nombre"
            />
          </label>
          <!-- Apellidos -->
          <label
            class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4"
          >
            <i class="fa-solid fa-user text-gray-400"></i>
            <input
              type="text"
              class="grow text-mdaBlack"
              placeholder="Apellidos"
            />
          </label>
          <!-- Número de documento -->
          <label
            class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4"
          >
            <i class="fa-solid fa-id-card text-gray-400"></i>
            <input
              type="text"
              class="grow text-mdaBlack"
              placeholder="Número de documento"
            />
          </label>
          <!-- Centro de formación -->
          <label
            class="bg-white text-mdaBlack input input-bordered flex items-center gap-2"
          >
            <i class="fa-solid fa-hospital text-gray-400"></i>
            <input
              type="text"
              class="grow text-mdaBlack"
              placeholder="Centro de formación"
            />
          </label>
          <button
            class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen"
          >
            Guardar
          </button>
          <button
            class="btn bg-transparent border border-mdaRed text-mdaRed mt-2 hover:bg-mdaRed hover:text-mdaWhite hover:border-mdaRed"
          >
            Cerrar sesión
          </button>
        </form>
      </section>
    </section>
    <!-- Indicador de rol -->
    <button
      class="bg-white btn btn-sm border-none text-mdaBlack absolute top-0 right-0 m-2.5 hover:bg-white"
    >
      <i class="fa-solid fa-user"></i>Instructor
    </button>

    <!-- Enlace para manejo del DOM -->
    <script src="../../scripts/script.js"></script>
  </body>
</html>
