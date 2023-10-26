package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for getting users List.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {

    /**
     * This is the Id field to be entered by new User.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * This is the userRole field which is user by default.
     */
    private String userRole;

    /**
     * This is the phone Number field to be entered by new User.
     */
    @NotBlank(message = ValidationMessages.PHONE_NULL)
    @Pattern(regexp = "\\d{10}", message = ValidationMessages.PHONE_PATTERN)
    private String phoneNumber;

}
