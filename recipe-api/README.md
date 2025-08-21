# Recipe Search API (Desafio 02)

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-green?logo=spring&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-4.0-red?logo=apache-maven&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-20.10-blue?logo=docker&logoColor=white)

API RESTful desenvolvida como solução para o segundo desafio. Este microserviço atua como uma fachada (proxy) para a API externa [Forkify API](https://forkify-api.herokuapp.com/), permitindo a busca de receitas por prato.

## ✨ Features

- **Proxy de API:** Encapsula a chamada para a API externa, desacoplando o cliente final dos detalhes da fonte de dados.
- **Busca de Receitas:** Endpoint para pesquisar receitas com base em um termo (prato).
- **Descoberta de Termos:** Endpoint que fornece uma lista de termos de busca válidos, melhorando a experiência do usuário e do desenvolvedor.
- **Programação Reativa:** Utiliza `WebClient` e `Mono` para chamadas HTTP assíncronas e não-bloqueantes, garantindo alta performance e escalabilidade.
- **Validação de Entrada:** Protege os endpoints contra dados inválidos.
- **Documentação Interativa:** API totalmente documentada com Swagger/OpenAPI.
- **Tratamento de Erros:** Handler global para exceções, garantindo respostas de erro consistentes e profissionais.

## 🛠️ Tecnologias Utilizadas

| Tecnologia            | Propósito                               |
| :-------------------- | :-------------------------------------- |
| **Java 17**           | Linguagem de programação.               |
| **Spring Boot 3.4.1** | Framework principal da API.             |
| **Spring WebFlux**    | Para chamadas reativas com `WebClient`. |
| **Springdoc-openapi** | Geração da documentação Swagger UI.     |
| **Maven**             | Gerenciamento de dependências e build.  |
| **Lombok**            | Redução de código boilerplate.          |

## 🚀 Como Executar

Esta API pode ser executada de duas maneiras: localmente para desenvolvimento ou como parte da plataforma completa via Docker.

### 1. Ambiente de Desenvolvimento Local

Esta opção é útil para focar no desenvolvimento deste microserviço específico.

1.  **Pré-requisitos:** Java 17 e Maven 3.8+ instalados.
2.  Navegue até a pasta deste projeto:
    ```bash
    cd challenge/recipe-api
    ```
3.  Inicie a aplicação com o Maven:
    ```bash
    mvn spring-boot:run
    ```

_A API estará disponível em `http://localhost:8081`._

### 2. Ambiente Full-Stack com Docker (Recomendado)

A forma mais simples de rodar esta API junto com todo o ecossistema é utilizando o `docker-compose.yml` que se encontra na raiz do repositório.

1.  **Pré-requisitos:** Docker e Docker Compose instalados.
2.  Navegue até a **pasta raiz do projeto**:
    ```bash
    cd challenge/
    ```
3.  Suba toda a plataforma:
    ```bash
    docker-compose up --build
    ```

_A API estará disponível em `http://localhost:8081`._

## 📡 Documentação da API (Swagger)

Com a aplicação rodando (em qualquer um dos modos), a documentação interativa e completa da API está disponível em:

**`http://localhost:8081/swagger-ui.html`**

**Endpoints Principais:**

- `GET /api/recipes/search`: Busca receitas com base em um parâmetro `dish`.
- `GET /api/recipes/available-dishes`: Retorna uma lista de todos os termos de busca válidos.

---

_Para informações sobre como rodar a plataforma completa com Docker, consulte o `README.md` na raiz do repositório._
