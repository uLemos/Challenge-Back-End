package br.com.challenge.dashboard_api.web.dtos;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class DashboardDataDTO {
  private List<TicketDTO> listaTickets;
  private Map<String, Long> agrupadoPorCliente;
  private Map<String, Long> agrupadoPorModulo;
}
