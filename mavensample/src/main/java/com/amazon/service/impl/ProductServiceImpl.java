package com.amazon.service.impl;

import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.service.ProductService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Implements the {@link ProductService} to provide services for {@link  Product}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {

    private static final Map<Long, Product> PRODUCT_LIST = new LinkedHashMap<>();
    private static final Map<Long,Order> ORDER_LIST = new HashMap<>();
    private static final Map<Long,Cart> CART_LIST = new HashMap<>();
    private static long productId = 1;
    private static final ProductServiceImpl AMAZON_PRODUCT_SERVICE = new ProductServiceImpl();

    private ProductServiceImpl() {
    }

    /**
     * <p>
     * Represents the object of {@link ProductServiceImpl} class can be created for only one time
     * </p>
     *
     * @return Represents {@link ProductServiceImpl}
     */
    public static ProductService getInstance() {
        return AMAZON_PRODUCT_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param product Represents {@link Product}
     * @return True if the product is added successfully
     */
    public boolean add(final Product product) {
        if (null == product) {
            return false;
        }

        try {
            product.setId(productId);
            PRODUCT_LIST.put(productId++, product);

            return true;
        } catch (ClassCastException exception) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return Collection view of product's
     */
    public Collection<Product> getAllProducts() {
        return PRODUCT_LIST.values();
    }

    /**
     * Represents the product details that the user create
     *
     * @param userId Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    public Map<Long, Product> getUserProduct(final Long userId) {
        final Map<Long, Product> products = new HashMap<>();

        for (final Product product : PRODUCT_LIST.values()) {

            if (product.getAdminId().equals(userId)) {
                products.put(product.getId(), product);
            }
        }
        return products;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link Product} from the product list
     */
    public Product get(final long productId) {
        return PRODUCT_LIST.get(productId);
    }

    /**
     * {@inheritDoc}
     *
     * @param id      Represents the id of the product
     * @param product Represents {@link Product}
     * @return True if the product name is updated successfully
     */
    public boolean update(long id, Product product) {
        if (null == product) {
            return false;
        }
        final Date time = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss E dd MM yyyy zz");
        String updatedTime = dateFormat.format(time);

        product.setUpdatedTime(updatedTime);
        product.setUpdatedTime(updatedTime);
        PRODUCT_LIST.put(id, product);

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents id for deleting product
     * @return True if the product is deleted successfully
     */
    public boolean delete(final long id) {
        if (getIds().contains(id)) {
            PRODUCT_LIST.remove(id);

            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return Set of product's id
     */
    public Collection<Long> getIds() {
        final Collection<Long> productsId = new HashSet<>();

        for (final Product product : PRODUCT_LIST.values()) {
            final long productId = product.getId();

            productsId.add(productId);
        }

        return productsId;
    }

    /**
     * Represents the order of {@link Product}
     *
     * @param order Represents {@link Order}
     * @return True if the order is added to the order list
     */
    public boolean order(final Order order) {
        try {
            ORDER_LIST.put(order.getId(), order);
            return true;
        } catch (ClassCastException exception) {
            return false;
        }
    }

    /**
     * Represents the List of {@link Order}
     *
     * @return Represents collection of {@link Order}
     */
    public List<Order> getOrderList(final Long userId) {
        final List<Order> orderList = new ArrayList<>();

        for(Order order: ORDER_LIST.values()) {

            if(order.getUserId() == userId) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    public Order getOrder(Long id) {
        return ORDER_LIST.get(id);
    }

    /**
     * Represents the removal of {@link Order}
     *
     * @return True if removed successfully
     */
    public boolean removeOrder(final Long orderId) {
        try {
            ORDER_LIST.remove(orderId);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public boolean addToCart(final Cart cart) {
        try {
            CART_LIST.put(cart.getId(), cart);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public List<Cart> getCartList(final Long userId) {
        final List<Cart> cartList = new LinkedList<>();

        for(Cart cart: CART_LIST.values()) {

            if(cart.getUserId() == userId) {
                cartList.add(cart);
            }
        }
        return cartList;
    }

    public Cart getCart(Long id) {
        for(Cart cart :CART_LIST.values()) {

            if(cart.getId() == id) {
                return cart;
            }
        }
        return null;
    }

    public List<Long> getProductIds(Long userId) {
        final List<Long> cartIds = new ArrayList<>();

        for(Cart cart : getCartList(userId)) {
            cartIds.add(cart.getProductId());
        }
        return cartIds;
    }

    public boolean removeCart(final Long cartId) {
        try {
            CART_LIST.remove(cartId);
            return true;
        } catch(ClassCastException exception ) {
            return false;
        }
    }

    public boolean updateQuantity(Long quantity, Long productId) {
        for(Cart cart : CART_LIST.values()) {
            if(cart.getProductId() == productId) {
                cart.setProductCount(cart.getProductCount() + quantity);

                return true;
            }
        }
        return false;
    }
}