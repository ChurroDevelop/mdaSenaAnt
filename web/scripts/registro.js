// Capturar el formulario para realizar las validaciones
const form = document.querySelector("#formularioRegistro");

// Capturar los inputs para las validaciones
const inputCorreo = document.querySelector("#inputCorreo");
const inputClave = document.querySelector("#inputClave");
const inputConfirm = document.querySelector("#inputConfirm");

// Capurar los label para estilos dependiendo de las validaciones
const labelCorreo = document.querySelector("#labelCorreo");
const labelClave = document.querySelector("#labelClave");
const labelConfirm = document.querySelector("#labelConfirm");

// Regex para el manejo de los correos institucionales
const regexAprendiz = /^[\w\.-]+@soy\.sena\.edu\.co$/;
const regexInstructor = /^[\w\.-]+@sena\.edu\.co$/;

// Funcion para validar el formulario
let validarForm = (event) => {
    // Estado para manejar el envio del formulario
    let estadoForm = true;
    
    // Campo para validar el correo si esta vacio o que sean correos institucionales
    if (inputCorreo.value.trim() === '') {
        console.log("El correo institucional es obligatorio");
        labelCorreo.classList.remove("border-green-500", "border-2");
        labelCorreo.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        if (regexAprendiz.test(inputCorreo.value) || regexInstructor.test(inputCorreo.value)) {
            labelCorreo.classList.remove("border-red-500", "border-2");
            labelCorreo.classList.add("border-green-500", "border-2");
        }
        else {
            console.log("No es correo institucional");
            labelCorreo.classList.remove("border-green-500", "border-2");
            labelCorreo.classList.add("border-red-500", "border-2");
            estadoForm = false;
        }
    }
    
    // Campo para validar las contraseñas si coinciden o no
    if (inputClave.value.trim() === '' || inputConfirm.value.trim() === '') {
        console.log("Campos de contraseñas obligatorios");
        labelClave.classList.remove("border-green-500", "border-2");
        labelConfirm.classList.remove("border-green-500", "border-2");
        labelClave.classList.remove("border-yellow-500", "border-2");
        labelConfirm.classList.remove("border-yellow-500", "border-2");
        labelClave.classList.add("border-red-500", "border-2");
        labelConfirm.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        if (inputClave.value !== inputConfirm.value) {
            console.log("Las contraseñas no coinciden");
            labelClave.classList.remove("border-red-500", "border-2");
            labelConfirm.classList.remove("border-red-500", "border-2");
            labelClave.classList.remove("border-green-500", "border-2");
            labelConfirm.classList.remove("border-green-500", "border-2");
            labelClave.classList.add("border-yellow-500", "border-2");
            labelConfirm.classList.add("border-yellow-500", "border-2");
            estadoForm = false;
        }
        else {
            if (inputClave.value === inputConfirm.value) {
                console.log("Las contraseñas coinciden");
                labelClave.classList.remove("border-yellow-500", "border-2");
                labelConfirm.classList.remove("border-yellow-500", "border-2");
                labelClave.classList.remove("border-red-500", "border-2");
                labelConfirm.classList.remove("border-red-500", "border-2");
                labelClave.classList.add("border-green-500", "border-2");
                labelConfirm.classList.add("border-green-500", "border-2");
            }
        }
    }
    
    // Validacion dependiendo de los campos del formulario
    if (!estadoForm) {
        alert("Campos incorrectos o vacios");
        event.preventDefault();
    }
    else {
        alert("Procesando correo para el envio del codigo de autenticacion");
    }
    
}

form.addEventListener("submit", validarForm);
