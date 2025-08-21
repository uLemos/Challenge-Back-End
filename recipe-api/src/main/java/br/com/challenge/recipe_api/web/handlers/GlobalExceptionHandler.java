package br.com.challenge.recipe_api.web.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.challenge.recipe_api.web.dtos.errors.ErrorResponseDTO;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handler para a validação do @NotBlank que adicionamos
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException ex,
      HttpServletRequest request) {
    String errors = ex.getConstraintViolations().stream()
        .map(cv -> cv.getMessage())
        .collect(Collectors.joining(", "));

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error("Erro de Validação de Parâmetro")
        .message(errors)
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // Handler bônus: para o erro 404 que a API externa pode nos retornar
  @SuppressWarnings("null")
  @ExceptionHandler(WebClientResponseException.NotFound.class)
  public ResponseEntity<ErrorResponseDTO> handleWebClientNotFoundException(WebClientResponseException.NotFound ex,
      HttpServletRequest request) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .error("Recurso Externo Não Encontrado")
        .message("A API externa não encontrou resultados para a busca. URL: " + ex.getRequest().getURI())
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error("Erro Interno no Servidor")
        .message("Ocorreu um erro inesperado.")
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
