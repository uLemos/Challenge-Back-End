package br.com.challenge.recipe_api.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.challenge.recipe_api.application.services.ForkifyService;
import br.com.challenge.recipe_api.web.dtos.ForkifyResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/recipes")
@Tag(name = "Recipes", description = "Endpoints para busca de receitas")
@Validated
public class RecipeController {

  private final ForkifyService forkifyService;

  public RecipeController(ForkifyService forkifyService) {
    this.forkifyService = forkifyService;
  }

  @Operation(summary = "Buscar receitas por prato", description = "Recebe um termo de busca (prato) e retorna a lista de receitas encontradas na API externa da Forkify.")
  @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.")
  @ApiResponse(responseCode = "404", description = "A API externa não foi encontrada.", content = @Content)
  @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content)
  @GetMapping("/search")
  public Mono<ForkifyResponseDTO> search(
      @Parameter(description = "Termo de busca para a receita.", required = true, example = "pizza") @RequestParam @NotBlank(message = "O parâmetro 'dish' não pode ser vazio.") String dish) {
    return forkifyService.searchRecipes(dish);
  }

  @Operation(summary = "Listar pratos disponíveis para busca", description = "Retorna uma lista de termos de busca de pratos conhecidos e válidos para usar no endpoint de busca.")
  @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
  @GetMapping("/available-dishes")
  public List<String> getAvailableDishes() {
    return forkifyService.getAvailableDishes();
  }
}
