import longitudMinima from "../validacionInputs/longitudMinima.js";
import confirmarClave from "../validacionInputs/confirmarClave.js";

const _form = document.getElementById("formCambiarContrasena");
const _clave = document.getElementById("inputClave");
const _claveConfirm = document.getElementById("inputConfirm");

_form.addEventListener("submit", (event) => {

    const longitudMinimaClave = longitudMinima(_clave, 8, "Cantidad de carácteres inválida");
    const longitudMinimaConfirmarClave = longitudMinima(_claveConfirm, 8, "Cantidad de carácteres inválida");

    if (!longitudMinimaClave || !longitudMinimaConfirmarClave) {
        event.preventDefault();
        return;
    }

    if (_clave.value !== _claveConfirm.value) {
        confirmarClave(_clave, "Las contraseñas no coinciden");
        confirmarClave(_claveConfirm, "Las contraseñas no coinciden");
        event.preventDefault();
        return;
    }
});
