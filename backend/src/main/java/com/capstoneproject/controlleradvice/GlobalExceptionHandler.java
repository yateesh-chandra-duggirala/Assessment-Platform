package com.capstoneproject.controlleradvice;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ConflictException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.UnAuthorizedException;
import com.capstoneproject.exceptions.ValidationException;
import com.capstoneproject.response.Response;

/**
 * This is the Controller Advice for handling Global exceptions.
 */
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

    /**
     * This is to handle Already Exists exceptions.
     * @param exception of Already Exists Exception.
     * @return the error response.
     */
    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<Response> handleAlreadyExistsException(
            final AlreadyExistsException exception) {
        String message = exception.getMessage();
        HttpStatus code = HttpStatus.CONFLICT;
        int statusCode = code.value();
        Response response = new Response(statusCode, message);
        return new ResponseEntity<Response>(response,
                HttpStatus.CONFLICT);
    }

    /**
     * This is to handle Validation Exceptions.
     * @param exception of Validation Exception.
     * @return the error response.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleValidationException(final
            ValidationException exception) {
        String message = exception.getMessage();
        HttpStatus code = HttpStatus.BAD_REQUEST;
        int statusCode = code.value();
        Response response = new Response(statusCode, message);
        return new ResponseEntity<Response>(response,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * This is to handle Conflict Exceptions.
     * @param exception of Conflict Exception.
     * @return the error response.
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Response> handleConflictException(final
            ConflictException exception) {
        String message = exception.getMessage();
        HttpStatus code = HttpStatus.CONFLICT;
        int statusCode = code.value();
        Response response = new Response(statusCode, message);
        return new ResponseEntity<Response>(response,
                HttpStatus.CONFLICT);
    }

    /**
     * This is to handle Element Not Exists Exception.
     * @param exception of Element Not Exists Exception.
     * @return the error response.
     */
    @ExceptionHandler(ElementNotExistsException.class)
    public final ResponseEntity<Response> handleElementNotExistsException(
            final ElementNotExistsException exception) {
        String message = exception.getMessage();
        HttpStatus code = HttpStatus.NOT_FOUND;
        int statusCode = code.value();
        Response response = new Response(statusCode, message);
        return new ResponseEntity<Response>(response,
                HttpStatus.NOT_FOUND);
    }

    /**
     * This is to handle UnAuthorized Exception.
     * @param exception of UnAuthorized Exception.
     * @return the error response.
     */
    @ExceptionHandler(UnAuthorizedException.class)
    public final ResponseEntity<Response> handleUnAuthorizedException(
            final UnAuthorizedException exception) {
        String message = exception.getMessage();
        int statusCode = HttpStatus.UNAUTHORIZED.value();
        Response loginResponse = new Response(statusCode, message);
        return new ResponseEntity<Response>(loginResponse,
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * This is to handle Bad Requests.
     * @param exception for Method Not Valid Exception.
     * @return the error response.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final Map<String, Object> handleEmptyInput(final
            MethodArgumentNotValidException exception) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
