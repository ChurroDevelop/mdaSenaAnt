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

// Modal para ver la cantidad de los archivos
const _cantidadArchivo = document.getElementById("cantidadArchivos");
_cantidadArchivo.addEventListener("click", () => {
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
    _modal.textContent = "Cantidad de archivos: 1"; // Por medio de JSP muestra la cantidad de archivos y muestra los arvhivos
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

    // Eu ChurroGOAT
    // Haga un GPTazo para hacer el bucle según la cantidad de archivos
    // Perdón si no lo hice, ya andaba con sueño

    // Ejemplo de archivo
    const _archivo = document.createElement("a");
    _archivo.classList.add("text-center");
    _archivo.textContent = "ChurroGOAT.pdf";
    _modal.appendChild(_archivo);

});

// Modal para ver la observación
const _observacion = document.getElementById("observacion");
_observacion.addEventListener("click", () => {
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
    _obser.textContent = "La mala pirobo, aprenda a redactar skdjskjdskdj, hola churro Xde";
    _modal.appendChild(_obser);
});

// Modal para confirmar aceptar el post
const _aceptar = document.getElementById("aceptarPost");
_aceptar.addEventListener("click", () => {
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

    _buttonUpdate.addEventListener("click", () => {
        // Aquí pega la el método para aceptar publicar el post
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


// Modal para rechazar el post
const _rechazar = document.getElementById("rechazarPost");
_rechazar.addEventListener("click", () => {
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
    _submitBtn.addEventListener("click", () => {
        const observacion = _inputObservacion.value;
        console.log("Observación enviada:", observacion);
        // Brou aquí el método para la consulta
        // Modificar el estado y agregar la observación
        document.body.removeChild(_section);
    });
    _modal.appendChild(_submitBtn);

    const _buttonContainer = document.createElement("div");
    _modal.appendChild(_buttonContainer);
});