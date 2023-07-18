package com.amazon.controller;

import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.service.Impl2.ProductServiceImpl2;
import com.amazon.service.ProductService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Acts as a Control between service and the view to {@link Product}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductController {

    private final ProductService PRODUCT_SERVICE = ProductService.getInstance();
    private static final ProductController PRODUCT_CONTROLLER = new ProductController();
    private final ProductService PRODUCT_SERVICE2 = ProductServiceImpl2.getInstance();

    private ProductController() {
    }

    /**
     * <p>
     * Represents the object of AmazonProductController class can be created for only one time
     * </p>
     *
     * @return Represents {@link ProductController}
     */
    public static ProductController getInstance() {
        return PRODUCT_CONTROLLER;
    }

    /**
     * <p>
     * adds the product object to product list
     * </p>
     *
     * @param product Represents {@link Product}
     * @return True if the {@link Product} is added successfully in the product list otherwise return false
     */
    public boolean add(final Product product) {
        return PRODUCT_SERVICE2.add(product);
    }

    /**
     * <p>
     * Gets the products from product list
     * </p>
     *
     * @return Collection view of products from the viewProduct method
     */
    public Collection<Product> getAllProduct() {
        return PRODUCT_SERVICE2.getAllProducts();
    }

    /**
     * <p>
     * Represents the product details that the user create
     * </p>
     *
     * @param userId Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    public Map<Long, Product> getUserProduct(final Long userId) {
        return PRODUCT_SERVICE2.getUserProduct(userId);
    }

    /**
     * <p>
     * get the product object from the product list
     * </p>
     *
     * @param productId id of the product
     * @return Represents {@link Product}
     */
    public Product get(final Long productId) {
        return PRODUCT_SERVICE2.get(productId);
    }

    /**
     * <p>
     * Updates the product in product list
     * </p>
     *
     * @param id      id of the product
     * @param product Represents {@link Product}
     * @return True if the {@link Product} is updated successfully in the product list otherwise return false
     */
    public boolean update(final Long id, final Product product) {
        return PRODUCT_SERVICE2.update(id, product);
    }

    /**
     * <p>
     * Deletes the product from product list
     * </p>
     *
     * @param id id of the product
     * @return True if the {@link Product} is deleted successfully in the product list otherwise return false
     */
    public boolean delete(final Long id) {
        return PRODUCT_SERVICE2.delete(id);
    }

    /**
     * <p>
     *     Represents the order of {@link Product}
     * </p>
     *
     * @param order Represents {@link Order}
     * @return True if the order is added to the order list
     */
    public boolean order(final Order order) {
        return PRODUCT_SERVICE2.order(order);
    }

    /**
     * <p>
     *     Retrieve the List of {@link Order}
     * </p>
     *
     * @return Represents collection of {@link Order}
     */
    public List<Order> getOrderList(final Long userId) {
        return PRODUCT_SERVICE2.getOrderList(userId);
    }

    /**
     * <p>
     *     Represents the order details of the particular order id
     * </p>
     *
     * @param orderId Represents the id of the {@link Product}
     * @return Represents {@link Order}
     */
    public Order getOrder(final Long orderId) {
        return PRODUCT_SERVICE2.getOrder(orderId);
    }

    /**
     * <p>
     *     Represents the cancelling the order of the particular order id
     * </p>
     *
     * @param orderId Represents the id of the {@link Product}
     * @return Represents {@link Order}
     */
    public boolean cancelOrder(final Long orderId) {
        return PRODUCT_SERVICE2.cancelOrder(orderId);
    }

    /**
     * <p>
     *     Represents adding a product to the cart
     * </p>
     *
     * @param cart Represents the {@link Cart}
     * @return True if the Product is added successfully in cart
     */
    public boolean addToCart(final Cart cart) {
        return PRODUCT_SERVICE2.addToCart(cart);
    }

    /**
     * <p>
     *     Represents the Product details from the cart for a particular user
     * </p>
     *
     * @param userId Represents the id of {@link User}
     * @return Collection of products from the cart
     */
    public List<Cart> getCartList(final Long userId) {
        return PRODUCT_SERVICE2.getCartList(userId);
    }

    /**
     * <p>
     *     Represents the particular id details of entered cart id
     * </p>
     *
     * @param id Represents the id of the cart
     * @return Represents {@link Cart}
     */
    public Cart getCart(final Long id) {
        return PRODUCT_SERVICE2.getCart(id);
    }

    /**
     * <p>
     *     Represents the removal of product for the particular cart id
     * </p>
     *
     * @param cartId Represents the id of the cart
     * @return True if the Product is removed successfully
     */
    public boolean removeCart(final Long cartId) {
        return PRODUCT_SERVICE2.removeCart(cartId);
    }

    /**
     * <p>
     *     Represents the product id's of the user created product
     * </p>
     *
     * @param userId Represents the id of the {@link User}
     * @return List of product id's
     */
    public List<Long> getCartProductIds(final Long userId) {
        return PRODUCT_SERVICE2.getCartProductIds(userId);
    }

    /**
     * <p>
     *     Represents updating the quantity of product in cart
     * </p>
     *
     * @param quantity  Quantity need to add with available products
     * @param productId Represents the id of the product need to update the quantity
     * @return True if the product quantity updated successfully
     */
    public boolean updateQuantityInCart(final Long quantity, final Long productId) {
        return PRODUCT_SERVICE2.updateQuantityInCart(quantity, productId);
    }

    /**
     * <p>
     *     Represents updating the quantity of product in {@link Product}
     * </p>
     *
     * @param quantity  Quantity need to add with available products
     * @param productId Represents the id of the product need to update the quantity
     * @return True if the product quantity updated successfully
     */
    public boolean updateQuantityInProduct(final Long quantity, final Long productId) {
        return PRODUCT_SERVICE.updateQuantityInProduct(quantity, productId);
    }
}