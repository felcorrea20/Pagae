# Casos de Uso - Pagae

### **UC-01: Registrar Novo Usuário**

* **Ator Principal:** Usuário não-cadastrado.
* **Objetivo:** Criar uma nova conta no aplicativo Pagae.
* **Pré-condição:** O usuário não deve ter uma conta existente.
* **Fluxo Principal:**
    1.  O usuário acessa a tela de cadastro.
    2.  O sistema exibe o formulário de cadastro.
    3.  O usuário preenche os campos com e-mail e senha.
    4.  O sistema valida os dados (ex: e-mail em formato correto, senha com requisitos mínimos).
    5.  O sistema cria a nova conta do usuário.
    6.  O sistema redireciona o usuário para o menu principal.
* **Fluxo Alternativo:**
    * **FA-01a: E-mail já cadastrado:**
        1.  No passo 4, o sistema detecta que o e-mail já está em uso.
        2.  O sistema exibe uma mensagem de erro informando que o e-mail já possui uma conta.
        3.  O usuário pode tentar um e-mail diferente ou usar a opção de login.
* **Pós-condição:** O usuário agora tem uma conta e está logado no sistema.

### **UC-02: Autenticar Usuário**

* **Ator Principal:** Usuário cadastrado.
* **Objetivo:** Entrar no aplicativo para acessar suas funcionalidades.
* **Pré-condição:** O usuário deve ter uma conta ativa.
* **Fluxo Principal:**
    1.  O usuário acessa a tela de login.
    2.  O usuário insere seu e-mail e senha.
    3.  O sistema valida as credenciais.
    4.  O sistema concede acesso ao usuário e o redireciona para o menu principal.
* **Fluxo Alternativo:**
    * **FA-02a: Credenciais incorretas:**
        1.  No passo 3, o sistema não encontra a combinação de e-mail e senha.
        2.  O sistema exibe uma mensagem de erro de credenciais inválidas.
        3.  O usuário pode tentar novamente ou usar a opção "Esqueci minha senha".
* **Pós-condição:** O usuário está logado no sistema.

### **UC-03: Criar um Grupo de Despesas**

* **Ator Principal:** Usuário logado.
* **Objetivo:** Iniciar um novo grupo para registrar e dividir gastos.
* **Pré-condição:** O usuário deve estar logado.
* **Fluxo Principal:**
    1.  O usuário navega para a tela de "Meus Grupos".
    2.  O usuário seleciona a opção para criar um novo grupo.
    3.  O sistema exibe um formulário de criação de grupo.
    4.  O usuário preenche o nome do grupo e outras informações.
    5.  O sistema cria o grupo e o exibe na lista do usuário.
* **Pós-condição:** Um novo grupo foi criado, e o usuário é o único membro.

### **UC-04: Registrar uma Nova Despesa**

* **Ator Principal:** Usuário de um grupo.
* **Objetivo:** Adicionar um gasto para ser dividido entre os membros do grupo.
* **Pré-condição:** O usuário deve ser membro de pelo menos um grupo.
* **Fluxo Principal:**
    1.  O usuário seleciona um grupo.
    2.  O usuário escolhe a opção para adicionar uma despesa.
    3.  O sistema exibe um formulário de registro de despesa.
    4.  O usuário preenche os dados da despesa (valor, descrição, data, quem pagou).
    5.  O sistema valida os dados e registra a despesa.
    6.  O sistema exibe a nova despesa na lista do grupo.
* **Fluxo Alternativo:**
    * **FA-04a: Dados incompletos:**
        1.  No passo 5, o sistema detecta que o valor da despesa não foi preenchido.
        2.  O sistema exibe uma mensagem de erro pedindo para o usuário preencher os campos obrigatórios.
* **Pós-condição:** A despesa está registrada no grupo, e os valores devidos são atualizados.

### **UC-05: Visualizar Detalhes de um Grupo**

* **Ator Principal:** Usuário de um grupo.
* **Objetivo:** Ver o histórico de despesas e os saldos dos membros de um grupo.
* **Pré-condição:** O usuário deve estar logado e ser membro de um grupo.
* **Fluxo Principal:**
    1.  O usuário seleciona um grupo na tela "Meus Grupos".
    2.  O sistema exibe o nome do grupo, a lista de despesas e os membros.
    3.  O sistema exibe o saldo de cada membro (quem deve ou para quem deve).
    4.  O usuário pode interagir com a lista de despesas.
* **Pós-condição:** O usuário tem uma visão clara da situação financeira do grupo.