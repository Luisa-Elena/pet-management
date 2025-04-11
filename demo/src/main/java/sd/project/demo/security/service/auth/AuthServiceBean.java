package sd.project.demo.security.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sd.project.demo.exception.model.ExceptionCode;
import sd.project.demo.model.entity.UserEntity;
import sd.project.demo.repository.user.UserRepository;

import java.util.UUID;

@Service("authService")
@RequiredArgsConstructor
public class AuthServiceBean implements AuthService {

    private final UserRepository userRepository;

    @Override
    public boolean isSelf(UUID userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new BadCredentialsException(ExceptionCode.FORBIDDEN_ACCESS.getMessage()));

        return user.getId().equals(userId);
    }
}
