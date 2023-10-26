package com.capstoneproject.response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginResponseTest {

    @Test
    void testParameterizedConstructor() {
        String message = "Login successful";
        Boolean status = true;
        String userRole = "User";
        Long userId = 1L;
        String name = "John Doe";
        String email = "john@example.com";
        LoginResponse loginResponse = new LoginResponse(message, status, userRole, userId, name, email);

        assertNotNull(loginResponse);
        assertEquals(message, loginResponse.getMessage());
        assertEquals(status, loginResponse.getStatus());
        assertEquals(userRole, loginResponse.getUserRole());
        assertEquals(userId, loginResponse.getUserId());
        assertEquals(name, loginResponse.getName());
        assertEquals(email, loginResponse.getEmail());
    }

    @Test
    void testMessageStatusConstructor() {

        String message = "Login successful";
        Boolean status = true;
        LoginResponse loginResponse = new LoginResponse(message, status);
        assertNotNull(loginResponse);
        assertEquals(message, loginResponse.getMessage());
        assertEquals(status, loginResponse.getStatus());
    }

    @Test
    void testGettersAndSetters() {

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login successful");
        loginResponse.setStatus(true);
        loginResponse.setUserRole("User");
        loginResponse.setUserId(1L);
        loginResponse.setName("John Doe");
        loginResponse.setEmail("john@example.com");

        assertEquals("Login successful", loginResponse.getMessage());
        assertEquals(true, loginResponse.getStatus());
        assertEquals("User", loginResponse.getUserRole());
        assertEquals(1L, loginResponse.getUserId());
        assertEquals("John Doe", loginResponse.getName());
        assertEquals("john@example.com", loginResponse.getEmail());
    }
}
