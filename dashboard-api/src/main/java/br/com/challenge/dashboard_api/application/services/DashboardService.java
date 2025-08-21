package br.com.challenge.dashboard_api.application.services;

import br.com.challenge.dashboard_api.domain.entitys.Cliente;
import br.com.challenge.dashboard_api.domain.entitys.Modulo;
import br.com.challenge.dashboard_api.domain.entitys.Ticket;
import br.com.challenge.dashboard_api.domain.repositories.ClienteRepository;
import br.com.challenge.dashboard_api.domain.repositories.ModuloRepository;
import br.com.challenge.dashboard_api.domain.repositories.TicketRepository;
import br.com.challenge.dashboard_api.mappers.ClienteMapper;
import br.com.challenge.dashboard_api.mappers.ModuloMapper;
import br.com.challenge.dashboard_api.mappers.TicketMapper;
import br.com.challenge.dashboard_api.web.dtos.ClienteDTO;
import br.com.challenge.dashboard_api.web.dtos.CreateTicketDTO;
import br.com.challenge.dashboard_api.web.dtos.DashboardDataDTO;
import br.com.challenge.dashboard_api.web.dtos.ModuloDTO;
import br.com.challenge.dashboard_api.web.dtos.PagedResultDTO;
import br.com.challenge.dashboard_api.web.dtos.TicketDTO;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

  private final TicketRepository ticketRepository;
  private final ClienteRepository clienteRepository;
  private final ModuloRepository moduloRepository;
  private final TicketMapper ticketMapper;
  private final ClienteMapper clienteMapper;
  private final ModuloMapper moduloMapper;

  public DashboardService(TicketRepository ticketRepository, ClienteRepository clienteRepository,
      ModuloRepository moduloRepository, TicketMapper ticketMapper, ClienteMapper clienteMapper,
      ModuloMapper moduloMapper) {
    this.ticketRepository = ticketRepository;
    this.clienteRepository = clienteRepository;
    this.moduloRepository = moduloRepository;
    this.ticketMapper = ticketMapper;
    this.clienteMapper = clienteMapper;
    this.moduloMapper = moduloMapper;
  }

  public DashboardDataDTO getDashboardData(int ano, int mes, Pageable pageable) {
    Page<Ticket> ticketsPaginados = ticketRepository.findByYearAndMonth(ano, mes, pageable);
    Page<TicketDTO> ticketsDTOsPaginados = ticketsPaginados.map(ticketMapper::toDTO);
    PagedResultDTO<TicketDTO> pagedResult = new PagedResultDTO<>(ticketsDTOsPaginados);

    List<Ticket> todosOsTicketsDoMes = ticketRepository.findAllByYearAndMonth(ano, mes);

    Map<String, Long> agrupadoPorCliente = todosOsTicketsDoMes.stream()
        .collect(Collectors.groupingBy(ticket -> ticket.getCliente().getNome(), Collectors.counting()));

    Map<String, Long> agrupadoPorModulo = todosOsTicketsDoMes.stream()
        .collect(Collectors.groupingBy(ticket -> ticket.getModulo().getNome(), Collectors.counting()));

    return DashboardDataDTO.builder()
        .listaTickets(pagedResult)
        .agrupadoPorCliente(agrupadoPorCliente)
        .agrupadoPorModulo(agrupadoPorModulo)
        .build();
  }

  public TicketDTO createTicket(CreateTicketDTO createTicketDTO) {
    Cliente cliente = clienteRepository.findById(createTicketDTO.getClienteId())
        .orElseThrow(
            () -> new EntityNotFoundException("Cliente não encontrado com o ID: " + createTicketDTO.getClienteId()));

    Modulo modulo = moduloRepository.findById(createTicketDTO.getModuloId())
        .orElseThrow(
            () -> new EntityNotFoundException("Módulo não encontrado com o ID: " + createTicketDTO.getModuloId()));

    Ticket newTicket = new Ticket();
    newTicket.setTitulo(createTicketDTO.getTitulo());
    newTicket.setDataAbertura(LocalDateTime.now());
    newTicket.setCliente(cliente);
    newTicket.setModulo(modulo);

    Ticket saveTicket = ticketRepository.save(newTicket);

    return ticketMapper.toDTO(saveTicket);
  }

  public List<ClienteDTO> findAllClientes() {
    List<Cliente> clientes = clienteRepository.findAll();
    return clienteMapper.toDTOList(clientes);
  }

  public List<ModuloDTO> findAllModulos() {
    List<Modulo> modulos = moduloRepository.findAll();
    return moduloMapper.toDTOList(modulos);
  }
}
