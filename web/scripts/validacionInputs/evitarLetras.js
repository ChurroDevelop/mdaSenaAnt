// Función para evitar que se escriban letras
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