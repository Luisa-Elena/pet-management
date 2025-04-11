package sd.project.demo.model.dto.adoption;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdoptionRequestDTO(
        @NotBlank(message = "User email is required and cannot be empty.")
        @Email(message = "Email must be a valid email address.")
        String userEmail,

        @NotBlank(message = "Pet name is required and cannot be empty.")
        @Size(min = 2, max = 30, message = "Pet name must be between 2 and 30 characters.")
        String petName,

        String status
) { }
