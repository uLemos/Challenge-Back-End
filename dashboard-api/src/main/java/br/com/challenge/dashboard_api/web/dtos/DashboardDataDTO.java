package br.com.challenge.dashboard_api.web.dtos;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class DashboardDataDTO {
  private PagedResultDTO<TicketDTO> listaTickets;
  private Map<String, Long> agrupadoPorCliente;
  private Map<String, Long> agrupadoPorModulo;
}
