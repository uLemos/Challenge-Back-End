package br.com.challenge.dashboard_api.web.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import br.com.challenge.dashboard_api.web.dtos.ModuloDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/modulos")
@Tag(name = "Módulos", description = "Endpoint para consulta de dados mestre de módulos")
public class ModuloController {

  private final DashboardService dashboardService;

  public ModuloController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @Operation(summary = "Listar todos os Módulos", description = "Retorna uma lista com ID e nome de todos os módulos cadastrados.")
  @ApiResponse(responseCode = "200", description = "Lista de módulos retornada com sucesso.")
  @GetMapping
  public List<ModuloDTO> findAllModulos() {
    return dashboardService.findAllModulos();
  }
}
