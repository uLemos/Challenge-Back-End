package br.com.challenge.dashboard_api.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTicketDTO {

  @NotBlank(message = "O título é obrigatório")
  private String titulo;

  @NotNull(message = "O ID do cliente é obrigatório")
  private Long clienteId;

  @NotNull(message = "O ID do módulo é obrigatório")
  private Long moduloId;
}
