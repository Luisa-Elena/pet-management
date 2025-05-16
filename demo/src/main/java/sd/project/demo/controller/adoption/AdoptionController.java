package sd.project.demo.controller.adoption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sd.project.demo.exception.model.ExceptionBody;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.adoption.AdoptionFilterDTO;
import sd.project.demo.model.dto.adoption.AdoptionRequestDTO;
import sd.project.demo.model.dto.adoption.AdoptionResponseDTO;

import java.util.UUID;

@RequestMapping("/v1/adoptions")
@Tag(name = "Adoption Management", description = "Operations for managing adoptions")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface AdoptionController {

    @GetMapping
    @Operation(summary = "Get all adoptions", description = "Retrieve a list of adoptions based on optional filtering criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of adoptions retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CollectionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    CollectionResponseDTO<AdoptionResponseDTO> findAll(@Validated AdoptionFilterDTO adoptionFilterDTO);

    @GetMapping("/{id}")
    @Operation(summary = "Get a adoption by ID", description = "Retrieve a adoption's details using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoption found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdoptionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Adoption not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    AdoptionResponseDTO findById(@PathVariable(name = "id") UUID id);

    @PostMapping
    @Operation(summary = "Create a new adoption", description = "Add a new adoption with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Adoption created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdoptionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    AdoptionResponseDTO save(@RequestBody @Valid AdoptionRequestDTO adoptionRequestDTO);

    @PutMapping("/{id}")
    @Operation(summary = "Update a adoption", description = "Modify an existing adoption's details using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoption updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdoptionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Adoption not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    AdoptionResponseDTO update(@PathVariable(name = "id") UUID id, @RequestBody @Valid AdoptionRequestDTO adoptionRequestDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a adoption", description = "Remove a adoption from the system using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Adoption deleted successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "404", description = "Adoption not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@PathVariable(name = "id") UUID id);
}