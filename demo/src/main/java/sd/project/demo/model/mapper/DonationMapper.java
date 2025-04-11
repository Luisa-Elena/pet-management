package sd.project.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sd.project.demo.model.dto.donation.DonationRequestDTO;
import sd.project.demo.model.dto.donation.DonationResponseDTO;
import sd.project.demo.model.entity.DonationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationMapper extends DtoMapper<DonationEntity, DonationRequestDTO, DonationResponseDTO> {

    @Override
    @Mapping(target = "userEmail", source = "user.email")
    DonationResponseDTO convertEntityToResponseDto(DonationEntity donationEntity);

    @Override
    List<DonationResponseDTO> convertEntitiesToResponseDtos(List<DonationEntity> donationEntityList);

    @Mapping(target = "id", ignore = true)
    void updateDonationEntity(@MappingTarget DonationEntity donationEntity, DonationRequestDTO donationRequestDTO);
}
