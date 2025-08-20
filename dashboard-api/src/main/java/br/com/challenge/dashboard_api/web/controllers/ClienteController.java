package br.com.challenge.dashboard_api.web.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import br.com.challenge.dashboard_api.web.dtos.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Endpoint para consulta de dados mestre de clientes")
public class ClienteController {

  private final DashboardService dashboardService;

  public ClienteController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @Operation(summary = "Listar todos os Clientes", description = "Retorna uma lista com ID e nome de todos os clientes cadastrados.")
  @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso.")
  @GetMapping
  public List<ClienteDTO> findAllClientes() {
    return dashboardService.findAllClientes();
  }
}
