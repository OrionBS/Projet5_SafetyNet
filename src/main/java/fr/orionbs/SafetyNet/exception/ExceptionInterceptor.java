package fr.orionbs.SafetyNet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(MissingParamException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handler(MissingParamException missingParamException) {
        return new ErrorDTO(missingParamException.getMessage());
    }
}
