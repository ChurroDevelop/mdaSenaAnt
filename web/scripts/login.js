// Atrapar inputs y formularios
const $formulario = document.querySelector("#formLogin");
const $inputCorreo = document.querySelector("#inputCorreo");
const $inputClave = document.querySelector("#inpuClave");
const $labelCorreo = document.querySelector("#labelCorreo");
const $labelClave = document.querySelector("#labelClave");
const $btnForm = document.querySelector("#btnForm");

let inputTex = (event) => {
    if (!/[A-Za-zàáâãéêíóôõúüñÑ\s]/.test(event.key)) {
        event.preventDefault();
    }
};

let inputNumber = (event) => {
    if (!/[0-9]/.test(event.key)) {
        event.preventDefault();
    }
};



let validarForm = (event) => {
    let estadoForm = false;
    
    if (!estadoForm) {
        event.preventDefault();
    }
};

