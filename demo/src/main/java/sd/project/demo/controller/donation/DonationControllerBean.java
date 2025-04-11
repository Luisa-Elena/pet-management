package sd.project.demo.controller.donation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.donation.DonationFilterDTO;
import sd.project.demo.model.dto.donation.DonationRequestDTO;
import sd.project.demo.model.dto.donation.DonationResponseDTO;
import sd.project.demo.service.donation.DonationService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DonationControllerBean implements DonationController {

    private final DonationService donationService;

    @Override
    public CollectionResponseDTO<DonationResponseDTO> findAll(DonationFilterDTO donationFilterDTO) {
        log.info("[DONATION] Finding all donations: {}", donationFilterDTO);

        return donationService.findAll(donationFilterDTO);
    }

    @Override
    public DonationResponseDTO findById(UUID id) {
        log.info("[DONATION] Finding donation by id: {}", id);

        return donationService.findById(id);
    }

    @Override
    public DonationResponseDTO save(DonationRequestDTO donationRequestDTO) {
        log.info("[DONATION] Saving donation: {}", donationRequestDTO);

        return donationService.save(donationRequestDTO);
    }

    @Override
    public DonationResponseDTO update(UUID id, DonationRequestDTO donationRequestDTO) {
        log.info("[DONATION] Updating donation: {}", donationRequestDTO);

        return donationService.update(id, donationRequestDTO);
    }

    @Override
    public void delete(UUID id) {
        log.info("[DONATION] Deleting donation: {}", id);

        donationService.delete(id);
    }
}
