package com.amazon.view.validation;

public class AmazonProductValidation {

    public boolean validateCategory(final String category) {
        return category.matches("^[a-zA-Z_]+$");
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
     * Check's whether the user value to determine the user wants to update or not
     *
     * @param userChoice value for validate
     * @return boolean true if the value is matches the pattern otherwise return false
     */
    public boolean updateChoiceValidation(final String userChoice) {
        if ('y' == userChoice.charAt(0) || 'Y' == userChoice.charAt(0)) {
            return true;
        }
        return false;
    }
}
