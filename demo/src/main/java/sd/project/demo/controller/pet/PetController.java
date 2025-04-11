package sd.project.demo.controller.pet;

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
import sd.project.demo.model.dto.pet.PetFilterDTO;
import sd.project.demo.model.dto.pet.PetRequestDTO;
import sd.project.demo.model.dto.pet.PetResponseDTO;

import java.util.UUID;


@RequestMapping("/v1/pets")
@Tag(name = "Pet Management", description = "Operations for managing pets")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface PetController {

    @GetMapping
    @Operation(summary = "Get all pets", description = "Retrieve a list of pets based on optional filtering criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of pets retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CollectionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    CollectionResponseDTO<PetResponseDTO> findAll(@Validated PetFilterDTO petFilterDTO);

    @GetMapping("/{id}")
    @Operation(summary = "Get a pet by ID", description = "Retrieve a pet's details using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pet not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    PetResponseDTO findById(@PathVariable(name = "id") UUID id);

    @PostMapping
    @Operation(summary = "Create a new pet", description = "Add a new pet with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pet created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    PetResponseDTO save(@RequestBody @Valid PetRequestDTO petRequestDTO);

    @PutMapping("/{id}")
    @Operation(summary = "Update a pet", description = "Modify an existing pet's details using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pet not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    PetResponseDTO update(@PathVariable(name = "id") UUID id, @RequestBody @Valid PetRequestDTO petRequestDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a pet", description = "Remove a pet from the system using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet deleted successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "404", description = "Pet not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@PathVariable(name = "id") UUID id);
}