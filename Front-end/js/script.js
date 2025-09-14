const form = document.getElementById('cadastroForm');

form.addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio padrão

    const formData = new FormData(form);
    const newUser = Object.fromEntries(formData.entries());

    fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Usuário criado:', data);
        // Lógica para redirecionar ou mostrar mensagem
    })
    .catch(error => {
        console.error('Erro:', error);
    });
}); // A chave de fechamento estava faltando aqui!