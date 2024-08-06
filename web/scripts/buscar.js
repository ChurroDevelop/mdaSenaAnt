document.addEventListener("DOMContentLoaded", () => {
    const _input = document.querySelector("#buscador");
    const _posts = document.querySelectorAll("#posts");
    
    _input.addEventListener("input", () => {
        const _valueLower = _input.value.toLowerCase();
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
        })
    })
})