package com.amazon.service.Impl2;

import com.amazon.dao.ProductServiceDao;
import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.service.ProductService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Implements the {@link ProductServiceDao} to provide services for {@link  Product}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductServiceImpl2 implements ProductService {

    private static final ProductService PRODUCT_SERVICE = new ProductServiceImpl2();
    private static final ProductServiceDao PRODUCT_SERVICE_DAO = ProductServiceDao.getInstance();

    private ProductServiceImpl2() {}

    public static ProductService getInstance() {
        return PRODUCT_SERVICE;
    }

    /**
     * <p>
     * Add product to the product list
     * </p>
     *
     * @param product Product object
     * @return Boolean true is the {@link Product} added successfully in the product list otherwise return false
     */
    public boolean add(Product product) {
        return PRODUCT_SERVICE_DAO.add(product);
    }

    /**
     * <p>
     * Provide the collection view of the products value
     * </p>
     *
     * @return Collection view of {@link Product}
     */
    public Collection<Product> getAllProducts() {
        return PRODUCT_SERVICE_DAO.getAllProducts();
    }

    /**
     * Represents the product details that the user create
     *
     * @param userId Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    public Map<Long, Product> getUserProduct(Long userId) {
        return PRODUCT_SERVICE_DAO.getUserProduct(userId);
    }

    /**
     * <p>
     * Gets the product from product list using productId
     * </p>
     *
     * @param productId product id of the product object
     * @return Represent {@link Product} in product list
     */
    public Product get(long productId) {
        return PRODUCT_SERVICE_DAO.get(productId);
    }

    /**
     * <p>
     * Updates product object in product list
     * </p>
     *
     * @param id      Product id of the product
     * @param product Represent {@link Product}
     * @return True if the {@link Product} is updated successfully in the product list otherwise return false
     */
    public boolean update(long id, Product product) {
        return PRODUCT_SERVICE_DAO.update(id, product);
    }

    /**
     * <p>
     * Deletes the product object from the product list
     * </p>
     *
     * @param id id of the product object
     * @return True if the {@link Product} deleted successfully in the product list otherwise return false
     */
    public boolean delete(long id) {
        return PRODUCT_SERVICE_DAO.delete(id);
    }

    /**
     * Represents the order of {@link Product}
     *
     * @param order Represents {@link Order}
     * @return True if the order is added to the order list
     */
    public boolean order(Order order) {
        return PRODUCT_SERVICE_DAO.order(order);
    }

    /**
     * Retrieve the List of {@link Order}
     *
     * @param userId Represent the id of user
     * @return Represents collection of {@link Order}
     */
    public List<Order> getOrderList(Long userId) {
        return PRODUCT_SERVICE_DAO.getOrderList(userId);
    }

    public Order getOrder(Long id) {
        return PRODUCT_SERVICE_DAO.getOrder(id);
    }

    public boolean removeOrder(Long orderId) {
        return PRODUCT_SERVICE_DAO.removeOrder(orderId);
    }

    public boolean addToCart(final Cart cart) {
        return PRODUCT_SERVICE_DAO.addToCart(cart);
    }

    public List<Cart> getCartList(final Long userId) {
        return PRODUCT_SERVICE_DAO.getCartList(userId);
    }

    public Cart getCart(final Long id) {
        return PRODUCT_SERVICE_DAO.getCart(id);
    }

    public List<Long> getProductIds(final Long userId) {
        return PRODUCT_SERVICE_DAO.getProductIds(userId);
    }

    public boolean removeCart(final Long cartId) {
        return PRODUCT_SERVICE_DAO.removeCart(cartId);
    }

    public boolean updateQuantity(Long quantity, Long productId) {
        return PRODUCT_SERVICE_DAO.updateQuantity(quantity, productId);
    }
}
