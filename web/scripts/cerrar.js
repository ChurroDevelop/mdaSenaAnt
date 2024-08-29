// Selecciona el botón con el id 'btnCerrar'
const $btnCerrar = document.querySelector("#btnCerrar");

// Obtiene el valor de la sesión almacenado en el localStorage
let $sesion = localStorage.getItem("sesion");

// Función para comprobar el estado de la sesión
let comprobar = () => {
    // Muestra el valor actual de la sesión en la consola
    console.log(localStorage.getItem("sesion"));
    
    // Si el valor de la sesión es "false", redirige al usuario a la página de login
    if (localStorage.getItem("sesion") === "false") {
        console.log("hola"); // Mensaje de depuración
        window.location.href = "../login.jsp";
    }
};

// Llama a la función 'comprobar' cada 200 milisegundos
setInterval(() => {
    comprobar();
}, 200);

// Añade un evento de clic al botón '$btnCerrar'
$btnCerrar.addEventListener("click", function cerrar(){
    // Muestra el valor actual de la sesión y un mensaje de depuración en la consola
    console.log($sesion + "hola");
    
    // Establece el valor de la sesión a false
    $sesion = false;
    
    // Muestra el nuevo valor de la sesión en la consola
    console.log($sesion);
    
    // Actualiza el valor de la sesión en el localStorage
    localStorage.setItem("sesion", $sesion);
    
    // Muestra el valor actualizado de la sesión en el localStorage en la consola
    console.log(localStorage.getItem("sesion"))
});
