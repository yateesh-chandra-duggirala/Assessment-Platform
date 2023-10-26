package com.capstoneproject.response;

/**
 * This class contains Validation Messages.
 */
public final class ValidationMessages {

    /**
     * No Arguments constructor.
     */
    private ValidationMessages() {
    }

    /**
     * Message shown when name is blank.
     */
    public static final String NAME_NOT_BLANK = "User Name is required";

    /**
     * Message shown when Email is blank.
     */
    public static final String EMAIL_NOT_BLANK = "Email is required";

    /**
     * Message shown when email is of invalid pattern.
     */
    public static final String EMAIL_PATTERN = "Invalid Email Format";

    /**
     * Message shown when Password is not null.
     */
    public static final String PASSWORD_NOT_NULL = "Password is required";

    /**
     * Message shown when password is not valid.
     */
    public static final String PASSWORD_PATTERN = "Password must contain"
            + " at least one uppercase letter and one digit";

    /**
     * Message shown when Phone number is not valid.
     */
    public static final String PHONE_PATTERN = "Phone Number should be"
            + " 10 Digits";

    /**
     * Message shown when Phone number is blank.
     */
    public static final String PHONE_NULL = "Phone Number Can not be null";

    /**
     * Message shown when Category Id is null.
     */
    public static final String CATEGORY_ID_NOTNULL = "Category Id is Required";

    /**
     * Message shown when Category Name is Blank.
     */
    public static final String CATEGORY_NAME_NOTBLANK = "Category Name is"
            + " Required";

    /**
     * Message shown when Category Description is Blank.
     */
    public static final String CATEGORY_DESC_NOTBLANK = "Category Description"
            + " is Required";

    /**
     * Message shown when Quiz Id is null.
     */
    public static final String QUIZ_ID_NOTNULL = "Quiz Id is required";

    /**
     * Message shown when Quiz Name is Blank.
     */
    public static final String QUIZ_NAME_NOTBLANK = "Quiz Name is Required";

    /**
     * Message shown when Quiz Description is null.
     */
    public static final String QUIZ_DESC_NOTBLANK = "Quiz Description is"
            + " Required";

    /**
     * Message shown when Time is null.
     */
    public static final String TIME_NOTNULL = "Time is Required";

    /**
     * Message shown when Number of Questions is null.
     */
    public static final String QUESTIONS_NOTNULL = "Number Of Questions"
            + " is Required";

    /**
     * Message shown when Question title is null.
     */
    public static final String QUESTION_TITLE_NOTBLANK = "Question Title"
            + " is Required";

    /**
     * Message shown when Option 1 is null.
     */
    public static final String OPTION1_NOTBLANK = "Option 1 is Required";

    /**
     * Message shown when Option 2 is null.
     */
    public static final String OPTION2_NOTBLANK = "Option 2 is Required";

    /**
     * Message shown when Option 3 is null.
     */
    public static final String OPTION3_NOTBLANK = "Option 3 is Required";

    /**
     * Message shown when Option 4 is null.
     */
    public static final String OPTION4_NOTBLANK = "Option 4 is Required";

    /**
     * Message shown when correct Option is null.
     */
    public static final String CORRECT_OPTION_NOTBLANK = "Correct Option "
            + "is Required";

    /**
     * Message shown when User Id is null.
     */
    public static final String USERID_NOTNULL = "User Id is Required";

    /**
     * Message shown when Total marks field is null.
     */
    public static final String TOTAL_MARKS_NOTNULL = "Total Marks Required";

    /**
     * Message shown when Number of questions is null.
     */
    public static final String NUM_OF_QUE_NOTNULL = "Total number of Questions"
            + " Required";

    /**
     * Message shown when Marks scored is null.
     */
    public static final String MARKS_SCORED_NOTNULL = "Scored Marks field "
            + "Required";

    /**
     * Message shown when number of answered questions is null.
     */
    public static final String NUM_OF_QUE_ANSWERED_NOTNULL = "Total number of"
            + " Questions Required";

    /**
     * Message shown when password length should be 8 characters.
     */
    public static final String PASSWORD_LENGTH = "Password should be atleast"
            + " 8 characters";

}
