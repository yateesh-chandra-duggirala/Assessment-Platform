package com.capstoneproject.response;

/**
 * This class contains Exception Messages thrown from exception classes.
 */
public final class ExceptionMessages {

    /**
     * No Arguments constructor.
     */
    private ExceptionMessages() {
    }

    /**
     * Message shown when Login fails.
     */
    public static final String LOGIN_FAIL = "Login Failed";

    /**
     * Message shown when Passwords are unmatched.
     */
    public static final String INCORRECT_PWD = "Invalid Password";

    /**
     * Message shown when Invalid number is identified.
     */
    public static final String INVALID_NUMBER = "Number cannot be 0 or less";

    /**
     * Message shown when Email already exists.
     */
    public static final String EMAIL_ALREADY_EXISTS = "Email already Exists";

    /**
     * Message shown when Quiz already exists.
     */
    public static final String QUIZ_ALREADY_EXISTS = "Quiz already Exists";

    /**
     * Message shown when Category already exists.
     */
    public static final String CATEGORY_ALREADY_EXISTS = "Category already"
            + " Exists";

    /**
     * Message shown when Email not exists.
     */
    public static final String EMAIL_NOT_EXISTS = "Email does not exist.!";

    /**
     * Message shown when User not exists.
     */
    public static final String USER_NOT_EXIST = "No user found with Id ";

    /**
     * Message shown when Quiz not exists.
     */
    public static final String QUIZ_NOT_EXIST = "No Quiz found with Id ";

    /**
     * Message shown when Category not exists.
     */
    public static final String CATEGORY_NOT_EXIST = "No Category found "
            + "with Id ";

    /**
     * Message shown when Question not exists.
     */
    public static final String QUESTION_NOT_EXIST = "No Question found "
            + "with Id ";

    /**
     * Message shown when Option are repeated.
     */
    public static final String OPTIONS_NOT_REPEATED = "Options should not"
            + " be repeated";

    /**
     * Message shown when Correct options does not match available Options.
     */
    public static final String OPTIONS_NOT_MATCHED = "Correct Option does not"
            + " match any option";

    /**
     * This is Question conflict field.
     */
    public static final String QUESTION_CONFLICT = "Number of Questions "
            + "answered should not be greater than Total questions";

    /**
     * This is marks conflict field.
     */
    public static final String MARKS_CONFLICT = "Scored Marks should not be "
            + "greater than Total Marks";
}
