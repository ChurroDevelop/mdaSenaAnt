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
            if ("Aprendiz".equals(user.getId_rol_fk().getNombre_rol())) {
        %>

        <!-- Botones navegación -->
        <div>
            <!-- Inicio -->
            <a href="../../inicio.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-house"></i>
                    Inicio
                </button>
            </a>
            <a href="../../editarPerfil.jsp">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-regular fa-address-card"></i>
                    Perfil
                </button>
            </a>
        </div>
    </div>

    <%
    } else if ("Instructor".equals(user.getId_rol_fk().getNombre_rol())) {
    %>

    <!-- Botones navegación -->
    <div>
        <!-- Inicio -->
        <a href="../../inicio.jsp">
            <button
                class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                <i class="fa-solid fa-house"></i>
                Inicio
            </button>
        </a>
        <!-- Asignar monitor -->
        <a href="">
            <form action="/svListarMonitores" method="GET">
                <input type="hidden" value="<%= user.getId_usuario()%> " name="txtIdInstructor">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-plus-minus"></i>
                    Asignar monitor
                </button>
            </form>
        </a>
        <a href="#">
            <button id="showModal-2"
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                <i class="fa-solid fa-bell"></i>
                Notificaciones
            </button>
        </a>
        <a href="../../editarPerfil.jsp">
            <button
                class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                <i class="fa-regular fa-address-card"></i>
                Perfil
            </button>
        </a>
    </div>
</div>

<%
} else if ("Monitor".equals(user.getId_rol_fk().getNombre_rol())) {
%>

<!-- Botones navegación -->
<div>
    <!-- Inicio -->
    <a href="../../inicio.jsp">
        <button
            class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
            <i class="fa-solid fa-house"></i>
            Inicio
        </button>
    </a>
    <!-- Crear post -->
    <a href="../../views/monitor/crearPost.jsp">
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
    <a href="../../editarPerfil.jsp">
        <button
            class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
            <i class="fa-regular fa-address-card"></i>
            Perfil
        </button>
    </a>
</div>
</div>

<%
    }
%>

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
