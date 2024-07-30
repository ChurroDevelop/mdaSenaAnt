import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import evitarLetras from "../validacionInputs/evitarLetras.js";

const _form = document.getElementById("formularioCodigoContrasena");
const _codigo = document.getElementById("inputCodigo");

evitarLetras(_codigo);
longitudMaxima(_codigo, 6);

_form.addEventListener("submit", (event) => {

    const longitudMinimaCodigo = longitudMinima(_codigo, 6, "Cantidad de carácteres inválida");

    if (!longitudMinimaCodigo) {
        event.preventDefault();
        return;
    }

    event.preventDefault();
    let codigo = _codigo.value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svVerificarCodigoContrasena", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            if (xhr.responseText === 'success') {
                window.location.href = "cambiarContrasena.jsp";
                resultadoDiv.innerHTML = `<p class="text-green-500 mt-4">Código verificado correctamente.</p>`;
            } else if (xhr.responseText === 'code_mismatch') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">El código de verificación no coincide. Inténtelo de nuevo.</p>`;
            } else {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        }
    };
    xhr.send("txtCodigo=" + encodeURIComponent(codigo));

});
