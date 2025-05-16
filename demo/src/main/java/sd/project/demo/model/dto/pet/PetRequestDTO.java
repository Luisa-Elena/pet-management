package sd.project.demo.model.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PetRequestDTO(
        @NotBlank(message = "Name is required and cannot be empty.")
        @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters.")
        String name,

        @NotBlank(message = "Description is required and cannot be empty.")
        @Size(min = 2, max = 1000, message = "Description must be between 2 and 1000 characters.")
        String description,

        @NotBlank(message = "Species is required and cannot be empty.")
        String speciesName,
        String imageUrl,
        Boolean isAdopted
) { }
