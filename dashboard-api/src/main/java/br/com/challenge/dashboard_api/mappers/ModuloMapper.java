package br.com.challenge.dashboard_api.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import br.com.challenge.dashboard_api.domain.entitys.Modulo;
import br.com.challenge.dashboard_api.web.dtos.ModuloDTO;

@Mapper(componentModel = "spring")
public interface ModuloMapper {

  ModuloDTO toDTO(Modulo modulo);

  List<ModuloDTO> toDTOList(List<Modulo> modulos);
}
