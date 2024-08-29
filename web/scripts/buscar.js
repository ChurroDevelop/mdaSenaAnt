// Este script se ejecuta cuando el DOM se ha cargado completamente
document.addEventListener("DOMContentLoaded", () => {
    
    // Selecciona el elemento de entrada con el id 'buscador' para filtrar los posts
    const _input = document.querySelector("#buscador");
    // Selecciona todos los elementos con el id 'posts' (se espera que estos sean múltiples elementos con el mismo id)
    const _posts = document.querySelectorAll("#posts");
    
    // Selecciona el elemento de entrada con el id 'buscarMonitor' para filtrar los monitores
    const _inputMonitor = document.querySelector("#buscarMonitor");
    // Selecciona todos los elementos con el id 'monitorItem' (se espera que estos sean múltiples elementos con el mismo id)
    const _monitores = document.querySelectorAll("#monitorItem");
    
    // Añade un evento de entrada (input) al elemento '_input' para filtrar los posts
    _input.addEventListener("input", () => {
        // Convierte el valor del campo de búsqueda a minúsculas
        const _valueLower = _input.value.toLowerCase();
        // Itera sobre todos los elementos '_posts'
        _posts.forEach((_post) => {
            // Obtiene el texto del título en minúsculas
            const _title = _post.querySelector("#title").innerText.toLowerCase();
            // Si el valor de búsqueda está vacío, muestra todos los posts
            if (_valueLower === '') {
                _post.classList.add("block");
                _post.classList.remove("hidden");
            }
            // Si el título del post incluye el valor de búsqueda, muestra el post
            else if(_title.includes(_valueLower)){
                _post.classList.add("block");
                _post.classList.remove("hidden");
            }
            // Si el título del post no incluye el valor de búsqueda, oculta el post
            else {
                _post.classList.add("hidden");
                _post.classList.remove("block");
            }
        });
    });
    
    // Añade un evento de entrada (input) al elemento '_inputMonitor' para filtrar los monitores
    _inputMonitor.addEventListener("input", () => {
        // Convierte el valor del campo de búsqueda a minúsculas
        const _valueLower = _inputMonitor.value.toLowerCase();
        // Itera sobre todos los elementos '_monitores'
        _monitores.forEach((_monitor) => {
            // Obtiene el texto del nombre del monitor en minúsculas
            const _nombre = _monitor.querySelector("#nombreMonitor").innerText.toLowerCase();
            // Si el valor de búsqueda está vacío, muestra todos los monitores
            if (_valueLower === '') {
                _monitor.classList.add("block");
                _monitor.classList.remove("hidden");
            }
            // Si el nombre del monitor incluye el valor de búsqueda, muestra el monitor
            else if(_nombre.includes(_valueLower)){
                _monitor.classList.add("block");
                _monitor.classList.remove("hidden");
            }
            // Si el nombre del monitor no incluye el valor de búsqueda, oculta el monitor
            else {
                _monitor.classList.add("hidden");
                _monitor.classList.remove("block");
            }
        });
    });
});
