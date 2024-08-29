// MODAL PARA AGREGAR MONITOR

// Selecciona el elemento del DOM que representa el modal
const modal = document.getElementById("modal-1");

// Verifica si el modal existe en el DOM
if (modal !== null) {
  // Selecciona el botón que muestra el modal
  const showModal = document.getElementById("showModal");
  
  // Asigna un evento de clic al botón para mostrar el modal
  showModal.onclick = function () {
    // Elimina la clase "hidden" del modal para mostrarlo
    modal.classList.remove("hidden");
  };

  // Selecciona el botón que cierra el modal
  const closeModal = document.getElementById("closeModal");
  
  // Asigna un evento de clic al botón para cerrar el modal
  closeModal.onclick = function () {
    // Agrega la clase "hidden" al modal para ocultarlo
    modal.classList.add("hidden");
  };
}
