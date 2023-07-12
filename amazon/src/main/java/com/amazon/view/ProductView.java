package com.amazon.view;

import com.amazon.controller.ProductController;
import com.amazon.exception.UnavailableQuantityException;
import com.amazon.model.Cart;
import com.amazon.model.Order;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.builder.CartBuilder;
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
public class ProductView extends ScannerInstance {

    private static ProductView productView = null;
    private static final UserView USER_VIEW = UserView.getInstance();
    public final ProductController productController = ProductController.getInstance();
    private final ProductBuilder productBuilder = ProductBuilder.getInstance();
    private final OrderBuilder orderBuilder = OrderBuilder.getInstance();
    private final CartBuilder cartBuilder = CartBuilder.getInstance();
    public final ProductValidation productValidation = ProductValidation.getInstance();


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

        if (productValidation.toContinueValidation(SCANNER.nextLine().trim())) {
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
     * @param userId Represents the id of {@link User}
     */
    private void update(final long userId) {
        System.out.println("Enter the product id for update product information");
        final long productId = USER_VIEW.getUserChoice();

        if (productValidation.validateProductIds(productId, userId)) {

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

        if (productValidation.toContinueValidation(userChoice)) {
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

        if (productValidation.toContinueValidation(userChoice)) {
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

        if (productValidation.toContinueValidation(userChoice)) {
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

        if (productValidation.toContinueValidation(userChoice)) {
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
        viewUserProduct(userId);
        System.out.println("Enter the product id to delete the product details ");
        final int productId = USER_VIEW.getUserChoice();

        if (productController.delete(productId)) {
            System.out.println("Product deleted successfully");

        } else {
            System.out.println("Deleted unsuccessful enter a valid product id");
            delete(userId);
        }
    }

    /**
     * <p>
     * Create a product object and add the product object to the product list
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void add(final Long userId) {
        System.out.println("Do you want to add new product press yes(y) or add existing product press press (E)");
        final String userChoice = SCANNER.nextLine().trim();

        if ("e".equalsIgnoreCase(userChoice)) {
            System.out.println("Enter the product id then enter the number of product have to be added");

            try {
                productController.updateQuantityInProduct(getQuantity(), USER_VIEW.getId());
            } catch (NumberFormatException exception) {
                System.out.println("Enter the values in numbers");
            }
        } else if ("y".equalsIgnoreCase(userChoice)) {
            final Product product = productBuilder.buildProduct(getCategory(userId), getName(userId), getDescription(userId), getQuantity(), getPrice(userId), userId);

            if (productController.add(product)) {
                System.out.println("Product added successfully");
            } else {
                System.out.println("Product adding is unsuccessful");
            }
            System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");

            if (productValidation.toContinueValidation(SCANNER.nextLine().trim())) {
                add(userId);
            } else {
                productView.accessProduct(userId);
            }
        } else {
            System.out.println("Enter a valid choice");
            add(userId);
        }
    }

    /**
     * Represents getting the product quantity from the user
     *
     * @return The quantity of product
     */
    private Long getQuantity() {
        System.out.println("Enter the quantity you want in number press # for back to product options");

        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidation.isReturnToMenu(userChoice)) {
                USER_VIEW.getUserOptions();
            }

            try {
                return Long.parseLong(userChoice);
            } catch (NumberFormatException exception) {
                System.out.println("Enter the quantity in number");
                getQuantity();
            }
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("Enter valid id");
        }
        return getQuantity();
    }

    /**
     * <p>
     * Gets the product category from the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represent {@link Product.Category}
     */
    public Product.Category getCategory(final Long userId) {
        System.out.println(String.join("", "Enter the product category\n1.mobile_phones\n",
                "2.footwear\n3.electronics\n4.clothing\n5.kitchen_appliances\n6.sports\n7.books\n8.toys\t",
                "(press # to return to product menu)"));

        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidation.isReturnToMenu(userChoice)) {
                accessProduct(userId);
            }

            final int productChoice = Integer.parseInt(userChoice);

            final Product.Category category = Product.Category.getCategory(productChoice);

            if (null == category) {
                System.out.println("Enter a valid category");
            } else {
                return category;
            }

        } catch (IllegalArgumentException exception) {
            System.out.println("Enter a valid category");
        }

        return getCategory(userId);
    }

    /**
     * <p>
     * Gets the product name from user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} name
     */
    public String getName(final Long userId) {
        try {
            System.out.println("Enter the product name\t(press # for back to menu)");
            final String productName = SCANNER.nextLine();

            if (productValidation.isReturnToMenu(productName)) {
                accessProduct(userId);
            }

            return productName;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getName(userId);
    }


    /**
     * <p>
     * Get the product description from the user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} description
     */
    public String getDescription(final Long userId) {
        try {
            System.out.println("Enter the product description\t(press # for back to menu)");
            final String description = SCANNER.nextLine();

            if (productValidation.isReturnToMenu(description)) {
                accessProduct(userId);
            }

            return description;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getDescription(userId);
    }

    /**
     * <p>
     * Gets and validate price that is entered by the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represents price value entered by user
     */
    public double getPrice(final Long userId) {
        System.out.println("Enter the Product price\t(press # for back to product menu)");

        try {
            final double productPrice = Double.parseDouble(SCANNER.nextLine().trim());

            if (productValidation.isReturnToMenu(String.valueOf(productPrice))) {
                accessProduct(userId);
            }
            return productPrice;
        } catch (final NumberFormatException Exception) {
            System.out.println("Enter the value is number");
        }
        System.out.println("Do you want to enter price again press yes(y) else press any letter key");

        try {
            if ("y".equalsIgnoreCase(SCANNER.nextLine().trim())) {
                return getPrice(userId);
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
        final Collection<Product> products = productController.getAllProduct();
        if (products == null) {
            System.out.println("The product list is empty");
            USER_VIEW.getUserOptions();
        }
        System.out.println(products);
        System.out.println("Do you want to add product to cart press yes(y) else no(n)");

        if (productValidation.toContinueValidation(SCANNER.nextLine().trim())) {
            addToCart(userId);
        }
        System.out.println("Do you want to order product press yes(y) else no(n)");

        if (productValidation.toContinueValidation(SCANNER.nextLine().trim())) {
            orderProduct(userId);
        }
        USER_VIEW.getUserOptions();
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void viewUserProduct(final Long userId) {
        final Map<Long, Product> products = productController.getUserProduct(userId);
        if (products.isEmpty()) {
            System.out.println("The product list is empty");
            accessProduct(userId);
        }
        System.out.println(products.values());
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
        final int userChoice = USER_VIEW.getUserChoice();

        switch (userChoice) {
            case 1:
                getOrderList(userId);
                break;
            case 2:
                cancelOrder(userId);
                break;
            case 3:
                getOrder(userId);
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
            if (productController.order(orderBuilder.buildOrder(getId(), getQuantity(), getPaymentType(),
                    userId))) {
                System.out.println("Product ordered successfully");
            } else {
                System.out.println("Product ordered unsuccessful");
            }
        } catch (UnavailableQuantityException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Do you want to order more product");

        if (productValidation.toContinueValidation(SCANNER.nextLine().trim())) {
            orderProduct(userId);
        } else {
            USER_VIEW.getUserOptions();
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
        System.out.println(productController.getOrderList(userId));
    }

    /**
     * <p>
     * Represents the payment type of user for order
     * </p>
     */
    public Order.Payment getPaymentType() {
        System.out.println(String.join("", "Enter the payment method\n1.cash on delivery\n",
                "2.credit or debit cart\n3.net banking\n4.other upi apps"));

        try {
            final int paymentChoice = Integer.parseInt(SCANNER.nextLine().trim());

            if (productValidation.isReturnToMenu(String.valueOf(paymentChoice))) {
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

    /**
     * <p>
     * Represents order detail for particular order id
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void getOrder(final Long userId) {
        System.out.println("Enter the order id (press # to back to menu)");
        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidation.isReturnToMenu(userChoice)) {
                accessOrder(userId);
            }
            final Order order = productController.getOrder(Long.valueOf(userChoice));

            if (null == order) {
                System.out.println("Order is not found");
                getOrder(userId);
            }
            System.out.println(order);
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid order id");
            getOrder(userId);
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
        } catch (NumberFormatException exception) {
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

    /**
     * <p>
     * Represents adding product to the cart
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    public void addToCart(final Long userId) {
        System.out.println("Enter the product id for add to cart(press # fro return to menu)");

        try {
            final Long productId = getId();

            if (productValidation.isReturnToMenu(String.valueOf(productId))) {
                USER_VIEW.getUserOptions();
            }
            final Long quantity = getQuantity();

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

            if (productValidation.toContinueValidation(SCANNER.nextLine())) {
                addToCart(userId);
            } else {
                USER_VIEW.getUserOptions();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Enter the correct product id");
            addToCart(userId);
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
        final List<Cart> cartList = productController.getCartList(userId);

        if (null == cartList) {
            System.out.println("Your cart is empty");
            USER_VIEW.getUserOptions();
        } else {
            System.out.println(cartList);
            System.out.println("do you want to order the products");

            if (productValidation.toContinueValidation(SCANNER.nextLine())) {
                System.out.println("Enter the cart id");

                try {
                    final Cart cart = productController.getCart(Long.parseLong(SCANNER.nextLine()));
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

            if (productValidation.toContinueValidation(SCANNER.nextLine())) {
                USER_VIEW.getUserOptions();
            } else {
                getCart(userId);
            }
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
        final Order order = new Order(cart, getPaymentType());
        productController.order(order);
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
            final Long cartId = Long.parseLong(SCANNER.nextLine());

            if (productController.removeCart(cartId)) {
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

    /**
     * <p>
     * Gets the id from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public Long getId() {
        try {
            System.out.println("Enter the product id\t(press # for logout to menu)");
            final String userChoice = SCANNER.nextLine().trim();

            if (productValidation.isReturnToMenu(userChoice)) {
                USER_VIEW.getUserOptions();
            }

            return Long.parseLong(userChoice);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getId();
    }
}

