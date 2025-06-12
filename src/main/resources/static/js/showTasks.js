document.addEventListener("DOMContentLoaded", () => {
  showTasks();
  showCompletedTasks();
});

async function showTasks() {
  try {
    const params = new URLSearchParams(window.location.search);
    const listId = params.get("id");

    const response = await fetch(`http://localhost:8080/api/task/list/${listId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("Error trying to show tasks");
    }

    const tasks = await response.json();

    const tareasPendientes = [];
    const tareasCompletadas = [];

    for (let task of tasks) {
		
      const isFinalized = task.state === "FINALIZED";

      const tareaHTML = `
        <div class="tarea-container">
          <div class="tarea" onclick="toggleDescripcion(this)" data-task-id="${task.id}" data-state="${task.state}">
            <div class="tarea-izquierda">
              <i class="${isFinalized ? 'fas' : 'far'} fa-circle" onclick="event.stopPropagation(); toggleCompleted(this)"></i>
              <span>${task.name}</span>
            </div>
            <div class="tarea-derecha">
              <i class="fas fa-pen" onclick="event.stopPropagation(); editTask(this)"></i>
              <i class="far fa-star" onclick="event.stopPropagation(); toggleImportante(this)"></i>
              <i class="fas fa-trash-alt" onclick="event.stopPropagation(); deleteTask(this)"></i>
            </div>
          </div>
          <div class="descripcion">${task.description || "Sin descripción"}</div>
        </div>
      `;

      if (isFinalized) {
        tareasCompletadas.push(tareaHTML);
      } else {
        tareasPendientes.push(tareaHTML);
      }
    }

     
    const tareasContainer = document.getElementById("tareas-pendientes");
    tareasContainer.innerHTML = tareasPendientes.join("");

    
    const completedDetails = document.querySelector(".completed details");
    completedDetails.innerHTML = `
      <summary>✅ Completado <span>${tareasCompletadas.length}</span></summary>
      ${tareasCompletadas.join("")}
    `;

  } catch (error) {
    console.error("Error mostrando tareas:", error.message);
  }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
