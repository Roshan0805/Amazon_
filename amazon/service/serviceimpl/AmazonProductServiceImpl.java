package com.amazon.service.serviceimpl;

import com.amazon.model.Product;
import com.amazon.service.AmazonProductService;

import java.util.*;

public class AmazonProductServiceImpl implements AmazonProductService {

    private static final Map<Integer, Product> PRODUCT_LIST = new LinkedHashMap<>();

    private static int productId = 1;

    /**
     * {@inheritDoc}
     *
     * @param product Product object
     * @return Boolean true is the product is added successfully
     */
    public boolean addProduct(final Product product) {
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
    public Collection<Product> viewProducts() {
        return PRODUCT_LIST.values();
    }

    /**
     * {@inheritDoc}
     *
     * @param id          Product id of the product
     * @param productName Product name for update
     * @return Boolean true if the product name is updated successfully
     */
    public boolean updateProductName(final int id, final String productName) {
        if (productName == null) {
            return false;
        }
        final Product product = PRODUCT_LIST.get(id);

        product.setName(productName);

        return true;
    }

    /**
     * Update the product description
     *
     * @param id                 Product id of the product object
     * @param productDescription Product description for update
     * @return Boolean true if the product description is updated successfully
     */
    public boolean updateProductDescription(final int id, final String productDescription) {
        final Product product = PRODUCT_LIST.get(id);

        product.setDescription(productDescription);

        return true;
    }

    /**
     * Update product price
     *
     * @param id           Product id of the product object
     * @param productPrice Product price for update
     * @return Boolean true if the price is updated successfully
     */
    public boolean updateProductPrice(final int id, final double productPrice) {
        final Product product = PRODUCT_LIST.get(id);

        product.setPrice(productPrice);

        return true;
    }

    /**
     * Delete the product from the product list
     *
     * @param id Product id for deleting product
     * @return Boolean true if the product is deleted successfully
     */
    public boolean deleteProduct(final int id) {
        PRODUCT_LIST.remove(id);

        return true;
    }

    /**
     * Get the product's id from the product list
     *
     * @return Set of product's id
     */
    public Set<Integer> getProductsId() {
        final Set<Integer> productsId = new HashSet<>();

        for (final Product product : PRODUCT_LIST.values()) {
            final int productId = product.getId();
            productsId.add(productId);
        }

        return productsId;
    }
}
