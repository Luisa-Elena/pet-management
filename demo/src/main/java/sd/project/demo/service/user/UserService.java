package sd.project.demo.service.user;

import sd.project.demo.model.dto.user.UserResponseDTO;

public interface UserService {

    UserResponseDTO getUserInfo(String email);
}
