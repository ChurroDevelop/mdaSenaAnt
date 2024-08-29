// Función para comprobar el estado de la sesión
let comprobar = () => {
    // Imprime en consola el valor actual del ítem "sesion" en localStorage
    console.log(localStorage.getItem("sesion"));

    // Verifica si el valor del ítem "sesion" en localStorage es "false"
    if (localStorage.getItem("sesion") === "false") {
        // Redirige al usuario a la página de inicio de sesión si la sesión es falsa
        window.location.href = "../login.jsp";
    }
};

// Configura un intervalo que ejecuta la función 'comprobar' cada 200 milisegundos
setInterval(() => {
    comprobar();
}, 200);
