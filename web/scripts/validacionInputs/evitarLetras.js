/**
 * Función para evitar que se escriban letras en un campo de entrada.
 * 
 * @param {HTMLInputElement} input - El campo de entrada (input) donde se aplicará la restricción.
 * 
 * La función realiza las siguientes acciones:
 * 1. Añade un escuchador de eventos para el evento `keypress` en el campo de entrada:
 *    - Define una expresión regular que permite solo dígitos y el signo de dólar (`$`).
 *    - Si el carácter presionado no coincide con la expresión regular, evita la acción predeterminada del evento (`event.preventDefault()`).
 *    - Si hay un mensaje de error previamente creado, lo elimina.
 *    - Crea un nuevo `<span>` con un mensaje de error ("Solo se permiten números") y lo añade al contenedor `<div>` más cercano al campo de entrada.
 * 2. Añade un escuchador de eventos para el evento `blur` en el campo de entrada:
 *    - Elimina el mensaje de error cuando el campo pierde el foco.
 * 
 * Nota: Esta función permite que el signo de dólar (`$`) se ingrese, además de los números.
 */
export default function evitarLetras(input) {
  // Variable para almacenar la referencia al elemento de error
  let _span;
  input.addEventListener("keypress", (event) => {
    const regexLetras = /^[0-9$]/;
    // Si ya existe el mensaje de error, lo elimina
    if (_span) {
      _span.remove();
      _span = null;
    }
    if (!regexLetras.test(event.key)) {
      event.preventDefault();
      // Crear y mostrar el mensaje de error
      _span = document.createElement("span");
      _span.textContent = "Solo se permiten números";
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
}
