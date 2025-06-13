 
async function toggleCompleted(element) {
  try {
    const tareaDiv = element.closest(".tarea");
    const taskId = tareaDiv.dataset.taskId;
    const currentState = tareaDiv.dataset.state;

    const newState = currentState === "FINALIZED" ? "CREATED" : "FINALIZED";

    const response = await fetch(`http://localhost:8080/api/task/changeState/${taskId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ state: newState })
    });

    if (!response.ok) {
      throw new Error("error al cambiar el estado de la tarea");
    }
    
    // actualiza el circulo visualmente
    element.classList.toggle("fas");
    element.classList.toggle("far");

    // actualiza el dataset
    tareaDiv.dataset.state = newState;

    // mueve la tarea al contenedor correspondiente
    const tareaContainer = tareaDiv.closest(".tarea-container");
    tareaContainer.remove(); // lo sacamos del DOM

    if (newState === "FINALIZED") {
      const completedSection = document.querySelector(".completed details");
      completedSection.insertAdjacentHTML("beforeend", tareaContainer.outerHTML);
    } else {
      const pendientes = document.getElementById("tareas-pendientes");
      pendientes.insertAdjacentHTML("beforeend", tareaContainer.outerHTML);
    }

     
    showTasks();  

  } catch (error) {
    console.error("error al cambiar estado:", error.message);
  }
}


