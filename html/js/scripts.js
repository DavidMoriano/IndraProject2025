document.addEventListener("DOMContentLoaded", () => {
  // Menú desplegable
  const button = document.querySelector(".botonMenu");
  const menu = document.getElementById("contenidoMenu");

  button.addEventListener("click", (e) => {
    e.stopPropagation(); //no siga funcionando una vez hecho el click
    menu.style.display = menu.style.display === "block" ? "none" : "block";
  });

  //Si se hace click fuera del menú se cierra
  window.addEventListener("click", (event) => {
    if (!event.target.closest(".botonMenu")) {
      menu.style.display = "none";
    }
  });
});