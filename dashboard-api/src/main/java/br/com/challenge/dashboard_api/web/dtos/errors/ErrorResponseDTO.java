package br.com.challenge.dashboard_api.web.dtos.errors;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponseDTO {
  private LocalDateTime timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
