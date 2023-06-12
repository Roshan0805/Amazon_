package com.amazon.service.impl;

import com.amazon.model.Product;
import com.amazon.service.AmazonProductService;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Implements the AmazonProductService to provide services for {@link  Product} for get products, update products and delete products
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonProductServiceImpl implements AmazonProductService {

    private static final Map<Long, Product> PRODUCT_LIST = new LinkedHashMap<>();
    private static long productId = 1;
    private static AmazonProductServiceImpl amazonProductService = null;

    private AmazonProductServiceImpl() {
    }

    /**
     * Represents the object of AmazonProductServiceImpl class can be created for only one time
     * @return Represents {@link AmazonProductServiceImpl}
     */
    public static AmazonProductServiceImpl getAmazonProductService() {
        if (amazonProductService == null) {
            amazonProductService = new AmazonProductServiceImpl();
        }

        return amazonProductService;
    }

    /**
     * {@inheritDoc}
     *
     * @param product Represents {@link Product}
     * @return True if the product is added successfully
     */
    public boolean add(final Product product) {
        if (null == product) {
            return false;
        }
        product.setId(productId);
        PRODUCT_LIST.put(productId++, product);

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return Collection view of product's
     */
    public Collection<Product> getAllProducts() {
        return PRODUCT_LIST.values();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link Product} from the product list
     */
    public Product get(final long productId) {
        return PRODUCT_LIST.get(productId);
    }

    /**
     * {@inheritDoc}
     *
     * @param id      Represents the id of the product
     * @param product Represents {@link Product}
     * @return True if the product name is updated successfully
     */
    public boolean update(long id, Product product) {
        if (product == null || id > PRODUCT_LIST.size()) {
            return false;
        }
        PRODUCT_LIST.put(id, product);

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents id for deleting product
     * @return True if the product is deleted successfully
     */
    public boolean delete(final long id) {
        if (getIds().contains(id)) {
            PRODUCT_LIST.remove(id);
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
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
