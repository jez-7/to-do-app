 
function getListIdFromURL() {
  const params = new URLSearchParams(window.location.search);
  return params.get('id');
}

 
async function editList() {
  const listNameSpan = document.querySelector('.list-name');
  if (!listNameSpan) return;
  
  const currentName = listNameSpan.textContent;
  
 
  const input = document.createElement('input');
  input.type = 'text';
  input.value = currentName;
  input.className = 'edit-list-input';
  input.style.cssText = `
    font-size: inherit;
    font-weight: inherit;
    border: 2px solid #007bff;
    border-radius: 4px;
    padding: 4px 8px;
    background: white;
    outline: none;
    min-width: 200px;
  `;
  
 
  listNameSpan.replaceWith(input);
  input.focus();
  input.select();
  
  
  const saveChanges = async () => {
    const newName = input.value.trim();
    if (newName && newName !== currentName) {
      const id = getListIdFromURL();
      if (!id) return;
      
      try {
        const response = await fetch(`http://localhost:8080/api/list/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ name: newName }),
        });
        
        if (!response.ok) throw new Error('Failed to update list name');
        
       
        const newSpan = document.createElement('span');
        newSpan.className = 'list-name';
        newSpan.textContent = newName;
        input.replaceWith(newSpan);
        
        
        updateListInSidebar(id, newName);
        
        
        setTimeout(() => refreshSidebarLists(), 100);
        
      } catch (err) {
        console.error('Error updating list name:', err);
         
        const restoredSpan = document.createElement('span');
        restoredSpan.className = 'list-name';
        restoredSpan.textContent = currentName;
        input.replaceWith(restoredSpan);
      }
    } else {
      
      const restoredSpan = document.createElement('span');
      restoredSpan.className = 'list-name';
      restoredSpan.textContent = currentName;
      input.replaceWith(restoredSpan);
    }
  };
  
   
  const cancelEdit = () => {
    const restoredSpan = document.createElement('span');
    restoredSpan.className = 'list-name';
    restoredSpan.textContent = currentName;
    input.replaceWith(restoredSpan);
  };
  
  
  input.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      saveChanges();
    } else if (e.key === 'Escape') {
      e.preventDefault();
      cancelEdit();
    }
  });
  
  input.addEventListener('blur', saveChanges);
}

 
async function deleteList() {
  const id = getListIdFromURL();
  if (!id) return;
  
  const confirmDelete = confirm('Seguro que quieres eliminar esta lista?');
  if (confirmDelete) {
    try {
      const response = await fetch(`http://localhost:8080/api/list/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) throw new Error('Failed to delete list');
      window.location.href = 'index.html';  
    } catch (err) {
      console.error('Error deleting list:', err);
    }
  }
}
 function attachListOptionsEventListeners() {
  const editButton = document.querySelector('.edit-list');
  const deleteButton = document.querySelector('.delete-list');
  
  if (editButton) {
    editButton.addEventListener('click', editList);
  }
  
  if (deleteButton) {
    deleteButton.addEventListener('click', deleteList);
  }
}

 
function updateListInSidebar(listId, newName) {
  const listItem = document.querySelector(`li[data-id="${listId}"]`);
  if (listItem) {
    
    listItem.innerHTML = `<i class="fa fa-list-ul"></i>${newName}`;
  }
}

 
 