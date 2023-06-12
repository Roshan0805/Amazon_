package com.amazon.controller;

import com.amazon.model.Product;
import com.amazon.service.AmazonProductService;
import com.amazon.service.impl.AmazonProductServiceImpl;
import com.amazon.service.impl.AmazonUserServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Acts as a Control between service and the view to provide product view, delete and update
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonProductController {

    private static final AmazonProductService AMAZON_PRODUCT_SERVICE = AmazonProductServiceImpl.getAmazonProductService();
    private static AmazonProductController amazonProductController = null;

    private AmazonProductController() {
    }

    /**
     * <p>
     * Represents the object of AmazonProductController class can be created for only one time
     * </p>
     *
     * @return Represents {@link AmazonProductController}
     */
    public static AmazonProductController getAmazonProductController() {
        if (amazonProductController == null) {
            amazonProductController = new AmazonProductController();
        }
        return amazonProductController;
    }

    /**
     * <p>
     * adds the product object to product list
     * </p>
     *
     * @param product Product object
     * @return boolean value from addProduct method
     */
    public boolean add(final Product product) {
        return AMAZON_PRODUCT_SERVICE.add(product);
    }

    /**
     * <p>
     * Gets the products from product list
     * </p>
     *
     * @return Collection view of products from the viewProduct method
     */
    public Collection<Product> getAllProducts() {
        return AMAZON_PRODUCT_SERVICE.getAllProducts();
    }

    /**
     * <p>
     * get the product object from the product list
     * </p>
     *
     * @param productId id of the product
     * @return Represents {@link Product}
     */
    public Product get(final long productId) {
        return AMAZON_PRODUCT_SERVICE.get(productId);
    }

    /**
     * <p>
     * Updates the product in product list
     * </p>
     *
     * @param id      id of the product
     * @param product Represents {@link Product}
     * @return boolean Value from updateProduct method
     */
    public boolean update(final long id, final Product product) {
        return AMAZON_PRODUCT_SERVICE.update(id, product);
    }

    /**
     * <p>
     * Deletes the product from product list
     * </p>
     *
     * @param id id of the product
     * @return Boolean value from the deleteProduct method
     */
    public boolean delete(final int id) {
        return AMAZON_PRODUCT_SERVICE.delete(id);
    }
}