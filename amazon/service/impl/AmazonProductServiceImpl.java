package com.amazon.service.impl;

import com.amazon.model.Product;
import com.amazon.service.AmazonProductService;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class AmazonProductServiceImpl implements AmazonProductService {

    private static final Map<Long, Product> PRODUCT_LIST = new LinkedHashMap<>();

    private static long productId = 1;

    /**
     * {@inheritDoc}
     *
     * @param product Product object
     * @return Boolean true is the product is added successfully
     */
    public boolean add(final Product product) {
        if (product == null) {
            return false;
        }
        product.setId(productId);
        PRODUCT_LIST.put(productId++, product);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return Collection view of product's view
     */
    public Collection<Product> getAllProducts() {
        return PRODUCT_LIST.values();
    }

    /**
     * {@inheritDoc}
     *
     * @return Product from the product list
     */
    public Product get(final int productId) {
        return PRODUCT_LIST.get(productId);
    }

    /**
     * Updates protect in product list
     *
     * @param id      Product id of the product
     * @param product Represent {@link Product}
     * @return Boolean true if the product name is updated successfully
     */
    public boolean update(long id, Product product) {
        if (product == null || id > PRODUCT_LIST.size()) {
            return false;
        }
        PRODUCT_LIST.put(id, product);
        return true;
    }

    /**
     * Delete the product from the product list
     *
     * @param id Product id for deleting product
     * @return Boolean true if the product is deleted successfully
     */
    public boolean delete(final long id) {
        if (id > PRODUCT_LIST.size()) {
            return false;
        }
        PRODUCT_LIST.remove(id);

        return true;
    }

    /**
     * Get the product's id from the product list
     *
     * @return Set of product's id
     */
    public Collection<Long> getIds() {
        final Collection<Long> productsId = new HashSet<>();

        for (final Product product : PRODUCT_LIST.values()) {
            final long productId = product.getId();
            productsId.add(productId);
        }

        return productsId;
    }
}
