# Sistema de gestão de carro de usuários

## ESTÓRIAS DE USUÁRIO

### Estória de Usuário 1: Configuração do projeto

**Como** desenvolvedor,
**quero** ter um ambiente de desenvolvimento configurado no backend e frontend
**para que** eu possa desenvolver a aplicação RESTful juntamente com a aplicação web.

**Critérios de Aceitação:**

- O backend deve ser desenvolvido no mínimo Java 8 com o framework Spring
- O Banco de dados deve ser em memória
- A Persistência de dados deve ser feita com JPA/Hibernate
- O frontend deve ser desenvolvido com o framework Angular

### Estória de Usuário 2: Cadastro de Novo Usuário

**Como** usuário,
**quero** poder criar um novo usuário na aplicação
**para que** eu possa acessar a aplicação e realizar operações.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/users` que aceite um objeto com os detalhes do novo usuário (nome, sobrenome, e-mail, data de nascimento, login, senha, telefone e lista de carros).
- Após o cadastro bem-sucedido, o servidor deve retornar as informações do novo usuário.

### Estória de Usuário 3: Listagem de Usuários

**Como** usuário,
**quero** poder visualizar uma lista de todos os usuários registrados na aplicação
**para que** eu possa ver informações de todos usuários cadastrados.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/users` que retorne uma lista de todos os usuários.

### Estória de Usuário 4: Autenticação e Obtenção de Token

**Como** usuário,
**quero** poder me autenticar na aplicação
**para que** eu possa acessar minhas informações pessoais e realizar operações protegidas.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/signin` que aceite um objeto com os campos `login` e `password`.
- Após a autenticação bem-sucedida, o servidor deve retornar um token de acesso JWT contendo as informações do usuário logado.
- O token de acesso deve ser usado nas requisições subsequentes para autenticar o usuário.

### Estória de Usuário 5: Busca de Usuário por ID

**Como** usuário,
**quero** poder buscar informações de um usuário específico pela sua identificação
**para que** eu possa ver detalhes sobre um usuário específico.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/users/{id}` que retorne as informações de um usuário específico, onde `{id}` é o identificador do usuário.

### Estória de Usuário 6: Remoção de Usuário por ID

**Como** usuário,
**quero** poder remover usuários pelo identificador único
**para que** esse usuário não faça mais parte da aplicação.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/users/{id}` que permita a remoção de um usuário específico, onde `{id}` é o identificador do usuário.
- Após a remoção bem-sucedida, o servidor deve retornar uma confirmação de que o usuário foi removido.

### Estória de Usuário 7: Atualização de Informações do Usuário

**Como** usuário,
**quero** poder atualizar informações de um usuário pelo identificador único
**para que** o sistema possa manter os dados atualizados.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/users/{id}` que aceite um objeto com os detalhes de um usuário já exisitente e efetue a atualização das informações, onde `{id}` é o identificador do usuário.
- Após a atualização bem-sucedida, o servidor deve retornar as informações atualizadas do usuário.

### Estória de Usuário 8: Visualização de Informações do Usuário Logado

**Como** usuário já logado,
**quero** poder visualizar minhas informações pessoais
**para que** eu possa verificar e gerenciar minhas informações de perfil.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/me` que retorne as informações do usuário logado, incluindo `firstName`, `lastName`, `email`, `birthday`, `login`, `phone`, `cars`, `createdAt` (data de criação do usuário) e `lastLogin` (data da última vez que o usuário realizou login).
- O endpoint deve ser acessível apenas para usuários autenticados com o token de acesso JWT via header `Authorization`.

### Estória de Usuário 9: Listagem de Carros do Usuário Logado

**Como** usuário já logado,
**quero** poder visualizar uma lista de todos os meus carros
**para que** eu possa ver meus veículos cadastrados.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/cars` que retorne uma lista de todos os carros do usuário logado.
- O endpoint deve ser acessível apenas para usuários autenticados com o token de acesso JWT via header `Authorization`.

### Estória de Usuário 10: Cadastro de Novo Carro para o Usuário Logado

**Como** usuário já logado,
**quero** poder adicionar um novo carro à minha frota
**para que** eu possa ter mais carros na minha frota.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/cars` que aceite um objeto com os detalhes do novo carro (ano, placa, modelo, cor) e associe-o ao usuário logado.
- O endpoint deve ser acessível apenas para usuários autenticados com o token de acesso JWT via header `Authorization`.
- Após o cadastro bem-sucedido, o servidor deve retornar as informações do novo carro, incluindo o ID do usuário associado.

### Estória de Usuário 11: Busca de Carro do Usuário Logado pelo ID

**Como** usuário já logado,
**quero** poder buscar informações detalhadas de um carro específico da minha frota
**para que** eu possa visualizar as informações de meus carros.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/cars/{id}` que retorne as informações de um carro específico do usuário logado, onde `{id}` é o identificador do carro.
- O endpoint deve ser acessível apenas para usuários autenticados com o token de acesso JWT via header `Authorization`.
- Após a autenticação bem-sucedida, o servidor deve retornar as informações do carro especificado.

### Estória de Usuário 12: Remoção de Carro do Usuário Logado pelo ID

**Como** usuário já logado,
**quero** poder remover um carro da minha frota
**para que** eu possa manter minha coleção de veículos atualizada.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/cars/{id}` que permita a remoção de um carro específico do usuário logado, onde `{id}` é o identificador do carro.
- O endpoint deve ser acessível apenas para usuários autenticados com o token de acesso JWT via header `Authorization`.
- Após a remoção bem-sucedida, o servidor deve retornar uma confirmação de que o carro foi removido.

### Estória de Usuário 13: Atualização de Informações de um Carro do Usuário Logado

**Como** usuário já logado,
**quero** poder atualizar as informações de um carro específico da minha frota
**para que** eu possa manter minhas informações de veículos atualizadas.

**Critérios de Aceitação:**

- A aplicação deve fornecer um endpoint `/api/cars/{id}` que permita a atualização das informações de um carro específico do usuário logado, onde `{id}` é o identificador do carro.
- O endpoint deve ser acessível apenas para usuários autenticados com o token de acesso JWT via header `Authorization`.
- Após a atualização bem-sucedida, o servidor deve retornar as informações atualizadas do carro especificado.

## SOLUÇÃO

Este projeto foi desenvolvido com o objetivo de criar uma aplicação que gerencia usuários e carros, com autenticação via JWT (JSON Web Tokens). A escolha de tecnologias e abordagens foi feita com base em requisitos específicos, como a necessidade de um servidor embutido, persistência com JPA/Hibernate, e a utilização de um banco de dados em memória para facilitar o desenvolvimento e testes.

### Justificativas e Defesa Técnica

- **Spring Framework**: Escolhido por sua robustez, facilidade de integração com outras tecnologias, e suporte a JPA/Hibernate, tornando a persistência de dados mais simples e eficiente.
- **JWT para Autenticação**: JWT oferece uma maneira segura e eficiente de autenticar usuários, permitindo a criação de tokens de acesso que podem ser facilmente validados.
- **Banco de Dados H2**: Utilizado para facilitar o desenvolvimento e testes, pois é um banco de dados em memória que não requer configuração adicional e é rápido para operações de leitura e escrita.
- **Angular para Frontend**: Escolhido por sua capacidade de criar interfaces de usuário dinâmicas, além de sua facilidade em integrar com o backend.
- **JPA/Hibernate**: Para a persistência de dados, oferecendo uma camada de abstração que simplifica a interação com o banco de dados, permitindo que o desenvolvedor se concentre na lógica de negócios.

## Como Executar o Projeto

### Pré-requisitos

- Java 8 ou superior
- Maven 3.x
- Node.js e npm (para o frontend Angular)

### Passos para Executar o Backend

1. Navegue até o diretório `/backend` do projeto.
2. Execute o comando `mvn clean install` para construir o projeto e instalar as dependências.
3. Execute o comando `mvn spring-boot:run` para iniciar o servidor Spring Boot.

### Passos para Executar o Frontend

1. Navegue até o diretório `/frontend` do projeto.
2. Execute o comando `npm install` para instalar as dependências do projeto Angular.
3. Execute o comando `npm run start` para iniciar o servidor de desenvolvimento Angular.

### Testes

Para executar os testes unitários do backend, navegue até o diretório `/backend` e execute o comando `mvn test`.
