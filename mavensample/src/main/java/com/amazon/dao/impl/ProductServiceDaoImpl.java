package com.amazon.dao.impl;

import com.amazon.dao.ProductServiceDao;
import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


/**
 * <p>
 * Implements the {@link ProductServiceDao} and provide {@link Product} service
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductServiceDaoImpl implements ProductServiceDao {

    private static final ProductServiceDao PRODUCT_SERVICE_DAO = new ProductServiceDaoImpl();
    private static final DBConnection DB_CONNECTION = DBConnection.getInstance();

    private ProductServiceDaoImpl() {
    }

    /**
     * <p>
     * Represents the object of {@link ProductServiceDaoImpl} class can be created for only one time
     * </p>
     *
     * @return Represents {@link ProductServiceDao}
     */
    public static ProductServiceDao getInstance() {
        return PRODUCT_SERVICE_DAO;
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
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, CATEGORY, UPDATED_TIME, ADMIN_ID) values (?,?,?,?,?,?)";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getCategory().toString());
            statement.setString(5, product.getUpdatedTime());
            statement.setLong(6, getAdminId(product.getAdminId()));
            statement.executeUpdate();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }

    /**
     * <p>
     * Provide the collection view of the products value
     * </p>
     *
     * @return Collection view of {@link Product}
     */
    public Collection<Product> getAllProducts() {
        try (final Connection connection = DB_CONNECTION.get()) {
            final Collection<Product> productList = new ArrayList<>();
            final String query = "SELECT * FROM PRODUCT";
            final PreparedStatement statement = connection.prepareStatement(query);
            final ResultSet result = statement.executeQuery();

            while (result.next()) {
                final Product product = new Product();

                product.setId(result.getLong("id"));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getDouble("price"));
                product.setCategory(Product.Category.valueOf(result.getString("category")));
                product.setUpdatedTime(result.getString("updated_time"));
                product.setAdminId(result.getLong("admin_id"));
                productList.add(product);
            }
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();
            return productList;

        } catch (SQLException | InterruptedException exception) {
            return null;
        }
    }

    /**
     * Represents the product details that the user create
     *
     * @param userId Represents admin id
     * @return Represents {@link Product} list created by the user
     */
    public Map<Long, Product> getUserProduct(Long userId) {
        final Map<Long, Product> productList = new HashMap<>();

        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "SELECT * FROM PRODUCT WHERE ADMIN_ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, getAdminId(userId));
            final ResultSet result = statement.executeQuery();

            while (result.next()) {
                final Product product = new Product();

                product.setId(result.getLong("id"));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getDouble("price"));
                product.setCategory(Product.Category.valueOf(result.getString("category")));
                product.setUpdatedTime(result.getString("updated_time"));
                product.setAdminId(result.getLong("admin_id"));
                productList.put(product.getId(), product);
            }
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();
        } catch (SQLException | InterruptedException exception) {
            System.out.println(exception.getMessage());
        }


        return productList;
    }

    /**
     * <p>
     * Gets the product from product list using productId
     * </p>
     *
     * @param productId product id of the product object
     * @return Represent {@link Product} in product list
     */
    public Product get(Long productId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "SELECT * FROM PRODUCT WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, productId);
            final ResultSet result = statement.executeQuery();

            if (result.next()) {
                final Product product = new Product();

                product.setId(result.getLong("id"));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getDouble("price"));
                product.setCategory(Product.Category.valueOf(result.getString("category")));
                product.setUpdatedTime(result.getString("updated_time"));
                product.setAdminId(result.getLong("admin_id"));

                DB_CONNECTION.release(connection);
                DB_CONNECTION.close();
                return product;
            }
        } catch (SQLException | InterruptedException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return null;
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
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ?, CATEGORY = ?, UPDATED_TIME = ?, ADMIN_ID = ? WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getCategory().name());
            statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            statement.setLong(6, product.getAdminId());
            statement.setLong(7, id);
            statement.execute();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
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
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "DELETE FROM PRODUCT WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);
            statement.execute();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }

    /**
     * <p>
     * Retrieves the admin id of the user
     * </p>
     *
     * @param userId Represents the id of user
     * @return Admin id of the user
     */
    public Long getAdminId(Long userId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String adminIdQuery = "SELECT ID FROM ADMIN WHERE USER_ID = ?";
            final PreparedStatement adminIdStatement = connection.prepareStatement(adminIdQuery);

            adminIdStatement.setLong(1, userId);
            final ResultSet result = adminIdStatement.executeQuery();

            if (result.next()) {
                DB_CONNECTION.release(connection);
                DB_CONNECTION.close();

                return result.getLong(1);
            }

            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();
        } catch (SQLException | InterruptedException exception) {
            return null;
        }
        return null;
    }


    /**
     * @param cart
     * @return
     */
    public boolean addToCart(Cart cart) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "INSERT INTO CART (PRODUCT_ID, QUANTITY, TOTAL_PRICE, USER_ID, PRODUCT_NAME) VALUES (?,?,?,?,?)";
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cart.getProductId());
            statement.setLong(2, cart.getProductCount());
            statement.setDouble(3, cart.getTotalPrice());
            statement.setLong(4, cart.getUserId());
            statement.setString(5, cart.getProductName());
            final int noOfRowsInserted = statement.executeUpdate();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }

    /**
     * @param userId
     * @return
     */
    public List<Cart> getCartList(Long userId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final List<Cart> cartList = new LinkedList<>();
            final String query = "SELECT * FROM CART WHERE USER_ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userId);
            final ResultSet result = statement.executeQuery();

            while (result.next()) {
                final Cart cart = new Cart();

                cart.setId(result.getLong("id"));
                cart.setProductId(result.getLong("product_id"));
                cart.setProductName(result.getString("product_name"));
                cart.setProductCount(result.getLong("quantity"));
                cart.setTotalPrice(result.getDouble("total_price"));
                cart.setUserId(result.getLong("user_id"));
                DB_CONNECTION.release(connection);
                DB_CONNECTION.close();

                cartList.add(cart);
            }

            return cartList;
        } catch (SQLException | InterruptedException exception) {
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    public Cart getCart(Long id) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "SELECT * FROM CART WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, id);
            final ResultSet result = statement.executeQuery();

            if (result.next()) {
                final Cart cart = new Cart();

                cart.setId(result.getLong("id"));
                cart.setProductId(result.getLong("product_id"));
                cart.setProductName(result.getString("product_name"));
                cart.setProductCount(result.getLong("quantity"));
                cart.setTotalPrice(result.getDouble("total_price"));
                cart.setUserId(result.getLong("user_id"));
                DB_CONNECTION.release(connection);
                DB_CONNECTION.close();

                return cart;
            }

            return null;
        } catch (SQLException | InterruptedException exception) {
            return null;
        }
    }

    /**
     * @param cartId
     * @return
     */
    public boolean removeCart(Long cartId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "DELETE FROM CART WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, cartId);
            statement.execute();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }

    public List<Long> getProductIds(Long userId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final List<Long> productIds = new LinkedList<>();
            final String query = "SELECT PRODUCT_ID FROM CART WHERE USER_ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);

            final ResultSet result = statement.executeQuery();

            while (result.next()) {
                productIds.add(result.getLong("product_id"));
            }
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return productIds;
        } catch (SQLException | InterruptedException exception) {
            return null;
        }
    }

    public boolean updateQuantity(Long quantity, Long productId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "UPDATE CART SET QUANTITY = QUANTITY + ? WHERE PRODUCT_ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, quantity);
            statement.setLong(2, productId);
            statement.execute();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }

    /**
     * Represents the order of {@link Product}
     *
     * @param order Represents {@link Order}
     * @return True if the order is added to the order list
     */
    public boolean order(Order order) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "INSERT INTO ORDER (PRODUCT_ID, PRODUCT_COUNT, TOTAL_PRICE, CART_ID, USER_ID, PAYMENT_TYPE) VALUES (?,?,?,?,?,?)";
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getProductId());
            statement.setLong(2, order.getProductCount());
            statement.setDouble(3, order.getTotalPrice());
            statement.setLong(4, order.getCartId());
            statement.setLong(5, order.getUserId());
            statement.setString(6, String.valueOf(order.getPaymentType()));
            statement.execute();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }


    /**
     * Retrieve the List of {@link User} order
     *
     * @param userId Represents id of {@link User}
     * @return Represents collection of {@link Order}
     */
    public List<Order> getOrderList(Long userId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final List<Order> orderList = new ArrayList<>();
            final String query = "SELECT * FROM ORDER WHERE USER_ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userId);
            final ResultSet result = statement.executeQuery();

            while (result.next()) {
                final Order order = new Order();

                order.setId(result.getLong("id"));
                order.setProductId(result.getLong("product_id"));
                order.setCartId(result.getLong("cart_id"));
                order.setProductCount(result.getLong("product_count"));
                order.setTotalPrice(result.getDouble("total_price"));
                order.setUserId(result.getLong("user_id"));
                order.setPaymentType(Order.Payment.valueOf(result.getString("payment_type")));
                orderList.add(order);
            }
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return orderList;
        } catch (SQLException | InterruptedException exception) {
            return null;
        }
    }

    public Order getOrder(Long orderId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "SELECT * FROM ORDER WHERE USER_ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, orderId);
            final ResultSet result = statement.executeQuery();
            final Order order = new Order();

            if (result.next()) {
                order.setId(result.getLong("ID"));
                order.setProductId(result.getLong("PRODUCT_ID"));
                order.setCartId(result.getLong("CART_ID"));
                order.setProductCount(result.getLong("PRODUCT_COUNT"));
                order.setTotalPrice(result.getDouble("TOTAL_PRICE"));
                order.setUserId(result.getLong(""));
                order.setPaymentType(Order.Payment.valueOf(result.getString("payment_type")));
            }
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return order;
        } catch (SQLException | InterruptedException exception) {
            return null;
        }
    }

    public boolean removeOrder(Long orderId) {
        try (final Connection connection = DB_CONNECTION.get()) {
            final String query = "DELETE FROM ORDER WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            statement.execute();
            DB_CONNECTION.release(connection);
            DB_CONNECTION.close();

            return true;
        } catch (SQLException | InterruptedException exception) {
            return false;
        }
    }
}
