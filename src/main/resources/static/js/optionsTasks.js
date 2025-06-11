async function editTask(element) {
  try {
    const taskId = element.closest(".tarea").dataset.taskId;

    const tareaDiv = element.closest(".tarea");
    const currentName = tareaDiv.querySelector("span").textContent;
    const currentDescription = tareaDiv
      .closest(".tarea-container")
      .querySelector(".descripcion").textContent;

    console.log("Editando tarea ID:", taskId);
    console.log("Nombre actual:", currentName);
    console.log("Descripción actual:", currentDescription);

    const newName = prompt("Nuevo nombre de la tarea:", currentName);
    const newDescription = prompt(
      "Nueva descripción:",
      currentDescription === "Sin descripción" ? "" : currentDescription
    );

    if (newName !== null && newName.trim() !== "") {
      const response = await fetch(`http://localhost:8080/api/task/${taskId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: newName.trim(),
          description: newDescription || "",
        }),
      });

      if (!response.ok) {
        throw new Error("Error al actualizar la tarea");
      }

      console.log("Tarea actualizada exitosamente");
      await showTasks();
    }
  } catch (error) {
    console.error("Error editando tarea:", error.message);
  }
}
async function deleteTask(element) {
  try {
    const taskId = element.closest(".tarea").dataset.taskId;
    const taskName = element
      .closest(".tarea")
      .querySelector("span").textContent;

    console.log("Eliminando tarea ID:", taskId);

    const confirmDelete = confirm(
      `¿Estás seguro de que quieres eliminar la tarea "${taskName}"?`
    );

    if (confirmDelete) {
      const response = await fetch(`http://localhost:8080/api/task/${taskId}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("Error al eliminar la tarea");
      }

      console.log("Tarea eliminada exitosamente");
      await showTasks();
    }
  } catch (error) {
    console.error("Error eliminando tarea:", error.message);
  }
}
