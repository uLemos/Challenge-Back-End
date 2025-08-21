# Desafios Back-End

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-green?logo=spring&logoColor=white) ![React](https://img.shields.io/badge/React-18-blue?logo=react&logoColor=white) ![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?logo=typescript&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-20.10-blue?logo=docker&logoColor=white)

Este repositório contém uma plataforma de software completa, desenvolvida como solução para múltiplos desafios de desenvolvimento, integrando uma aplicação front-end com uma arquitetura de microserviços no back-end.

## 🎯 Desafios Implementados

1.  **Dashboard de Chamados (Full-Stack):** Uma aplicação completa com uma API back-end que fornece dados para um dashboard de chamados e um front-end interativo que consome e exibe esses dados com gráficos e tabelas.
2.  **API de Receitas (Proxy API):** Um microserviço back-end que atua como um proxy para uma API externa de receitas, expondo endpoints para busca e consulta de pratos disponíveis.

## ✨ Features Globais

- **Arquitetura de Monorepo:** Múltiplos projetos gerenciados em um único repositório Git.
- **Ambiente 100% Containerizado:** Configuração completa com Docker Compose para orquestrar toda a plataforma (Front-end, 2 Back-ends, Banco de Dados) com um único comando.
- **Fluxo de Trabalho Profissional:** Comandos simplificados com `Makefile`, estrutura de branches `main`/`develop` e testes unitários para a lógica de negócio crítica.
- **Documentação Completa:** Documentação interativa com Swagger para cada API e READMEs detalhados em cada nível do projeto.
- **Código de Alta Qualidade:** Projetos construídos com princípios de Clean Code, tratamento de erros centralizado e validação de entrada.

## 🛠️ Tecnologias Utilizadas

### Back-end

| Tecnologia                                                                               | Propósito                                    |
| :--------------------------------------------------------------------------------------- | :------------------------------------------- |
| [**Java 17**](https://www.oracle.com/java/)                                              | Linguagem de programação.                    |
| [**Spring Boot 3.4.1**](https://spring.io/projects/spring-boot)                          | Framework principal da API.                  |
| [**Spring Data JPA**](https://spring.io/projects/spring-data-jpa)                        | Camada de persistência de dados.             |
| [**Spring WebFlux**](https://docs.spring.io/spring-framework/reference/web/webflux.html) | Para chamadas reativas com `WebClient`.      |
| [**PostgreSQL 14**](https://www.postgresql.org/)                                         | Banco de dados relacional.                   |
| [**Flyway**](https://flywaydb.org/)                                                      | Versionamento e migração do schema do banco. |
| [**MapStruct**](https://mapstruct.org/)                                                  | Mapeamento de DTOs para Entidades.           |
| [**Springdoc-openapi**](https://springdoc.org/)                                          | Geração da documentação Swagger UI.          |
| [**Maven**](https://maven.apache.org/)                                                   | Gerenciamento de dependências e build.       |

### Front-end

| Tecnologia                                        | Propósito                                          |
| :------------------------------------------------ | :------------------------------------------------- |
| [**React 18**](https://react.dev/)                | Biblioteca para construção da UI.                  |
| [**TypeScript**](https://www.typescriptlang.org/) | Superset do JavaScript com tipagem estática.       |
| [**Vite**](https://vitejs.dev/)                   | Ferramenta de build e servidor de desenvolvimento. |
| [**Tailwind CSS**](https://tailwindcss.com/)      | Framework de estilização utility-first.            |
| [**Shadcn/ui**](https://ui.shadcn.com/)           | Biblioteca de componentes de UI.                   |
| [**Recharts**](https://recharts.org/)             | Biblioteca para criação dos gráficos.              |
| [**Nginx**](https://www.nginx.com/)               | Servidor web para a versão de produção.            |

## 📁 Estrutura do Projeto

```
challenge/
├── dashboard-api/      # Projeto Back-end (Desafio 1)
│   └── README.md
├── front-end/          # Projeto Front-end (UI para Desafio 1)
│   └── README.md
├── recipe-api/         # Projeto Back-end (Desafio 2)
│   └── README.md
├── .gitignore          # Regras globais para o repositório
├── docker-compose.yml  # Orquestração de todos os containers
└── README.md           # Esta documentação (Visão Geral)
```

## 🚀 Como Executar

Este projeto foi projetado para ser executado de duas maneiras principais, oferecendo flexibilidade para diferentes necessidades.

### 1. Ambiente Full-Stack com Docker (Método Recomendado)

Esta é a forma mais simples e garantida de rodar a plataforma completa, pois não exige nenhuma dependência além do Docker.

```bash
# Clone este repositório e entre na pasta raiz
git clone <url-do-repositorio>
cd challenge/

# Suba todos os containers com um único comando
docker-compose up --build
```

Após a inicialização, os serviços estarão disponíveis nos seguintes endereços:

| Serviço                                 | URL de Acesso                           |
| :-------------------------------------- | :-------------------------------------- |
| **Aplicação Front-end**                 | `http://localhost`                      |
| **Dashboard API (Swagger)**             | `http://localhost:8080/swagger-ui.html` |
| **Recipe API (Swagger)**                | `http://localhost:8081/swagger-ui.html` |
| **Banco de Dados (para DBeaver, etc.)** | `localhost:5433`                        |

Para parar tudo, pressione `Ctrl + C` no terminal e depois rode `docker-compose down -v`.

### 2. Ambiente de Desenvolvimento Local

Esta opção é útil para desenvolvimento ativo. Requer a instalação de todas as tecnologias (`Java`, `Node.js`, `PostgreSQL`, etc.) e o uso de **três terminais separados**.

_Para instruções detalhadas de como configurar e rodar cada serviço individualmente, consulte o `README.md` dentro da pasta de cada projeto._

## 🌟 Melhorias Futuras

- **Segurança:** Implementar uma camada de segurança com Spring Security e JWT.
- **Testes:** Adicionar mais testes de integração para validar o fluxo completo da API.
- **Paginação:** Implementar paginação no front-end para a tabela de tickets.
- **CI/CD:** Criar um pipeline de integração e entrega contínua para automatizar os builds e deploys.
