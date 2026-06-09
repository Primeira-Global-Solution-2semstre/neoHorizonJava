# Neo Horizon API
## Links

Video Apresentação:
https://youtu.be/M_Y0cl_03Mo

Link da aplicação hospedada: 
https://neohorizonjava.onrender.com

Link do pitch:
https://youtu.be/huuaQSYgBt4

## Sobre o Projeto

Neo Horizon é uma API RESTful baseada em Spring Boot, projetada para a indústria aeroespacial. Ela fornece serviços essenciais para gerenciar e analisar dados orbitais, incluindo a previsão de possíveis colisões de objetos espaciais, a recomendação de janelas de lançamento ideais e o gerenciamento de um banco de dados de corpos celestes. A aplicação é protegida usando JWT e oferece documentação completa da API através do Swagger.

## Principais Funcionalidades

-   **Autenticação de Usuários**: Sistema seguro de registro e login de usuários utilizando JWT para autenticação baseada em token.
-   **Gerenciamento de Objetos Espaciais**: Funcionalidade completa de CRUD (Criar, Ler, Atualizar, Deletar) para gerenciar objetos espaciais, incluindo seus vetores de massa, posição, velocidade e aceleração.
-   **Previsão de Colisões**: Um modelo preditivo que simula trajetórias de objetos durante um determinado período para detectar possíveis eventos de colisão.
-   **Recomendação de Lançamento**: Um serviço que analisa a densidade de objetos em uma altitude desejada para calcular um índice de risco e recomendar uma data segura para lançamento.
-   **Documentação da API**: Interface Swagger UI integrada para fácil exploração e teste dos endpoints da API.
-   
## Endpoints da API

A API está estruturada em torno de vários recursos principais:

### Autenticação (`/auth`)

| Método | Endpoint         | Descrição                                      |
| :----- | :--------------- | :--------------------------------------------- |
| `POST` | `/auth/signup`   | Registra um novo usuário.                      |
| `POST` | `/auth/login`    | Autentica um usuário e retorna um JWT.         |

### Objeto Espacial (`/objeto`)

| Método   | Endpoint   | Descrição                                 |
| :------- | :--------- | :---------------------------------------- |
| `POST`   | `/`        | Cria um novo objeto espacial.             |
| `GET`    | `/?id={id}`  | Recupera um objeto espacial pelo seu ID.  |
| `PUT`    | `/`        | Atualiza um objeto espacial existente.    |
| `DELETE` | `/?id={id}` | Deleta um objeto espacial pelo seu ID.    |

### Predição (`/predict`)

| Método | Endpoint | Descrição                                                              |
| :----- | :------- | :--------------------------------------------------------------------- |
| `POST` | `/`      | Gera uma nova predição de colisão com base em um período e uma tag.    |
| `GET`  | `/`      | Lista todas as predições geradas anteriormente.                        |

### Recomendação (`/recommendation`)

| Método | Endpoint                             | Descrição                                                       |
| :----- | :----------------------------------- | :-------------------------------------------------------------- |
| `GET`  | `/?altitudeDesejada={altitude}` | Gera uma recomendação de lançamento com base na altitude desejada. |

## Construído Com

-   [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
-   [Spring Boot 3](https://spring.io/projects/spring-boot)
-   [Spring Security](https://spring.io/projects/spring-security)
-   [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
-   [Maven](https://maven.apache.org/)
-   [Swagger (OpenAPI 3)](https://swagger.io/)
-   [Oracle Database](https://www.oracle.com/database/)
-   [H2 Database](https://www.h2database.com/html/main.html)

## Getting Started

To get a local copy up and running, follow these simple steps.

## Começando

Para obter uma cópia local em funcionamento, siga estes passos simples.

### Pré-requisitos

-   Java Development Kit (JDK) 17 ou superior.
-   Apache Maven.

### Instalação e Configuração

1.  **Clone o repositório**
    ```sh
    git clone https://github.com/Primeira-Global-Solution-2semstre/neoHorizonJava.git
2.  **Navegue até o diretório do projeto**
    ```sh
    cd neoHorizonJava
    ```
3.  **Configure o banco de dados**
    A aplicação está configurada para se conectar a um banco de dados Oracle. Atualize o arquivo `src/main/resources/application.properties` com suas credenciais do banco de dados ou defina as seguintes variáveis de ambiente:
    -   `SPRING_DATASOURCE_URL`
    -   `SPRING_DATASOURCE_USERNAME`
    -   `SPRING_DATASOURCE_PASSWORD`
    ```properties
    spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:oracle:thin:@oracle.fiap.com.br:1521/ORCL}
    spring.datasource.username=${SPRING_DATASOURCE_USERNAME:your_username}
    spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:your_password}
    ```

### Executando a Aplicação

Use o wrapper do Maven para compilar e executar a aplicação:

```sh
./mvnw spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080.`

## Uso

Uma vez que a aplicação esteja em execução, você pode interagir com a API.

1.  **Acesse a Documentação da API**
    Abra seu navegador web e navegue até `http://localhost:8080/swagger-ui.html` para visualizar a documentação interativa da API.
    
2.  **Autenticação**
    -   Use o endpoint `/auth/signup` para criar uma nova conta de usuário.
    -   Use o endpoint `/auth/login` com suas credenciais para obter um token JWT.
    -   Clique no botão "Authorize" na interface do Swagger e insira seu token JWT no formato `Bearer {seu_token}` para autenticar suas requisições em endpoints protegidos.

3.  **Fazendo Requisições**
    Agora você pode usar a interface do Swagger para enviar requisições para os diversos endpoints, como criar objetos espaciais, gerar predições e obter recomendações de lançamento.
