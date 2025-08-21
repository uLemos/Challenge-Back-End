# Dashboard API (Desafio 01)

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-green?logo=spring&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-4.0-red?logo=apache-maven&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql&logoColor=white)

Esta é a API RESTful para o Desafio de Dashboard. Construída com Spring Boot, ela fornece os endpoints necessários para alimentar um front-end com dados de chamados de suporte, incluindo a lógica de negócio de agrupar os dados em memória, conforme o requisito.

Este projeto faz parte de um monorepo full-stack e é consumido pela aplicação `front-end`.

## ✨ Features

- **Endpoints Abrangentes:** Fornece endpoints para dados de dashboard, criação de tickets e consulta de dados mestre (clientes e módulos).
- **Lógica de Negócio em Memória:** Realiza a agregação de dados por cliente e módulo na camada de serviço, sem sobrecarregar o banco de dados.
- **Banco de Dados Automatizado:** Utiliza o Flyway para gerenciar o versionamento do schema do banco e para popular os dados iniciais de forma automática.
- **Validação Robusta:** Valida tanto os parâmetros de URL (`@RequestParam`) quanto os corpos de requisição (`@RequestBody`) para garantir a integridade dos dados.
- **Tratamento de Erros Centralizado:** Um `GlobalExceptionHandler` fornece respostas de erro JSON consistentes e profissionais.
- **Testes Unitários:** A lógica de negócio crítica na camada de serviço é coberta por testes unitários com JUnit 5 e Mockito.
- **Documentação Interativa:** A API é totalmente documentada com Swagger/OpenAPI, permitindo a exploração e o teste dos endpoints diretamente pelo navegador.

## 🛠️ Tecnologias Utilizadas

| Tecnologia            | Propósito                                    |
| :-------------------- | :------------------------------------------- |
| **Java 17**           | Linguagem de programação.                    |
| **Spring Boot 3.4.1** | Framework principal da API.                  |
| **Spring Data JPA**   | Camada de persistência de dados.             |
| **PostgreSQL 14**     | Banco de dados relacional.                   |
| **Flyway**            | Versionamento e migração do schema do banco. |
| **MapStruct**         | Mapeamento de DTOs para Entidades.           |
| **Springdoc-openapi** | Geração da documentação Swagger UI.          |
| **Maven**             | Gerenciamento de dependências e build.       |
| **Lombok**            | Redução de código boilerplate.               |

## 🚀 Como Executar em Modo de Desenvolvimento

Para rodar este serviço de forma isolada para desenvolvimento ou depuração.

### Pré-requisitos

- **Java (JDK) 17 ou superior**
- **Maven 3.8 ou superior**
- **PostgreSQL 14 ou superior**

### Configuração Inicial do Banco de Dados (Passo único)

1.  Garanta que você tem um servidor PostgreSQL rodando localmente (ex: em `localhost:5432`).
2.  Crie um novo banco de dados vazio com o nome `dashboard_db_dev`. Comando SQL:
    ```sql
    CREATE DATABASE dashboard_db_dev;
    ```
3.  Verifique se o usuário e senha no arquivo `dashboard-api/src/main/resources/application-dev.yml` correspondem às suas credenciais. O padrão no projeto é `username: postgres` e `password: admin`.

### Iniciando a Aplicação

- **Opção A: Com Makefile (Recomendado)**
  ```bash
  # Este comando reseta o schema e inicia a aplicação
  make dev-run-fresh
  ```
- **Opção B: Manualmente (Sem Makefile)**

  ```bash
  # 1. Resete o schema do banco (requer psql)
  psql -U postgres -d dashboard_db_dev -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"

  # 2. Inicie a aplicação com Maven
  mvn spring-boot:run
  ```

  A API estará disponível em `http://localhost:8080`.

## 🐳 Como Executar com Docker

Este serviço é parte de uma plataforma orquestrada pelo arquivo `docker-compose.yml` na raiz do monorepo. Para rodar a aplicação completa (Back-end, Front-end e Banco), consulte as instruções no `README.md` principal.

## 🧪 Como Rodar os Testes

Para executar a suíte de testes unitários, rode o seguinte comando na raiz desta pasta (`dashboard-api/`):

```bash
mvn test
```

## 📡 Documentação da API (Swagger)

Com a aplicação rodando, a documentação interativa da API está disponível em:

**`http://localhost:8080/swagger-ui.html`**

**Endpoints Principais:**

- `GET /api/dashboard`: Retorna os dados para a composição do dashboard.
- `POST /api/tickets`: Cria um novo ticket.
- `GET /api/clientes`: Retorna a lista de todos os clientes.
- `GET /api/modulos`: Retorna a lista de todos os módulos.

---

_Para informações sobre como rodar a plataforma completa com Docker, consulte o `README.md` na raiz do repositório._
