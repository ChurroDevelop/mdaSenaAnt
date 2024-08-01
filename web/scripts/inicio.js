let comprobar = () => {
    console.log(localStorage.getItem("sesion"));
    if (localStorage.getItem("sesion") === "false") {
        window.location.href = "../login.jsp";
    }
};

setInterval(() => {
    comprobar();
}, 200);