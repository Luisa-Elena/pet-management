package sd.project.demo.controller.species;

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
import org.springframework.web.bind.annotation.*;
import sd.project.demo.exception.model.ExceptionBody;
import sd.project.demo.model.dto.CollectionResponseDTO;
import sd.project.demo.model.dto.species.SpeciesRequestDTO;
import sd.project.demo.model.dto.species.SpeciesResponseDTO;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/species")
@Tag(name = "Species Management", description = "Operations for managing species")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public interface SpeciesController {

    @GetMapping
    @Operation(summary = "Get all species", description = "Retrieve a list of all species")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of species retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CollectionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<SpeciesResponseDTO> findAll();

    @GetMapping("/{name}")
    @Operation(summary = "Get a species by ID", description = "Retrieve a species's details using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Species found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SpeciesResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Species not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    SpeciesResponseDTO findByName(@PathVariable(name = "name") String name);

    @PostMapping
    @Operation(summary = "Create a new species", description = "Add a new species with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Species created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SpeciesResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    SpeciesResponseDTO save(@RequestBody @Valid SpeciesRequestDTO speciesRequestDTO);

    @PutMapping("/{id}")
    @Operation(summary = "Update a species", description = "Modify an existing species's details using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Species updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SpeciesResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Species not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    SpeciesResponseDTO update(@PathVariable(name = "id") UUID id, @RequestBody @Valid SpeciesRequestDTO speciesRequestDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a species", description = "Remove a species from the system using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Species deleted successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "404", description = "Species not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@PathVariable(name = "id") UUID id);
}