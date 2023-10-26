package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the Already Exists Exception - 409.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {

    /**
     * This is the Serial Version Id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is AlreadyExistsException.
     * @param message .
     */
    public AlreadyExistsException(final String message) {
        super(message);
    }
}
