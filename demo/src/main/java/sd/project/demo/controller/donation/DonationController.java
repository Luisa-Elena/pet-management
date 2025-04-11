package sd.project.demo.controller.donation;

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
import sd.project.demo.model.dto.donation.DonationFilterDTO;
import sd.project.demo.model.dto.donation.DonationRequestDTO;
import sd.project.demo.model.dto.donation.DonationResponseDTO;

import java.util.UUID;

@RequestMapping("/v1/donations")
@Tag(name = "Donation Management", description = "Operations for managing donations")
public interface DonationController {

    @GetMapping
    @Operation(summary = "Get all donations", description = "Retrieve a list of donations based on optional filtering criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of donations retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CollectionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    CollectionResponseDTO<DonationResponseDTO> findAll(@Validated DonationFilterDTO donationFilterDTO);

    @GetMapping("/{id}")
    @Operation(summary = "Get a donation by ID", description = "Retrieve a donation's details using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DonationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Donation not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    DonationResponseDTO findById(@PathVariable(name = "id") UUID id);

    @PostMapping
    @Operation(summary = "Create a new donation", description = "Add a new donation with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Donation created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DonationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    DonationResponseDTO save(@RequestBody @Valid DonationRequestDTO donationRequestDTO);

    @PutMapping("/{id}")
    @Operation(summary = "Update a donation", description = "Modify an existing donation's details using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DonationResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Donation not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    DonationResponseDTO update(@PathVariable(name = "id") UUID id, @RequestBody @Valid DonationRequestDTO donationRequestDTO);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a donation", description = "Remove a donation from the system using their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Donation deleted successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class))),
            @ApiResponse(responseCode = "404", description = "Donation not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionBody.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    void delete(@PathVariable(name = "id") UUID id);
}