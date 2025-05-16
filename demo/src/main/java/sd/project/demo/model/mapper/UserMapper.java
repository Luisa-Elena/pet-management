package sd.project.demo.model.mapper;

import org.mapstruct.Mapper;
import sd.project.demo.model.dto.user.UserResponseDTO;
import sd.project.demo.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO convertEntityToResponseDto(UserEntity entity);
}
