package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is about Unauthorized Exception - 401.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {

    /**
     * This is Serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public UnAuthorizedException(final String message) {
        super(message);
    }
}
