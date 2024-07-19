// Atrapar inputs y formularios
const $formulario = document.querySelector("#formLogin");
const $inputCorreo = document.querySelector("#inputCorreo");
const $inputClave = document.querySelector("#inpuClave");
const $labelCorreo = document.querySelector("#labelCorreo");
const $labelClave = document.querySelector("#labelClave");
const $btnForm = document.querySelector("#btnForm");

// Expresiones regulares para el manejo del formulario del login
const regexAprendiz = /^[\w\.-]+@soy\.sena\.edu\.co$/;
const regexInstructor = /^[\w\.-]+@sena\.edu\.co$/;

let validarForm = (event) => {
    let $sesion = true;
    let estadoForm = true;
    
    if ($inputCorreo.value.trim() === '') {
        console.log("El correo es obligatorio");
        $labelCorreo.classList.remove("border-green-500", "border-2");
        $labelCorreo.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    
    if ($inputClave.value.trim() === '') {
        console.log("La contraseña es obligatoria");
        $labelClave.classList.remove("border-green-500", "border-2");
        $labelClave.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    
    if (!estadoForm) {
        event.preventDefault();
    }
    else {
        localStorage.setItem("sesion", $sesion);
        console.log("No se ha enviado el formulario");
    }
};

$formulario.addEventListener("submit", validarForm);
