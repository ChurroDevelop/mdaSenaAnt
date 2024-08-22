import longitudMinima from "../validacionInputs/longitudMinima.js";
import confirmarClave from "../validacionInputs/confirmarClave.js";
import validarCorreo from "../validacionInputs/validarCorreo.js";

const _form = document.getElementById("formularioLogin");
const _correo = document.getElementById("inputCorreo");
const _clave = document.getElementById("inputClave");

_form.addEventListener("submit", (event) => {
    event.preventDefault();

    const longitudMinimaCorreo = longitudMinima(_correo, 10, "Cantidad de carácteres inválida");
    const longitudMinimaClave = longitudMinima(_clave, 2, "Cantidad de carácteres inválida");

    if (!longitudMinimaCorreo || !longitudMinimaClave) {
        return;
    }

    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");

    if (!validaExtensionCorreo) {
        return;
    }

    let formData = new FormData(_form);
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svLogin", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            if (xhr.responseText === 'success') {
                window.location.href = "inicio.jsp";
                localStorage.setItem("sesion", true);
            } else if (xhr.responseText === 'error') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo o contraseña incorrectos. Inténtelo de nuevo.</p>`;
            }
            else if(xhr.responseText === 'disabled'){
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Usuario deshabilitado</p>`;
            } else {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        } else {
            resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Error en la solicitud. Inténtelo de nuevo más tarde.</p>`;
        }
    };
    xhr.send(new URLSearchParams(new FormData(_form)).toString());
});
