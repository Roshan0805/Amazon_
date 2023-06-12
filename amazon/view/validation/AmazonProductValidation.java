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

    private static AmazonProductValidation amazonProductValidation  = null;

    private AmazonProductValidation() {}

    /**
     * Represents the object of AmazonProductValidation class can be created for only one time
     * @return Represents {@link AmazonProductValidation}
     */
    public static AmazonProductValidation getAmazonProductValidation() {
        if(amazonProductValidation== null) {
            return new AmazonProductValidation();
        } else {
            return amazonProductValidation;
        }
    }

    /**
     * Validates the category name of the product enter by the admin user
     *
     * @param category Category name entered by the user
     * @return True if the pattern matches the pattern otherwise return false
     */
    public Category validateCategory(final String category) {
        return Category.valueOf(category);
    }

    /**
     *
     * @param price
     * @return
     */
    public boolean validatePrice(final String price) {
        return price.matches("^[\\d\\S]+$");
    }

}
