/**
 * Script para manejar la validación y el envío de un formulario de verificación de código.
 * 
 * Importa funciones de validación para la longitud mínima del código y para evitar letras.
 * 
 * @module
 * 
 * @requires longitudMinima - Función para validar la longitud mínima del código.
 * @requires longitudMaxima - Función para validar la longitud máxima del código.
 * @requires evitarLetras - Función para evitar la entrada de letras en el campo del código.
 */

import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import evitarLetras from "../validacionInputs/evitarLetras.js";

// Selección de elementos del formulario
const _form = document.getElementById("formularioCodigo");
const _codigo = document.getElementById("inputCodigo");

// Aplicar validaciones de longitud y caracteres permitidos
evitarLetras(_codigo);
longitudMaxima(_codigo, 6);

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {

    // Validación de longitud mínima del código
    const longitudMinimaCodigo = longitudMinima(_codigo, 6, "Cantidad de carácteres inválida");

    if (!longitudMinimaCodigo) {
        event.preventDefault(); // Prevenir el envío del formulario si la validación falla
        return;
    };

    // Prevención del envío del formulario para manejo personalizado
    event.preventDefault();
    let codigo = _codigo.value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svRegistro", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            // Manejo de respuestas del servidor
            if (xhr.responseText === 'success') {
                window.location.href = "crearPerfil.jsp";
                resultadoDiv.innerHTML = `<p class="text-green-500 mt-4">Código verificado correctamente.</p>`;
            } else if (xhr.responseText === 'code_mismatch') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">El código de verificación no coincide. Inténtelo de nuevo.</p>`;
            } else if (xhr.responseText === 'invalid_email') {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo institucional no válido.</p>`;
            } else {
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        }
    };
    // Enviar el código como una cadena URL-encoded
    xhr.send("txtCodigo=" + encodeURIComponent(codigo));

});
