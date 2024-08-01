// Función para validar extensión de correos
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
