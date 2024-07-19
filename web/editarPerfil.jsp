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
    <link href="https://cdn.jsdelivr.net/npm/daisyui@4.12.2/dist/full.min.css" rel="stylesheet" type="text/css" />
    <!-- Enlace para iconos de Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
      integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>MDA Sena - Editar perfil</title>
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
            <i class="fa-solid fa-magnifying-glass text-gray-400"></i>
            <input type="text" class="grow text-mdaBlack" placeholder="Search" />
          </label>
        </form>
        <!-- HR -->
        <div class="flex flex-col w-full">
          <div class="divider m-0 h-0"></div>
        </div>
        <%
            if ("Aprendiz".equals(user.getId_rol_fk().getNombre_rol())){
        %>
        <!-- Botones navegación Aprendiz -->
        <div>
            <a href="inicio.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-house"></i>
                    Inicio
                </button>
            </a>
            <a href="editarPerfil.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-regular fa-address-card"></i>
                    Perfil
                </button>
            </a>
        </div>
        <%
            } else if ("Instructor".equals(user.getId_rol_fk().getNombre_rol())) {
        %>
         <!-- Botones navegación Instructor -->
        <div>
            <a href="inicio.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-house"></i>
                    Inicio
                </button>
            </a>
            <a href="views/instructor/asignarMonitor.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-plus-minus"></i>
                    Asignar monitor
                </button>
            </a>
            <a href="#">
                <button id="showModal-2"
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-bell"></i>
                    Notificaciones
                </button>
            </a>
            <a href="editarPerfil.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-regular fa-address-card"></i>
                    Perfil
                </button>
            </a>
        </div>
         <%
             } else if ("Monitor".equals(user.getId_rol_fk().getNombre_rol())) {
         %>
          <!-- Botones navegación Monitor -->
        <div>
            <a href="inicio.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-house"></i>
                    Inicio
                </button>
            </a>
            <a href="views/monitor/crearPost.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-plus-minus"></i>
                    Crear post
                </button>
            </a>
            <a href="#">
                <button id="showModal-2"
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-bell"></i>
                    Notificaciones
                </button>
            </a>
            <a href="editarPerfil.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-regular fa-address-card"></i>
                    Perfil
                </button>
            </a>
        </div>
        <%
            }
        %>
      </div>
        

        <!-- Barra notificaciones -->
        <nav id="modal-2" class="hidden bg-white p-7 shadow-md absolute top-0 left-full h-screen w-full">
            <div class="grid gap-y-5">
                <button id="closeModal-2" class="btn btn-sm btn-circle btn-ghost text-mdaGreen">
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
                    <button class="btn btn-sm bg-mdaGreen border-none text-white mt-2 hover:bg-mdaGreen w-full">
                        Ver evidencia
                    </button>
                </div>
            </div>
        </nav>
    </nav>

  <div id="modal-2__background" class="hidden bg-mdaBlack_400 w-full min-h-screen absolute"></div>

  <!-- Contenedor para los artículos -->
  <section class="m-auto flex w-full justify-center">
    <!-- Artículo -->
    <section class="container grid bg-white p-7 rounded-lg shadow-md w-full md:w-96">
      <!-- Texto informativo -->
      <h3 class="text-mdaBlack text-2xl text-center mb-1">Perfil</h3>
      <p class="text-mdaBlack text-center font-thin mb-4">
        Actualiza tus datos personales
      </p>
      <!-- Foto de perfil -->
      <div class="flex items-center flex-col mb-4">
        <h2 class="text-mdaBlack text-center mb-1">Foto de perfil</h2>
        <img class="w-24" src="../../images/fotoPerfil.svg" alt="" />
      </div>
      <form action="svModificar" class="grid w-full" method="POST">
          
        <p class="text-center text-mdaBlack mb-4">Datos básicos del perfil</p>
        <!-- Id del perfil -->
        <input type="number" name="txtIdPerfil" class="hidden" value="<%= perfil.getId_perfil() %>">
        <!-- Nombre -->
        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4">
          <i class="fa-solid fa-user text-gray-400"></i>
          <input type="text" class="grow text-mdaBlack" name="txtNombre" placeholder="Nombre" value="<%= perfil.getNombre_usuario() %>"/>
        </label>
        <!-- Apellidos -->
        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4">
          <i class="fa-solid fa-user text-gray-400"></i>
          <input type="text" class="grow text-mdaBlack" name="txtApellido" placeholder="Apellidos" value="<%= perfil.getApellido_usuario() %>"/>
        </label>
        <!-- Número de documento -->
        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2 mb-4">
          <i class="fa-solid fa-id-card text-gray-400"></i>
          <input type="text" class="grow text-mdaBlack" name="txtDocumento" placeholder="Número de documento" value="<%= perfil.getNum_documento() %>"/>
        </label>
        <!-- Centro de formación -->
        <label class="bg-white text-mdaBlack input input-bordered flex items-center gap-2">
          <i class="fa-solid fa-hospital text-gray-400"></i>
          <input type="text" class="grow text-mdaBlack" name="txtCentro" placeholder="Centro de formación" value="<%= perfil.getCentro_formacion() %>"/>
        </label>
        <button class="btn bg-mdaGreen border-none text-white mt-4 hover:bg-mdaGreen">
          Modificar datos
        </button>
      </form>
      <form action="svCerrarSesion" method="POST" class="grid w-full">
          <button class="btn bg-transparent border border-mdaRed text-mdaRed mt-2 hover:bg-mdaRed hover:text-mdaWhite hover:border-mdaRed" type="submit">
                Cerrar sesion
          </button>
      </form>
    </section>
  </section>
  <!-- Indicador de rol -->
  <button class="bg-white btn btn-sm border-none text-mdaBlack absolute top-0 right-0 m-2.5 hover:bg-white">
    <i class="fa-solid fa-user"></i> <%= user.getId_rol_fk().getNombre_rol() %>
  </button>

  <!-- Enlace para manejo del DOM -->
  <script src="../../scripts/script.js"></script>
</body>

</html>