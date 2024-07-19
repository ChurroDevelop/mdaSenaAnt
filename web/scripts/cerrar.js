const $btnCerrar = document.querySelector("#btnCerrar");
let $sesion = localStorage.getItem("sesion");
let comprobar = () => {
    console.log(localStorage.getItem("sesion"));
    if (localStorage.getItem("sesion") === "false") {
        console.log("hola");
        window.location.href = "../login.jsp";
    }
};

setInterval(() => {
    comprobar();
}, 1000);

$btnCerrar.addEventListener("click", function cerrar(){
    console.log($sesion + "hola");
    $sesion = false;
    console.log($sesion);
    localStorage.setItem("sesion", $sesion);
    console.log(localStorage.getItem("sesion"))
    
});