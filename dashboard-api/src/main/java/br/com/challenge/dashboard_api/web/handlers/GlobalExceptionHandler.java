package br.com.challenge.dashboard_api.web.handlers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.challenge.dashboard_api.web.dtos.errors.ErrorResponseDTO;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex,
      HttpServletRequest request) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.NOT_FOUND.value())
        .error("Recurso não encontrado")
        .message(ex.getMessage())
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    String errors = ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", "));

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error("Erro de Validação")
        .message(errors)
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error("Erro Interno no Servidor")
        .message("Ocorreu um erro inesperado. Tente novamente mais tarde.")
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

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

  @SuppressWarnings("null")
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponseDTO> handleTypeMismatchException(MethodArgumentTypeMismatchException ex,
      HttpServletRequest request) {
    String message = String.format("O parâmetro '%s' recebeu um valor inválido: '%s'. O tipo esperado é '%s'.",
        ex.getName(),
        ex.getValue(),
        ex.getRequiredType().getSimpleName());

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .timestamp(LocalDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .error("Tipo de Parâmetro Inválido")
        .message(message)
        .path(request.getRequestURI())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
