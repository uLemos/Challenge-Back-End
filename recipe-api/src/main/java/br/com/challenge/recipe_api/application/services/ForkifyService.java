package br.com.challenge.recipe_api.application.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.challenge.recipe_api.web.dtos.ForkifyResponseDTO;
import reactor.core.publisher.Mono;

@Service
public class ForkifyService {
  private final WebClient webClient;

  public ForkifyService(WebClient webClient) {
    this.webClient = webClient;
  }

  private static final List<String> AVAILABLE_DISHES = Arrays.asList(
      "carrot", "broccoli", "asparagus", "cauliflower", "corn", "cucumber",
      "green pepper", "lettuce", "mushrooms", "onion", "potato", "pumpkin",
      "red pepper", "tomato", "beetroot", "brussel sprouts", "peas", "zucchini",
      "radish", "sweet potato", "artichoke", "leek", "cabbage", "celery",
      "chili", "garlic", "basil", "coriander", "parsley", "dill", "rosemary",
      "oregano", "cinnamon", "saffron", "green bean", "bean", "chickpea",
      "lentil", "apple", "apricot", "avocado", "banana", "blackberry",
      "blackcurrant", "blueberry", "boysenberry", "cherry", "coconut", "fig",
      "grape", "grapefruit", "kiwifruit", "lemon", "lime", "lychee",
      "mandarin", "mango", "melon", "nectarine", "orange", "papaya",
      "passion fruit", "peach", "pear", "pineapple", "plum", "pomegranate",
      "quince", "raspberry", "strawberry", "watermelon", "salad", "pizza",
      "pasta", "popcorn", "lobster", "steak", "bbq", "pudding", "hamburger",
      "pie", "cake", "sausage", "tacos", "kebab", "poutine", "seafood",
      "chips", "fries", "masala", "paella", "som tam", "chicken", "toast",
      "marzipan", "tofu", "ketchup", "hummus", "maple syrup", "parma ham",
      "fajitas", "champ", "lasagna", "poke", "chocolate", "croissant", "arepas",
      "bunny chow", "pierogi", "donuts", "rendang", "sushi", "ice cream",
      "duck", "curry", "beef", "goat", "lamb", "turkey", "pork", "fish",
      "crab", "bacon", "ham", "pepperoni", "salami", "ribs");

  public Mono<ForkifyResponseDTO> searchRecipes(String query) {
    return webClient.get()
        .uri("/search?q={query}", query)
        .retrieve()
        .bodyToMono(ForkifyResponseDTO.class);
  }

  public List<String> getAvailableDishes() {
    return AVAILABLE_DISHES;
  }
}
