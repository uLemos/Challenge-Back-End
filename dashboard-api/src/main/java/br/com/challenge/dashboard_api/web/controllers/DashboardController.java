package br.com.challenge.dashboard_api.web.controllers;

import br.com.challenge.dashboard_api.web.dtos.CreateTicketDTO;
import br.com.challenge.dashboard_api.web.dtos.DashboardDataDTO;
import br.com.challenge.dashboard_api.web.dtos.TicketDTO;
import br.com.challenge.dashboard_api.web.dtos.errors.ErrorResponseDTO;
import br.com.challenge.dashboard_api.application.services.DashboardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequestMapping("/api/dashboard")
@Validated
@Tag(name = "Dashboard", description = "Endpoints principais para visualização e criação de tickets")
public class DashboardController {

  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }

  @Operation(summary = "Obter Dados do Dashboard por Mês e Ano", description = "Retorna a lista de tickets e os dados agrupados por cliente e módulo para um mês/ano específico.")
  @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso.")
  @ApiResponse(responseCode = "400", description = "Parâmetros inválidos (ex: mês > 12).", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @GetMapping("/by-month-and-year")
  public DashboardDataDTO getDashboardData(
      @Parameter(description = "Ano para o filtro.", required = true, example = "2021") @RequestParam @Min(value = 2021, message = "O ano deve ser 2021 ou superior.") int ano,
      @Parameter(description = "Mês para o filtro (1-12).", required = true, example = "3") @RequestParam @Min(value = 1, message = "O mês deve ser entre 1 e 12.") @Max(value = 12, message = "O mês deve ser entre 1 e 12.") int mes,
      @ParameterObject Pageable pageable) {
    return dashboardService.getDashboardData(ano, mes, pageable);
  }

  @Operation(summary = "Obter Dados do Dashboard por Mês (Agregado de todos os anos)")
  @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso.")
  @ApiResponse(responseCode = "400", description = "Parâmetro 'mês' inválido (deve ser entre 1 e 12).", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @GetMapping("/by-month")
  public DashboardDataDTO getDashboardDataByMonth(
      @Parameter(description = "Mês para o filtro (1-12).", required = true, example = "3") @RequestParam @Min(1) @Max(12) int mes,
      @ParameterObject Pageable pageable) {
    return dashboardService.getDashboardDataByMonth(mes, pageable);
  }

  @Operation(summary = "Criar Novo Ticket", description = "Cria um novo registro de ticket no sistema.")
  @ApiResponse(responseCode = "201", description = "Ticket criado com sucesso.")
  @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "404", description = "Cliente ou Módulo não encontrado pelo ID fornecido.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  @PostMapping("/tickets")
  @ResponseStatus(HttpStatus.CREATED)
  public TicketDTO createTicket(@Valid @RequestBody CreateTicketDTO createTicketDTO) {
    return dashboardService.createTicket(createTicketDTO);
  }
}
