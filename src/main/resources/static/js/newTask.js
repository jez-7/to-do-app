document.addEventListener("DOMContentLoaded", () => {
  const input = document.getElementById("inp-add-task");
  const button = document.getElementById("btn-add-task");

  input.addEventListener("keydown", createTask);
  button.addEventListener("click", createTask);
});

async function createTask(event) {
  const params = new URLSearchParams(window.location.search);
  const listId = params.get("id");
  const taskInput = document.getElementById("inp-add-task");

  const nombre = taskInput.value.trim();
  if (!nombre || !listId) return;
  if (event.type === "keydown" && event.key !== "Enter") return;

  try {
    const response = await fetch("http://localhost:8080/api/task", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: nombre,
        description: "Sin descripción",
        taskListId: parseInt(listId),
      }),
    });

    if (!response.ok) {
      throw new Error("Error to create task");
    }

    const data = await response.json();
    console.log("Task created:", data);

    await showTasks();

    taskInput.value = "";
  } catch (error) {}
}
