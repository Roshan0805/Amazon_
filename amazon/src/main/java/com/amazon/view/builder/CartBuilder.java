package com.amazon.view.builder;

import com.amazon.model.Cart;
import com.amazon.model.Product;
import com.amazon.view.ProductView;
import com.amazon.view.ScannerInstance;

public class CartBuilder extends ScannerInstance {

    private static final CartBuilder CART_BUILDER = new CartBuilder();
    private final ProductView productView = ProductView.getInstance();

    private CartBuilder () {}

    public static CartBuilder getInstance() {
        return CART_BUILDER;
    }

    public Cart getCart(final Long productId, final Long quantity) {
        final Cart cart = new Cart();
        final Product product = productView.productController.get(productId);

        cart.setProductId(productId);
        cart.setProductName(product.getName());
        cart.setQuantity(quantity);
        cart.setUserId(product.getUserId());
        cart.setPrice(product.getPrice());

        return cart;
    }
}
