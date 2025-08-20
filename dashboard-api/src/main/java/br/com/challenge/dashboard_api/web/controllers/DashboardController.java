package br.com.challenge.dashboard_api.web.controllers;

import br.com.challenge.dashboard_api.web.dtos.CreateTicketDTO;
import br.com.challenge.dashboard_api.web.dtos.DashboardDataDTO;
import br.com.challenge.dashboard_api.web.dtos.TicketDTO;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DashboardController {

  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping("/dashboard")
  public DashboardDataDTO getDashboardData(@RequestParam int ano, @RequestParam int mes) {
    return dashboardService.getDashboardData(ano, mes);
  }

  @PostMapping("/tickets")
  @ResponseStatus(HttpStatus.CREATED)
  public TicketDTO createTicket(@Valid @RequestBody CreateTicketDTO createTicketDTO) {
    return dashboardService.createTicket(createTicketDTO);
  }
}
