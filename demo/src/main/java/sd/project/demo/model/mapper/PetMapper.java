package sd.project.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sd.project.demo.model.dto.pet.PetRequestDTO;
import sd.project.demo.model.dto.pet.PetResponseDTO;
import sd.project.demo.model.entity.PetEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper extends DtoMapper<PetEntity, PetRequestDTO, PetResponseDTO> {

    @Override
    @Mapping(target = "speciesName", source = "species.name")
    PetResponseDTO convertEntityToResponseDto(PetEntity petEntity);

    @Override
    List<PetResponseDTO> convertEntitiesToResponseDtos(List<PetEntity> petEntityList);

    @Mapping(target = "id", ignore = true)
    void updatePetEntity(@MappingTarget PetEntity petEntity, PetRequestDTO petRequestDTO);
}
