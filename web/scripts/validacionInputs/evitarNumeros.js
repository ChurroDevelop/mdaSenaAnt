/**
 * Función para evitar que se escriban números en un campo de entrada.
 * 
 * @param {HTMLInputElement} input - El campo de entrada (input) donde se aplicará la restricción.
 * 
 * La función realiza las siguientes acciones:
 * 1. Añade un escuchador de eventos para el evento `keypress` en el campo de entrada:
 *    - Define una expresión regular que permite solo letras (mayúsculas y minúsculas), caracteres acentuados, espacios y signos de puntuación.
 *    - Si el carácter presionado no coincide con la expresión regular, evita la acción predeterminada del evento (`event.preventDefault()`).
 *    - Si hay un mensaje de error previamente creado, lo elimina.
 *    - Crea un nuevo `<span>` con un mensaje de error ("Solo se permiten letras") y lo añade al contenedor `<div>` más cercano al campo de entrada.
 * 2. Añade un escuchador de eventos para el evento `blur` en el campo de entrada:
 *    - Elimina el mensaje de error cuando el campo pierde el foco.
 * 
 * Nota: Esta función permite letras, caracteres acentuados, espacios y signos de puntuación, pero evita la entrada de números.
 */
export default function evitarNumeros(input) {
  // Variable para almacenar la referencia al elemento de error
  let _span;
  input.addEventListener("keypress", (event) => {
    const regexLetras = /^[a-zA-ZàáâãéêíóôõúüñÑ $]/;
    // Si ya existe el mensaje de error, lo elimina
    if (_span) {
      _span.remove();
      _span = null;
    }
    if (!regexLetras.test(event.key)) {
      event.preventDefault();
      // Crear y mostrar el mensaje de error
      _span = document.createElement("span");
      _span.textContent = "Solo se permiten letras";
      _span.classList.add("text-red-600", "inline-block");
      input.closest("div").appendChild(_span);
    };
  });
  input.addEventListener("blur", () => {
    // Eliminar el mensaje de error cuando se pierde el foco
    if (_span) {
      _span.remove();
      _span = null;
    }
  });
};
