package com.amazon.view.validation;

/**
 * Validation
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonUserValidation {

    /**
     * Validates the email format enter by the user
     *
     * @param email Email that is entered by the user
     * @return true if the email is matches pattern otherwise return false
     */
    public boolean validateEmail(final String email) {
        return email.matches("^[a-zA-z]+[a-zA-Z0-9_.\\S]+@[a-z]{3,}\\.[a-z\\S]{2,3}$");
    }

    /**
     * Validates the password format enter by the user
     *
     * @param password Password that is entered by the user
     * @return True if the password matches the pattern otherwise return false
     */
    public boolean validatePassword(final String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).\\S{7,20}$");
    }

    /**
     * Validates username format enter by the user
     *
     * @param userName users name that is entered by user
     * @return boolean true the users name matches the pattern
     */
    public boolean validateUserName(final String userName) {
        return userName.matches("^[a-zA-Z][a-zA-Z0-9]{3,16}");
    }

    /**
     * Validates user address format enter by the user
     *
     * @param address user address
     * @return boolean true if the address is valid
     */
    public boolean validateAddress(final String address) {
        return address.matches("^[0-9]+/[0-9]{1,3}[\\w*\\s*[0-9]*]{2,}");
    }

    /**
     * Validates the phone no format enter by the user
     *
     * @param phoneNo Phone number entered by the user
     * @return True if the Phone number matches the pattern otherwise returns false
     */
    public boolean validatePhone(final String phoneNo) {
        return phoneNo.matches("^[6-9][0-9]{9}+$");
    }

    /**
     * Validates the value is number
     *
     * @param value value for validate
     * @return boolean true if the value is matches the pattern otherwise return false
     */
    public boolean validateAsNumber(final String value) {
        return value.matches("^\\d+$");
    }

    /**
     * Check's whether the user value to determine the user wants to return back to menu
     *
     * @param valueForCheck value for validate
     * @return boolean true if the value is matches the pattern otherwise return false
     */
    public boolean returnToMenu(final String valueForCheck) {
        if ('#' == valueForCheck.charAt(0)) {
            return true;
        }
        return false;
    }

    /**
     * Check's whether the user wants to exit or not
     *
     * @param userChoice value for validate
     * @return boolean true if the value is matches the pattern otherwise return false
     */
    public boolean exitChoiceValidation(final String userChoice) {
        if ('y' == userChoice.charAt(0) || 'Y' == userChoice.charAt(0)) {
            return true;
        }
        return false;
    }
}
