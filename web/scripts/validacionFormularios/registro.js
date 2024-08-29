/**
 * Script para manejar la validación y envío de un formulario de registro.
 * 
 * Importa funciones de validación para longitud mínima, confirmación de contraseñas y validación de correos electrónicos.
 * 
 * @module
 * 
 * @requires longitudMinima - Función para validar la longitud mínima de un campo de entrada.
 * @requires confirmarClave - Función para confirmar que dos contraseñas coinciden.
 * @requires validarCorreo - Función para validar la extensión de correos electrónicos permitidos.
 */

import longitudMinima from "../validacionInputs/longitudMinima.js";
import confirmarClave from "../validacionInputs/confirmarClave.js";
import validarCorreo from "../validacionInputs/validarCorreo.js";

// Selección de elementos del formulario
const _form = document.getElementById("formularioRegistro");
const _correo = document.getElementById("inputCorreo");
const _clave = document.getElementById("inputClave");
const _claveConfirm = document.getElementById("inputConfirm");

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {
    event.preventDefault();

    // Validación de longitud mínima de los campos
    const longitudMinimaCorreo = longitudMinima(_correo, 10, "Cantidad de carácteres inválida");
    const longitudMinimaClave = longitudMinima(_clave, 8, "Cantidad de carácteres inválida");
    const longitudMinimaConfirmarClave = longitudMinima(_claveConfirm, 8, "Cantidad de carácteres inválida");

    // Si alguna validación de longitud falla, detener el proceso
    if (!longitudMinimaCorreo || !longitudMinimaClave || !longitudMinimaConfirmarClave) {
        return;
    }

    // Validación de coincidencia de contraseñas
    if (_clave.value !== _claveConfirm.value) {
        confirmarClave(_clave, "Las contraseñas no coinciden");
        confirmarClave(_claveConfirm, "Las contraseñas no coinciden");
        return;
    }

    // Validación de la extensión del correo electrónico
    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");

    // Si la validación del correo electrónico falla, detener el proceso
    if (!validaExtensionCorreo) {
        return;
    }

    // Crear una nueva instancia de FormData con los datos del formulario
    let formData = new FormData(_form);

    // Crear una nueva instancia de XMLHttpRequest para realizar una solicitud HTTP
    let xhr = new XMLHttpRequest();

    // Inicializar una solicitud POST a la URL "/svCodigo" y establecer la solicitud como asíncrona (true)
    xhr.open('POST', "/svCodigo", true);

    // Establecer el encabezado de la solicitud para enviar los datos como application/x-www-form-urlencoded
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Definir la función que se ejecutará cuando la solicitud se complete (onload)
    xhr.onload = function () {
        // Obtener el elemento con el id 'resultado' del DOM
        let resultadoDiv = document.getElementById('resultado');

        // Verificar si el estado de la respuesta es 200 (OK)
        if (xhr.status === 200) {
            // Comprobar el contenido de la respuesta y actualizar la interfaz de usuario en consecuencia
            if (xhr.responseText === 'success') {
                // Redirigir a 'autenticacion.jsp' y mostrar un mensaje de éxito
                window.location.href = "autenticacion.jsp";
                resultadoDiv.innerHTML = `<p class="text-green-500 mt-4">Código verificado correctamente.</p>`;
            } else if (xhr.responseText === 'email_exists') {
                // Mostrar un mensaje indicando que el correo ya está registrado
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ya hay un correo registrado.</p>`;
            } else if (xhr.responseText === 'invalid_email') {
                // Mostrar un mensaje indicando que el correo no es válido
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo institucional no válido.</p>`;
            } else if (xhr.responseText === 'password_mismatch') {
                // Mostrar un mensaje indicando que las contraseñas no coinciden
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Las contraseñas no coinciden.</p>`;
            } else {
                // Mostrar un mensaje de error genérico para cualquier otra respuesta
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        } else {
            // Mostrar un mensaje de error en la solicitud si el estado no es 200
            resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Error en la solicitud. Inténtelo de nuevo más tarde.</p>`;
        }
    };

    // Enviar la solicitud con los datos del formulario, convertidos a una cadena URL-encoded
    xhr.send(new URLSearchParams(new FormData(_form)).toString());
});
