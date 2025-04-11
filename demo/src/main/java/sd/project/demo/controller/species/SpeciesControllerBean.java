package sd.project.demo.controller.species;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import sd.project.demo.model.dto.species.SpeciesRequestDTO;
import sd.project.demo.model.dto.species.SpeciesResponseDTO;
import sd.project.demo.service.species.SpeciesService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpeciesControllerBean implements SpeciesController {

    private final SpeciesService speciesService;

    @Override
    public List<SpeciesResponseDTO> findAll() {
        log.info("[SPECIES] Finding all species");
        return speciesService.findAll();
    }

    @Override
    public SpeciesResponseDTO findByName(String name) {
        log.info("[SPECIES] Finding species by name: {}", name);

        return speciesService.findByName(name);
    }

    @Override
    public SpeciesResponseDTO save(SpeciesRequestDTO speciesRequestDTO) {
        log.info("[SPECIES] Saving species: {}", speciesRequestDTO);

        return speciesService.save(speciesRequestDTO);
    }

    @Override
    public SpeciesResponseDTO update(UUID id, SpeciesRequestDTO speciesRequestDTO) {
        log.info("[SPECIES] Updating species: {}", speciesRequestDTO);

        return speciesService.update(id, speciesRequestDTO);
    }

    @Override
    public void delete(UUID id) {
        log.info("[SPECIES] Deleting species: {}", id);

        speciesService.delete(id);
    }
}
