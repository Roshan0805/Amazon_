package com.amazon.view.builder;

import com.amazon.model.Cart;
import com.amazon.model.Product;
import com.amazon.view.ProductView;
import com.amazon.view.View;

public class CartBuilder extends View {

    private static final CartBuilder CART_BUILDER = new CartBuilder();
    private final ProductView productView;

    private CartBuilder () {
        productView = ProductView.getInstance();
    }

    public static CartBuilder getInstance() {
        return CART_BUILDER;
    }

    public Cart getCart(final Long productId, final Long quantity) {
        final Cart cart = new Cart();
        final Product product = productView.getProductController().get(productId);

        cart.setProductId(productId);
        cart.setProductName(product.getName());
        cart.setQuantity(quantity);
        cart.setUserId(product.getUserId());
        cart.setPrice(product.getPrice());

        return cart;
    }
}
