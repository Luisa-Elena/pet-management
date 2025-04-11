package sd.project.demo.model.dto.donation;

import jakarta.validation.constraints.Min;
import sd.project.demo.controller.util.RestUtil;
import sd.project.demo.model.entity.Status;

import java.util.Objects;

public record DonationFilterDTO(
        String userEmail,
        Integer amount,
        Status status,

        @Min(value = 0, message = "Page number must be at least 0.")
        Integer pageNumber,

        @Min(value = 1, message = "Page size must be at least 1.")
        Integer pageSize
){
    public DonationFilterDTO {
        pageNumber = Objects.requireNonNullElse(pageNumber, RestUtil.DEFAULT_PAGE_NUMBER);
        pageSize = Objects.requireNonNullElse(pageSize, RestUtil.DEFAULT_PAGE_SIZE);
    }
}
