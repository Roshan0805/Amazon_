package com.amazon.view.validation;

/**
 * <p>
 * Provides the methods for validates the user wants to return back to menu and user wants to continue in process
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class Validation {

    private static final Validation amazonValidation = new Validation();

    Validation() {
    }

    /**
     * <p>
     * Represents the object of {@link Validation} class can be created for only one time
     * </p>
     *
     * @return Represents {@link Validation}
     */
    public static Validation getInstance() {
        return amazonValidation;
    }

    /**
     * <p>
     * Check's whether the user value to determine the user wants to return back to menu
     * </p>
     *
     * @param value Represents the value for validate
     * @return True if the value is matches the pattern otherwise return false
     */
    public boolean isReturnToMenu(final String value) {
        return ('#' == value.charAt(0));
    }

    /**
     * <p>
     * Check's whether the user value to determine the user wants to update or not
     * </p>
     *
     * @param value Represents the value for validate
     * @return True if the value is matches the pattern otherwise return false
     */
    public boolean updateChoiceValidation(final String value) {
        return (value.equalsIgnoreCase("y") || value.equalsIgnoreCase("yes"));
    }

    /**
     * <p>
     * Check's whether the user wants to exit or not
     * </p>
     *
     * @param userChoice Represents value for validate
     * @return True if the value is matches the pattern otherwise return false
     */
    public boolean toContinueValidation(final String userChoice) {
        return ('y' == userChoice.charAt(0) || 'Y' == userChoice.charAt(0));
    }
}
