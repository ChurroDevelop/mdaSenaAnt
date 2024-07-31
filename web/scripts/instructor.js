// Atrapar elementos del DOM
const form = document.getElementById("buscarForm");
const detalles = document.getElementById("detallesAprendiz");
const informacion = document.getElementById("infoAprendiz");
const idIntructor = document.getElementById("idInstructor").value;
console.log(idIntructor);

form.addEventListener("submit", function(event) {
    event.preventDefault(); 
    let numeroDocumento = document.getElementById("numAprendiz").value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svBuscarAprendiz", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        console.log(xhr.status);
        if (xhr.status === 200) {
            let response = JSON.parse(xhr.response);
            console.log(response);
            if (response) {
                const formAsignacion = document.createElement("form");
                formAsignacion.setAttribute("method", "POST");
                formAsignacion.setAttribute("action", "/svAsignacion");
                const hiddenId = document.createElement("input");
                hiddenId.setAttribute("type", "hidden");
                hiddenId.setAttribute("value", response.userId);
                hiddenId.setAttribute("name", "txtAsignacion");
                const hiddenIdInstructor = document.createElement("input");
                hiddenIdInstructor.setAttribute("type", "hidden");
                hiddenIdInstructor.setAttribute("value", idIntructor);
                hiddenIdInstructor.setAttribute("name", "txtIdInstructor");
                const btn = document.createElement("button");
                btn.textContent = "Asignar rol monitor";
                btn.setAttribute("type", "submit");
                formAsignacion.appendChild(hiddenId);
                formAsignacion.appendChild(hiddenIdInstructor);
                formAsignacion.appendChild(btn);
                informacion.appendChild(formAsignacion);
                detalles.innerHTML = response.details;
            }
            else {
               console.log("No se encontro el usuario");
            }
        };
    };
    xhr.send("txtNumero=" +  encodeURIComponent(numeroDocumento));
});

// Verificacion de la session al momento de registrar el invalidate
let comprobar = () => {
    console.log(localStorage.getItem("sesion"));
    if (localStorage.getItem("sesion") === "false") {
        window.location.href = "../login.jsp";
    }
};
setInterval(() => {
    comprobar();
}, 200);