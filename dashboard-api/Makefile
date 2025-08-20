.PHONY: help run dev-run-fresh docker-up docker-down

default: help

help: ## Mostra os comandos essenciais.
	@echo "----------------------------------------------------"
	@echo "🚀 Comandos Essenciais"
	@echo "----------------------------------------------------"
	@echo "\033[36mrun\033[0m              ## Roda a aplicação localmente (dev)."
	@echo "\033[36mdev-run-fresh\033[0m    ## Reseta o banco local e roda a aplicação."
	@echo "\033[36mdocker-up\033[0m        ## Sobe os containers Docker em background."
	@echo "\033[36mdocker-down\033[0m      ## Para e remove os containers Docker."
	@echo "----------------------------------------------------"

# --- Ambiente de Desenvolvimento Local ---
run: ## Roda a aplicação Spring Boot localmente (perfil 'dev').
	@mvn spring-boot:run

dev-run-fresh: ## Reseta o banco local e depois inicia a aplicação(necessário para toda migration).
	@psql -U postgres -d dashboard_db_dev -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"
	@mvn spring-boot:run

# --- Ambiente Docker (Produção) ---
docker-up: ## Sobe todos os containers Docker em background e reconstrói a imagem da API.
	@docker-compose up --build -d

docker-down: ## Para e remove os containers Docker.
	@docker-compose down -v