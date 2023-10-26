package com.capstoneproject.controller;

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
     * checks the login response.
     * @param messagee of String Type.
     * @param statuss of boolean type is checked.
     */
    public LoginResponse(final String messagee, final Boolean statuss) {
        super();
        this.message = messagee;
        this.status = statuss;
    }
}
