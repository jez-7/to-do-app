function showInputNewList() {
  document.querySelector('.new-list').style.display = 'none';
  const input = document.querySelector('.input-new-list');
  input.style.display = 'inline-block';
  input.focus();
}

async function createList(event) {
  if (event.key === 'Enter') {
    const nombre = event.target.value.trim();
    if (nombre !== '') {
      try {
        const response = await fetch('http://localhost:8080/api/list', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ name: nombre })
        });

        if (!response.ok) {
          throw new Error('Error to create list');
        }

        const data = await response.json();
        console.log('List created:', data);

        // Limpiar y ocultar input
        event.target.value = '';
        event.target.style.display = 'none';
        document.querySelector('.new-list').style.display = 'inline-block';

        await showLists();
        
      } catch (error) {
        console.error('Ocurrió un error:', error.message);
      }
    }
  }
}




















 