package br.com.challenge.recipe_api.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecipeDTO(
    String publisher,
    String title,
    @JsonProperty("source_url") String sourceUrl,
    @JsonProperty("recipe_id") String recipeId,
    @JsonProperty("image_url") String imageUrl,
    @JsonProperty("social_rank") double socialRank,
    @JsonProperty("publisher_url") String publisherUrl) {
}
