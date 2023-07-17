package com.amazon.view.builder;

import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.ProductView;

import java.sql.Timestamp;
import java.time.Instant;

public class ProductBuilder extends ProductView {

    private static final ProductBuilder PRODUCT_BUILDER = new ProductBuilder();

    private ProductBuilder() {}
    /**
     * <p>
     * Represents the object of {@link ProductBuilder} class can be created for only one time
     * </p>
     *
     * @return Represents object of {@link ProductBuilder}
     */
    public static ProductBuilder getInstance() {
        return PRODUCT_BUILDER;
    }

    /**
     * Represents building a product
     * @param userId Represents the id of {@link User}
     * @return Represents {@link  Product}
     */
    public Product buildProduct(final Product.Category category, final String name, final String description, final Long quantity, final Double price, final Long userId) {
        final Product product = new Product();

        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setAvailable(quantity);
        product.setPrice(price);
        product.setUpdatedTime(Timestamp.from(Instant.now()));
        product.setUserId(userId);

        return product;
    }
}
