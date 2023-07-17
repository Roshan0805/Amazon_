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
 * Acts as a Control between service and the view to provide product view, delete and update
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductController {

    private static final ProductService PRODUCT_SERVICE = ProductService.getInstance();
    private static final ProductController AMAZON_PRODUCT_CONTROLLER = new ProductController();
    private static final ProductService PRODUCT_SERVICE2 = ProductServiceImpl2.getInstance();

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
        return AMAZON_PRODUCT_CONTROLLER;
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
     * Represents the product details that the user create
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
    public Product get(final long productId) {
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
    public boolean update(final long id, final Product product) {
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
    public boolean delete(final int id) {
        return PRODUCT_SERVICE2.delete(id);
    }

    /**
     * Represents the order of {@link Product}
     *
     * @param order Represents {@link Order}
     * @return True if the order is added to the order list
     */
    public boolean order(final Order order) {
        return PRODUCT_SERVICE2.order(order);
    }

    /**
     * Retrieve the List of {@link Order}
     *
     * @return Represents collection of {@link Order}
     */
    public List<Order> getOrderList(final Long userId) {
        return PRODUCT_SERVICE2.getOrderList(userId);
    }

    /**
     * Represents the order details of the particular order id
     *
     * @param orderId Represents the id of the {@link Product}
     * @return Represents {@link Order}
     */
    public Order getOrder(final Long orderId) {
        return PRODUCT_SERVICE2.getOrder(orderId);
    }

    /**
     * Represents the cancelling the order of the particular order id
     *
     * @param orderId Represents the id of the {@link Product}
     * @return Represents {@link Order}
     */
    public boolean cancelOrder(final Long orderId) {
        return PRODUCT_SERVICE2.cancelOrder(orderId);
    }

    /**
     * Represents adding a product to the cart
     *
     * @param cart Represents the {@link Cart}
     * @return True if the Product is added successfully in cart
     */
    public boolean addToCart(final Cart cart) {
        return PRODUCT_SERVICE2.addToCart(cart);
    }

    /**
     * Represents the Product details from the cart for a particular user
     *
     * @param userId Represents the id of {@link User}
     * @return Collection of products from the cart
     */
    public List<Cart> getCartList(final Long userId) {
        return PRODUCT_SERVICE2.getCartList(userId);
    }

    /**
     * Represents the particular id details of entered cart id
     *
     * @param id Represents the id of the cart
     * @return Represents {@link Cart}
     */
    public Cart getCart(final Long id) {
        return PRODUCT_SERVICE2.getCart(id);
    }

    /**
     * Represents the removal of product for the particular cart id
     *
     * @param cartId Represents the id of the cart
     * @return True if the Product is removed successfully
     */
    public boolean removeCart(final Long cartId) {
        return PRODUCT_SERVICE2.removeCart(cartId);
    }

    /**
     * Represents the product id's of the user created product
     *
     * @param userId Represents the id of the {@link User}
     * @return List of product id's
     */
    public List<Long> getCartProductIds(final Long userId) {
        return PRODUCT_SERVICE2.getCartProductIds(userId);
    }

    /**
     * Represents updating the quantity of product in cart
     *
     * @param quantity  Quantity need to add with available products
     * @param productId Represents the id of the product need to update the quantity
     * @return True if the product quantity updated successfully
     */
    public boolean updateQuantityInCart(final Long quantity, final Long productId) {
        return PRODUCT_SERVICE2.updateQuantityInCart(quantity, productId);
    }

    /**
     * Represents updating the quantity of product in {@link Product}
     *
     * @param quantity  Quantity need to add with available products
     * @param productId Represents the id of the product need to update the quantity
     * @return True if the product quantity updated successfully
     */
    public boolean updateQuantityInProduct(final Long quantity, final Long productId) {
        return PRODUCT_SERVICE.updateQuantityInProduct(quantity, productId);
    }
}
