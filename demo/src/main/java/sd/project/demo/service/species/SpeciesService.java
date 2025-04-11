package sd.project.demo.service.species;

import sd.project.demo.model.dto.species.SpeciesRequestDTO;
import sd.project.demo.model.dto.species.SpeciesResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SpeciesService {

    List<SpeciesResponseDTO> findAll();

    SpeciesResponseDTO findByName(String name);

    SpeciesResponseDTO save(SpeciesRequestDTO speciesRequestDTO);

    SpeciesResponseDTO update(UUID id, SpeciesRequestDTO speciesRequestDTO);

    void delete(UUID id);
}
