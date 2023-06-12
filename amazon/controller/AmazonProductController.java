package com.amazon.controller;

import com.amazon.model.Product;
import com.amazon.service.AmazonProductService;
import com.amazon.service.impl.AmazonProductServiceImpl;

import java.util.Collection;

/**
 * Provides Control between service and the view to provide product view, delete and update
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonProductController {

    private static final AmazonProductService AMAZON_PRODUCT_SERVICE = AmazonProductServiceImpl .getAmazonProductService();
    private static AmazonProductController amazonProductController = null;

    private AmazonProductController() {}

    public static AmazonProductController getAmazonProductController() {
        if (amazonProductController ==  null) {
            amazonProductController = new AmazonProductController();
        }
        return amazonProductController;
    }

    /**
     * adds the product object to product list
     *
     * @param product Product object
     * @return boolean value from addProduct method
     */
    public boolean add(final Product product) {
        return AMAZON_PRODUCT_SERVICE.add(product);
    }

    /**
     * Gets the products from product list
     *
     * @return Collection view of products from the viewProduct method
     */
    public Collection<Product> getAllProducts() {
        return AMAZON_PRODUCT_SERVICE.getAllProducts();
    }

    /**
     * get the product object from the product list
     *
     * @param productId id of the product
     * @return Represents {@link Product}
     */
    public Product get(final long productId) {
        return AMAZON_PRODUCT_SERVICE.get(productId);
    }

    /**
     * Updates the product in product list
     *
     * @param id      id of the product
     * @param product Represents {@link Product}
     * @return boolean Value from updateProduct method
     */
    public boolean update(final long id, final Product product) {
        return AMAZON_PRODUCT_SERVICE.update(id, product);
    }

    /**
     * Deletes the product from product list
     *
     * @param id id of the product
     * @return Boolean value from the deleteProduct method
     */
    public boolean delete(final int id) {
        return AMAZON_PRODUCT_SERVICE.delete(id);
    }
}