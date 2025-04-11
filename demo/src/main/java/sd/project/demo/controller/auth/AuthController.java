package sd.project.demo.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sd.project.demo.exception.model.ExceptionBody;
import sd.project.demo.model.dto.auth.LoginRequestDTO;
import sd.project.demo.model.dto.auth.LoginResponseDTO;

@RequestMapping("/v1/auth")
@Tag(name = "Authentication", description = "Operations for user authentication and login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface AuthController {

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user credentials and return an authentication token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    void login(@RequestBody @Valid LoginRequestDTO loginRequestDTO);
}
