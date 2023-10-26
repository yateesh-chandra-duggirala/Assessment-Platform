package com.capstoneproject.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class containing the Login response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    /**
     * message variable of String type.
     */
    private String message;
    /**
     * status variable of Boolean type.
     */
    private Boolean status;
    /**
     * userRole of String type having values Admin and User.
     */
    private String userRole;
    /**
     * This is the User Id.
     */
    private Long userId;
    /**
     * This is User Name.
     */
    private String name;
    /**
     * This is the email.
     */
    private String email;

    /**
     * checks the login response.
     * @param messagee of String Type.
     * @param statuss of boolean type is checked.
     */
    public LoginResponse(final String messagee, final Boolean statuss) {
        this.message = messagee;
        this.status = statuss;
    }
}
