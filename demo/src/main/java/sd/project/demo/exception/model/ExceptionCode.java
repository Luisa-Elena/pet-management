package sd.project.demo.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // Validation & Constraint Violations
    VALIDATION_ERROR("Validation failed.", "ERR_1001"),
    CONSTRAINT_VIOLATION("Constraint violation.", "ERR_1002"),

    // Pet Errors
    PET_NOT_FOUND("Pet %s not found.", "ERR_2001"),
    PET_NAME_TAKEN("Pet name %s is already taken.", "ERR_2002"),

    //Species Errors
    SPECIES_NOT_FOUND("Species %s not found.", "ERR_2003"),
    SPECIES_NAME_TAKEN("Species name %s is already taken.", "ERR_2004"),

    //Adoption Errors
    ADOPTION_NOT_FOUND("Adoption %s not found.", "ERR_2005"),
    PET_ALREADY_ADOPTED("The pet named %s is already adopted.", "ERR_2006"),

    //User Errors
    USER_NOT_FOUND("User %s not found.", "ERR_2007"),

    //Donation Errors
    DONATION_NOT_FOUND("Donation %s not found.", "ERR_2008"),

    // Auth Errors
    INVALID_CREDENTIALS("Invalid credentials.", "ERR_3001"),
    FORBIDDEN_ACCESS("Access is forbidden.", "ERR_3002"),

    // Server Errors
    SERVER_ERROR("Internal server error.", "ERR_5000");

    private final String message;
    private final String code;
}
