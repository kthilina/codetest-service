package com.noetic.codetest.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@ResponseStatus(HttpStatus.CONFLICT)
public class RecordAlreadyExistsException extends RuntimeException {
    public RecordAlreadyExistsException() {
    }

    public RecordAlreadyExistsException(String message) {
        super(message);
    }

    public RecordAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public RecordAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
