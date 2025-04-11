package sd.project.demo.controller.pet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import sd.project.demo.controller.pet.PetController;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.pet.PetFilterDTO;
import sd.project.demo.model.dto.pet.PetRequestDTO;
import sd.project.demo.model.dto.pet.PetResponseDTO;
import sd.project.demo.service.pet.PetService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PetControllerBean implements PetController {

    private final PetService petService;

    @Override
    public CollectionResponseDTO<PetResponseDTO> findAll(PetFilterDTO petFilterDTO) {
        log.info("[PET] Finding all pets: {}", petFilterDTO);
        return petService.findAll(petFilterDTO);
    }

    @Override
    public PetResponseDTO findById(UUID id) {
        log.info("[PET] Finding pet by id: {}", id);

        return petService.findById(id);
    }

    @Override
    public PetResponseDTO save(PetRequestDTO petRequestDTO) {
        log.info("[PET] Saving pet: {}", petRequestDTO);

        return petService.save(petRequestDTO);
    }

    @Override
    public PetResponseDTO update(UUID id, PetRequestDTO petRequestDTO) {
        log.info("[PET] Updating pet: {}", petRequestDTO);

        return petService.update(id, petRequestDTO);
    }

    @Override
    public void delete(UUID id) {
        log.info("[PET] Deleting pet: {}", id);

        petService.delete(id);
    }
}
