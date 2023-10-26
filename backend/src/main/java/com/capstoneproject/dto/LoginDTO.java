/**
 * This package contains the Login DTO class.
 */
package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains Data Transfer Objects.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    /**
     * This is email we have to input while logging in.
     */
    @NotBlank(message = ValidationMessages.EMAIL_NOT_BLANK)
    private String email;

    /**
     * This is password we have to input while logging in.
     */
    @NotBlank(message = ValidationMessages.PASSWORD_NOT_NULL)
    private String password;
}
