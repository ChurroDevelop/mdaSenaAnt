<!-- Barra lateral izquierda -->
<nav class="bg-white px-7 py-2 shadow-md fixed top-0 w-full z-10">
    <div class="grid gap-y-5">
        <div class="flex place-items-center gap-5">
            <!-- Nombre -->
            <h1 class="text-base text-mdaBlack leading-none">
                <span class="text-mdaGreen">MDA</span> <br />
                SENA
            </h1>
            <!-- Input de búsqueda -->
            <form action="" class="w-full">
                <label class="input input-bordered flex items-center gap-2 bg-white">
                    <i class="fa-solid fa-magnifying-glass text-gray-400"></i>
                    <input type="text" class="grow text-mdaBlack" placeholder="Search" id="buscador2"/>
                </label>
            </form>

            <button class="" id="abrirNavegacion">
                <i class="fa-solid fa-bars text-mdaBlack"></i>
            </button>

        </div>

        <div class="hidden" id="modalNavegacion">

            <%
                if ("Aprendiz".equals(user.getId_rol_fk().getNombre_rol())) {
            %>

            <!-- Botones navegación -->
            <div>
                <!-- Inicio -->
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
        </div>

        <%
        } else if ("Instructor".equals(user.getId_rol_fk().getNombre_rol())) {
        %>

        <!-- Botones navegación -->
        <div>
            <!-- Inicio -->
            <a href="inicio.jsp">
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
            <a href="">
                <form action="/svListarPosts" method="POST">
                    <input type="hidden" value="<%= user.getId_usuario()%>" name="txtIdInstructor">
                    <button
                        class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                        <i class="fa-solid fa-bell"></i>
                        Tablero de control
                    </button>
                </form>
            </a>
            <a href="editarPerfil.jsp">
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
        <a href="inicio.jsp">
            <button
                class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                <i class="fa-solid fa-house"></i>
                Inicio
            </button>
        </a>
        <!-- Crear post -->
        <a href="views/monitor/crearPost.jsp">
            <button
                class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                <i class="fa-solid fa-plus-minus"></i>
                Crear post
            </button>
        </a>
        <a href="">
            <form action="/svListarPostsMonitor" method="POST">
                <input type="hidden" value="<%= user.getId_usuario()%>" name="txtIdMonitor">
                <button
                    class="btn bg-transparent shadow-none w-full border-none text-mdaBlack hover:bg-mdaGreen_400 flex justify-start">
                    <i class="fa-solid fa-bell"></i>
                    Tablero de control
                </button>
            </form>
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
</div>
</nav>
