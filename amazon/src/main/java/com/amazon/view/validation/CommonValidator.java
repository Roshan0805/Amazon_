package com.amazon.view.validation;

/**
 * <p>
 *     Represents the common validator it represents the common methods in user and product validator
 * </p>
 *
 * @author Roshan B
 * @version  1.0
 */
public class CommonValidator {

    private static final CommonValidator COMMON_VALIDATOR = new CommonValidator();

    protected CommonValidator() {}

    /**
     * <p>
     * Represents the object of {@link CommonValidator} class can be created for only one time
     * </p>
     *
     * @return Represents {@link CommonValidator}
     */
    public static CommonValidator getInstance(){
        return COMMON_VALIDATOR;
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
        return '#' == value.charAt(0);
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
