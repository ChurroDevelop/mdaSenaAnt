import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import validarCorreo from "../validacionInputs/validarCorreo.js";

const _form = document.getElementById("formularioCorreoRecuperacion");
const _correo = document.getElementById("inputCorreo");

longitudMaxima(_correo, 50);

_form.addEventListener("submit", (event) => {

    const longitudMinimaCorreo = longitudMinima(_correo, 10, "Cantidad de carácteres inválida");

    if (!longitudMinimaCorreo) {
        event.preventDefault();
        return;
    }

    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");

    if (!validaExtensionCorreo) {
        event.preventDefault();
        return;
    }

    event.preventDefault();
    let formData = new FormData(_form);
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svCorreoRecuperacion", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            if (xhr.responseText === 'success') {
                window.location.href = "codigoContrasena.jsp";
                resultadoDiv.innerHTML = `<p class="text-green-500 mt-4">Código enviado correctamente.</p>`;
            } else if (xhr.responseText === 'user_not_found') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">El usuario no existe.</p>`;
            } else if (xhr.responseText === 'invalid_email') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo institucional no válido.</p>`;
            } else {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        } else {
            resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Error en la solicitud. Inténtelo de nuevo más tarde.</p>`;
        }
    };
    xhr.send(new URLSearchParams(new FormData(_form)).toString());
});
