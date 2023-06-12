package com.amazon.view.validation;

import com.amazon.view.AmazonView;

/**
 * <p>
 * Provides the methods for validates the user want to return back to menu and user wants to continue in process
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonValidation {

    private static AmazonValidation amazonValidation  = null;

    AmazonValidation() {}

    /**
     * <p>
     *     Represents the object of AmazonValidation class can be created for only one time
     * </p>
     * @return Represents {@link AmazonValidation}
     */
    public static AmazonValidation getAmazonValidation() {
        if(amazonValidation == null) {
            return new AmazonValidation();
        } else {
            return amazonValidation;
        }
    }
    /**
     * <p>
     * Check's whether the user value to determine the user wants to return back to menu
     * </p>
     *
     * @param value value for validate
     * @return True if the value is matches the pattern otherwise return false
     */
    public boolean isReturnToMenu(final String value) {
        if ('#' == value.charAt(0)) {
            return true;
        }
        return false;
    }

    /**
     * <p>
     * Check's whether the user value to determine the user wants to update or not
     * </p>
     *
     * @param value value for validate
     * @return True if the value is matches the pattern otherwise return false
     */
    public boolean updateChoiceValidation(final String value) {
        if ('y' == value.charAt(0) || 'Y' == value.charAt(0)) {
            return true;
        }
        return false;
    }
}
