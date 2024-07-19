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
    let estadoForm = true;
    
    if ($inputCorreo.value.trim() === '') {
        console.log("El correo es obligatorio");
        $labelCorreo.classList.remove("border-green-500", "border-2");
        $labelCorreo.classList.add("border-red-500", "border-2");
        estadoForm = false;
    }
    else {
        if (!regexAprendiz.test($inputCorreo.value) || !regexInstructor.test($inputCorreo.value)) {
            console.log("Debe que ser un correo institucional");
            $labelCorreo.classList.remove("border-green-500", "border-2");
            $labelCorreo.classList.add("border-red-500", "border-2");
            estadoForm = false;
        }
        else {
            $labelCorreo.classList.remove("border-red-500", "border-2");
            $labelCorreo.classList.add("border-green-500", "border-2");
        }
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
        console.log("No se ha enviado el formulario");
    }
};

$formulario.addEventListener("submit", validarForm);
