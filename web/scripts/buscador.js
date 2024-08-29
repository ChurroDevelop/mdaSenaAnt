// Selecciona el elemento con el id 'buscador'
const _buscador = document.querySelector("#buscador");

// Verifica que el elemento se haya seleccionado correctamente
console.log(_buscador);

// Define una función que se ejecutará cuando se haga clic en el elemento
let buscador = () => {
    console.log("HOLA"); // Mensaje de prueba en la consola
    // Redirige a la página 'inicio.jsp'
    window.location.href = "http://localhost:8070/inicio.jsp";
};

// Añade un evento de clic al elemento que ejecuta la función 'buscador'
_buscador.addEventListener("click", buscador);
