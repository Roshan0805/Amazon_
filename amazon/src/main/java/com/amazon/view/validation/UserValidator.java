package com.amazon.view.validation;

/**
 * <p>
 * Validates the user details
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserValidator extends CommonValidator {

    private static final UserValidator UserValidator = new UserValidator();

    private UserValidator() {
    }

    /**
     * <p>
     * Represents the {@link UserValidator} class object can be created for only once
     * </p>
     *
     * @return {@link UserValidator}
     */
    public static UserValidator getInstance() {
        return UserValidator;
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
        final String emailRegex = "^[a-zA-z]+[a-zA-Z0-9_.\\S]+@[a-z]{3,}\\.[a-z\\S]{2,3}$";

        return email.matches(emailRegex);
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
        final String idRegex = "^[0-9]+$";

        return email.matches(idRegex);
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
        final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).\\S{7,20}$";

        return password.matches(passwordRegex);
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
        final String nameRegex = "^[a-zA-Z][a-zA-Z0-9]{3,16}";

        return userName.matches(nameRegex);
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
        final String addressRegex = "^[0-9]+/[0-9]{1,3}[\\w*\\s*[0-9]*]{2,}";

        return address.matches(addressRegex);
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
        final String phoneNumberRegex = "^[6-9][0-9]{9}+$";

        return phoneNumber.matches(phoneNumberRegex);
    }
}