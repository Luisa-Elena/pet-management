package sd.project.demo.exception.model;

import lombok.Getter;

@Getter
public class PetAlreadyAdoptedException extends RuntimeException {

    private final String code;

    public PetAlreadyAdoptedException(ExceptionCode exceptionCode, Object... args) {
        super(String.format(exceptionCode.getMessage(), args));
        this.code = exceptionCode.getCode();
    }
}
