/**
 * Función para validar la longitud máxima de un campo de entrada.
 * 
 * @param {HTMLInputElement} input - El campo de entrada (input) cuya longitud se va a validar.
 * @param {number} maximo - La longitud máxima permitida para el campo de entrada.
 * 
 * La función realiza las siguientes acciones:
 * 1. Añade un escuchador de eventos para el evento `keypress` en el campo de entrada:
 *    - Verifica si la longitud actual del valor del campo es igual o mayor que el valor máximo permitido (`maximo`).
 *    - Si la longitud actual es igual o mayor, evita la acción predeterminada del evento (`event.preventDefault()`), evitando así que se ingresen más caracteres.
 * 
 * Nota: Esta función solo controla la longitud del texto ingresado mientras se escribe. No realiza validación en el texto ya existente.
 */
export default function longitudMaxima(input, maximo) {
  input.addEventListener("keypress", (event) => {
    if (input.value.length >= maximo) {
      event.preventDefault();
    };
  });
};
