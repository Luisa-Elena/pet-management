package sd.project.demo.controller.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import sd.project.demo.model.dto.auth.LoginRequestDTO;

@Slf4j
@RestController
public class AuthControllerBean implements AuthController {

    @Override
    public void login(LoginRequestDTO loginRequestDTO) {
        log.info("[AUTH] User {} logged in", loginRequestDTO.email());
    }
}
