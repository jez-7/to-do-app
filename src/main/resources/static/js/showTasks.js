document.addEventListener("DOMContentLoaded", () => {
  showTasks();
});

async function showTasks() {
  try {
    const params = new URLSearchParams(window.location.search);
    const listId = params.get("id");

    const response = await fetch(
      `http://localhost:8080/api/task/list/${listId}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error("Error trying to show tasks");
    }

    const tasks = await response.json();

    const existingTasks = document.querySelectorAll(
      ".tarea-container:not(:last-child)"
    );
    existingTasks.forEach((task) => task.remove());

    let taskHtml = "";

    for (let task of tasks) {
      taskHtml += `
        <div class="tarea-container">
          <div class="tarea" onclick="toggleDescripcion(this)" data-task-id="${
            task.id
          }">
            <div class="tarea-izquierda">
              <i class="far fa-circle" onclick="event.stopPropagation(); toggleCompletado(this)"></i>
              <span>${task.name}</span>
            </div>
            <div class="tarea-derecha">
              <i class="fas fa-pen" onclick="event.stopPropagation(); editTask(this)"></i>
              <i class="far fa-star" onclick="event.stopPropagation(); toggleImportante(this)"></i>
              <i class="fas fa-trash-alt" onclick="event.stopPropagation(); deleteTask(this)"></i>
            </div>
          </div>
          <div class="descripcion">${
            task.description || "Sin descripción"
          }</div>
        </div>
      `;
    }

    const completedSection = document.querySelector(".completed");

    if (taskHtml) {
      completedSection.insertAdjacentHTML("beforebegin", taskHtml);
    }
  } catch (error) {
    console.error("Error mostrando tareas:", error.message);
  }
}
