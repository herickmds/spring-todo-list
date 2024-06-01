
# Backend Todo-List

## Descrição
Este é o projeto de backend para um aplicativo de gerenciamento de tarefas. O backend foi desenvolvido utilizando Java com Spring Boot e segue boas práticas de desenvolvimento para garantir código limpo, seguro e escalável.

## Estrutura do Projeto
A estrutura do projeto é organizada da seguinte forma:

```
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dev/mrv/backend/
│   │   │       ├── config/               # Configurações do aplicativo
│   │   │       ├── controller/           # Controladores REST
│   │   │       ├── dto/                  # Objetos de Transferência de Dados
│   │   │       ├── exception/            # Exceções personalizadas
│   │   │       ├── mapper/               # Mapeamento entre entidades e DTOs
│   │   │       ├── model/                # Modelos de dados (Entidades)
│   │   │       ├── repository/           # Repositórios (Camada de acesso a dados)
│   │   │       ├── security/             # Configurações de segurança (JWT)
│   │   │       ├── service/              # Serviços de negócio
│   │   │       └── util/                 # Utilitários
│   │   └── resources/
│   │       ├── application.properties    # Configurações do Spring Boot
│   │       └── error-messages.properties # Mensagens de erro personalizadas
│   └── test/
│       └── java/com/dev/mrv/backend/     # Testes unitários e de integração
├── .gitignore                            # Arquivos a serem ignorados pelo Git
├── HELP.md                               # Ajuda sobre o projeto
├── mvnw                                  # Script Maven Wrapper
├── mvnw.cmd                              # Script Maven Wrapper para Windows
├── pom.xml                               # Configuração do Maven
└── README.md                             # Documentação do projeto
```

## Boas Práticas de Desenvolvimento

### Estrutura do Código
- **Separação de responsabilidades:** Utilize pacotes para organizar o código de acordo com a responsabilidade (ex: `controller`, `service`, `repository`, etc).
- **DTOs (Data Transfer Objects):** Use DTOs para transferir dados entre as camadas da aplicação, protegendo a integridade dos dados das entidades.

### Configuração e Segurança
- **Configurações centralizadas:** Use o arquivo `application.properties` para centralizar as configurações do aplicativo.
- **JWT para autenticação:** Implemente a segurança utilizando JWT (JSON Web Tokens) para autenticar e autorizar usuários.
- **Mensagens de erro:** Utilize um arquivo de propriedades (`error-messages.properties`) para gerenciar mensagens de erro personalizadas.

### Código Limpo
- **Mapeamento automático:** Use bibliotecas como MapStruct para mapear automaticamente entre entidades e DTOs.
- **Injeção de dependência:** Utilize o Spring para gerenciar dependências, facilitando a injeção de dependências e promovendo um código mais testável.

### Testes
- **Testes unitários e de integração:** Escreva testes para as camadas de serviço e mapeamento para garantir que o comportamento do código esteja conforme o esperado.
- **Cobertura de testes:** Garanta uma boa cobertura de testes para identificar e corrigir bugs precocemente.

## Instalação e Execução

### Pré-requisitos
- Java 11 ou superior
- Maven

### Passos para execução
1. **Clone o repositório:**
    ```sh
    git clone https://github.com/herickmds/spring-todo-list.git
    cd seu-repositorio-backend
    ```

2. **Instale as dependências:**
    ```sh
    ./mvnw clean install
    ```

3. **Execute a aplicação:**
    ```sh
    ./mvnw spring-boot:run
    ```

4. **Acesse a aplicação:**
    A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

## Endpoints Principais
- **Autenticação:**
  - `POST /authenticate` - Autenticar um usuário
- **Usuários:**
  - `POST /register` - Registrar um novo usuário
- **Tarefas:**
  - `GET /tarefas` - Listar todas as tarefas
  - `POST /tarefas` - Criar uma nova tarefa
  - `PUT /tarefas/{id}` - Atualizar uma tarefa existente
  - `DELETE /tarefas/{id}` - Excluir uma tarefa

## Contribuição
Se você deseja contribuir com o projeto, siga os passos abaixo:

1. Faça um fork do projeto.
2. Crie uma nova branch (`git checkout -b feature/nova-feature`).
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Envie para o repositório remoto (`git push origin feature/nova-feature`).
5. Abra um pull request.

## Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

---
Criado por Herick Marçal dos Santos
