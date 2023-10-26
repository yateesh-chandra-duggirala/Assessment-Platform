package com.capstoneproject.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.dto.UserListDTO;
import com.capstoneproject.response.LoginResponse;
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
import com.capstoneproject.service.UserService;

import jakarta.validation.Valid;

/**
 * This is the Controller class for User.
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    /**
     * This variable is used to perform operations on UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * Creating Instance for logger.
     */
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * This method is used for new users to register.
     * @param userDTO of UserDTO class.
     * @return the id after registering.
     */
    @SuppressWarnings("rawtypes")
    @PostMapping(path = "/save")
    public final Response saveUser(
            @RequestBody @Valid final UserDTO userDTO) {
            userService.addUser(userDTO);
            logger.info(SuccessMessages.REGISTRATION_SUCCESS);
            Response response = new Response(HttpStatus.OK.value(),
                    SuccessMessages.REGISTRATION_SUCCESS);
            return response;
    }

    /**
     * This is for Existing users to login.
     *
     * @param loginDTO of LoginDTO.
     * @return the Id if it exists.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(path = "/login")
    public final Response loginUser(
            @RequestBody @Valid final LoginDTO loginDTO) {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            logger.info(SuccessMessages.LOGIN_SUCCESS);
            Response response = new Response(HttpStatus.OK.value(),
                    SuccessMessages.LOGIN_SUCCESS, loginResponse);
            return response;
    }

    /**
     * This is for deleting the User by User Id.
     * @param userId - of Long type.
     * @return the no content status.
     */
    @SuppressWarnings("rawtypes")
    @DeleteMapping(path = "/{userId}")
    public final Response deleteUser(
            @PathVariable final Long userId) {
        userService.deleteUser(userId);
        logger.info(SuccessMessages.USER_DELETE_SUCCESS);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.USER_DELETE_SUCCESS);
        return response;
    }

    /**
     * This is the controller method to get all users.
     * @return the response.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping
    public final Response getUsers() {
        List<UserListDTO> userDto = userService.getUsers();
        logger.info(SuccessMessages.USER_FETCH);
        Response response = new Response(HttpStatus.OK.value(),
                SuccessMessages.USER_FETCH, userDto);
        return response;
    }
}
