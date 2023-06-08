package com.amazon.service;

import com.amazon.model.Product;

import java.util.Collection;
import java.util.Set;

public interface AmazonProductService {
    /**
     * Add product to the product list
     *
     * @param product Product object
     * @return Boolean true is the product is added successfully
     */
    boolean addProduct(final Product product);

    /**
     * Provide the collection view of the products value
     *
     * @return Collection view of product's view
     */
    Collection<Product> viewProducts();

    /**
     * Update protect name of protect object
     *
     * @param id          Product id of the product
     * @param productName Product name for update
     * @return Boolean true if the product name is updated successfully
     */
    boolean updateProductName(final int id, final String productName);

    /**
     * Update the product description
     *
     * @param id                 Product id of the product object
     * @param productDescription Product description for update
     * @return Boolean true if the product description is updated successfully
     */
    boolean updateProductDescription(final int id, final String productDescription);

    /**
     * Update product price
     *
     * @param id           Product id of the product object
     * @param productPrice Product price for update
     * @return Boolean true if the price is updated successfully
     */
    boolean updateProductPrice(final int id, final double productPrice);

    /**
     * Delete the product from the product list
     *
     * @param id Product id for deleting product
     * @return Boolean true if the product is deleted successfully
     */
    boolean deleteProduct(final int id);

    /**
     * Get the product's id from the product list
     *
     * @return Set of product's id
     */
    Set<Integer> getProductsId();
}

