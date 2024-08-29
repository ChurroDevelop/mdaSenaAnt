document.addEventListener('DOMContentLoaded', function () {
    // Manejo de pestañas (tabs)
    const tabLinks = document.querySelectorAll('.tab-link');
    const tabContents = document.querySelectorAll('.tab-content');

    tabLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();

            // Elimina la clase 'active' de todos los enlaces y oculta todos los contenidos
            tabLinks.forEach(link => link.classList.remove('bg-white', 'text-mdaBlack'));
            tabContents.forEach(content => content.classList.remove('active'));

            // Añade la clase 'active' al enlace clicado y muestra el contenido correspondiente
            link.classList.add('bg-white', 'text-mdaBlack');
            const targetId = link.getAttribute('href').substring(1);
            document.getElementById(targetId).classList.add('active');
        });
    });

    // Muestra la primera pestaña por defecto
    tabLinks[0].click();
});

// Manejo del contador de archivos
const _btnContador = document.querySelectorAll("#cantidadArchivos");
_btnContador.forEach((btn) => {
    btn.addEventListener('click', () => {
        const _divs = document.querySelectorAll('#divPost');
        _divs.forEach((e) => {
            let idDiv = e.getAttribute('data-id');
            let idBtn = btn.getAttribute('data-id');
            if (idDiv === idBtn) {
                if (!e.classList.contains('hidden')) {
                    e.classList.add("hidden");
                } else {
                    e.classList.remove('hidden');
                }
            }
        });
    });
});

// Manejo del botón para cerrar el modal de la cantidad de documentos
const _cerrarCantidadPost = document.querySelectorAll("#cerrarCantidadPost");
_cerrarCantidadPost.forEach((e) => {
    e.addEventListener("click", () => {
        const _archivos = document.querySelectorAll("#divPost");
        _archivos.forEach((i) => {
            i.classList.add("hidden");
        });
    });
});

// Modal para ver la observación
const _observacion = document.querySelectorAll("#observacion");
_observacion.forEach((element) => {
    element.addEventListener("click", async () => {
        const _section = document.createElement("section");
        _section.classList.add(
                "flex",
                "bg-[#1D1D1D60]",
                "fixed",
                "min-h-screen",
                "w-full",
                "justify-center",
                "items-center",
                "z-10"
                );
        document.body.appendChild(_section);

        const _modal = document.createElement("div");
        _modal.classList.add(
                "bg-white",
                "w-96",
                "rounded-lg",
                "p-5",
                "text-center",
                "flex",
                "gap-5",
                "flex-col",
                "items-center",
                "relative"
                );
        _modal.textContent = "Observación"; // Aquí puedes mostrar la observación
        _section.appendChild(_modal);

        // Botón de cierre
        const _closeBtn = document.createElement("button");
        _closeBtn.classList.add(
                "absolute",
                "top-2",
                "right-2",
                "bg-red-500",
                "text-white",
                "rounded-full",
                "w-6",
                "h-6",
                "flex",
                "items-center",
                "justify-center"
                );
        _closeBtn.textContent = "X";
        _closeBtn.addEventListener("click", () => {
            document.body.removeChild(_section);
        });
        _modal.appendChild(_closeBtn);

        const _obser = document.createElement("p");
        _obser.classList.add("text-center");
        _obser.textContent = element.textContent;
        _modal.appendChild(_obser);
    });
});

// Modal para confirmar la aceptación de un post
const _aceptarDos = document.querySelectorAll("#eliminarPost");
_aceptarDos.forEach((e) => {
    e.addEventListener("click", async () => {
        const _section = document.createElement("section");
        _section.classList.add(
                "flex",
                "bg-[#1D1D1D60]",
                "fixed",
                "min-h-screen",
                "w-full",
                "justify-center",
                "items-center",
                "z-10"
                );
        document.body.appendChild(_section);

        const _modal = document.createElement("div");
        _modal.classList.add(
                "bg-white",
                "w-96",
                "rounded-lg",
                "p-5",
                "text-center",
                "flex",
                "gap-5",
                "flex-col",
                "items-center"
                );
        _modal.textContent = "¿Seguro que desea eliminar el post?";
        _section.appendChild(_modal);

        const _br = document.createElement("br");
        _modal.appendChild(_br);

        const _buttonContainer = document.createElement("div");
        _modal.appendChild(_buttonContainer);

        const _buttonUpdate = document.createElement("button");
        _buttonUpdate.classList.add(
                "btn",
                "w-14",
                "bg-mdaGreen",
                "text-white",
                "hover:bg-mdaGreen",
                "border-none"
                );
        _buttonUpdate.textContent = "Sí";
        _buttonContainer.appendChild(_buttonUpdate);

        _buttonUpdate.addEventListener("click", async () => {
            // Aquí pega el método para aceptar publicar el post
            let _idPost = e.getAttribute('data-id');
            let _idInstructor = document.getElementById("idInstructor").value;
            console.log(_idPost);
            let response = await fetch('/svEliminarPost', {
                method: 'POST',
                headers: { // Corrige 'header' a 'headers'
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'txtIdPost': _idPost,
                    'txtIdInstructor': _idInstructor
                })
            });
            location.reload();
            document.body.removeChild(_section);
        });

        const _buttonDeclive = document.createElement("button");
        _buttonDeclive.classList.add(
                "btn",
                "w-14",
                "bg-mdaRed",
                "text-white",
                "hover:bg-mdaRed",
                "border-none",
                "ml-2"
                );
        _buttonDeclive.textContent = "No";
        _buttonContainer.appendChild(_buttonDeclive);

        _buttonDeclive.addEventListener("click", () => {
            document.body.removeChild(_section);
        });

    });
});

// Modal para confirmar la publicación de un post
const _aceptar = document.querySelectorAll("#aceptarPost");
_aceptar.forEach((e) => {
    e.addEventListener("click", async () => {
        const _section = document.createElement("section");
        _section.classList.add(
                "flex",
                "bg-[#1D1D1D60]",
                "fixed",
                "min-h-screen",
                "w-full",
                "justify-center",
                "items-center",
                "z-10"
                );
        document.body.appendChild(_section);

        const _modal = document.createElement("div");
        _modal.classList.add(
                "bg-white",
                "w-96",
                "rounded-lg",
                "p-5",
                "text-center",
                "flex",
                "gap-5",
                "flex-col",
                "items-center"
                );
        _modal.textContent = "¿Seguro que desea publicar el post?";
        _section.appendChild(_modal);

        const _br = document.createElement("br");
        _modal.appendChild(_br);

        const _buttonContainer = document.createElement("div");
        _modal.appendChild(_buttonContainer);

        const _buttonUpdate = document.createElement("button");
        _buttonUpdate.classList.add(
                "btn",
                "w-14",
                "bg-mdaGreen",
                "text-white",
                "hover:bg-mdaGreen",
                "border-none"
                );
        _buttonUpdate.textContent = "Sí";
        _buttonContainer.appendChild(_buttonUpdate);

        _buttonUpdate.addEventListener("click", async () => {
            // Aquí pega el método para aceptar publicar el post
            let _idPost = e.getAttribute('data-id');
            let _idInstructor = document.getElementById("idInstructor").value;
            console.log(_idInstructor);

            let response = await fetch('/svEstadoPost', {
                method: 'POST',
                headers: { // Corrige 'header' a 'headers'
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'txtIdPost': _idPost,
                    'txtIdInstructor': _idInstructor
                })
            });
            location.reload();
            document.body.removeChild(_section);
        });

        const _buttonDeclive = document.createElement("button");
        _buttonDeclive.classList.add(
                "btn",
                "w-14",
                "bg-mdaRed",
                "text-white",
                "hover:bg-mdaRed",
                "border-none",
                "ml-2"
                );
        _buttonDeclive.textContent = "No";
        _buttonContainer.appendChild(_buttonDeclive);

        _buttonDeclive.addEventListener("click", () => {
            document.body.removeChild(_section);
        });

    });
});
