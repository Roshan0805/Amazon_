package com.amazon.view.validation;

import com.amazon.model.Product;
import com.amazon.controller.ProductController;

/**
 * <p>
 * Validates the details of {@link Product}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductValidation extends Validation {

    private static final ProductValidation PRODUCT_VALIDATION = new ProductValidation();

    private static final ProductController PRODUCT_CONTROLLER = ProductController.getInstance();

    private ProductValidation() {
    }

    /**
     * <p>
     * Represents the object of {@link ProductValidation} class can be created for only one time
     * </p>
     *
     * @return Represents {@link ProductValidation}
     */
    public static ProductValidation getInstance() {
        return PRODUCT_VALIDATION;
    }

    /**
     * <p>
     * Validates the admin enter id with the product id of the product that is added by the user
     * </p>
     *
     * @param id Represents the id entered by the user
     * @return True if the user entered id matches the get products id
     */
    public boolean validateProductIds(final Long id, final Long userId) {
        return PRODUCT_CONTROLLER.getUserProduct(userId).containsKey(id);
    }
}