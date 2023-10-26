package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is Conflict Exception - 409.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public ConflictException(final String message) {
        super(message);
    }
}
