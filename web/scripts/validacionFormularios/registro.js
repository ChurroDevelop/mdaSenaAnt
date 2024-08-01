import longitudMinima from "../validacionInputs/longitudMinima.js";
import confirmarClave from "../validacionInputs/confirmarClave.js";
import validarCorreo from "../validacionInputs/validarCorreo.js";

const _form = document.getElementById("formularioRegistro");
const _correo = document.getElementById("inputCorreo");
const _clave = document.getElementById("inputClave");
const _claveConfirm = document.getElementById("inputConfirm");

_form.addEventListener("submit", (event) => {
    event.preventDefault();

    const longitudMinimaCorreo = longitudMinima(_correo, 10, "Cantidad de carácteres inválida");
    const longitudMinimaClave = longitudMinima(_clave, 8, "Cantidad de carácteres inválida");
    const longitudMinimaConfirmarClave = longitudMinima(_claveConfirm, 8, "Cantidad de carácteres inválida");

    if (!longitudMinimaCorreo || !longitudMinimaClave || !longitudMinimaConfirmarClave) {
        return;
    }

    if (_clave.value !== _claveConfirm.value) {
        confirmarClave(_clave, "Las contraseñas no coinciden");
        confirmarClave(_claveConfirm, "Las contraseñas no coinciden");
        return;
    }

    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");

    if (!validaExtensionCorreo) {
        return;
    }

    let formData = new FormData(_form);
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svCodigo", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            if (xhr.responseText === 'success') {
                window.location.href = "autenticacion.jsp";
                resultadoDiv.innerHTML = `<p class="text-green-500 mt-4">Código verificado correctamente.</p>`;
            } else if (xhr.responseText === 'email_exists') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ya hay un correo registrado.</p>`;
            } else if (xhr.responseText === 'invalid_email') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo institucional no válido.</p>`;
            } else if (xhr.responseText === 'password_mismatch') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Las contraseñas no coinciden.</p>`;
            } else {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        } else {
            resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Error en la solicitud. Inténtelo de nuevo más tarde.</p>`;
        }
    };
    xhr.send(new URLSearchParams(new FormData(_form)).toString());
});
