package br.com.challenge.dashboard_api.web.dtos;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketDTO {
  private Long id;
  private String titulo;
  private String cliente;
  private String modulo;
  private LocalDateTime dataAbertura;
  private LocalDateTime dataEncerramento;

  public TicketDTO(Long id, String titulo, String cliente, String modulo, LocalDateTime dataAbertura,
      LocalDateTime dataEncerramento) {
    this.id = id;
    this.titulo = titulo;
    this.cliente = cliente;
    this.modulo = modulo;
    this.dataAbertura = dataAbertura;
    this.dataEncerramento = dataEncerramento;
  }
}
