document.addEventListener("DOMContentLoaded", () => {
    const _input2 = document.querySelector("#buscador2");
    const _posts = document.querySelectorAll("#posts");
    
    const _inputMonitor = document.querySelector("#buscarMonitor");
    const _monitores = document.querySelectorAll("#monitorItem");
    
    _input2.addEventListener("input", () => {
        const _valueLower = _input2.value.toLowerCase();
        _posts.forEach((_post) => {
            const _title = _post.querySelector("#title").innerText.toLowerCase();
            if (_valueLower === '') {
                _post.classList.add("block");
                _post.classList.remove("hidden");
            }
            else if(_title.includes(_valueLower)){
                _post.classList.add("block");
                _post.classList.remove("hidden");
            }
            else {
                _post.classList.add("hidden");
                _post.classList.remove("block");
            }
        });
    });
    
    _inputMonitor.addEventListener("input", () => {
        const _valueLower = _inputMonitor.value.toLowerCase();
        _monitores.forEach((_monitor) => {
            const _nombre = _monitor.querySelector("#nombreMonitor").innerText.toLowerCase();
            if (_valueLower === '') {
                _monitor.classList.add("block");
                _monitor.classList.remove("hidden");
            }
            else if(_nombre.includes(_valueLower)){
                _monitor.classList.add("block");
                _monitor.classList.remove("hidden");
            }
            else {
                _monitor.classList.add("hidden");
                _monitor.classList.remove("block");
            }
        });
    });
});