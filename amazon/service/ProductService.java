package com.amazon.service;

import com.amazon.model.Product;
import com.amazon.service.impl.ProductServiceImpl;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Provides services for {@link Product}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface ProductService {

    /**
     * <p>
     * Represents the {@link ProductService} interface implemented class object can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link ProductServiceImpl}
     */
    static ProductService getInstance() {
        return ProductServiceImpl.getInstance();
    }

    /**
     * <p>
     * Add product to the product list
     * </p>
     *
     * @param product Product object
     * @return Boolean true is the {@link Product} added successfully in the product list otherwise return false
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

    /**
     * Represents the product details that the user create
     *
     * @param id Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    Map<Long, Product> getUserProduct(final Long id);

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
     * @return True if the {@link Product} is updated successfully in the product list otherwise return false
     */
    boolean update(final long id, final Product product);

    /**
     * <p>
     * Deletes the product object from the product list
     * </p>
     *
     * @param id id of the product object
     * @return True if the {@link Product} deleted successfully in the product list otherwise retuirn false
     */
    boolean delete(final long id);
}