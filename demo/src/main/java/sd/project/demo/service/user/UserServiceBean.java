package sd.project.demo.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sd.project.demo.exception.model.DataNotFoundException;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.model.dto.user.UserResponseDTO;
import sd.project.demo.model.entity.UserEntity;
import sd.project.demo.model.mapper.UserMapper;
import sd.project.demo.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO getUserInfo(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException(ExceptionCode.USER_NOT_FOUND, email));

        return userMapper.convertEntityToResponseDto(user);
    }
}
