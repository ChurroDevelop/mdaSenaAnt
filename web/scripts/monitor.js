// Función para verificar el estado de la sesión
let comprobar = () => {
    // Imprime el valor del ítem "sesion" en el almacenamiento local
    console.log(localStorage.getItem("sesion"));
    
    // Verifica si el valor de "sesion" es "false"
    if (localStorage.getItem("sesion") === "false") {
        // Redirige al usuario a la página de inicio de sesión si la sesión es falsa
        window.location.href = "../login.jsp";
    }
};

// Configura un intervalo para ejecutar la función 'comprobar' cada 200 milisegundos
setInterval(() => {
    comprobar(); // Llama a la función 'comprobar' para verificar el estado de la sesión
}, 200);
