package sd.project.demo.model.dto.user;

import sd.project.demo.model.entity.Role;

import java.util.UUID;

public record UserResponseDTO(UUID id, String email, Role role) { }
