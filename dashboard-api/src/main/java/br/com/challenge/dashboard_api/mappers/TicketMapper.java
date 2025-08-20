package br.com.challenge.dashboard_api.mappers;

import br.com.challenge.dashboard_api.domain.entitys.Ticket;
import br.com.challenge.dashboard_api.web.dtos.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

  @Mapping(source = "cliente.nome", target = "cliente")
  @Mapping(source = "modulo.nome", target = "modulo")
  TicketDTO toDTO(Ticket ticket);
}
