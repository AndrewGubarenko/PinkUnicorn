package com.pink.unicorn.exceptions;

        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Empty data's field")
public class EmptyDataException extends Exception {
    public EmptyDataException() {}
    public EmptyDataException(String message) {
        super(message);
    }
}
