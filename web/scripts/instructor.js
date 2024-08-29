// Atrapar elementos del DOM
const form = document.getElementById("buscarForm"); // Elemento del formulario de búsqueda
const detalles = document.getElementById("detallesAprendiz"); // Elemento para mostrar detalles del aprendiz
const informacion = document.getElementById("infoAprendiz"); // Elemento para mostrar información del aprendiz
const idIntructor = document.getElementById("idInstructor").value; // Valor del ID del instructor
console.log(idIntructor); // Imprime el ID del instructor en la consola

// Evento de envío del formulario
form.addEventListener("submit", function (event) {
    event.preventDefault(); // Previene el comportamiento por defecto del formulario

    // Obtiene el número de documento del aprendiz del formulario
    let numeroDocumento = document.getElementById("numAprendiz").value;

    // Crea una nueva solicitud HTTP
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svBuscarAprendiz", true); // Configura la solicitud POST
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // Establece el encabezado de contenido

    // Define la función a ejecutar cuando la solicitud se completa
    xhr.onload = function () {
        console.log(xhr.status); // Imprime el estado de la solicitud en la consola
        if (xhr.status === 200) { // Verifica si la solicitud fue exitosa
            let response = JSON.parse(xhr.response); // Parsea la respuesta JSON
            console.log(response); // Imprime la respuesta en la consola
            if (response) { // Verifica si la respuesta contiene datos
                // Verifica si el formulario de asignación no existe
                if (!document.querySelector("#formX")) {
                    // Crea un nuevo formulario para asignación
                    const formAsignacion = document.createElement("form");
                    formAsignacion.setAttribute("method", "POST");
                    formAsignacion.setAttribute("action", "/svAsignacion");
                    formAsignacion.setAttribute("id", "formX");

                    // Crea un campo oculto para el ID del usuario
                    const hiddenId = document.createElement("input");
                    hiddenId.setAttribute("type", "hidden");
                    hiddenId.setAttribute("value", response.userId);
                    hiddenId.setAttribute("name", "txtAsignacion");

                    // Crea un campo oculto para el ID del instructor
                    const hiddenIdInstructor = document.createElement("input");
                    hiddenIdInstructor.setAttribute("type", "hidden");
                    hiddenIdInstructor.setAttribute("value", idIntructor);
                    hiddenIdInstructor.setAttribute("name", "txtIdInstructor");

                    // Crea un botón para asignar el rol de monitor
                    const btn = document.createElement("button");
                    btn.textContent = "Asignar rol monitor";
                    btn.setAttribute("type", "submit");
                    btn.classList.add("btn", "bg-mdaGreen", "border-none", "text-white", "hover:bg-mdaGreen");

                    // Agrega los campos ocultos y el botón al formulario
                    formAsignacion.appendChild(hiddenId);
                    formAsignacion.appendChild(hiddenIdInstructor);
                    formAsignacion.appendChild(btn);

                    // Agrega el formulario a la sección de información y muestra los detalles
                    informacion.appendChild(formAsignacion);
                    detalles.innerHTML = response.details;
                }
            } else {
                console.log("No se encontro el usuario"); // Imprime mensaje si no se encuentra el usuario
            }
        }
    };

    // Envía la solicitud con el número de documento como parámetro
    xhr.send("txtNumero=" + encodeURIComponent(numeroDocumento));
});

// Verificación de la sesión al momento de registrar el invalidate
let comprobar = () => {
    console.log(localStorage.getItem("sesion")); // Imprime el valor de "sesion" en la consola
    if (localStorage.getItem("sesion") === "false") { // Verifica si la sesión es falsa
        window.location.href = "../login.jsp"; // Redirige al usuario a la página de inicio de sesión
    }
};

// Configura un intervalo que ejecuta la función 'comprobar' cada 200 milisegundos
setInterval(() => {
    comprobar();
}, 200);
