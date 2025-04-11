package sd.project.demo.model.dto.donation;

import sd.project.demo.model.entity.Status;

import java.util.UUID;

public record DonationResponseDTO(UUID id, String userEmail, int amount, Status status){ }
