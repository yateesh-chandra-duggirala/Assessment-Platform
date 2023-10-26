/**
 * This package contains the Registration DTO class.
 */
package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is the Data Transfer Object for User Registration.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * This ID_MIN_LENGTH contains the minimum value to be the value in column.
     */
    private static final int MIN_LENGTH = 8;

    /**
     * This is the User Id field.
     */
    private Long userId;

    /**
     * This is the name field to be entered by new User.
     */
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;

    /**
     * This is the email field to be entered by new User.
     */
    @NotBlank(message = ValidationMessages.EMAIL_NOT_BLANK)
    @Pattern(regexp = "^[a-z][a-zA-Z0-9.]*@nucleusteq\\.com",
    message = ValidationMessages.EMAIL_PATTERN)
    private String email;

    /**
     * This is the password field to be entered by new User.
     */
    @NotBlank(message = ValidationMessages.PASSWORD_NOT_NULL)
    @Size(min = MIN_LENGTH, message = ValidationMessages.PASSWORD_LENGTH)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
    message = ValidationMessages.PASSWORD_PATTERN)
    private String password;

    /**
     * This is the userRole field which is user by default.
     */
    private String userRole = "USER";

    /**
     * This is the phone Number field to be entered by new User.
     */
    @NotBlank(message = ValidationMessages.PHONE_NULL)
    @Pattern(regexp = "\\d{10}", message = ValidationMessages.PHONE_PATTERN)
    private String phoneNumber;
}
