import longitudMinima from "../validacionInputs/longitudMinima.js";
import longitudMaxima from "../validacionInputs/longitudMaxima.js";
import evitarLetras from "../validacionInputs/evitarLetras.js";
import evitarNumeros from "../validacionInputs/evitarNumeros.js";


const _form = document.getElementById("formularioCrearPerfil");
const _nombre = document.getElementById("inputNombre");
const _apellidos = document.getElementById("inputApellido");
const _documento = document.getElementById("inputDocumento");
const _centro = document.getElementById("inputCentro");

longitudMaxima(_nombre, 50);
evitarNumeros(_nombre);
longitudMaxima(_apellidos, 50);
evitarNumeros(_apellidos);
longitudMaxima(_documento, 10);
evitarLetras(_documento);
longitudMaxima(_centro, 100);
evitarNumeros(_centro);

_form.addEventListener("submit", (event) => {

    const longitudMinimaNombre = longitudMinima(_nombre, 4, "Cantidad de carácteres inválida");
    const longitudMinimaApellidos = longitudMinima(_apellidos, 4, "Cantidad de carácteres inválida");
    const longitudMinimaDocumento = longitudMinima(_documento, 8, "Cantidad de carácteres inválida");
    const longitudMinimaCentro = longitudMinima(_centro, 4, "Cantidad de carácteres inválida");

    if (!longitudMinimaNombre || !longitudMinimaApellidos || !longitudMinimaDocumento || !longitudMinimaCentro) {
        event.preventDefault();
    };
    
    const validaExtensionCorreo = validarCorreo(_correo, "Extensión del correo no válida");
    
    if(!validaExtensionCorreo){
        event.preventDefault();
    };

});