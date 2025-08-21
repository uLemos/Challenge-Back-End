# Desafio Full-Stack: Dashboard de Chamados

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-green?logo=spring&logoColor=white) ![React](https://img.shields.io/badge/React-18-blue?logo=react&logoColor=white) ![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?logo=typescript&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-20.10-blue?logo=docker&logoColor=white)

Aplicação Full-Stack desenvolvida como solução para o desafio. A plataforma consiste em uma **API RESTful** construída com Spring Boot e um **Front-end interativo** construído com React, que consome a API para exibir um dashboard de chamados de suporte.

O requisito central do back-end é que o agrupamento de dados (por cliente e por módulo) seja realizado na lógica da aplicação (em memória), e não diretamente no banco de dados via SQL.

## ✨ Features

- **API Robusta:** Back-end completo com endpoints para consulta e criação de dados.
- **Dashboard Interativo:** Front-end com gráficos e tabelas que reagem dinamicamente aos filtros do usuário.
- **Ambiente 100% Containerizado:** Configuração completa com Docker Compose para orquestrar o Front-end (Nginx), Back-end (Java) e Banco de Dados (PostgreSQL).
- **Setup Automatizado:** Banco de dados e carga de dados iniciais gerenciados via Flyway.
- **Documentação Interativa:** API documentada com Swagger/OpenAPI.
- **Qualidade de Código:** Testes unitários na lógica de negócio e tratamento de erros centralizado.
- **Estrutura de Branches Profissional:** Branches `main` e `develop` separadas, prontas para um fluxo de trabalho de CI/CD.
- **Fluxo de Trabalho Simplificado:** Um `Makefile` para facilitar a execução, build e gerenciamento dos ambientes.

## 🛠️ Tecnologias Utilizadas

### Back-end

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

### Front-end

| Tecnologia       | Propósito                                            |
| :--------------- | :--------------------------------------------------- |
| **React 18**     | Biblioteca para construção da UI.                    |
| **TypeScript**   | Superset do JavaScript com tipagem estática.         |
| **Vite**         | Ferramenta de build e servidor de desenvolvimento.   |
| **Tailwind CSS** | Framework de estilização utility-first.              |
| **Shadcn/ui**    | Biblioteca de componentes de UI.                     |
| **Recharts**     | Biblioteca para criação dos gráficos.                |
| **Nginx**        | Servidor web para a versão de produção do front-end. |

## 📁 Estrutura do Projeto

O projeto está organizado em um monorepo, com o back-end e o front-end em diretórios separados na raiz.

```
challenge/
├── dashboard-api/      # Projeto Back-end (Spring Boot)
├── front-end/          # Projeto Front-end (React + Vite)
├── .gitignore          # Regras globais para o repositório
├── docker-compose.yml  # Orquestração de todos os containers
└── README.md           # Esta documentação
```

## ⚙️ Pré-requisitos

- **Docker e Docker Compose** (para o método de execução recomendado).
- **Java (JDK) 17, Maven 3.8+, Node.js 20+** (para o ambiente de desenvolvimento local).
- **Make** (opcional, para usar os atalhos do `Makefile`).

## 🚀 Como Executar

### 1. Ambiente Full-Stack com Docker (Método Recomendado)

Esta é a forma mais simples e garantida de rodar a aplicação completa, pois não exige nenhuma outra dependência além do Docker.

```bash
# Clone este repositório
git clone <url-do-repositorio>
cd challenge/

# Suba todos os containers (Front, Back e Banco) com um único comando
# O --build garante que as imagens serão construídas do zero na primeira vez
docker-compose up --build
```

Após a inicialização, acesse:

- **Aplicação Front-end:** `http://localhost`
- **Documentação da API (Swagger):** `http://localhost:8080/swagger-ui.html`

### 2. Ambiente de Desenvolvimento Local

Esta opção é útil para desenvolvimento ativo e requer que as ferramentas (Java, Maven, Node, Postgres) estejam instaladas na sua máquina.

**Configuração Inicial do Banco de Dados (Passo único):**

Antes de rodar o back-end pela primeira vez, você precisa garantir que o banco de dados local esteja pronto:

1.  Garanta que você tem um servidor PostgreSQL rodando em `localhost:5432`.
2.  Crie um novo banco de dados vazio com o nome `dashboard_db_dev`. Você pode usar o comando SQL:
    ```sql
    CREATE DATABASE dashboard_db_dev;
    ```
3.  Verifique se o usuário e senha no arquivo `dashboard-api/src/main/resources/application-dev.yml` correspondem às suas credenciais do PostgreSQL. O padrão no projeto é `username: postgres` e `password: admin`.

Com o banco de dados criado, você pode iniciar as aplicações. Requer dois terminais.

**Terminal 1 - Rodando o Back-end:**

- **Opção A: Com Makefile (Recomendado)**
  ```bash
  cd challenge/dashboard-api
  make dev-run-fresh
  ```
- **Opção B: Manualmente (Sem Makefile)**
  ```bash # 1. Resete o schema do banco de dados (requer psql)
  psql -U postgres -d dashboard_db_dev -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"
      # 2. Inicie a aplicação
      cd challenge/dashboard-api
      mvn spring-boot:run
      ```
  _A API estará disponível em `http://localhost:8080`._

**Terminal 2 - Rodando o Front-end:**

```bash
cd challenge/front-end

# Instala as dependências (apenas na primeira vez)
npm install

# Inicia o servidor de desenvolvimento do Vite
npm run dev
```

_A aplicação estará disponível em `http://localhost:5173` (ou a porta indicada pelo Vite)._

## 📡 Documentação da API (Swagger)

Com a aplicação rodando, a documentação interativa da API está disponível em:

**`http://localhost:8080/swagger-ui.html`**

Nela, você pode ver todos os endpoints, seus parâmetros, schemas de resposta e testá-los diretamente.

**Endpoints Principais:**

- `GET /api/dashboard`: Retorna os dados para a composição do dashboard.
- `POST /api/tickets`: Cria um novo ticket.
- `GET /api/clientes`: Retorna a lista de todos os clientes.
- `GET /api/modulos`: Retorna a lista de todos os módulos.

## 🌟 Melhorias Futuras

- **Segurança:** Implementar uma camada de segurança com Spring Security e JWT.
- **Testes:** Adicionar mais testes de integração para validar o fluxo completo da API.
- **Paginação:** Implementar paginação no front-end para a tabela de tickets.
- **CI/CD:** Criar um pipeline de integração e entrega contínua para automatizar os builds e deploys.
