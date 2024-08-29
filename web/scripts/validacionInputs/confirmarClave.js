/**
 * Función para validar la confirmación de una clave en un formulario.
 * 
 * @param {HTMLInputElement} input - El campo de entrada (input) donde se realiza la validación.
 * @param {string} error - Mensaje de error a mostrar si la validación falla. Si es una cadena vacía, no se muestra ningún error.
 * @returns {boolean} - Devuelve `true` si la validación es exitosa (no hay error), o `false` si hay un mensaje de error.
 * 
 * La función realiza las siguientes acciones:
 * 1. Busca el elemento `<label>` más cercano al input y luego el siguiente hermano de ese label.
 * 2. Si se proporciona un mensaje de error:
 *    - Añade una clase de borde rojo al `<label>` asociado al campo de entrada.
 *    - Si el siguiente hermano no es un `<span>`, crea uno nuevo, establece su contenido con el mensaje de error y lo añade después del `<label>`.
 *    - Devuelve `false` para indicar que hay un error en la validación.
 * 3. Si no se proporciona un mensaje de error:
 *    - Elimina las clases de borde rojo del `<label>`.
 *    - Si existe un `<span>` que contiene el mensaje de error, lo elimina.
 *    - Devuelve `true` para indicar que la validación fue exitosa.
 */
export default function confirmarClave(input, error) {
  let _span = input.closest("label").nextElementSibling;

  if (error) {
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
  }
}
