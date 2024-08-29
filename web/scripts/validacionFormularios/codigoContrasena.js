/**
 * Script para manejar la validación y el envío de un formulario de verificación de código de contraseña.
 * 
 * Importa funciones de validación para longitud mínima de un campo de entrada y evitar letras en un campo numérico.
 * 
 * @module
 * 
 * @requires longitudMinima - Función para validar la longitud mínima de un campo de entrada.
 * @requires longitudMaxima - Función para validar la longitud máxima de un campo de entrada.
 * @requires evitarLetras - Función para evitar la entrada de letras en un campo.
 */

import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import evitarLetras from "../validacionInputs/evitarLetras.js";

// Selección de elementos del formulario
const _form = document.getElementById("formularioCodigoContrasena");
const _codigo = document.getElementById("inputCodigo");

// Aplicar restricciones de longitud máxima y evitar letras en el campo de código
evitarLetras(_codigo);
longitudMaxima(_codigo, 6);

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {
    // Validación de longitud mínima del código
    const longitudMinimaCodigo = longitudMinima(_codigo, 6, "Cantidad de carácteres inválida");

    if (!longitudMinimaCodigo) {
        event.preventDefault();
        return;
    }

    // Prevenir el envío del formulario y realizar la solicitud AJAX
    event.preventDefault();
    let codigo = _codigo.value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svVerificarCodigoContrasena", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            // Procesar la respuesta del servidor y actualizar la interfaz de usuario
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
    // Enviar la solicitud con el código de verificación, codificado en URL
    xhr.send("txtCodigo=" + encodeURIComponent(codigo));
});
