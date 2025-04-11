package sd.project.demo.service.adoption;

import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.adoption.AdoptionFilterDTO;
import sd.project.demo.model.dto.adoption.AdoptionRequestDTO;
import sd.project.demo.model.dto.adoption.AdoptionResponseDTO;

import java.util.UUID;

public interface AdoptionService {

    CollectionResponseDTO<AdoptionResponseDTO> findAll(AdoptionFilterDTO filter);

    AdoptionResponseDTO findById(UUID id);

    AdoptionResponseDTO save(AdoptionRequestDTO adoptionRequestDTO);

    AdoptionResponseDTO update(UUID id, AdoptionRequestDTO adoptionRequestDTO);

    void delete(UUID id);
}
