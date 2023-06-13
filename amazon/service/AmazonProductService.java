package com.amazon.service;

import com.amazon.model.Product;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Provides services for {@link Product} like add product, get product, update product and delete product
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface AmazonProductService {
    /**
     * <p>
     * Add product to the product list
     * </p>
     *
     * @param product Product object
     * @return Boolean true is the {@link Product} added successfully
     */
    boolean add(final Product product);

    /**
     * <p>
     * Provide the collection view of the products value
     * </p>
     *
     * @return Collection view of {@link Product}
     */
    Collection<Product> getAllProducts();

    Map<Long, Product> getProduct(final long id);
    /**
     * <p>
     * Gets the product from product list using productId
     * </p>
     *
     * @param productId product id of the product object
     * @return Represent {@link Product} in product list
     */
    Product get(final long productId);

    /**
     * <p>
     * Updates product object in product list
     * </p>
     *
     * @param id      Product id of the product
     * @param product Represent {@link Product}
     * @return True if the {@link Product} is updated successfully
     */
    boolean update(final long id, final Product product);

    /**
     * <p>
     * Deletes the product object from the product list
     * </p>
     *
     * @param id id of the product object
     * @return True if the {@link Product} deleted successfully
     */
    boolean delete(final long id);

}

