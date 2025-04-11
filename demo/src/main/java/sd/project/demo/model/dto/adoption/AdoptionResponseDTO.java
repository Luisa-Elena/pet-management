package sd.project.demo.model.dto.adoption;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdoptionResponseDTO(
        UUID id,
        String userEmail,
        String petName,
        String status,
        LocalDateTime adoptionTimestamp
)
{ }
