package com.amazon.controller;

import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
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

    public boolean order(final Order order) {
        return PRODUCT_SERVICE2.order(order);
    }

    public List<Order> getOrderList(final Long userId) {
        return PRODUCT_SERVICE2.getOrderList(userId);
    }

    public Order getOrder(final Long orderId) { return PRODUCT_SERVICE2.getOrder(orderId);}

    public boolean removeOrder(final Long orderId) {
        return PRODUCT_SERVICE2.removeOrder(orderId);
    }

    public boolean addToCart(final Cart cart) {
        return PRODUCT_SERVICE2.addToCart(cart);
    }

    public List<Cart> getCartList(final Long userId){
        return PRODUCT_SERVICE2.getCartList(userId);
    }

    public Cart getCart(final Long id){
        return PRODUCT_SERVICE2.getCart(id);
    }

    public boolean removeCart(final Long cartId) {
        return PRODUCT_SERVICE2.removeCart(cartId);
    }

    public List<Long> getProductIds(final Long userId) {
        return PRODUCT_SERVICE2.getProductIds(userId);
    }

    public boolean updateQuantity(final Long quantity, final Long productId ){
        return PRODUCT_SERVICE2.updateQuantity(quantity, productId);
    }
}