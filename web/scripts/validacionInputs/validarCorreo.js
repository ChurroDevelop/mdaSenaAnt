/**
 * Función para validar la extensión de correos electrónicos permitidos.
 * 
 * @param {HTMLInputElement} input - El campo de entrada (input) donde se encuentra el correo electrónico a validar.
 * @param {string} error - Mensaje de error a mostrar si la extensión del correo electrónico no es válida.
 * @returns {boolean} - Devuelve `true` si el correo electrónico cumple con una de las extensiones permitidas, o `false` si no es válido.
 * 
 * La función realiza las siguientes acciones:
 * 1. Define dos expresiones regulares para validar las extensiones de correo electrónico permitidas:
 *    - `regexInstructor` para correos electrónicos con el dominio `@sena.edu.co`.
 *    - `regexAprendiz` para correos electrónicos con el dominio `@soy.sena.edu.co`.
 * 2. Verifica si el valor del campo de entrada coincide con alguna de las expresiones regulares:
 *    - Si el correo electrónico no coincide con ninguna de las expresiones regulares, añade una clase de borde rojo al `<label>` asociado al campo de entrada.
 *    - Si el siguiente hermano del `<label>` no es un `<span>`, crea un nuevo `<span>`, establece su contenido con el mensaje de error y lo añade después del `<label>`.
 *    - Devuelve `false` para indicar que el correo electrónico no es válido.
 * 3. Si el correo electrónico cumple con una de las extensiones permitidas:
 *    - Elimina las clases de borde rojo del `<label>`.
 *    - Si existe un `<span>` que contiene el mensaje de error, lo elimina.
 *    - Devuelve `true` para indicar que el correo electrónico es válido.
 */
export default function validarExtensionCorreo(input, error) {
  const regexInstructor = /^[a-zA-Z0-9.]+@sena\.edu\.co$/i;
  const regexAprendiz = /^[a-zA-Z0-9.]+@soy\.sena\.edu\.co$/;
  
  let _span = input.closest("label").nextElementSibling;
  
  if (!regexInstructor.test(input.value) && !regexAprendiz.test(input.value)) {
    input.closest("label").classList.add("border-red-600", "border-2");
    if (!_span || _span.tagName !== "SPAN") {
      _span = document.createElement("span");
      _span.textContent = error;
      _span.classList.add("text-red-600", "text-end", "inline-block");
      input.closest("label").insertAdjacentElement("afterend", _span);
    }
    return false;
  } else {
    input.closest("label").classList.remove("border-red-600", "border-2");
    // Si el <span> de error existe y es un <span>, elimínalo
    if (_span && _span.tagName === "SPAN") {
      _span.remove();
    };
    return true;
  };
};
