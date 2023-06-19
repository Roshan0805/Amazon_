package com.amazon.view;

import com.amazon.controller.ProductController;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.validation.ProductValidation;
import com.amazon.model.Product.Category;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 * Represents {@link Product} view, update and delete
 * </p>
 *
 * @version 1.0
 * @auther Roshan
 */
public class ProductView {

    private static final ProductView PRODUCT_VIEW = new ProductView();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final CustomerView CUSTOMER_VIEW = CustomerView.getInstance();
    private static final AdminView ADMIN_VIEW = AdminView.getInstance();
    private static final ProductController PRODUCT_CONTROLLER = ProductController.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ProductValidation PRODUCT_VALIDATION = ProductValidation.getInstance();

    private ProductView() {
    }

    /**
     * <p>
     * Represents the object for {@link ProductView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link ProductView}
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
                "2.add product", "\t3.update product\t4.delete product\t5.user created product",
                "\n6.Back to admin option"));
        final int userOption = USER_VIEW.getUserChoice();

        switch (userOption) {
            case 1:
                viewProduct(userId);
                break;
            case 2:
                addProduct(userId);
                break;
            case 3:
                updateProduct(userId);
                break;
            case 4:
                deleteProduct(userId);
                break;
            case 5:
                viewUserProduct(userId);
                break;
            case 6:
                ADMIN_VIEW.getAdminOptions();
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
            ADMIN_VIEW.getAdminOptions();
        }
    }

    /**
     * <p>
     * Updates the product details by getting product id from the user
     * </p>
     *
     * @param userId Represents the user id
     */
    private void updateProduct(final long userId) {
        System.out.println("Enter the product id for update product information");
        final long productId = USER_VIEW.getUserChoice();

        if (PRODUCT_VALIDATION.validateProductIds(productId, userId)) {

            final Product product = PRODUCT_CONTROLLER.get(productId);

            updateProductName(product);
            updateProductDescription(product);
            updateProductPrice(product);

            if (PRODUCT_CONTROLLER.update(productId, product)) {
                System.out.println("Updated successfully");
                accessProduct(userId);
            } else {
                System.out.println("Updated unsuccessful");
            }

        } else {
            System.out.println("Enter a valid id for update");
            updateProduct(userId);
        }
    }

    /**
     * <p>
     * Updates the product name in product object
     * </p>
     *
     * @param product Represents {@link Product}
     */
    private void updateProductName(final Product product) {
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
    private void updateProductDescription(final Product product) {
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
    private void updateProductPrice(final Product product) {
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
    private void deleteProduct(final long id) {
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
     * Gets and validate price that is entered by the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represents price value entered by user
     */
    private double getProductPrice(final Long userId) {
        System.out.println("Enter the Product price\t(press # for back to product menu)");

        try {
            final double productPrice = Double.parseDouble(SCANNER.nextLine().trim());

            if (PRODUCT_VALIDATION.isReturnToMenu(String.valueOf(productPrice))) {
                accessProduct(userId);
            }
            return productPrice;
        } catch (final NumberFormatException Exception) {
            System.out.println("Enter the value is number");
        }
        System.out.println("Do you want to enter price again press yes(y) else press any letter key");

        try {
            if ("y".equalsIgnoreCase(SCANNER.nextLine().trim())) {
                return getProductPrice(userId);
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    /**
     * <p>
     * Create a product object and add the product object to the product list
     * </p>
     *
     * @param userId Represents {@link User} id
     */
    private void addProduct(final Long userId) {
        final Product product = new Product();

        product.setCategory(getProductCategory(userId));
        product.setName(getProductName(userId));
        product.setDescription(getProductDescription(userId));
        product.setPrice(getProductPrice(userId));
        product.setAdminId(userId);

        if (PRODUCT_CONTROLLER.add(product)) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("Product adding is unsuccessful");
        }
        System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");
        final String userChoice = SCANNER.nextLine().trim();

        if (PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            addProduct(userId);
        } else {
            PRODUCT_VIEW.accessProduct(userId);
        }
    }

    /**
     * <p>
     * Gets the product category from the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represent {@link Product.Category}
     */
    private Category getProductCategory(final Long userId) {
        System.out.println(String.join("", "Enter the product category\n1.mobile_phones\n",
                "2.footwear\n3.electronics\n4.clothing\n5.kitchen_appliances\n6.sports\n7.books\n8.toys\t",
                "(press # to return to product menu)"));

        try {
            final int productChoice = Integer.parseInt(SCANNER.nextLine().trim());

            if (PRODUCT_VALIDATION.isReturnToMenu(String.valueOf(productChoice))) {
                accessProduct(userId);
            }
            final Category category = Product.Category.getCategory(productChoice);

            if (null == category) {
                System.out.println("Enter a valid category");
            } else {
                return category;
            }

        } catch (IllegalArgumentException exception) {
            System.out.println("Enter a valid category");
        }

        return getProductCategory(userId);
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     */
    public void viewProduct(final long id) {
        final Collection<Product> products = PRODUCT_CONTROLLER.getAllProduct();
        if (products.isEmpty()) {
            System.out.println("The product list is empty");
            PRODUCT_VIEW.accessProduct(id);
        }
        System.out.println(products);
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     */
    public void viewProduct() {
        final Collection<Product> products = PRODUCT_CONTROLLER.getAllProduct();
        if (products.isEmpty()) {
            System.out.println("The product list is empty");
            CUSTOMER_VIEW.getUserOptions();
        }
        System.out.println(products);
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

    /**
     * <p>
     * Gets the product name from user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} name
     */
    private String getProductName(final Long userId) {
        try {
            System.out.println("Enter the product name\t(press # for back to menu)");
            final String productName = SCANNER.nextLine();

            if (PRODUCT_VALIDATION.isReturnToMenu(productName)) {
                accessProduct(userId);
            }

            return productName;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getProductName(userId);
    }

    /**
     * <p>
     * Get the product description from the user
     * </p>
     *
     * @param userId Represents user userId
     * @return Represents {@link Product} description
     */
    private String getProductDescription(final Long userId) {
        try {
            System.out.println("Enter the product description\t(press # for back to menu)");
            final String description = SCANNER.nextLine();

            if (PRODUCT_VALIDATION.isReturnToMenu(description)) {
                accessProduct(userId);
            }

            return description;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getProductDescription(userId);
    }
}
