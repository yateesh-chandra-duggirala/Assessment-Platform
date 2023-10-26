package com.capstoneproject.controlleradvice;

import com.capstoneproject.exceptions.*;
import com.capstoneproject.response.Response;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleAlreadyExistsException() {

        AlreadyExistsException exception = new AlreadyExistsException("Resource already exists");
        ResponseEntity<Response> response = globalExceptionHandler.handleAlreadyExistsException(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.CONFLICT.value(), responseBody.getStatusCode());
        assertEquals("Resource already exists", responseBody.getMessage());
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleValidationException() {

        ValidationException exception = new ValidationException("Validation failed");
        ResponseEntity<Response> response = globalExceptionHandler.handleValidationException(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseBody.getStatusCode());
        assertEquals("Validation failed", responseBody.getMessage());
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleConflictException() {
        ConflictException exception = new ConflictException("Conflict occurred.");
        ResponseEntity<Response> response = globalExceptionHandler.handleConflictException(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.CONFLICT.value(), responseBody.getStatusCode());
        assertEquals("Conflict occurred.", responseBody.getMessage());
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleElementNotExistsException() {
        ElementNotExistsException exception = new ElementNotExistsException("Element not found.");
        ResponseEntity<Response> response = globalExceptionHandler.handleElementNotExistsException(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseBody.getStatusCode());
        assertEquals("Element not found.", responseBody.getMessage());
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleUnAuthorizedException() {
        UnAuthorizedException exception = new UnAuthorizedException("Unauthorized access.");
        ResponseEntity<Response> response = globalExceptionHandler.handleUnAuthorizedException(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseBody.getStatusCode());
        assertEquals("Unauthorized access.", responseBody.getMessage());
    }
}
