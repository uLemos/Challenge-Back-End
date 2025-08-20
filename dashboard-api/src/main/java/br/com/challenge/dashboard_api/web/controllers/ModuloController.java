package br.com.challenge.dashboard_api.web.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import br.com.challenge.dashboard_api.web.dtos.ModuloDTO;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

  private final DashboardService dashboardService;

  public ModuloController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @GetMapping
  public List<ModuloDTO> findAllModulos() {
    return dashboardService.findAllModulos();
  }
}
