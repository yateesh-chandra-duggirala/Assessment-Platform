package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is about Bad Request as Exception - 400.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    /**
     * This is Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public ValidationException(final String message) {
        super(message);
    }
}
