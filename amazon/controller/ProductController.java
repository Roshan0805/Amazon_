package com.amazon.controller;

import com.amazon.model.Product;
import com.amazon.service.ProductService;
import com.amazon.service.impl.ProductServiceImpl;

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
public class ProductController {

    private static final ProductService PRODUCT_SERVICE = ProductServiceImpl.getInstance();
    private static final ProductController AMAZON_PRODUCT_CONTROLLER = new ProductController();

    private ProductController() {
    }

    /**
     * <p>
     * Represents the object of AmazonProductController class can be created for only one time
     * </p>
     *
     * @return Represents {@link ProductController}
     */
    public static ProductController getInstance() {
        return AMAZON_PRODUCT_CONTROLLER;
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
        return PRODUCT_SERVICE.add(product);
    }

    /**
     * <p>
     * Gets the products from product list
     * </p>
     *
     * @return Collection view of products from the viewProduct method
     */
    public Collection<Product> getAllProduct() {
        return PRODUCT_SERVICE.getAllProducts();
    }

    /**
     * Represents the product details that the user create
     *
     * @param id Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    public Map<Long, Product> getUserProduct(final Long id) {
        return PRODUCT_SERVICE.getUserProduct(id);
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
        return PRODUCT_SERVICE.get(productId);
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
        return PRODUCT_SERVICE.update(id, product);
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
        return PRODUCT_SERVICE.delete(id);
    }
}