package sd.project.demo.model.dto.pet;

import jakarta.validation.constraints.Min;
import sd.project.demo.controller.util.RestUtil;

import java.util.Objects;

public record PetFilterDTO (
        String name,
        String species,
        Boolean isAdopted,

        @Min(value = 0, message = "Page number must be at least 0.")
        Integer pageNumber,

        @Min(value = 1, message = "Page size must be at least 1.")
        Integer pageSize
) {
    public PetFilterDTO {
        pageNumber = Objects.requireNonNullElse(pageNumber, RestUtil.DEFAULT_PAGE_NUMBER);
        pageSize = Objects.requireNonNullElse(pageSize, RestUtil.DEFAULT_PAGE_SIZE);
    }
}
