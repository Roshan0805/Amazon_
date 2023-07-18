package com.amazon.view.builder;

import com.amazon.controller.ProductController;
import com.amazon.exception.UnavailableQuantityException;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.ProductView;

public class OrderBuilder {

    private static final OrderBuilder ORDER_BUILDER = new OrderBuilder();
    private final ProductView productView = ProductView.getInstance();
    private final ProductController productController = ProductController.getInstance();
    private static Long orderId = 1L;

    private OrderBuilder() {
    }

    /**
     * <p>
     * Represents the {@link OrderBuilder} class object can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link OrderBuilder}
     */
    public static OrderBuilder getInstance() {
        return ORDER_BUILDER;
    }

    /**
     * Represent the creation of order made by the builder method
     *
     * @param productId   Represents the id of {@link Product } to be added to the cart
     * @param quantity    Represents the quantity of the product
     * @param paymentType Represents the payment type of the order
     * @param userId      Represents the id og the {@link User}
     * @return Represents {@link Order}
     */
    public Order buildOrder(final Long productId, final Long quantity, final Order.Payment paymentType, final Long userId) {

        final Product product = productController.get(productId);
        System.out.println(product);
        if (product.getAvailable() < quantity) {
            throw new UnavailableQuantityException("Unavailable quantity");
        }
        final Order order = new Order();

        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setQuantity(quantity);
        order.setUserId(userId);
        order.setPrice(product.getPrice());
        System.out.println(product.getPrice());
        order.setPaymentType(paymentType);

        return order;
    }
}