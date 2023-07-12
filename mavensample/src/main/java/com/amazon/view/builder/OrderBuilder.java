package com.amazon.view.builder;

import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.view.ProductView;

public class OrderBuilder extends ProductView {

    private static final OrderBuilder ORDER_BUILDER = new OrderBuilder();
    private static Long orderId = 1L;

    private OrderBuilder() {
    }

    public static OrderBuilder getInstance() {
        return ORDER_BUILDER;
    }

    public Order buildOrder(final Long userId) {

        System.out.println("Enter the product id for order");

        try {
            final Long productId = Long.parseLong(SCANNER.nextLine());

            if (PRODUCT_VALIDATION.isReturnToMenu(String.valueOf(productId))) {
                USER_VIEW.getUserOptions();
            }
            System.out.println("Enter the quantity you want in number");
            final Long quantity = Long.parseLong(SCANNER.nextLine().trim());
            final Order order = new Order();
            final Product product = PRODUCT_CONTROLLER.get(productId);

            order.setId(orderId++);
            order.setProductId(productId);
            order.setProductName(product.getName());
            order.setProductCount(quantity);
            order.setUserId(userId);
            order.setTotalPrice(product.getPrice() * quantity);
            order.setPaymentType(getPaymentType());

            return order;
        } catch (NumberFormatException exception) {
            System.out.println("Enter the correct product id");
            buildOrder(userId);
        }
        return null;
    }
}
