package sd.project.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sd.project.demo.model.dto.species.SpeciesRequestDTO;
import sd.project.demo.model.dto.species.SpeciesResponseDTO;
import sd.project.demo.model.entity.SpeciesEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeciesMapper extends DtoMapper<SpeciesEntity, SpeciesRequestDTO, SpeciesResponseDTO> {
    @Override
    @Mapping(target = "id", source = "id")
    SpeciesResponseDTO convertEntityToResponseDto(SpeciesEntity speciesEntity);

    @Override
    @Mapping(target = "id", source = "id")
    List<SpeciesResponseDTO> convertEntitiesToResponseDtos(List<SpeciesEntity> speciesEntityList);

    SpeciesEntity convertRequestDtoToEntity(SpeciesRequestDTO speciesRequestDTO);

    @Mapping(target = "id", ignore = true)
    void updateSpeciesEntity(@MappingTarget SpeciesEntity speciesEntity, SpeciesRequestDTO speciesRequestDTO);
}
