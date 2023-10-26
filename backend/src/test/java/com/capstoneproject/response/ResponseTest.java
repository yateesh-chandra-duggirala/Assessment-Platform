package com.capstoneproject.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {

    @Test
    public void testDefaultConstructor() {
        Response<String> response = new Response<>();
        
        assertEquals(0, response.getStatusCode());
        assertNull(response.getMessage());
        assertNull(response.getBody());
    }

    @Test
    public void testAllArgsConstructor() {
        int statusCode = 200;
        String message = "OK";
        String body = "Response Body";

        Response<String> response = new Response<>(statusCode, message, body);

        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertEquals(body, response.getBody());
    }

    @Test
    public void testConstructorWithStatusCodeAndMessage() {
        int statusCode = 404;
        String message = "Not Found";

        Response<String> response = new Response<>(statusCode, message);

        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertNull(response.getBody());
    }

    @Test
    public void testGetterAndSetter() {
        Response<String> response = new Response<>();

        int statusCode = 500;
        String message = "Internal Server Error";
        String body = "Response Body";

        response.setStatusCode(statusCode);
        response.setMessage(message);
        response.setBody(body);

        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertEquals(body, response.getBody());
    }
}
