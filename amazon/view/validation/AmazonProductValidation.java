package com.amazon.view.validation;

import com.amazon.model.Product.Category;

/**
 * <p>
 * Validates the product Category name enter by the user
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonProductValidation extends AmazonValidation {

    private static AmazonProductValidation amazonProductValidation = null;

    private AmazonProductValidation() {
    }

    /**
     * <p>
     * Represents the object of AmazonProductValidation class can be created for only one time
     * </p>
     *
     * @return Represents {@link AmazonProductValidation}
     */
    public static AmazonProductValidation getAmazonProductValidation() {
        if (amazonProductValidation == null) {
            return new AmazonProductValidation();
        } else {
            return amazonProductValidation;
        }
    }

    /**
     * <p>
     * Validates the category name of the product enter by the admin user
     * </p>
     *
     * @param category Category name entered by the user
     * @return True if the pattern matches the pattern otherwise return false
     */
    public Category validateCategory(final String category) {
        return Category.valueOf(category);
    }

    /**
     * <p>
     * Validates the product price
     * </p>
     *
     * @param price Product price for validation
     * @return True is the price match the pattern
     */
    public boolean validatePrice(final String price) {
        return price.matches("^[\\d\\S]+$");
    }

}
