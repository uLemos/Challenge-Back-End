package br.com.challenge.dashboard_api.web.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import br.com.challenge.dashboard_api.web.dtos.ClienteDTO;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  private final DashboardService dashboardService;

  public ClienteController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping
  public List<ClienteDTO> findAllClientes() {
    return dashboardService.findAllClientes();
  }
}
