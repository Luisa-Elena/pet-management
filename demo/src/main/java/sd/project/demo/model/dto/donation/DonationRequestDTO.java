package sd.project.demo.model.dto.donation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sd.project.demo.model.entity.Status;

public record DonationRequestDTO(
        @NotBlank(message = "Email is required and cannot be empty.")
        @Email(message = "Email must be a valid email address.")
        String userEmail,

        @NotNull(message = "Amount is required and cannot be null.")
        int amount,

        Status status
)
{ }
