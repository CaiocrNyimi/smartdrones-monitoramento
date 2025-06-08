# SmartDrones Monitoramento

Aplicação mobile para monitoramento de drones, sensores e suas leituras. Desenvolvido em Java Spring Boot.

## Pré-requisitos

### Backend
- Java 17+
- Maven 3.8+
- Banco de dados configurado (Utilizei Oracle)
- API rodando em `http://localhost:8080`
- Swagger em `http://localhost:8080/swagger-ui/index.html`

1. Clonando o Projeto

    git clone https://github.com/CaiocrNyimi/smartdrones-monitoramento.git
    cd smartdrones-monitoramento

2. Iniciando o Backend

    Importe o projeto Java no VS Code (ou uma IDE de sua preferência).

----------------------------------------------------------------------
    Verifique o arquivo application.properties para configurar o acesso ao  banco de dados (já tem que estar configurado corretamente).

    Execute a aplicação.
-----------------------------------------------------------------------

API estará disponível em: http://localhost:8080

3. Acesso ao Sistema

- Cadastro e Login

Cadastre um novo usuário no swagger.
Após o login, copie o token e cole no simbolo de cadeado para liberar acesso aos CRUDs.

4. Funcionalidades Disponíveis

- Listagem, cadastro, edição e exclusão de drones
- Listagem, cadastro, edição e exclusão de sensores
- Listagem, cadastro, edição e exclusão de leituras
- Autenticação com usuário e token JWT
- Navegação com React Navigation

5. Testes

Você pode testar:

- Criar e autenticar usuários;
- Cadastrar novos itens e verificar a listagem;
- Editar e excluir elementos.