/**
 * Función para verificar que la longitud del valor en un campo de entrada cumpla con un mínimo requerido.
 * 
 * @param {HTMLInputElement} input - El campo de entrada (input) cuya longitud se va a verificar.
 * @param {number} minimo - La longitud mínima requerida para el campo de entrada.
 * @param {string} error - Mensaje de error a mostrar si la longitud del valor es menor que el mínimo requerido.
 * @returns {boolean} - Devuelve `true` si la longitud del valor es igual o mayor que el mínimo requerido, o `false` si es menor.
 * 
 * La función realiza las siguientes acciones:
 * 1. Verifica si la longitud actual del valor del campo es menor que el valor mínimo permitido (`minimo`):
 *    - Si la longitud es menor, añade una clase de borde rojo al `<label>` asociado al campo de entrada.
 *    - Si el siguiente hermano del `<label>` no es un `<span>`, crea un nuevo `<span>`, establece su contenido con el mensaje de error y lo añade después del `<label>`.
 *    - Devuelve `false` para indicar que el valor no cumple con la longitud mínima requerida.
 * 2. Si la longitud del valor es igual o mayor que el mínimo requerido:
 *    - Elimina las clases de borde rojo del `<label>`.
 *    - Si existe un `<span>` que contiene el mensaje de error, lo elimina.
 *    - Devuelve `true` para indicar que el valor cumple con la longitud mínima requerida.
 */
export default function longitudMinima(input, minimo, error) {
  let _span = input.closest("label").nextElementSibling;
  if (input.value.length < minimo) {
    input.closest("label").classList.add("border-red-600", "border-2");
    // Si el <span> de error no existe o no es un <span>, créalo
    if (!_span || _span.tagName !== "SPAN") {
      _span = document.createElement("span");
      _span.textContent = error;
      _span.classList.add("text-red-600", "text-end", "inline-block");
      input.closest("label").insertAdjacentElement("afterend", _span);
    };
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
