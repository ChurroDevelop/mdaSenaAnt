// Capturar el formulario para las validaciones
const form = document.querySelector("#formularioCodigo");

// Capturar el input para las validaciones
const inputCodigo = document.querySelector("#inputCodigo");

// Capturar el label para los estilos dependiendo de la validacion
const labelCodigo = document.querySelector("#labelCodigo");

// Funcion para que permita ingresar nada mas numeros
let inputNumber = (event) => {
    if (!/[0-9]/.test(event.key)) {
        event.preventDefault();
    }
};

// Funcion para validar el formulario
let validarForm = (event) => {
    // Manejo del estado para enviar el formulario
    let estadoForm = true;
    
    // Campo para validar que el el codigo de verificacion no este vacio
    if (inputCodigo.value.trim() === '') {
        console.log("Campo de codigo es obligatorio");
        labelCodigo.classList.remove("border-green-500", "border-2");
        labelCodigo.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        labelCodigo.classList.remove("border-red-500", "border-2");
        labelCodigo.classList.add("border-green-500", "border-2");
    }
    
    if (!estadoForm) {
        alert("El codigo de verificacion es obligatorio o esta vacio");
        event.preventDefault();
    }
    else {
        alert("Procesando codigo de verificacion");
    }
};

// Manejo de eventos para el formulario y el input
form.addEventListener("submit", validarForm);
inputCodigo.addEventListener("keypress", inputNumber);