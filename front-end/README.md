# Front-end: Dashboard de Chamados

![React](https://img.shields.io/badge/React-18-blue?logo=react&logoColor=white) ![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?logo=typescript&logoColor=white) ![Vite](https://img.shields.io/badge/Vite-5-purple?logo=vite&logoColor=white) ![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-3-blue?logo=tailwindcss&logoColor=white)

Esta é a aplicação de front-end para o Desafio de Dashboard. Construída com React e TypeScript, esta Single Page Application (SPA) consome a `dashboard-api` para renderizar uma interface de usuário interativa, seguindo fielmente o protótipo do desafio.

## ✨ Features

- **Dashboard Interativo:** Exibe gráficos de pizza e uma tabela detalhada com os dados dos chamados.
- **Filtragem Dinâmica:** Permite ao usuário filtrar os dados exibidos por mês e ano, com a interface se atualizando em tempo real.
- **Componentização Moderna:** Utiliza uma stack de UI moderna com Tailwind CSS para estilização e Shadcn/ui para componentes acessíveis e bem estruturados.
- **Tipagem Estática:** O uso de TypeScript garante um código mais seguro, legível e de fácil manutenção.

## 🛠️ Tecnologias Utilizadas

| Tecnologia       | Propósito                                                              |
| :--------------- | :--------------------------------------------------------------------- |
| **React 18**     | Biblioteca principal para a construção da UI.                          |
| **TypeScript**   | Linguagem para adicionar tipagem estática ao JavaScript.               |
| **Vite**         | Ferramenta de build e servidor de desenvolvimento de alta performance. |
| **Tailwind CSS** | Framework CSS utility-first para estilização rápida e customizável.    |
| **Shadcn/ui**    | Biblioteca de componentes de UI reusáveis.                             |
| **Recharts**     | Biblioteca para a renderização dos gráficos de pizza.                  |

## 🚀 Como Executar em Modo de Desenvolvimento

Para rodar este projeto localmente, siga os passos abaixo.

### Pré-requisitos

- **Node.js v20+**
- **npm** ou um gerenciador de pacotes compatível

### Passos

1.  **Inicie o Back-end**
    Esta aplicação front-end depende da `dashboard-api` para funcionar. Garanta que o back-end esteja rodando. Você pode iniciá-lo localmente ou via Docker, conforme as instruções no `README.md` principal do repositório. A API deve estar respondendo em `http://localhost:8080`.

2.  **Instale as Dependências do Front-end**
    Na primeira vez que for rodar o projeto, instale as dependências. No terminal, dentro da pasta `front-end/`:

    ```bash
    npm install
    ```

3.  **Inicie o Servidor de Desenvolvimento**
    Com as dependências instaladas, inicie o servidor do Vite:
    ```bash
    npm run dev
    ```

A aplicação estará disponível em `http://localhost:5173` (ou a porta indicada pelo Vite no seu terminal) e se atualizará automaticamente conforme você edita o código.

## 📜 Scripts Disponíveis

No diretório do projeto, você pode rodar:

- **`npm run dev`**: Inicia a aplicação em modo de desenvolvimento.
- **`npm run build`**: Compila e agrupa a aplicação para produção na pasta `dist/`.
- **`npm run lint`**: Executa o linter para analisar o código em busca de erros.
- **`npm run preview`**: Inicia um servidor local para visualizar a versão de produção (após o build).

---

_Para informações sobre como rodar a plataforma completa com Docker, consulte o `README.md` na raiz do repositório._
