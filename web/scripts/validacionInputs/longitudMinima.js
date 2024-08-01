// Función para verificar la longitud
export default function longitudMinima(input, minimo, error) {
  let _span = input.closest("label").nextElementSibling;
  if (input.value.length < minimo) {
    // input.closest("label").classList.add("border-red-600", "border-2");
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