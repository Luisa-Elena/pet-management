package sd.project.demo.service.donation;

import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.donation.DonationFilterDTO;
import sd.project.demo.model.dto.donation.DonationRequestDTO;
import sd.project.demo.model.dto.donation.DonationResponseDTO;

import java.util.UUID;

public interface DonationService {

    CollectionResponseDTO<DonationResponseDTO> findAll(DonationFilterDTO filter);

    DonationResponseDTO findById(UUID id);

    DonationResponseDTO save(DonationRequestDTO donationRequestDTO);

    DonationResponseDTO update(UUID id, DonationRequestDTO donationRequestDTO);

    void delete(UUID id);
}
