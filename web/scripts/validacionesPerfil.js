// Capturar el formulario para las validaciones
const form = document.querySelector("#formularioPerfil");

// Capturar los input para hacer las respectivas validaciones
const inputName = document.querySelector("#inputNombre");
const inputLast = document.querySelector("#inputApellido");
const inputDocumento = document.querySelector("#inputDocumento");
const inputCentro = document.querySelector("#inputCentro");

// Capturar los label de los input para los estilos dependiendo de las validaciones
const labelName = document.querySelector("#labelName");
const labelLast = document.querySelector("#labelLast");
const labelDocumento = document.querySelector("#labelDocumento");
const labelCentro = document.querySelector("#labelCentro");

// Evento para que los input tipo text se puedan ingresar nada mas letras
let inputText = (event) => {
    let input = event.target;
    let valor = input.value;
    let regex = /^[a-zA-ZñÑ\s]+$/g;
    let cambiar = valor.replace(regex, "");
    input.value = cambiar;
};

// Funcion para que los input tipo numbre se puedan ingresar nada mas numeros
let inputNumber = (event) => {
    let input = event.target;
    let valor = input.value;
    let regex = /[^0-9]/g;
    let cambiar = valor.replace(regex, "");
    input.value = cambiar;
};

// Funcion para validar todos los input dentro del formulario
let validarForm = (event) => {
    // Manejo del estado del formulario, dependiendo de los campos se envien o no
    let estadoForm = true; 
    
    // Campo para validacin del nombre
    if (inputName.value.trim() === '') {
        console.log("Campo de nombre obligatorio");
        labelName.classList.remove("border-green-500", "border-2");
        labelName.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        labelName.classList.remove("border-red-500", "border-2");
        labelName.classList.add("border-green-500", "border-2");
    }
    
    // Campo para validacion del apellido
    if (inputLast.value.trim() === '') {
        console.log("Campo de apellido obligatorio");
        labelLast.classList.remove("border-green-500", "border-2");
        labelLast.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        labelLast.classList.remove("border-red-500", "border-2");
        labelLast.classList.add("border-green-500", "border-2");
    }
    
    // Campo para validacion del numero de documento
    if (inputDocumento.value.trim() === '') {
        console.log("Campo numero de documento obligatorio");
        labelDocumento.classList.remove("border-green-500", "border-2");
        labelDocumento.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        labelDocumento.classList.remove("border-red-500", "border-2");
        labelDocumento.classList.add("border-green-500", "border-2");
    }
    
    // Campo para la validacion del centro de formacion
    if (inputCentro.value.trim() === '') {
        console.log("Campo centro de formacion obigatorio");
        labelCentro.classList.remove("border-green-500", "border-2");
        labelCentro.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        labelCentro.classList.remove("border-red-500", "border-2");
        labelCentro.classList.add("border-green-500", "border-2");
    }
    
    // Campo para evitar el envio del formulario a la base de datos
    if (!estadoForm) {
        alert("Campos incompletos o incorrecto");
        event.preventDefault();
    }
    else {
        alert("Creando un nuevo perfil");
    }
};

// Eventos para el formulario y los input
form.addEventListener("submit", validarForm);
inputName.addEventListener("input", inputText);
inputLast.addEventListener("input", inputText);
inputCentro.addEventListener("input", inputText);
inputDocumento.addEventListener("input", inputNumber);
