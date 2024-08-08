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

    // Crear una nueva instancia de FormData, pasando el formulario como argumento
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
                // Si la respuesta es 'success', redirigir a 'autenticacion.jsp' y mostrar un mensaje de éxito
                window.location.href = "autenticacion.jsp";
                resultadoDiv.innerHTML = `<p class="text-green-500 mt-4">Código verificado correctamente.</p>`;
            } else if (xhr.responseText === 'email_exists') {
                // Si la respuesta es 'email_exists', mostrar un mensaje indicando que el correo ya está registrado
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ya hay un correo registrado.</p>`;
            } else if (xhr.responseText === 'invalid_email') {
                // Si la respuesta es 'invalid_email', mostrar un mensaje indicando que el correo no es válido
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Correo institucional no válido.</p>`;
            } else if (xhr.responseText === 'password_mismatch') {
                // Si la respuesta es 'password_mismatch', mostrar un mensaje indicando que las contraseñas no coinciden
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Las contraseñas no coinciden.</p>`;
            } else {
                // Para cualquier otra respuesta, mostrar un mensaje de error genérico
                resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Ocurrió un error. Inténtelo de nuevo más tarde.</p>`;
            }
        } else {
            // Si el estado de la respuesta no es 200, mostrar un mensaje de error en la solicitud
            resultadoDiv.innerHTML = `<p class="text-red-500 mt-4">Error en la solicitud. Inténtelo de nuevo más tarde.</p>`;
        }
    };

// Enviar la solicitud con los datos del formulario, convertidos a una cadena URL-encoded
    xhr.send(new URLSearchParams(new FormData(_form)).toString());

});
