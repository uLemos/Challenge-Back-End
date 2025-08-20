# Desafio - API de Dashboard de Chamados

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0-red?logo=apache-maven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-20.10-blue?logo=docker&logoColor=white)

API RESTful desenvolvida como solução para o desafio de Back-end. A aplicação fornece dados para um dashboard de chamados de suporte, permitindo a consulta de tickets e a criação de novos registros.

Um dos requisitos centrais do desafio é que o agrupamento de dados (por cliente e por módulo) seja realizado na lógica da aplicação (em memória), e não diretamente no banco de dados via SQL.

## ✨ Features

- **Listagem de Tickets**: Retorna a lista de tickets para um determinado mês e ano.
- **Dados Agrupados**: Retorna a contagem de tickets agrupados por cliente e por módulo.
- **Criação de Tickets**: Permite a criação de novos tickets de suporte.
- **Ambiente Containerizado**: Configuração completa com Docker e Docker Compose para um ambiente de produção consistente.
- **Setup Automatizado**: Banco de dados e carga de dados iniciais gerenciados via Flyway.
- **Comandos Simplificados**: Um `Makefile` para facilitar a execução, build e gerenciamento dos ambientes.

## 🛠️ Tecnologias Utilizadas

| Tecnologia                  | Propósito                                                          |
| :-------------------------- | :----------------------------------------------------------------- |
| **Java 17**                 | Linguagem de programação principal.                                |
| **Spring Boot 3.x**         | Framework principal para construção da API.                        |
| **Spring Data JPA**         | Camada de persistência de dados.                                   |
| **Maven**                   | Gerenciamento de dependências e build.                             |
| **PostgreSQL 14**           | Banco de dados relacional.                                         |
| **Flyway**                  | Ferramenta para versionamento e migração do schema do banco.       |
| **Docker & Docker Compose** | Containerização da aplicação e do banco para ambiente de produção. |
| **MapStruct**               | Mapeamento de DTOs para Entidades.                                 |
| **Lombok**                  | Redução de código boilerplate.                                     |
| **Makefile**                | Automação de comandos do projeto.                                  |

## 📁 Estrutura do Projeto

O projeto está organizado em uma estrutura de monorepo, separando o back-end e um placeholder para o front-end.

```
challenge/
├── dashboard-api/      # Projeto Back-end Spring Boot (código-fonte)
└── front-end/          # (Placeholder para futura aplicação front-end)
```

A arquitetura interna do back-end segue os princípios de Clean Code, com separação clara de responsabilidades:

- `application/services`: Camada de lógica de negócio.
- `domain/entitys`: Entidades do banco de dados (JPA).
- `domain/repositories`: Repositórios Spring Data JPA.
- `infrastructure/config`: Configurações do Spring (CORS).
- `mappers`: Conversores de Entidade para DTO (MapStruct).
- `web/controllers`: Camada da API REST.
- `web/dtos`: Objetos de Transferência de Dados.

## ⚙️ Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas:

- **Java (JDK) 17 ou superior**
- **Maven 3.8 ou superior**
- **PostgreSQL 14 ou superior** (apenas para o ambiente local)
- **Cliente `psql`** (para o reset do banco local)
- **Docker e Docker Compose** (para o ambiente de produção)
- **Make** (opcional, para usar os comandos simplificados)

## 🚀 Como Executar

Você pode rodar o projeto de duas maneiras. Usar os comandos do `Makefile` é o método recomendado por ser mais simples.

### 1. Ambiente de Desenvolvimento Local (Perfil `dev`)

Esta opção utiliza o Java, Maven e PostgreSQL instalados na sua máquina.

**Método Recomendado (com Makefile):**

```bash
# Clone o repositório e entre na pasta da API
git clone <url-do-repositorio>
cd challenge/dashboard-api

# Reseta o banco de dados local e inicia a aplicação
# (Certifique-se que seu banco 'dashboard_db_dev' existe e as credenciais em
# application-dev.yml estão corretas)
make dev-run-fresh
```

**Ou com os Comandos Padrões (sem Makefile):**

```bash
# 1. Navegue até a pasta da API
cd challenge/dashboard-api

# 2. Resete o banco de dados manualmente (requer o cliente psql)
# Lembre-se de substituir o usuário 'postgres' se o seu for diferente
psql -U postgres -d dashboard_db_dev -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"

# 3. Inicie a aplicação com Maven
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### 2. Ambiente Docker (Perfil `prod`)

Esta é a forma mais simples, pois não exige nada além do Docker.

**Método Recomendado (com Makefile):**

```bash
# Clone o repositório e entre na pasta da API
git clone <url-do-repositorio>
cd challenge/dashboard-api

# Constrói a imagem e inicia os containers em background
make docker-up
```

**Ou com o Comando Padrão (sem Makefile):**

```bash
# Navegue até a pasta da API
cd challenge/dashboard-api

# Constrói a imagem e inicia os containers em background
docker-compose up --build -d
```

A API estará disponível em `http://localhost:8080`. Para parar os containers, use `make docker-down` ou `docker-compose down`.

## 📡 Endpoints da API

### 1. Obter Dados do Dashboard

Retorna a lista de tickets e os dados agrupados por cliente e módulo para um mês/ano específico.

- **Método:** `GET`
- **URL:** `/api/dashboard`
- **Parâmetros:**
  - `ano` (int, obrigatório): O ano desejado.
  - `mes` (int, obrigatório): O mês desejado.
- **Exemplo:** `http://localhost:8080/api/dashboard?ano=2021&mes=3`
- **Resposta de Sucesso (200 OK):**
  ```json
  {
    "listaTickets": [
      {
        "id": 1,
        "titulo": "Mussum Ipsum cacilds vidis litro",
        "cliente": "Apple Inc.",
        "modulo": "Financeiro",
        "dataAbertura": "2021-03-01T00:00:00",
        "dataEncerramento": "2021-03-02T00:00:00"
      }
    ],
    "agrupadoPorCliente": {
      "Apple Inc.": 33,
      "Google": 45,
      "Tesla": 27,
      "Microsoft": 46,
      "SpaceX": 25
    },
    "agrupadoPorModulo": {
      "Financeiro": 57,
      "Expedição": 33,
      "Vendas": 45,
      "Foguetes": 41
    }
  }
  ```

### 2. Criar Novo Ticket

Cria um novo registro de ticket.

- **Método:** `POST`
- **URL:** `/api/tickets`
- **Corpo da Requisição (JSON):**
  ```json
  {
    "titulo": "Meu novo ticket de teste",
    "clienteId": 1,
    "moduloId": 2
  }
  ```
- **Resposta de Sucesso (201 Created):**
  ```json
  {
    "id": 201,
    "titulo": "Meu novo ticket de teste",
    "cliente": "Apple Inc.",
    "modulo": "Vendas",
    "dataAbertura": "2025-08-19T23:25:00",
    "dataEncerramento": null
  }
  ```

## 🌟 Melhorias Futuras

- **Segurança:** Implementar uma camada de segurança com Spring Security e JWT para proteger os endpoints.
- **Testes:** Adicionar testes unitários (para a camada de serviço) e de integração (para a camada de controller) para garantir a qualidade e a estabilidade do código.
- **Paginação:** Implementar paginação no endpoint de listagem de tickets para lidar com grandes volumes de dados.
