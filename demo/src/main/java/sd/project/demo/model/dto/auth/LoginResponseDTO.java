package sd.project.demo.model.dto.auth;

import sd.project.demo.model.entity.Role;

import java.util.UUID;
public record LoginResponseDTO(UUID id, String email, Role role) { }
