package com.pink.unicorn.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Empty data's field")
public class EmptyDataException extends DataAccessException {
    public EmptyDataException(String message) {
        super(message);
    }
    public EmptyDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
