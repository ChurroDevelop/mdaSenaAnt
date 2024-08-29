/**
 * Script para manejar la validación y envío de un formulario de inicio de sesión.
 * 
 * Importa funciones de validación para longitud mínima y extensión de correos electrónicos.
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
const _form = document.getElementById("formularioLogin");
const _correo = document.getElementById("inputCorreo");
const _clave = document.getElementById("inputClave");

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {
    event.preventDefault();

    // Validación de longitud mínima de los campos
    const longitudMinimaCorreo = longitudMinima(_correo, 10, "Cantidad de carácteres inválida");
    const longitudMinimaClave = longitudMinima(_clave, 2, "Cantidad de carácteres inválida");

    // Si alguna validación de longitud falla, detener el proceso
    if (!longitudMinimaCorreo || !longitudMinimaClave) {
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

    // Inicializar una solicitud POST a la URL "/svLogin" y establecer la solicitud como asíncrona (true)
    xhr.open('POST', "/svLogin", true);

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
                // Redirigir a 'inicio.jsp', establecer el estado de sesión en localStorage y mostrar un mensaje de éxito
                window.location.href = "inicio.jsp";
                localStorage.setItem("sesion", true);
            } else if (xhr.responseText === 'error') {
                // Mostrar un mensaje indicando que el correo o la contraseña son incorrectos
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo o contraseña incorrectos. Inténtelo de nuevo.</p>`;
            } else if (xhr.responseText === 'disabled') {
                // Mostrar un mensaje indicando que el usuario está deshabilitado
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Usuario deshabilitado</p>`;
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
