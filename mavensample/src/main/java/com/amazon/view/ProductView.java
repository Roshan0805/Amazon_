package com.amazon.view;

import com.amazon.controller.ProductController;
import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.builder.OrderBuilder;
import com.amazon.view.builder.ProductBuilder;
import com.amazon.view.validation.ProductValidation;

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

    private static final ProductView PRODUCT_VIEW = new ProductView();
    public static final UserView USER_VIEW = UserView.getInstance();
    protected static final ProductController PRODUCT_CONTROLLER = ProductController.getInstance();
    private static final ProductBuilder PRODUCT_BUILDER = ProductBuilder.getInstance();
    private static final OrderBuilder ORDER_BUILDER = OrderBuilder.getInstance();
    protected static final ProductValidation PRODUCT_VALIDATION = ProductValidation.getInstance();


    protected ProductView() {
    }

    /**
     * <p>
     * Represents the object for {@link com.amazon.view.ProductView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link com.amazon.view.ProductView}
     */
    public static ProductView getInstance() {
        return PRODUCT_VIEW;
    }

    /**
     * <p>
     * Provides methods for product add, view ,update, delete details
     * </p>
     *
     * @param userId Represents the user's id
     */
    public void accessProduct(final Long userId) {
        System.out.println(String.join("", "Choose from the options\n1.get all product\t",
                "2.add product", "\t3.update product\t4.delete product\t5.Back to admin option"));
        final int userOption = USER_VIEW.getUserChoice();

        switch (userOption) {
            case 1:
                viewUserProduct(userId);
                break;
            case 2:
                add(userId);
                break;
            case 3:
                update(userId);
                break;
            case 4:
                delete(userId);
                break;
            case 5:
                USER_VIEW.getUserOptions();
                break;
            default:
                System.out.println("Enter the correct option");
                accessProduct(userId);
                break;
        }
        System.out.println(String.join("", "Do you want to continue on product press yes(y) else ",
                "press any letter key for back to admin options"));

        if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine().trim())) {
            accessProduct(userId);
        } else {
            USER_VIEW.getUserOptions();
        }
    }

    /**
     * <p>
     * Updates the product details by getting product id from the user
     * </p>
     *
     * @param userId Represents the user id
     */
    private void update(final long userId) {
        System.out.println("Enter the product id for update product information");
        final long productId = USER_VIEW.getUserChoice();

        if (PRODUCT_VALIDATION.validateProductIds(productId, userId)) {

            final Product product = PRODUCT_CONTROLLER.get(productId);

            updateName(product);
            updateDescription(product);
            updatePrice(product);

            if (PRODUCT_CONTROLLER.update(productId, product)) {
                System.out.println("Updated successfully");
                accessProduct(userId);
            } else {
                System.out.println("Updated unsuccessful");
            }

        } else {
            System.out.println("Enter a valid id for update");
            update(userId);
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

        if (PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
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

        if (PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
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

        if (PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
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
     * Deletes the product from the products list
     * </p>
     *
     * @param id Represents the admin id
     */
    private void delete(final long id) {
        viewUserProduct(id);
        System.out.println("Enter the product id to delete the product details ");
        final int productId = USER_VIEW.getUserChoice();

        if (PRODUCT_CONTROLLER.delete(productId)) {
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Deleted unsuccessful enter a valid product id");
        }
    }

    /**
     * <p>
     * Create a product object and add the product object to the product list
     * </p>
     *
     * @param userId Represents {@link User} id
     */
    private void add(final Long userId) {
        System.out.println("Do you want to add new product press yes(y) or add existing product press press (E)");
        final String userChoice = SCANNER.nextLine().trim();

        if ("e".equalsIgnoreCase(userChoice)) {
            System.out.println("Enter the product id then enter the number of product have to be added");
            try {
                final Long productId = Long.parseLong(SCANNER.nextLine().trim());
                final Long quantity = Long.parseLong(SCANNER.nextLine().trim());
                updateQuantity(quantity, productId);
            } catch (NumberFormatException exception) {
                System.out.println("Enter the values in numbers");
            }
        } else if ("y".equalsIgnoreCase(userChoice)) {
            final Product product = PRODUCT_BUILDER.buildProduct(userId);

            if (PRODUCT_CONTROLLER.add(product)) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Product adding is unsuccessful");
            }
            System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");

            if (PRODUCT_VALIDATION.updateChoiceValidation(SCANNER.nextLine().trim())) {
                add(userId);
            } else {
                PRODUCT_VIEW.accessProduct(userId);
            }
        } else {
            System.out.println("Enter a valid choice");
            add(userId);
        }
    }


    /**
     * <p>
     * Gets the products from the product list
     * </p>
     */
    public void viewProduct(final Long userId) {
        final Collection<Product> products = PRODUCT_CONTROLLER.getAllProduct();
        if (products.isEmpty()) {
            System.out.println("The product list is empty");
            USER_VIEW.getUserOptions();
        }
        System.out.println(products);
        System.out.println("Do you want to add product to cart press yes(y) else no(n)");

        if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine().trim())) {
            addToCart(userId);
        }
        System.out.println("Do you want to order product press yes(y) else no(n)");

        if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine().trim())) {
            orderProduct(userId);
        }
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     *
     * @param userId Represents user userId
     */
    private void viewUserProduct(final Long userId) {
        final Map<Long, Product> products = PRODUCT_CONTROLLER.getUserProduct(userId);
        if (products.isEmpty()) {
            System.out.println("The product list is empty");
            accessProduct(userId);
        }
        System.out.println(products.values());
    }

    public void accessOrder(final Long userId) {
        System.out.println("Enter the Option\n1.get all orders \n2.remove order\n3.get order");
        final int userChoice = USER_VIEW.getUserChoice();

        switch (userChoice) {
            case 1:
                getOrderList(userId);
                break;
            case 2:
                orderRemove(userId);
                break;
            case 3:
                getOrder(userId);
                break;
            default:
                System.out.println("Enter the correct option");
                accessCart(userId);
        }
    }

    public void orderProduct(final Long userId) {
        PRODUCT_VIEW.viewProduct(userId);
        if (PRODUCT_CONTROLLER.order(ORDER_BUILDER.buildOrder(userId))) {
            System.out.println("Product ordered successfully");
        } else {
            System.out.println("Product ordered unsuccessful");
        }
        System.out.println("Do you want to order more product");

        if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            orderProduct(userId);
        } else {
            USER_VIEW.getUserOptions();
        }
    }

    public void getOrderList(final Long userId) {
        System.out.println(PRODUCT_CONTROLLER.getOrderList(userId));
    }

    public Order.Payment getPaymentType() {
        System.out.println(String.join("", "Enter the payment method\n1.cash on delivery\n",
                "2.credit or debit cart\n3.net banking\n4.other upi apps"));

        try {
            final int paymentChoice = Integer.parseInt(SCANNER.nextLine().trim());

            if (PRODUCT_VALIDATION.isReturnToMenu(String.valueOf(paymentChoice))) {
                USER_VIEW.getUserOptions();
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
        return getPaymentType();
    }

    public void getOrder(final Long userId) {
        System.out.println("Enter the order id (press # to back to menu)");
        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (PRODUCT_VALIDATION.isReturnToMenu(userChoice)) {
                accessOrder(userId);
            }
            final Order order = PRODUCT_CONTROLLER.getOrder(Long.valueOf(userChoice));

            if (null == order) {
                System.out.println("Order is not found");
                getOrder(userId);
            }
            System.out.println(order);
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid order id ");
            getOrder(userId);
        }
        accessOrder(userId);
    }

    public void orderRemove(final Long userId) {
        getOrderList(userId);
        System.out.println("Enter the order id for remove from order");

        try {
            final Long orderId = Long.parseLong(SCANNER.nextLine());

            if (PRODUCT_CONTROLLER.removeOrder(orderId)) {
                System.out.println("Order removed successfully");
                accessOrder(userId);
            } else {
                System.out.println("Order removed unsuccessful");
                orderRemove(userId);
            }
        } catch (NumberFormatException exception) {
            System.out.println("Enter the valid order id");
            orderRemove(userId);
        }
    }

    public void accessCart(final Long userId) {
        System.out.println("Enter the Option\n1.get cart\n2.remove product cart");
        final int userChoice = USER_VIEW.getUserChoice();

        switch (userChoice) {
            case 1:
                getCart(userId);
                break;
            case 2:
                removeCart(userId);
                break;
            default:
                System.out.println("Enter the correct option");
                accessCart(userId);
        }
    }

    public void addToCart(final Long userId) {
        System.out.println("Enter the product id for add to cart(press # fro return to menu");

        try {
            final Long productId = Long.parseLong(SCANNER.nextLine());

            if (PRODUCT_VALIDATION.isReturnToMenu(String.valueOf(productId))) {
                USER_VIEW.getUserOptions();
            }
            System.out.println("Enter the quantity you want in number");
            final Long quantity = Long.parseLong(SCANNER.nextLine().trim());
            System.out.println(PRODUCT_CONTROLLER.getCartList(userId));

            if (PRODUCT_CONTROLLER.getProductIds(userId).contains(productId)) {
                updateQuantity(quantity, productId);
                accessCart(userId);
            }

            final Cart cart = new Cart();
            final Product product = PRODUCT_CONTROLLER.get(productId);

            cart.setProductId(productId);
            cart.setProductName(product.getName());
            cart.setProductCount(quantity);
            cart.setUserId(userId);
            final Double totalPrice = product.getPrice() * quantity;

            cart.setTotalPrice(totalPrice);

            if (PRODUCT_CONTROLLER.addToCart(cart)) {
                System.out.println("Product added to cart successfully");
            } else {
                System.out.println("Product added to cart is unsuccessfully");
            }
            System.out.println("Do you want to add more product in cart");

            if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
                addToCart(userId);
            } else {
                USER_VIEW.getUserOptions();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Enter the correct product id");
            addToCart(userId);
        }

    }

    public void getCart(final Long userId) {
        final List<Cart> cartList = PRODUCT_CONTROLLER.getCartList(userId);

        if (null == cartList) {
            System.out.println("Your cart is empty");
            USER_VIEW.getUserOptions();
        } else {
            System.out.println(cartList);
            System.out.println("do you want to order the products");

            if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
                System.out.println("Enter the cart id");

                try {
                    final Cart cart = PRODUCT_CONTROLLER.getCart(Long.parseLong(SCANNER.nextLine()));
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

            if (PRODUCT_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
                USER_VIEW.getUserOptions();
            } else {
                getCart(userId);
            }
        }
    }

    public void cartToOrder(final Cart cart) {
        final Order order = new Order(cart, getPaymentType());
        PRODUCT_CONTROLLER.order(order);
    }

    public void removeCart(final Long userId) {
        System.out.println(PRODUCT_CONTROLLER.getCartList(userId));
        System.out.println("Enter the cart id for remove from cart");

        try {
            final Long cartId = Long.parseLong(SCANNER.nextLine());

            if (PRODUCT_CONTROLLER.removeCart(cartId)) {
                System.out.println("product removed successfully");
                accessCart(userId);
            } else {
                System.out.println("product remove unsuccessful Enter a valid product key");
                removeCart(userId);
            }
        } catch (NumberFormatException exception) {
            System.out.println("Enter the valid product key");
            removeCart(userId);
        }
    }

    public void updateQuantity(final Long quantity, final Long productId) {
        if (PRODUCT_CONTROLLER.updateQuantity(quantity, productId)) {
            System.out.println("updated successfully");
        }
    }

}

