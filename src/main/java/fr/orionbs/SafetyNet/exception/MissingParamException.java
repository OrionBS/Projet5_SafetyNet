package fr.orionbs.SafetyNet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingParamException extends RuntimeException {
    public MissingParamException(String s) {
        super(s);
    }
}
