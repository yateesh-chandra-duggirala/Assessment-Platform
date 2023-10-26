package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.dto.UserListDTO;
import com.capstoneproject.response.LoginResponse;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.UserService;

@SuppressWarnings("rawtypes")
class UserControllerTest {

    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testSaveUser() {

        UserDTO userDTO = new UserDTO();
        when(userService.addUser(userDTO)).thenReturn(null);

        Response response = userController.saveUser(userDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.REGISTRATION_SUCCESS, response.getMessage());

    }

    @Test
    public void testLoginUser() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("test@nucleusteq.com");
        when(userService.loginUser(loginDto)).thenReturn(new LoginResponse());

        Response response = userController.loginUser(loginDto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.LOGIN_SUCCESS, response.getMessage());
    }

    @Test
    public void deleteUser() {
        Long userId = 1L;
        Response response = userController.deleteUser(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(SuccessMessages.USER_DELETE_SUCCESS, response.getMessage());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetUsers() {

        List<UserListDTO> userListDTOs = new ArrayList<>();
        userListDTOs.add(new UserListDTO(1L, "User1", "user1@nucleusteq.com", "USER", "9876543210"));
        userListDTOs.add(new UserListDTO(2L, "User2", "user1@nucleusteq.com", "USER", "9876543201"));

        when(userService.getUsers()).thenReturn(userListDTOs);

        Response response = userController.getUsers();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<UserListDTO> responseBody = (List<UserListDTO>) response.getBody();
        assertNotNull(responseBody);
        assertEquals(2, responseBody.size());
        assertEquals(1L, responseBody.get(0).getUserId());
        assertEquals("User1", responseBody.get(0).getName());
        assertEquals(2L, responseBody.get(1).getUserId());
        assertEquals("User2", responseBody.get(1).getName());

    }
}
