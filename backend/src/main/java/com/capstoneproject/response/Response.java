package com.capstoneproject.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is Response Class.
 * @param <T> General Body.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    /**
     * code variable is of integer type to display status code.
     */
    private int statusCode;

    /**
     * This is message for the status code.
     */
    private String message;

    /**
     * This is the Response Body Entity.
     */
    private T body;

    /**
     * This is the constructor for error response.
     * @param code of integer type.
     * @param statusMessage of String type.
     */
    public Response(final int code, final String statusMessage) {
        this.statusCode = code;
        this.message = statusMessage;
    }

}
