package com.capstoneproject.service;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.dto.UserListDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.UnAuthorizedException;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.response.LoginResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser_ValidUser_ShouldAddUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Yateesh");
        userDTO.setEmail("yateesh@nucleusteq.com");
        userDTO.setPassword("password123");
        userDTO.setPhoneNumber("1234567890");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

        String result = userService.addUser(userDTO);

        assertEquals("Yateesh", result);
    }

    @Test
    void addUser_DuplicateEmail_ShouldThrowAlreadyExistsException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Yateesh");
        userDTO.setEmail("yateesh@nucleusteq.com");
        userDTO.setPassword("password123");
        userDTO.setPhoneNumber("1234567890");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(AlreadyExistsException.class, () -> userService.addUser(userDTO));
    }

    @Test
    void loginUser_ValidCredentials_ShouldReturnLoginResponse() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("yateesh@nucleusteq.com");
        loginDTO.setPassword("password123");

        User user = new User();
        user.setEmail("yateesh@nucleusteq.com");
        user.setPassword("encodedPassword");
        user.setUserRole("USER");
        user.setUserId(1L);
        user.setName("Yateesh");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(true);
        when(userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), user.getPassword())).thenReturn(Optional.of(user));

        LoginResponse response = userService.loginUser(loginDTO);

        assertTrue(response.getStatus());
        assertEquals("USER", response.getUserRole());
        assertEquals(1L, response.getUserId());
        assertEquals("Yateesh", response.getName());
        assertEquals("yateesh@nucleusteq.com", response.getEmail());
    }

    @Test
    void loginUser_InvalidEmail_ShouldThrowUnAuthorizedException() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("nonexistent@nucleusteq.com");
        loginDTO.setPassword("password123");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());
        assertThrows(UnAuthorizedException.class, () -> userService.loginUser(loginDTO));
    }

    @Test
    void deleteUser_ExistingUser_ShouldDeleteUser() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);
    }

    @Test
    void deleteUser_NonExistingUser_ShouldThrowElementNotExistsException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> userService.deleteUser(userId));
    }

    @Test
    void testLoginUser_InvalidPassword_ShouldThrowUnAuthorizedException() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("yateesh@nucleusteq.com");
        loginDTO.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("yateesh@nucleusteq.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(UnAuthorizedException.class, () -> userService.loginUser(loginDTO));
    }

    @Test
    void testGetUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Yateesh", "yateesh.chandra@nucleusteq.com", "yateesh", "USER", "9876543201"));
        users.add(new User(2L, "Chandra", "chandra.yateesh@nucleusteq.com", "chandra", "USER", "7932590431"));

        when(userRepository.findAll()).thenReturn(users);

        List<UserListDTO> userList = userService.getUsers();
        assertNotNull(userList);
        assertEquals("Yateesh", userList.get(0).getName());
        assertEquals(1L, userList.get(0).getUserId());
        assertEquals("Chandra", userList.get(1).getName());
        assertEquals(2L, userList.get(1).getUserId());
    }
}
