package br.com.challenge.dashboard_api.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.challenge.dashboard_api.domain.entitys.Cliente;
import br.com.challenge.dashboard_api.domain.entitys.Modulo;
import br.com.challenge.dashboard_api.domain.entitys.Ticket;
import br.com.challenge.dashboard_api.domain.repositories.ClienteRepository;
import br.com.challenge.dashboard_api.domain.repositories.ModuloRepository;
import br.com.challenge.dashboard_api.domain.repositories.TicketRepository;
import br.com.challenge.dashboard_api.mappers.ClienteMapper;
import br.com.challenge.dashboard_api.mappers.ModuloMapper;
import br.com.challenge.dashboard_api.mappers.TicketMapper;
import br.com.challenge.dashboard_api.web.dtos.CreateTicketDTO;
import br.com.challenge.dashboard_api.web.dtos.DashboardDataDTO;
import jakarta.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

  @Mock
  private TicketRepository ticketRepository;
  @Mock
  private ClienteRepository clienteRepository;
  @Mock
  private ModuloRepository moduloRepository;
  @Mock
  private TicketMapper ticketMapper;
  @Mock
  private ClienteMapper clienteMapper;
  @Mock
  private ModuloMapper moduloMapper;

  @InjectMocks
  private DashboardService dashboardService;

  private Cliente clienteA;
  private Cliente clienteB;
  private Modulo moduloX;
  private Modulo moduloY;

  @BeforeEach
  void setUp() {
    clienteA = new Cliente();
    clienteA.setId(1L);
    clienteA.setNome("Cliente A");

    clienteB = new Cliente();
    clienteB.setId(2L);
    clienteB.setNome("Cliente B");

    moduloX = new Modulo();
    moduloX.setId(1L);
    moduloX.setNome("Modulo X");

    moduloY = new Modulo();
    moduloY.setId(2L);
    moduloY.setNome("Modulo Y");
  }

  @Test
  @DisplayName("Deve agrupar tickets por cliente e por módulo corretamente")
  void getDashboardData_deveAgruparCorretamente() {

    // Arrange
    // Criamos uma lista de tickets de teste para simular o retorno do banco.
    List<Ticket> mockTickets = Arrays.asList(
        createTicket(1L, clienteA, moduloX),
        createTicket(2L, clienteA, moduloY),
        createTicket(3L, clienteB, moduloX));

    // Quando o método findByYearAndMonth
    // for chamado com quaisquer inteiros, ENTÃO retorne a nossa lista de teste".
    when(ticketRepository.findByYearAndMonth(anyInt(), anyInt())).thenReturn(mockTickets);

    // Act
    // Executamos o método que queremos testar.
    DashboardDataDTO result = dashboardService.getDashboardData(2025, 8);

    // Assert
    // Verificamos se o resultado é o esperado.
    assertNotNull(result);

    // Verificamos a lógica de agrupamento por cliente
    assertEquals(2, result.getAgrupadoPorCliente().get("Cliente A"));
    assertEquals(1, result.getAgrupadoPorCliente().get("Cliente B"));

    // Verificamos a lógica de agrupamento por módulo
    assertEquals(2, result.getAgrupadoPorModulo().get("Modulo X"));
    assertEquals(1, result.getAgrupadoPorModulo().get("Modulo Y"));

    // Verificamos se o repositório foi chamado exatamente 1 vez.
    verify(ticketRepository, times(1)).findByYearAndMonth(2025, 8);
  }

  @Test
  @DisplayName("Deve retornar listas e mapas vazios quando o repositório não encontrar tickets")
  void getDashboardData_deveRetornarVazio_quandoNaoHaTickets() {

    // Arrange Configuramos o mock para retornar uma lista vazia.
    when(ticketRepository.findByYearAndMonth(anyInt(), anyInt())).thenReturn(Collections.emptyList());

    // Act: Executamos o método.
    DashboardDataDTO result = dashboardService.getDashboardData(2025, 8);

    // Assert: Verificamos se o resultado não é nulo e se as listas/mapas estão
    // vazios.
    assertNotNull(result);

    assertTrue(result.getListaTickets().isEmpty());
    assertTrue(result.getAgrupadoPorCliente().isEmpty());
    assertTrue(result.getAgrupadoPorModulo().isEmpty());
  }

  @Test
  @DisplayName("Deve lançar EntityNotFoundException ao tentar criar ticket com cliente inexistente")
  void createTicket_deveLancarExcecao_quandoClienteNaoExiste() {

    // Arrange
    CreateTicketDTO dto = new CreateTicketDTO();
    dto.setTitulo("Ticket de Teste");
    dto.setClienteId(99L);
    dto.setModuloId(1L);

    // Configuramos o mock para simular que o cliente não foi encontrado
    when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

    // Act & Assert
    // Verificamos se a exceção correta é lançada quando o método é chamado
    assertThrows(EntityNotFoundException.class, () -> {
      dashboardService.createTicket(dto);
    });

    // Verificamos que o método save do ticketRepository NUNCA foi chamado
    verify(ticketRepository, never()).save(any(Ticket.class));
  }

  private Ticket createTicket(Long id, Cliente cliente, Modulo modulo) {
    Ticket ticket = new Ticket();
    ticket.setId(id);
    ticket.setCliente(cliente);
    ticket.setModulo(modulo);
    ticket.setTitulo("Ticket de Teste " + id);
    return ticket;
  }
}
