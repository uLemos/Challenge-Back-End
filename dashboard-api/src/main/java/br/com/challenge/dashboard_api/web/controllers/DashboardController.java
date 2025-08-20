package br.com.challenge.dashboard_api.web.controllers;

import br.com.challenge.dashboard_api.web.dtos.CreateTicketDTO;
import br.com.challenge.dashboard_api.web.dtos.DashboardDataDTO;
import br.com.challenge.dashboard_api.web.dtos.TicketDTO;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class DashboardController {

  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping("/dashboard")
  public DashboardDataDTO getDashboardData(
      @RequestParam @Min(value = 2021, message = "O ano deve ser 2021 ou superior.") int ano,
      @RequestParam @Min(value = 1, message = "O mês deve ser entre 1 e 12.") @Max(value = 12, message = "O mês deve ser entre 1 e 12.") int mes) {
    return dashboardService.getDashboardData(ano, mes);
  }

  @PostMapping("/tickets")
  @ResponseStatus(HttpStatus.CREATED)
  public TicketDTO createTicket(@Valid @RequestBody CreateTicketDTO createTicketDTO) {
    return dashboardService.createTicket(createTicketDTO);
  }
}
