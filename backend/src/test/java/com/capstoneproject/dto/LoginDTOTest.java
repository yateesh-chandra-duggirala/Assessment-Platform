package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginDTOTest {

    @Test
    public void testLoginDTOConstructor() {
        String email = "yateesh.chandra@nucleusteq.com";
        String password = "03072001";

        LoginDTO loginDto = new LoginDTO(email, password);
        assertNotNull(loginDto);
        assertEquals(email, loginDto.getEmail());
        assertEquals(password, loginDto.getPassword());
    }
    
    @Test
    public void testDTOGetterAndSetter() {
        String email = "yateesh.chandra@nucleusteq.com";
        String password = "03072001";

        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        assertEquals(email, loginDto.getEmail());
        assertEquals(password, loginDto.getPassword());
    }

    @Test
    public void testDefaultConstructor() {
        LoginDTO loginDto = new LoginDTO();
        assertEquals(null, loginDto.getEmail());
        assertEquals(null, loginDto.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        LoginDTO loginDTO = new LoginDTO("test@example.com", "testpassword");
        assertEquals("test@example.com", loginDTO.getEmail());
        assertEquals("testpassword", loginDTO.getPassword());
    }
}
