package com.amazon.service;

import com.amazon.model.Product;

import java.util.Collection;

public interface AmazonProductService {
    /**
     * Add product to the product list
     *
     * @param product Product object
     * @return Boolean true is the product is added successfully
     */
    boolean add(final Product product);

    /**
     * Provide the collection view of the products value
     *
     * @return Collection view of product's view
     */
    Collection<Product> getAllProducts();

    /**
     * Gets the product from product list using productId
     * @param productId product id of the product object
     * @return Product object
     */
    Product get(final int productId);
    /**
     * Updates protect in product list
     *
     * @param id          Product id of the product
     * @param product  Represent {@link Product}
     * @return Boolean true if the product name is updated successfully
     */
    boolean update(final long id, final Product product);

    boolean delete(final long id);

    /**
     * Get the product's id from the product list
     *
     * @return Set of product's id
     */
    Collection<Long> getIds();
}

