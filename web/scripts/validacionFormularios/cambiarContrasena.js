/**
 * Script para manejar la validación y el envío de un formulario de cambio de contraseña.
 * 
 * Importa funciones de validación para la longitud mínima de una contraseña y la confirmación de la contraseña.
 * 
 * @module
 * 
 * @requires longitudMinima - Función para validar la longitud mínima de una contraseña.
 * @requires confirmarClave - Función para verificar si las contraseñas coinciden.
 */

import longitudMinima from "../validacionInputs/longitudMinima.js";
import confirmarClave from "../validacionInputs/confirmarClave.js";

// Selección de elementos del formulario
const _form = document.getElementById("formCambiarContrasena");
const _clave = document.getElementById("inputClave");
const _claveConfirm = document.getElementById("inputConfirm");

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {
    // Validación de longitud mínima para la contraseña y la confirmación de la contraseña
    const longitudMinimaClave = longitudMinima(_clave, 8, "Cantidad de carácteres inválida");
    const longitudMinimaConfirmarClave = longitudMinima(_claveConfirm, 8, "Cantidad de carácteres inválida");

    if (!longitudMinimaClave || !longitudMinimaConfirmarClave) {
        event.preventDefault(); // Prevenir el envío del formulario si la validación falla
        return;
    }

    // Verificación de que las contraseñas coinciden
    if (_clave.value !== _claveConfirm.value) {
        confirmarClave(_clave, "Las contraseñas no coinciden");
        confirmarClave(_claveConfirm, "Las contraseñas no coinciden");
        event.preventDefault(); // Prevenir el envío del formulario si las contraseñas no coinciden
        return;
    }
});
