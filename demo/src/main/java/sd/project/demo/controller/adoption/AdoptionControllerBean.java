package sd.project.demo.controller.adoption;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.adoption.AdoptionFilterDTO;
import sd.project.demo.model.dto.adoption.AdoptionRequestDTO;
import sd.project.demo.model.dto.adoption.AdoptionResponseDTO;
import sd.project.demo.service.adoption.AdoptionService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdoptionControllerBean implements AdoptionController {

    private final AdoptionService adoptionService;

    @Override
    public CollectionResponseDTO<AdoptionResponseDTO> findAll(AdoptionFilterDTO adoptionFilterDTO) {
        log.info("[ADOPTION] Finding all adoptions: {}", adoptionFilterDTO);
        return adoptionService.findAll(adoptionFilterDTO);
    }

    @Override
    public AdoptionResponseDTO findById(UUID id) {
        log.info("[ADOPTION] Finding adoption by id: {}", id);

        return adoptionService.findById(id);
    }

    @Override
    public AdoptionResponseDTO save(AdoptionRequestDTO adoptionRequestDTO) {
        log.info("[ADOPTION] Saving adoption: {}", adoptionRequestDTO);

        return adoptionService.save(adoptionRequestDTO);
    }

    @Override
    public AdoptionResponseDTO update(UUID id, AdoptionRequestDTO adoptionRequestDTO) {
        log.info("[ADOPTION] Updating adoption: {}", adoptionRequestDTO);

        return adoptionService.update(id, adoptionRequestDTO);
    }

    @Override
    public void delete(UUID id) {
        log.info("[ADOPTION] Deleting adoption: {}", id);

        adoptionService.delete(id);
    }
}
