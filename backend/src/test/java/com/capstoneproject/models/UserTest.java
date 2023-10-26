package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User(1L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com","ramesh","USER","9876543210");
        assertNotNull(user);
        assertEquals(1L, user.getUserId());
        assertEquals("Ramesh Kumar", user.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", user.getEmail());
        assertEquals("ramesh", user.getPassword());
        assertEquals("USER", user.getUserRole());
        assertEquals("9876543210", user.getPhoneNumber());
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setUserId(1L);
        user.setName("Ramesh Kumar");
        user.setEmail("ramesh.kumar@nucleusteq.com");
        user.setPassword("ramesh");
        user.setPhoneNumber("9876543210");
        user.setUserRole("USER");

        assertEquals(1L, user.getUserId());
        assertEquals("Ramesh Kumar", user.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", user.getEmail());
        assertEquals("ramesh", user.getPassword());
        assertEquals("USER", user.getUserRole());
        assertEquals("9876543210", user.getPhoneNumber());
    }

    @Test
    public void testUserResponsesGetterAndSetter() {
        User user = new User();

        List<UserResponses> userResponses = new ArrayList<>();
        userResponses.add(new UserResponses(4L,5, 4, 20, 16, "2023-09-20"));
        userResponses.add(new UserResponses(5L,5, 3, 20, 12, "2023-09-19"));
        user.setUserResponses(userResponses);

        List<UserResponses> list = user.getUserResponses();
        assertNotSame(userResponses, list);
        assertEquals(userResponses, list);
    }

}
