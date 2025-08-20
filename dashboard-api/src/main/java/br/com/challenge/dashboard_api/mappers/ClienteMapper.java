package br.com.challenge.dashboard_api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import br.com.challenge.dashboard_api.domain.entitys.Cliente;
import br.com.challenge.dashboard_api.web.dtos.ClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

  ClienteDTO toDTO(Cliente cliente);

  List<ClienteDTO> toDTOList(List<Cliente> clientes);
}
