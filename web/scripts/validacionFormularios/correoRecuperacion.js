/**
 * Script para manejar la validación y el envío de un formulario de recuperación de correo.
 * 
 * Importa funciones de validación para longitud mínima y extensión de correos electrónicos.
 * 
 * @module
 * 
 * @requires longitudMinima - Función para validar la longitud mínima de un campo de entrada.
 * @requires longitudMaxima - Función para validar la longitud máxima de un campo de entrada.
 * @requires validarCorreo - Función para validar la extensión de correos electrónicos permitidos.
 */

import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import validarCorreo from "../validacionInputs/validarCorreo.js";

// Selección de elementos del formulario
const _form = document.getElementById("formularioCorreoRecuperacion");
const _correo = document.getElementById("inputCorreo");

// Aplicar restricciones de longitud máxima al campo de correo
longitudMaxima(_correo, 50);

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {
    // Validación de longitud mínima del correo
    const longitudMinimaCorreo = longitudMinima(_correo, 10, "Cantidad de carácteres inválida");

    if (!longitudMinimaCorreo) {
        event.preventDefault();
        return;
    }

    // Validación de la extensión del correo electrónico
    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");

    if (!validaExtensionCorreo) {
        event.preventDefault();
        return;
    }

    // Prevenir el envío del formulario y realizar la solicitud AJAX
    event.preventDefault();
    let formData = new FormData(_form);
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "/svCorreoRecuperacion", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
        let resultadoDiv = document.getElementById('resultado');
        if (xhr.status === 200) {
            // Procesar la respuesta del servidor y actualizar la interfaz de usuario
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
    // Enviar la solicitud con los datos del formulario, convertidos a una cadena URL-encoded
    xhr.send(new URLSearchParams(new FormData(_form)).toString());
});
