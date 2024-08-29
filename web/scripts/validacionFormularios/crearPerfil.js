/**
 * Script para manejar la validación de un formulario de creación de perfil.
 * 
 * Importa funciones de validación para longitud mínima, longitud máxima, y evitar caracteres no deseados.
 * 
 * @module
 * 
 * @requires longitudMinima - Función para validar la longitud mínima de un campo de entrada.
 * @requires longitudMaxima - Función para validar la longitud máxima de un campo de entrada.
 * @requires evitarLetras - Función para evitar que se escriban letras en un campo de entrada.
 * @requires evitarNumeros - Función para evitar que se escriban números en un campo de entrada.
 * @requires validarCorreo - Función para validar la extensión de correos electrónicos permitidos.
 */

import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import evitarLetras from "../validacionInputs/evitarLetras.js";
import evitarNumeros from "../validacionInputs/evitarNumeros.js";
import validarCorreo from "../validacionInputs/validarCorreo.js";

// Selección de elementos del formulario
const _form = document.getElementById("formularioCrearPerfil");
const _nombre = document.getElementById("inputNombre");
const _apellidos = document.getElementById("inputApellido");
const _documento = document.getElementById("inputDocumento");
const _centro = document.getElementById("inputCentro");
const _correo = document.getElementById("inputCorreo");

// Aplicar restricciones de longitud máxima y caracteres permitidos a los campos de entrada
longitudMaxima(_nombre, 50);
evitarNumeros(_nombre);
longitudMaxima(_apellidos, 50);
evitarNumeros(_apellidos);
longitudMaxima(_documento, 10);
evitarLetras(_documento);
longitudMaxima(_centro, 100);
evitarNumeros(_centro);

// Manejo del evento de envío del formulario
_form.addEventListener("submit", (event) => {
    // Validación de longitud mínima de los campos
    const longitudMinimaNombre = longitudMinima(_nombre, 4, "Cantidad de carácteres inválida");
    const longitudMinimaApellidos = longitudMinima(_apellidos, 4, "Cantidad de carácteres inválida");
    const longitudMinimaDocumento = longitudMinima(_documento, 8, "Cantidad de carácteres inválida");
    const longitudMinimaCentro = longitudMinima(_centro, 4, "Cantidad de carácteres inválida");

    // Validación de la extensión del correo electrónico
    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");

    // Si alguna validación falla, detener el envío del formulario
    if (!longitudMinimaNombre || !longitudMinimaApellidos || !longitudMinimaDocumento || !longitudMinimaCentro || !validaExtensionCorreo) {
        event.preventDefault();
    }
});
