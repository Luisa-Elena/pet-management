package sd.project.demo.service.pet;

import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.pet.PetFilterDTO;
import sd.project.demo.model.dto.pet.PetRequestDTO;
import sd.project.demo.model.dto.pet.PetResponseDTO;

import java.util.UUID;

public interface PetService {

    CollectionResponseDTO<PetResponseDTO> findAll(PetFilterDTO filter);

    PetResponseDTO findById(UUID id);

    PetResponseDTO save(PetRequestDTO petRequestDTO);

    PetResponseDTO update(UUID id, PetRequestDTO petRequestDTO);

    void delete(UUID id);
}
