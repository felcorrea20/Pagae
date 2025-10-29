// --- CÓDIGO PARA CADASTRO DE USUÁRIOS (C do CRUD) ---
const cadastroForm = document.getElementById('cadastroForm');

if (cadastroForm) {
    cadastroForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(cadastroForm);
        const newUser = Object.fromEntries(formData.entries());

        fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        })
        .then(response => {
            if (!response.ok) throw new Error('Erro na requisição: ' + response.statusText);
            return response.json();
        })
        .then(data => {
            console.log('Usuário criado:', data);
            alert('Usuário cadastrado com sucesso!');
            cadastroForm.reset();
            fetchAndRenderUsers();
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Ocorreu um erro no cadastro. Tente novamente.');
        });
    });
}

// -----------------------------------------------------------------------

// --- CÓDIGO PARA LISTAR, EDITAR E EXCLUIR USUÁRIOS ---

async function fetchAndRenderUsers() {
    try {
        const response = await fetch('http://localhost:8080/users');
        if (!response.ok) throw new Error('Erro ao buscar usuários: ' + response.statusText);

        const users = await response.json();
        renderUsersTable(users);
    } catch (error) {
        console.error('Erro:', error);
        alert('Não foi possível carregar a lista de usuários.');
    }
}

function renderUsersTable(users) {
    const tableBody = document.querySelector('#usersTable tbody');
    if (!tableBody) return;

    tableBody.innerHTML = '';
    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <button class = "doubleBtn editBtn" onclick="editUser(${user.id})">Editar</button>
                <button class = "doubleBtn exeBtn" onclick="deleteUser(${user.id})">Excluir</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function deleteUser(id) {
    if (!confirm('Tem certeza que deseja excluir este usuário?')) return;

    try {
        const response = await fetch(`http://localhost:8080/users/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Erro ao excluir usuário: ' + response.statusText);

        alert('Usuário excluído com sucesso!');
        fetchAndRenderUsers();
    } catch (error) {
        console.error('Erro:', error);
        alert('Não foi possível excluir o usuário.');
    }
}

// --- LÓGICA DE EDIÇÃO ---

const editFormContainer = document.getElementById('editFormContainer');
const editForm = document.getElementById('editForm');

function editUser(id) {
    // Pega os dados diretamente da tabela
    const row = [...document.querySelectorAll('#usersTable tbody tr')].find(
        tr => tr.children[0].textContent == id
    );

    if (!row) {
        alert('Usuário não encontrado.');
        return;
    }

    const user = {
        id: id,
        name: row.children[1].textContent,
        email: row.children[2].textContent
    };

    // Preenche o formulário de edição
    document.getElementById('edit-id').value = user.id;
    document.getElementById('edit-name').value = user.name;
    document.getElementById('edit-email').value = user.email;
    document.getElementById('edit-password').value = ''; // vazio por segurança

    // Alterna os formulários
    cadastroForm.style.display = 'none';
    editFormContainer.style.display = 'block';
}

if (editForm) {
    editForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const formData = new FormData(editForm);
        const userId = formData.get('id'); // CORREÇÃO: pega do input hidden

        const updatedUser = {
            name: formData.get('name'),
            email: formData.get('email'),
            password: formData.get('password') // pode ser vazio se não quiser atualizar
        };

        try {
            const response = await fetch(`http://localhost:8080/users/${userId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatedUser)
            });

            if (!response.ok) throw new Error('Erro ao atualizar usuário: ' + response.statusText);

            alert('Usuário atualizado com sucesso!');
            editFormContainer.style.display = 'none';
            cadastroForm.style.display = 'block';
            fetchAndRenderUsers();
        } catch (error) {
            console.error('Erro ao salvar alterações:', error);
            alert('Não foi possível atualizar o usuário.');
        }
    });
}

function cancelEdit() {
    editFormContainer.style.display = 'none';
    cadastroForm.style.display = 'block';
}

// Carrega a lista de usuários ao abrir a página
fetchAndRenderUsers();