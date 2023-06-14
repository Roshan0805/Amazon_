package com.amazon.controller;

import com.amazon.model.Product;
import com.amazon.service.AmazonProductService;
import com.amazon.service.impl.AmazonProductServiceImpl;

import java.util.Collection;
import java.util.Map;

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
    private static final AmazonProductController amazonProductController = new AmazonProductController();

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
        return amazonProductController;
    }

    /**
     * <p>
     * adds the product object to product list
     * </p>
     *
     * @param product Represents {@link Product}
     * @return True if the {@link Product} is added successfully in the product list otherwise return false
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
     * Represents the product details that the user create
     *
     * @param id Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    public Map<Long, Product> getAllProducts(final long id) {
        return AMAZON_PRODUCT_SERVICE.getAllProduct(id);
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
     * @return True if the {@link Product} is updated successfully in the product list otherwise return false
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
     * @return True if the {@link Product} is deleted successfully in the product list otherwise return false
     */
    public boolean delete(final int id) {
        return AMAZON_PRODUCT_SERVICE.delete(id);
    }
}