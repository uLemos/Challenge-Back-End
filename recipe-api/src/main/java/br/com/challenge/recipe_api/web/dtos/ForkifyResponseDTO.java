package br.com.challenge.recipe_api.web.dtos;

import java.util.List;

public record ForkifyResponseDTO(
    int count,
    List<RecipeDTO> recipes) {
}
