package com.amazon.view.validation;

import com.amazon.model.Product.Category;
import com.amazon.controller.AmazonProductController;

/**
 * <p>
 * Validates the product {@link Category} and product id enter by the user
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonProductValidation extends AmazonValidation {

    private static final AmazonProductValidation AMAZON_PRODUCT_VALIDATION = new AmazonProductValidation();

    private static final AmazonProductController AMAZON_PRODUCT_CONTROLLER =
            AmazonProductController.getAmazonProductController();

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
        return AMAZON_PRODUCT_VALIDATION;
    }

    /**
     * <p>
     * Validates the category name of the product enter by the admin user
     * </p>
     *
     * @param category Represents {@link Category} entered by the user
     * @return True if the pattern matches the pattern otherwise return false
     */
    public Category validateCategory(final String category) {
        return Category.valueOf(category);
    }

    /**
     * <p>
     * Validates the admin enter id with the product id of the product that is added by the user
     * </p>
     *
     * @param id Represents the id entered by the user
     * @return True if the user entered id matches the get products id
     */
    public boolean validateIds(final Long id) {
        return AMAZON_PRODUCT_CONTROLLER.getAllProducts(id).containsKey(id);
    }
}
