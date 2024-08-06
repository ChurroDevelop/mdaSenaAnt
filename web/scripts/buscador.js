const _buscador = document.querySelector("#buscador");
console.log(_buscador);

let buscador = () => {
    console.log("HOLA");
    window.location.href = "http://localhost:8070/inicio.jsp";
};

_buscador.addEventListener("click", buscador);