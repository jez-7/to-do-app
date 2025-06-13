document.addEventListener('DOMContentLoaded', showLists);
document.addEventListener('DOMContentLoaded', showListName);

// get and show lists
async function showLists() {
  try {
    const response = await fetch('https://to-do-app-steq.onrender.com/api/list', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    });

    if (!response.ok) {
      throw new Error('Error trying to show lists');
    }

    const lists = await response.json(); 
    let listsHtml = '';

    for (let taskList of lists) {
      listsHtml += `
         <a href="list.html?id=${taskList.id}">
         <li data-id="${taskList.id}" > <i class="fa fa-list-ul"></i>${taskList.name}</li>  
         `;
    }

    const taskListListing = document.querySelector('.task-list-listing');
    if (taskListListing) {
      taskListListing.innerHTML = listsHtml;
    } else {
      console.error('TaskList list not found');
    }

  } catch (error) {
    console.error('Error loading lists:', error);
  }
}

// show list name in list.html
function getListIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get('id');
}

async function showListName() {
  const id = getListIdFromURL();
  if (!id) return;

  try {
    const response = await fetch(`https://to-do-app-steq.onrender.com/api/list/${id}`);
    if (!response.ok) throw new Error('Failed to fetch list');

    const list = await response.json();
    const headerTitle = document.querySelector('.header h2');
    if (headerTitle) {
      headerTitle.innerHTML = `
        <span class="list-name">${list.name}</span>
        <div class="menu-container">
            <i class="fa fa-ellipsis-v menu-icon"></i>
          <div class="dropdown-menu">
              <button class="edit-list">Editar</button>
              <button class="delete-list">Eliminar</button>
          </div>
        </div>
      `;

      const icon = headerTitle.querySelector('.menu-icon');
      const menu = headerTitle.querySelector('.dropdown-menu');

      icon.addEventListener('click', (e) => {
        e.stopPropagation();
        menu.style.display = menu.style.display === 'flex' ? 'none' : 'flex';
      });

      document.addEventListener('click', () => {
        menu.style.display = 'none';
      });

      menu.addEventListener('click', (e) => e.stopPropagation());
      
     
      if (typeof attachListOptionsEventListeners === 'function') {
        attachListOptionsEventListeners();
      }
    }
  } catch (error) {
    console.error('Error loading list name:', error);
  }
}
