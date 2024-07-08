
# ForumHubChallenge

Bem-vindo ao ForumHubChallenge! Este projeto foi criado com o objetivo de replicar o funcionamento de um fórum no nível do back end, utilizando o framework Spring para construir uma API REST que gerencia tópicos, cursos e usuários.

## História

Um fórum é um espaço onde todos os participantes de uma plataforma podem colocar suas perguntas sobre determinados assuntos.
O objetivo com este desafio é implementar uma API REST com as seguintes funcionalidades:

- API com rotas implementadas seguindo as melhores práticas do modelo REST;
- Validações realizadas segundo as regras de negócio;
- Implementação de uma base de dados relacional para a persistência da informação;
- Serviço de autenticação/autorização para restringir o acesso à informação.

## Funcionalidades

A API fornece as seguintes funcionalidades para gerenciar tópicos, cursos e usuários:

### Tópicos

- **Criar um novo tópico**
- **Mostrar todos os tópicos criados**
- **Mostrar um tópico específico**
- **Atualizar um tópico**
- **Eliminar um tópico**

### Cursos

- **Criar um novo curso**
- **Mostrar todos os cursos criados**
- **Mostrar um curso específico**

### Usuários

- **Criar um novo usuário**
- **Mostrar todos os usuários criados**
- **Mostrar um usuário específico**

### Autenticação

- **Autenticar um usuário existente**
- **Autorização de acesso às funcionalidades da API**

## Estrutura do Projeto

### Controllers

- `AutenticacaoController.java`: Responsável pelas funcionalidades de autenticação e autorização dos usuários.
- `CursoController.java`: Gerencia as operações relacionadas aos cursos.
- `TopicoController.java`: Gerencia as operações relacionadas aos tópicos.
- `UsuarioController.java`: Gerencia as operações relacionadas aos usuários.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- JPA/Hibernate
- Banco de Dados Relacional

## Acesse a API:
- A API estará disponível em `http://localhost:8080`.

## Endpoints da API

### Tópicos

- **Criar um novo tópico**
  - **POST** `/topicos`
- **Mostrar todos os tópicos criados**
  - **GET** `/topicos`
- **Mostrar um tópico específico**
  - **GET** `/topicos/{id}`
- **Atualizar um tópico**
  - **PUT** `/topicos/{id}`
- **Eliminar um tópico**
  - **DELETE** `/topicos/{id}`

### Cursos

- **Criar um novo curso**
  - **POST** `/cursos`
- **Mostrar todos os cursos criados**
  - **GET** `/cursos`
- **Mostrar um curso específico**
  - **GET** `/cursos/{id}`

### Usuários

- **Criar um novo usuário**
  - **POST** `/usuarios`
- **Mostrar todos os usuários criados**
  - **GET** `/usuarios`
- **Mostrar um usuário específico**
  - **GET** `/usuarios/{id}`

### Autenticação

- **Autenticar um usuário existente**
  - **POST** `/autenticacao`
