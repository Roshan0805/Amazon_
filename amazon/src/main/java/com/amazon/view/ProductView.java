package com.amazon.view;

import com.amazon.controller.ProductController;
import com.amazon.exception.DBException;
import com.amazon.exception.UnavailableQuantityException;
import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.model.Product.Category;
import com.amazon.model.User;
import com.amazon.view.builder.CartBuilder;
import com.amazon.view.builder.OrderBuilder;
import com.amazon.view.builder.ProductBuilder;
import com.amazon.view.validation.ProductValidator;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Represents the view of {@link Product}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class ProductView extends View {

    private final ProductController productController;
    private static ProductView productView = null;
    private static final UserView userView = UserView.getInstance();
    private final ProductBuilder productBuilder;
    private final OrderBuilder orderBuilder;
    private final CartBuilder cartBuilder;
    protected final ProductValidator productValidator;

    private ProductView() {
        productController = ProductController.getInstance();
        productBuilder = ProductBuilder.getInstance();
        orderBuilder = OrderBuilder.getInstance();
        cartBuilder = CartBuilder.getInstance();
        productValidator = ProductValidator.getInstance();
    }

    /**
     * <p>
     * Represents the object for {@link ProductView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link ProductView}
     */
    public static ProductView getInstance() {
        return productView == null ? productView = new ProductView() : productView;
    }

    /**
     * <p>
     * Provides methods for product add, view ,update, delete details
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void accessProduct(final Long userId) {
        System.out.println(String.join("", "Choose from the options\n1.get all product\t",
                "2.add product", "\t3.update product\t4.delete product\t5.Back to admin option"));
        final UserChoice userOption = userView.obtainUserChoice();

        switch (userOption) {
            case ONE:
                viewUserProduct(userId);
                break;
            case TWO:
                createProduct(userId);
                break;
            case THREE:
                updateProduct(userId);
                break;
            case FOUR:
                delete(userId);
                break;
            case FIVE:
                userView.obtainUserOptions();
                break;
            default:
                System.out.println("Enter the correct option");
                accessProduct(userId);
                break;
        }
        System.out.println(String.join("", "Do you want to continue on product press yes(y) else ",
                "press any letter key for back to admin options"));

        if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            accessProduct(userId);
        } else {
            userView.obtainUserOptions();
        }
    }

    /**
     * <p>
     * Updates the product details by getting product id from the user
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void updateProduct(final long userId) {

        try {
            System.out.println("Enter the product id for update product information");
            final Long productId = userView.obtainId();

            if (productValidator.validateProductIds(productId, userId)) {
                final Product product = productController.get(productId);

                System.out.println(product.getAvailable());
                updateName(product);
                updateDescription(product);
                updatePrice(product);
                updateAvailable(product);

                if (productController.update(productId, product)) {
                    System.out.println("Updated successfully");
                    accessProduct(userId);
                } else {
                    System.out.println("Updated unsuccessful");
                }
            } else {
                System.out.println("Enter a valid id for update");
                updateProduct(userId);
            }
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Updates the product name in product object
     * </p>
     *
     * @param product Represents {@link Product}
     */
    private void updateName(final Product product) {
        System.out.println("Do you want to update product name press yes(y) else press any letter key");
        final String userChoice = SCANNER.nextLine().trim();

        if (productValidator.toContinueValidation(userChoice)) {
            System.out.println("Enter the product name for update");
            final String productName = SCANNER.nextLine().trim();

            product.setName(productName);
        }
    }

    /**
     * <p>
     * Updates the product description in product object
     * </p>
     *
     * @param product Represents {@link Product}
     */
    private void updateDescription(final Product product) {
        System.out.println("Do you want to update product description press yes(y) else enter any other letter key");
        final String userChoice = SCANNER.nextLine().trim();

        if (productValidator.toContinueValidation(userChoice)) {
            System.out.println("Enter the product description for update");
            final String description = SCANNER.nextLine().trim();

            product.setDescription(description);
        }
    }

    /**
     * <p>
     * Updates the product price in product object
     * </p>
     *
     * @param product Represents {@link Product}
     */
    private void updatePrice(final Product product) {
        System.out.println("Do you want to update product price press yes(y) else enter any other letter key");
        final String userChoice = SCANNER.nextLine().trim();

        if (productValidator.toContinueValidation(userChoice)) {
            System.out.println("Enter the product price for update");

            try {
                final double productPrice = Double.parseDouble(SCANNER.nextLine().trim());
                product.setPrice(productPrice);
            } catch (final NumberFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * <p>
     * Updates the available product in product object
     * </p>
     *
     * @param product Represents {@link Product}
     */
    private void updateAvailable(final Product product) {
        System.out.println("Do you want to update available product press yes(y) else enter any other letter key");
        final String userChoice = SCANNER.nextLine().trim();

        if (productValidator.toContinueValidation(userChoice)) {
            System.out.println("Enter the available product for update");

            try {
                final Long availableProduct = Long.parseLong(SCANNER.nextLine().trim());

                product.setAvailable(availableProduct);
            } catch (final NumberFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * <p>
     * Deletes the product from the products list
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void delete(final Long userId) {
        try {
            viewUserProduct(userId);
            System.out.println("Enter the product id to delete the product details ");
            final Long productId = userView.obtainId();

            if (productController.delete(productId)) {
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Deleted unsuccessful enter a valid product id");
                delete(userId);
            }
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Create a product object and add the product object to the product list
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void createProduct(final Long userId) {
        System.out.println("Do you want to add new product press yes(y) or add existing product press press (E)");
        final String userChoice = SCANNER.nextLine().trim();

        if ("e".equalsIgnoreCase(userChoice)) {
            System.out.println("Enter the product id then enter the number of product have to be added");

            try {
                if (productController.updateQuantityInProduct(obtainQuantity(), userView.obtainId())) {
                    System.out.println("Product updated successfully");
                } else {
                    System.out.println("Product updated unsuccessful");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Enter the values in numbers");
            }
        } else if ("y".equalsIgnoreCase(userChoice)) {
            final Product product = productBuilder.buildProduct(obtainCategory(userId), obtainName(userId), obtainDescription(userId), obtainQuantity(), obtainPrice(userId), userId);

            if (productController.add(product)) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Product adding is unsuccessful");
            }
            System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");

            if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
                createProduct(userId);
            } else {
                productView.accessProduct(userId);
            }
        } else {
            System.out.println("Enter a valid choice");
            createProduct(userId);
        }
    }

    /**
     * Represents getting the product quantity from the user
     *
     * @return The quantity of product
     */
    private Long obtainQuantity() {
        System.out.println("Enter the quantity you want in number press # for back to product options");

        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidator.isReturnToMenu(userChoice)) {
                userView.obtainUserOptions();
            }

            try {
                return Long.parseLong(userChoice);
            } catch (NumberFormatException exception) {
                System.out.println("Enter the quantity in number");
                obtainQuantity();
            }
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("Enter valid quantity");
        }
        return obtainQuantity();
    }

    /**
     * <p>
     * Gets the product category from the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represent {@link Product.Category}
     */
    public Product.Category obtainCategory(final Long userId) {
        System.out.println(String.join("", "Enter the product category\n1.mobile_phones\n",
                "2.footwear\n3.electronics\n4.clothing\n5.kitchen_appliances\n6.sports\n7.books\n8.toys\t",
                "(press # to return to product menu)"));

        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidator.isReturnToMenu(userChoice)) {
                accessProduct(userId);
            }

            final int productChoice = Integer.parseInt(userChoice);

            final Category category = Category.getCategory(productChoice);

            if (null == category) {
                System.out.println("Enter a valid category");
            } else {
                return category;
            }

        } catch (IllegalArgumentException exception) {
            System.out.println("Enter a valid category");
        }

        return obtainCategory(userId);
    }

    /**
     * <p>
     * Gets the product name from user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} name
     */
    public String obtainName(final Long userId) {
        try {
            System.out.println("Enter the product name\t(press # for back to menu)");
            final String productName = SCANNER.nextLine().trim();

            if (productValidator.isReturnToMenu(productName)) {
                accessProduct(userId);
            }

            return productName;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return obtainName(userId);
    }


    /**
     * <p>
     * Get the product description from the user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} description
     */
    public String obtainDescription(final Long userId) {
        try {
            System.out.println("Enter the product description\t(press # for back to menu)");
            final String description = SCANNER.nextLine().trim();

            if (productValidator.isReturnToMenu(description)) {
                accessProduct(userId);
            }

            return description;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return obtainDescription(userId);
    }

    /**
     * <p>
     * Gets and validate price that is entered by the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represents price value entered by user
     */
    public double obtainPrice(final Long userId) {
        System.out.println("Enter the Product price\t(press # for back to product menu)");

        try {
            final double productPrice = Double.parseDouble(SCANNER.nextLine().trim());

            if (productValidator.isReturnToMenu(String.valueOf(productPrice))) {
                accessProduct(userId);
            }

            return productPrice;
        } catch (final NumberFormatException Exception) {
            System.out.println("Enter the value is number");
        }
        System.out.println("Do you want to enter price again press yes(y) else press any letter key");

        try {
            if ("y".equalsIgnoreCase(SCANNER.nextLine().trim())) {
                return obtainPrice(userId);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return 0;
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     */
    public void viewProduct(final Long userId) {
        try {
            final Collection<Product> products = productController.getAllProduct();
            if (products == null) {
                System.out.println("The product list is empty");
                userView.obtainUserOptions();
            }
            System.out.println(products);
            System.out.println("Do you want to add product to cart press yes(y) else no(n)");

            if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
                createCart(userId);
            }
            System.out.println("Do you want to order product press yes(y) else no(n)");

            if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
                orderProduct(userId);
            }
            userView.obtainUserOptions();
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void viewUserProduct(final Long userId) {
        try {
            final Map<Long, Product> products = productController.getUserProduct(userId);

            if (products.isEmpty()) {
                System.out.println("The product list is empty");
                accessProduct(userId);
            }
            System.out.println(products.values());
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Represents the menu option for order
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void accessOrder(final Long userId) {
        System.out.println("Enter the Option\n1.get all orders \n2.remove order\n3.get order");
        final UserChoice userChoice = userView.obtainUserChoice();

        switch (userChoice) {
            case ONE:
                getOrderList(userId);
                break;
            case TWO:
                cancelOrder(userId);
                break;
            case THREE:
                obtainOrder(userId);
                break;
            default:
                System.out.println("Enter the correct option");
                accessCart(userId);
        }
    }

    /**
     * <p>
     * Represents the user's order
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void orderProduct(final Long userId) {

        try {
            if (productController.order(orderBuilder.buildOrder(obtainId(), obtainQuantity(), obtainPaymentType(),
                    userId))) {
                System.out.println("Product ordered successfully");
            } else {
                System.out.println("Product ordered unsuccessful");
            }
        } catch (UnavailableQuantityException | DBException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Do you want to order more product");

        if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            orderProduct(userId);
        } else {
            userView.obtainUserOptions();
        }
    }

    /**
     * <p>
     * Represents the product order list of the user
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void getOrderList(final Long userId) {
        try {
            System.out.println(productController.getOrderList(userId));
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Represents the payment type of user for order
     * </p>
     */
    public Order.Payment obtainPaymentType() {
        System.out.println(String.join("", "Enter the payment method\n1.cash on delivery\n",
                "2.credit or debit cart\n3.net banking\n4.other upi apps"));

        try {
            final int paymentChoice = Integer.parseInt(SCANNER.nextLine().trim());

            if (productValidator.isReturnToMenu(String.valueOf(paymentChoice))) {
                userView.obtainUserOptions();
            }
            final Order.Payment paymentType = Order.Payment.getPayment(paymentChoice);

            if (null == paymentType) {
                System.out.println("Enter a valid category");
            } else {
                return paymentType;
            }

        } catch (IllegalArgumentException exception) {
            System.out.println("Enter a valid category");
        }
        return obtainPaymentType();
    }

    /**
     * <p>
     * Represents order detail for particular order id
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void obtainOrder(final Long userId) {
        System.out.println("Enter the order id (press # to back to menu)");
        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidator.isReturnToMenu(userChoice)) {
                accessOrder(userId);
            }
            final Order order = productController.getOrder(Long.valueOf(userChoice));

            if (null == order) {
                System.out.println("Order is not found");
                obtainOrder(userId);
            }
            System.out.println(order);
        } catch (NumberFormatException | DBException exception) {
            System.out.println("Enter a valid order id");
            obtainOrder(userId);
        }
        accessOrder(userId);
    }

    /**
     * <p>
     * Represents the cancelling of a particular order based on the order id
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void cancelOrder(final Long userId) {
        getOrderList(userId);
        System.out.println("Enter the order id for remove from order");

        try {
            final Long orderId = Long.parseLong(SCANNER.nextLine().trim());

            if (productController.cancelOrder(orderId)) {
                System.out.println("Order removed successfully");
                accessOrder(userId);
            } else {
                System.out.println("Order removed unsuccessful");
                cancelOrder(userId);
            }
        } catch (NumberFormatException | DBException exception) {
            System.out.println("Enter the valid order id");
            cancelOrder(userId);
        }
    }

    /**
     * <p>
     * Represents the cart access to the user
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void accessCart(final Long userId) {
        System.out.println("Enter the Option\n1.get cart\n2.remove product cart");
        final UserChoice userChoice = userView.obtainUserChoice();

        switch (userChoice) {
            case ONE:
                getCart(userId);
                break;
            case TWO:
                removeCart(userId);
                break;
            default:
                System.out.println("Enter the correct option");
                accessCart(userId);
        }
    }

    /**
     * <p>
     * Represents adding product to the cart
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void createCart(final Long userId) {
        System.out.println("Enter the product id for add to cart(press # fro return to menu)");

        try {
            final Long productId = obtainId();

            if (productValidator.isReturnToMenu(String.valueOf(productId))) {
                userView.obtainUserOptions();
            }
            final Long quantity = obtainQuantity();

            if (productController.getCartProductIds(userId).contains(productId)) {
                productController.updateQuantityInCart(quantity, productId);
                accessCart(userId);
            }

            if (productController.addToCart(cartBuilder.getCart(productId, quantity))) {
                System.out.println("Product added to cart successfully");
            } else {
                System.out.println("Product added to cart is unsuccessfully");
            }
            System.out.println("Do you want to add more product in cart");

            if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
                createCart(userId);
            } else {
                userView.obtainUserOptions();
            }
        } catch (NumberFormatException | DBException exception) {
            System.out.println("Enter the correct product id");
            createCart(userId);
        }
    }

    /**
     * <p>
     * Represents the cart details of the particular user
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void getCart(final Long userId) {
        try {
            final List<Cart> cartList = productController.getCartList(userId);

            if (null == cartList) {
                System.out.println("Your cart is empty");
                userView.obtainUserOptions();
            } else {
                System.out.println(cartList);
                System.out.println("do you want to order the products");

                if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
                    System.out.println("Enter the cart id");

                    try {
                        final Cart cart = productController.getCart(Long.parseLong(SCANNER.nextLine().trim()));
                        if (null == cart) {
                            System.out.println("Enter the valid cart id ");
                            getCart(userId);
                        }
                        cartToOrder(cart);
                    } catch (NumberFormatException exception) {
                        System.out.println(exception.getMessage());
                        getCart(userId);
                    }
                }
                System.out.println("Do you want to exit press yes(y) else no(n)");

                if (productValidator.toContinueValidation(SCANNER.nextLine().trim())) {
                    userView.obtainUserOptions();
                } else {
                    getCart(userId);
                }
            }
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Represents order the product from the cart
     * </p>
     *
     * @param cart Represents the {@link Cart}
     */
    public void cartToOrder(final Cart cart) {
        try {
            final Order order = new Order(cart, obtainPaymentType());
            productController.order(order);
        }  catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Represents remove the particular product from the cart
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void removeCart(final Long userId) {
        System.out.println(productController.getCartList(userId));
        System.out.println("Enter the cart id for remove from cart");

        try {
            final Long cartId = Long.parseLong(SCANNER.nextLine().trim());

            if (productController.removeCart(cartId)) {
                System.out.println("product removed successfully");
                accessCart(userId);
            } else {
                System.out.println("product remove unsuccessful Enter a valid product key");
                removeCart(userId);
            }
        } catch (NumberFormatException | DBException exception) {
            System.out.println("Enter the valid product key");
            removeCart(userId);
        }
    }

    /**
     * <p>
     * Gets the id from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public Long obtainId() {
        try {
            System.out.println("Enter the product id\t(press # for logout to menu)");
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidator.isReturnToMenu(userChoice)) {
                userView.obtainUserOptions();
            }

            return Long.parseLong(userChoice);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return obtainId();
    }

    /**
     * <p>
     *     Represents getting a product controller
     * </p>
     * @return {@link ProductController}
     */
    public ProductController getProductController() {
        return productController;
    }
}

