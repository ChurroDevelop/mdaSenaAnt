document.addEventListener('DOMContentLoaded', function () {
    const tabLinks = document.querySelectorAll('.tab-link');
    const tabContents = document.querySelectorAll('.tab-content');

    tabLinks.forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();

            // Remove active class from all links and hide all contents
            tabLinks.forEach(link => link.classList.remove('bg-white', 'text-mdaBlack'));
            tabContents.forEach(content => content.classList.remove('active'));

            // Add active class to the clicked link and show corresponding content
            link.classList.add('bg-white', 'text-mdaBlack');
            const targetId = link.getAttribute('href').substring(1);
            document.getElementById(targetId).classList.add('active');
        });
    });

    // Show the first tab by default
    tabLinks[0].click();
});

// Esto es para ver la cantidad de archivos que tiene ese post
// Ayudeme a agregarle lo del modal a esta vain pofavo
const _btnContador = document.querySelectorAll("#cantidadArchivos");
_btnContador.forEach((btn) => {
    btn.addEventListener('click', () => {
        const _divs = document.querySelectorAll('#divPost')
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



//
//// Modal para ver la cantidad de los archivos
//const _cantidadArchivo = document.querySelectorAll("#cantidadArchivos");
//_cantidadArchivo.forEach((e) => {
//    e.addEventListener("click", async () => {
//        const _section = document.createElement("section");
//        _section.classList.add(
//                "flex",
//                "bg-[#1D1D1D60]",
//                "fixed",
//                "min-h-screen",
//                "w-full",
//                "justify-center",
//                "items-center",
//                "z-10"
//                );
//        document.body.appendChild(_section);
//
//        const _modal = document.createElement("div");
//        _modal.classList.add(
//                "bg-white",
//                "w-96",
//                "rounded-lg",
//                "p-5",
//                "text-center",
//                "flex",
//                "gap-5",
//                "flex-col",
//                "items-center",
//                "relative"
//                );
//        _modal.textContent = "Cantidad de archivos: 1"; // Por medio de JSP muestra la cantidad de archivos y muestra los arvhivos
//        _section.appendChild(_modal);
//
//        // Botón de cierre
//        const _closeBtn = document.createElement("button");
//        _closeBtn.classList.add(
//                "absolute",
//                "top-2",
//                "right-2",
//                "bg-red-500",
//                "text-white",
//                "rounded-full",
//                "w-6",
//                "h-6",
//                "flex",
//                "items-center",
//                "justify-center"
//                );
//        _closeBtn.textContent = "X";
//        _closeBtn.addEventListener("click", () => {
//            document.body.removeChild(_section);
//        });
//        _modal.appendChild(_closeBtn);
//
//        // Eu ChurroGOAT
//        // Haga un GPTazo para hacer el bucle según la cantidad de archivos
//        // Perdón si no lo hice, ya andaba con sueño
//
//        // Ejemplo de archivo
//        const _id = e.getAttribute('data-id');
//        console.log(_id);
//
//        event.preventDefault();
//
//        let response = await fetch('/svListarArchivos', {
//            method: 'POST',
//            header: {
//                'Content-Type': 'application/x-www-form-urlencoded'
//            },
//            body: new URLSearchParams({
//                'txtIdPost': _id
//            })
//        });
//
//        let data = await response.json();
//        console.log(data);
//
//        const _div = document.createElement("div");
////        _modal.appendChild(_div);
//
//        data.forEach((archivo) => {
////            const _archivo = document.createElement("a");
////            _archivo.setAttribute("href", "");
////            _archivo.classList.add("text-center", "text-black", "inline-block");
////            _div.appendChild(_archivo);
//        })
//
////        const _archivo = document.createElement("a");
////        _archivo.classList.add("text-center");
////        _archivo.textContent = "ChurroGOAT.pdf";
////        _modal.appendChild(_archivo);
//
//    });
//})
//
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
        _modal.textContent = "Observación"; // Por medio de JSP muestra la cantidad de archivos y muestra los arvhivos
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
})


// Modal para confirmar aceptar el post
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
            // Aquí pega la el método para aceptar publicar el post
            let _idPost = e.getAttribute('data-id');
            let _idInstructor = document.getElementById("idInstructor").value;
            console.log(_idInstructor);

            let response = await fetch('/svEstadoPost', {
                method: 'POST',
                header: {
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
})
//
//
// Modal para rechazar el post
const _rechazar = document.querySelectorAll("#rechazarPost");
_rechazar.forEach((btn) => {
    btn.addEventListener("click", () => {
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
                "gap-2.5",
                "flex-col",
                "items-center",
                "relative"
                );
        _modal.textContent = "¿Motivo del rechazo al post?";
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

        // Input para observaciones
        const _inputObservacion = document.createElement("textarea");
        _inputObservacion.classList.add(
                "w-full",
                "p-2",
                "textarea",
                "textarea-bordered",
                "bg-white"
                );
        _inputObservacion.placeholder = "Escribe tu observación aquí...";
        _modal.appendChild(_inputObservacion);

        // Botón para enviar observación
        const _submitBtn = document.createElement("button");
        _submitBtn.classList.add(
                "btn",
                "bg-mdaGreen",
                "border-none",
                "text-white",
                "hover:bg-mdaGreen",
                "w-full"
                );
        _submitBtn.textContent = "Enviar Observación";
        _submitBtn.addEventListener("click", async () => {
            let observacion = _inputObservacion.value;
            console.log("Observación enviada:", observacion);
            // Brou aquí el método para la consulta

            let _idPost = btn.getAttribute("data-id");
            let _idInstructor = document.getElementById("idInstructor").value;
            console.log(_idInstructor);
            console.log(_idPost);

            let request = await fetch("/svRechazarPost", {
                method: "POST",
                header: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    "txtIdPost": _idPost,
                    "txtIdInstructor": _idInstructor,
                    "txtObservacion": observacion
                })
            })

            location.reload();

            // Modificar el estado y agregar la observación
            document.body.removeChild(_section);
        });
        _modal.appendChild(_submitBtn);

        const _buttonContainer = document.createElement("div");
        _modal.appendChild(_buttonContainer);
    });
})
