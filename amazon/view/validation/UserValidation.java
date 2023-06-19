package com.amazon.view.validation;

/**
 * <p>
 * Validates the email, password, username, address, phone number
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserValidation extends Validation {

    private static final UserValidation amazonUserValidation = new UserValidation();

    private UserValidation() {
    }

    /**
     * <p>
     * Represents the object of {@link UserValidation} class can be created for only one time
     * </p>
     *
     * @return Represents {@link UserValidation}
     */
    public static UserValidation getInstance() {
        return amazonUserValidation;
    }

    /**
     * <p>
     * Validates the email format enter by the user
     * </p>
     *
     * @param email Represents email that is entered by the user
     * @return True if the email is matches pattern otherwise return false
     */
    public boolean validateEmail(final String email) {
        return email.matches("^[a-zA-z]+[a-zA-Z0-9_.\\S]+@[a-z]{3,}\\.[a-z\\S]{2,3}$");
    }

    /**
     * <p>
     * Validates the email format enter by the user
     * </p>
     *
     * @param email Represents email that is entered by the user
     * @return True if the email is matches pattern otherwise return false
     */
    public boolean validateId(final String email) {
        return email.matches("^[0-9]+$");
    }

    /**
     * <p>
     * Validates the password format enter by the user
     * </p>
     *
     * @param password Represents password that is entered by the user
     * @return True if the password matches the pattern otherwise return false
     */
    public boolean validatePassword(final String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).\\S{7,20}$");
    }

    /**
     * <p>
     * Validates username format enter by the user
     * </p>
     *
     * @param userName Represents users name that is entered by user
     * @return True if the users name matches the pattern
     */
    public boolean validateUserName(final String userName) {
        return userName.matches("^[a-zA-Z][a-zA-Z0-9]{3,16}");
    }

    /**
     * <p>
     * Validates user address format enter by the user
     * </p>
     *
     * @param address Represents user address
     * @return True if the address is valid
     */
    public boolean validateAddress(final String address) {
        return address.matches("^[0-9]+/[0-9]{1,3}[\\w*\\s*[0-9]*]{2,}");
    }

    /**
     * <p>
     * Validates the phone no format enter by the user
     * </p>
     *
     * @param phoneNumber Represents phone number entered by the user
     * @return True if the Phone number matches the pattern otherwise returns false
     */
    public boolean validatePhone(final String phoneNumber) {
        return phoneNumber.matches("^[6-9][0-9]{9}+$");
    }

    /**
     * <p>
     * Check's whether the user value to determine the user wants to return back to menu
     * </p>
     *
     * @param value Represents value for validate
     * @return True if the value is matches the pattern otherwise return false
     */
    public boolean isReturnToMenu(final String value) {
        return ('#' == value.charAt(0));
    }

}