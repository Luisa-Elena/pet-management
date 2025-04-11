package sd.project.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sd.project.demo.model.dto.adoption.AdoptionRequestDTO;
import sd.project.demo.model.dto.adoption.AdoptionResponseDTO;
import sd.project.demo.model.entity.AdoptionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdoptionMapper extends DtoMapper<AdoptionEntity, AdoptionRequestDTO, AdoptionResponseDTO> {

    @Override
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "petName", source = "pet.name")
    AdoptionResponseDTO convertEntityToResponseDto(AdoptionEntity adoptionEntity);

    @Override
    List<AdoptionResponseDTO> convertEntitiesToResponseDtos(List<AdoptionEntity> adoptionEntityList);

    @Mapping(target = "id", ignore = true)
    void updateAdoptionEntity(@MappingTarget AdoptionEntity adoptionEntity, AdoptionRequestDTO adoptionRequestDTO);
}
