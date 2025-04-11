package sd.project.demo.model.dto.pet;

import java.util.UUID;

public record PetResponseDTO(UUID id, String name, String description, String speciesName, String imageUrl, Boolean isAdopted) { }