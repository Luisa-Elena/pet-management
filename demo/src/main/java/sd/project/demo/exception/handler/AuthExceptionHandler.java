package sd.project.demo.exception.handler;

import java.time.ZonedDateTime;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sd.project.demo.exception.model.ExceptionBody;
import sd.project.demo.exception.model.ExceptionCode;

@Hidden
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ExceptionBody handleAuthorizationDeniedException(AuthorizationDeniedException exception) {
        return ExceptionBody.builder()
                .timestamp(ZonedDateTime.now())
                .code(ExceptionCode.FORBIDDEN_ACCESS.getCode())
                .message(exception.getMessage())
                .build();
    }
}
