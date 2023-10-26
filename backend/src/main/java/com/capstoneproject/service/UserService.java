package com.capstoneproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.dto.UserListDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.UnAuthorizedException;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.response.ExceptionMessages;
import com.capstoneproject.response.LoginResponse;
import com.capstoneproject.response.SuccessMessages;

/**
 * Service class for handling user-related Operations.
 */
@Service
public class UserService {
    /**
     * The repository variable for user related operations.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The Encoder variable to encode the password entered.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creating Instance for logger.
     */
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Add the user.
     *
     * @param userDTO The user data to create.
     * @return the name of the user.
     */
    public final String addUser(final UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            logger.error(ExceptionMessages.EMAIL_ALREADY_EXISTS);
            throw new AlreadyExistsException(
                    ExceptionMessages.EMAIL_ALREADY_EXISTS);
        }
        logger.info(SuccessMessages.REGISTRATION_SUCCESS);
        User users = new User(userDTO.getUserId(), userDTO.getName(),
                    userDTO.getEmail(),
                    this.passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getUserRole(), userDTO.getPhoneNumber());
        userRepository.save(users);
        return users.getName();
    }

    /**
     * Login a user having the provided credentials.
     *
     * @param loginDTO The Login credentials.
     * @return A LoginResponse object indicating the result of the login
     *         attempt.
     */
    public final LoginResponse loginUser(final LoginDTO loginDTO) {
        Optional<User> user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1.isPresent()) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.get().getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password,
                    encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(
                        loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    logger.info(SuccessMessages.LOGIN_SUCCESS);
                    return new LoginResponse(SuccessMessages.LOGIN_SUCCESS,
                            true, user.get().getUserRole(),
                            user.get().getUserId(), user.get().getName(),
                            user.get().getEmail());
                } else {
                    logger.error(ExceptionMessages.LOGIN_FAIL);
                    throw new UnAuthorizedException(
                            ExceptionMessages.LOGIN_FAIL);
                }
            } else {
                logger.error(ExceptionMessages.INCORRECT_PWD);
                throw new UnAuthorizedException(
                        ExceptionMessages.INCORRECT_PWD);
            }
        } else {
            logger.error(ExceptionMessages.EMAIL_NOT_EXISTS);
            throw new UnAuthorizedException(
                    ExceptionMessages.EMAIL_NOT_EXISTS);
        }
    }

    /**
     * This is the method to find by Email.
     * @param userId of Long type is taken as input.
     */
    public void deleteUser(final Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ElementNotExistsException(
                        ExceptionMessages.USER_NOT_EXIST));
        logger.info(SuccessMessages.USER_DELETE_SUCCESS);
        userRepository.deleteById(userId);
    }

    /**
     * This is the Method to get all users.
     * @return the users list.
     */
    public List<UserListDTO> getUsers() {
        List<User> users = userRepository.findAll();
        logger.info(SuccessMessages.USER_FETCH);
        return users.stream().map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    /**
     * This is the method to convert the model to DTO.
     * @param user of User type.
     * @return the DTO of the model.
     */
    private UserListDTO convertModelToDto(final User user) {
        logger.info(SuccessMessages.MODEL_TO_DTO);
        UserListDTO userListDto = new UserListDTO();
        userListDto.setUserId(user.getUserId());
        userListDto.setName(user.getName());
        userListDto.setEmail(user.getEmail());
        userListDto.setUserRole(user.getUserRole());
        userListDto.setPhoneNumber(user.getPhoneNumber());
        return userListDto;
    }
}
