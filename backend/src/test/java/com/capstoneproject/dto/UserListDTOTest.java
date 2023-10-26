package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserListDTOTest {


    @Test
    public void testUserListDTOConstructor() {
        UserListDTO userDto = new UserListDTO(1L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com","USER","9876543210");
        assertNotNull(userDto);
        assertEquals(1L, userDto.getUserId());
        assertEquals("Ramesh Kumar", userDto.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", userDto.getEmail());
        assertEquals("USER", userDto.getUserRole());
        assertEquals("9876543210", userDto.getPhoneNumber());
    }

    @Test
    public void testUserListDTOGettersAndSetters() {
        UserListDTO userDto = new UserListDTO();
        userDto.setUserId(1L);
        userDto.setName("Ramesh Kumar");
        userDto.setEmail("ramesh.kumar@nucleusteq.com");
        userDto.setPhoneNumber("9876543210");
        userDto.setUserRole("USER");

        assertEquals(1L, userDto.getUserId());
        assertEquals("Ramesh Kumar", userDto.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", userDto.getEmail());
        assertEquals("USER", userDto.getUserRole());
        assertEquals("9876543210", userDto.getPhoneNumber());
    }

    @Test
    public void testDefaultConstructor() {
        Long id = 5L;
        UserListDTO userDto = new UserListDTO();
        userDto.setUserId(id);
        assertEquals(id, userDto.getUserId());
        assertEquals(null, userDto.getName());
        assertEquals(null, userDto.getEmail());
        assertEquals(null, userDto.getUserRole());
        assertEquals(null, userDto.getPhoneNumber());
    }

}
