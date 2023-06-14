package com.amazon.view;

import com.amazon.controller.ProductController;
import com.amazon.controller.UserController;
import com.amazon.model.Product;
import com.amazon.model.Product.Category;
import com.amazon.model.User;
import com.amazon.view.validation.ProductValidation;
import com.amazon.view.validation.UserValidation;
import com.amazon.view.validation.Validation;

import java.util.Scanner;

/**
 * <p>
 * Provides methods for {@link User} sign in, sign up, update, delete and {@link Product} view, add, update, delete
 * </P>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonView {

    private static final AmazonView amazonView = new AmazonView();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AmazonView AMAZON_VIEW = getAmazonView();
    private static final UserController AMAZON_USER_CONTROLLER = UserController.getAmazonUserController();
    private static final ProductController AMAZON_PRODUCT_CONTROLLER = ProductController.getAmazonProductController();
    private static final Validation AMAZON_VALIDATION = Validation.getAmazonValidation();
    private static final UserValidation AMAZON_USER_VALIDATION = UserValidation.getAmazonUserValidation();
    private static final ProductValidation AMAZON_PRODUCT_VALIDATION = ProductValidation.getAmazonProductValidation();

    private AmazonView() {
    }

    /**
     * Represents the object of amazonView class be created for only one time
     *
     * @return Represents {@link AmazonView}
     */
    public static AmazonView getAmazonView() {
        return amazonView;
    }

    /**
     * <p>
     * Provides Methods for {@link User} sign in, sign up and exit
     * </p>
     */
    private void displayMenu() {
        System.out.println("1.signUp\n2.signIn\n3.exit");
        final int option = getUserChoice();

        switch (option) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            case 3:
                SCANNER.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                displayMenu();
        }
    }

    /**
     * <p>
     * Gets user type from the user and providing sign in
     * </p>
     */
    private void signIn() {
        System.out.println("Enter the user type\n1.admin user\n2.customer");
        final int userChoice = getUserChoice();

        if (AMAZON_USER_VALIDATION.isReturnToMenu(String.valueOf(userChoice))) {
            displayMenu();
        }

        if (1 == userChoice) {
            signIn(getUserEmail(), getUserPassword(), getAdminKey());
        } else if (2 == userChoice) {
            signIn(getUserEmail(), getUserPassword());
        } else {
            System.out.println("Enter the valid option");
            signIn();
        }
    }

    /**
     * <p>
     * Gets the email and password from the user and provides customer sign in
     * </p>
     *
     * @param email    Represents user email
     * @param password Represents user password
     */
    private void signIn(final String email, final String password) {
        if (AMAZON_USER_CONTROLLER.signIn(email, password)) {
            System.out.println("User sign in successful");
            getUserOptions();
        } else {
            System.out.println("Invalid details");
            signIn();
        }
    }

    /**
     * <p>
     * Gets the email and password from the user and provides admin sign in
     * </p>
     *
     * @param email    Represents user email
     * @param password Represents user password
     * @param key      Represents user key
     */
    private void signIn(final String email, final String password, final String key) {
        if (AMAZON_USER_CONTROLLER.signIn(email, password, key)) {
            System.out.println("Admin singIn successful");
            getAdminOptions();
        } else {
            System.out.println("Invalid details");
            signIn();
        }
    }

    /**
     * <p>
     * Gets the user details and provides signup for the user
     * </p>
     */
    private void signUp() {
        try {
            final String userEmail = getUserEmail();

            if (AMAZON_USER_CONTROLLER.isUserEmailExists(userEmail)) {
                System.out.println("The email is already exists");
                getUserEmail();
            }
            final User user = new User();

            user.setEmail(userEmail);
            user.setPassword(getUserPassword());
            user.setName(getUserName());
            user.setAddress(getUserAddress());
            user.setPhoneNumber(getUserPhoneNumber());
            System.out.println("Are you an admin user press yes(y) or no(n)");
            final String userChoice = SCANNER.nextLine().trim();

            if ("y".equalsIgnoreCase(userChoice)) {
                verifyAdmin(user);
            }
            if (AMAZON_USER_CONTROLLER.signUp(user)) {
                System.out.println("Sing up successful");
                getUserOptions();
            } else {
                System.out.println("Sign up unsuccessful");
            }
        } catch (final StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Gets the user object and admin key verify the key and provide user sign up
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void verifyAdmin(final User user) {
        if (AMAZON_USER_CONTROLLER.signUp(user, getAdminKey())) {
            System.out.println("Admin sign up successful");
            getAdminOptions();
        } else {
            System.out.println("Do you want to enter admin key again press yes(y) else press no(n)");

            if ('y' == SCANNER.nextLine().charAt(0)) {
                verifyAdmin(user);
            } else {
                System.out.println("Verification failed");
                displayMenu();
            }
        }
    }

    /**
     * <p>
     * Gets the admin key while admin registration
     * </p>
     *
     * @return Represents admin key entered by the user
     */
    private String getAdminKey() {
        try {
            System.out.println("Enter the admin key for verification\t(press # for back to menu)");
            final String adminKey = SCANNER.nextLine();

            if (AMAZON_USER_VALIDATION.isReturnToMenu(adminKey)) {
                displayMenu();
            }

            return adminKey;
        } catch (final StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getAdminKey();
    }

    /**
     * <p>
     * Provides option for access admin details and the products details and logout
     * </p>
     */
    private void getAdminOptions() {
        System.out.println("Choose from the options\n1.admin details\t2.product details\t3.logout");
        final int adminOption = getUserChoice();

        if (3 == adminOption) {
            displayMenu();
        }
        final User user = getsUserDetails();

        switch (adminOption) {
            case 1:
                accessUser(user);
                break;
            case 2:
                accessProduct(user.getId());
                break;
            default:
                System.out.println("Enter the correct option");
                getAdminOptions();
        }
    }

    /**
     * <p>
     * Provides navigation for admin details and the products details and logout
     * </p>
     */
    private void getUserOptions() {
        System.out.println("Choose from the options\n1.usersDetail\n2.viewProducts\n3.logout");
        final int userOption = getUserChoice();

        switch (userOption) {
            case 1:
                final User user = getsUserDetails();
                accessUser(user);
                break;
            case 2:
                viewProducts();
                break;
            case 3:
                displayMenu();
                break;
            default:
                System.out.println("Enter the correct option");
                getUserOptions();
        }
    }

    /**
     * <p>
     * Provides methods for product add, view ,update, delete details
     * </p>
     *
     * @param userId Represents the user's id
     */
    private void accessProduct(final Long userId) {
        System.out.println(String.join("", "Choose from the options\n1.get all product\t",
                "2.add product", "\t3.update product\t4.delete product\t5.Back to admin option"));
        final int userOption = getUserChoice();

        switch (userOption) {
            case 1:
                viewProducts(userId);
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
                getAdminOptions();
                break;
            default:
                System.out.println("Enter the correct option");
                accessProduct(userId);
                break;
        }
        System.out.println("Do you want to continue press yes(y) else press any letter key");

        if (AMAZON_USER_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            accessProduct(userId);
        } else {
            getAdminOptions();
        }
    }

    /**
     * <p>
     * Retrieves the admin details using email ,update the admin details and delete admin details
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void accessUser(final User user) {
        System.out.println("Choose from the options\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
        final int userOption = getUserChoice();

        switch (userOption) {
            case 1:
                System.out.println(user);
                break;
            case 2:
                updateUserDetails(user);
                break;
            case 3:
                deleteUser(user);
                break;
            case 4:
                getUserOptions();
                break;
            default:
                System.out.println("Enter the correct option");
                accessUser(user);
        }
        System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

        if (AMAZON_USER_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            accessUser(user);
        } else {
            if (user.getIsAdmin()) {
                getAdminOptions();
            } else {
                getUserOptions();
            }
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
        viewProducts(userId);
        System.out.println("Enter the product id for update product information");

        final long productId = getUserChoice();

        if (AMAZON_PRODUCT_VALIDATION.validateProductIds(productId - 1)) {

            final Product product = AMAZON_PRODUCT_CONTROLLER.get(productId - 1);

            updateProductName(product);
            updateProductDescription(product);
            updateProductPrice(product);

            if (AMAZON_PRODUCT_CONTROLLER.update(productId, product)) {
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
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product name for update");
            final String productName = SCANNER.nextLine();

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
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product description for update");
            final String description = SCANNER.nextLine();

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

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
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
        viewProducts(id);
        System.out.println("Enter the product id to delete the product details ");
        final int productId = getUserChoice();

        if (AMAZON_PRODUCT_CONTROLLER.delete(productId)) {
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Deleted unsuccessful enter a valid product id");
        }
    }


    /**
     * <p>
     * Gets the user details in user list by using email
     * </p>
     *
     * @return Represents {@link User}
     */
    private User getsUserDetails() {
        final String email = getUserEmail();

        final User user = AMAZON_USER_CONTROLLER.getDetail(email);

        if (user == null) {
            System.out.println("The user is not exists");

            return getsUserDetails();
        } else {
            return user;
        }
    }

    /**
     * <p>
     * Updates the Admin details in user list using user object
     * </p>
     *
     * @param user {@link User} Admin object want to update
     */
    private void updateUserDetails(final User user) {
        updateUserName(user);
        updateUserEmail(user);
        updateUserPassword(user);
        updateUserAddress(user);
        updateUserPhoneNo(user);

        if (user.getIsAdmin()) {
            getAdminOptions();
        } else {
            getUserOptions();
        }
    }

    /**
     * <p>
     * Updates the username in user object
     * </p>
     *
     * @param user Represents {@link User} wants to update
     */
    private void updateUserName(final User user) {
        System.out.println("Do you want to update user name press yes(y) else press no(n)");

        if (AMAZON_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
            user.setName(getUserName());
        }
    }


    /**
     * <p>
     * Updates the user email in user object
     * </p>
     *
     * @param user Represents {@link User} want's to update
     */
    private void updateUserEmail(final User user) {
        System.out.println("Do you want to update user email press yes(y) else press no(n)");

        if (AMAZON_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
            user.setEmail(getUserEmail());
        }
    }

    /**
     * <p>
     * Updates the user password in user object
     * </p>
     *
     * @param user {@link User} User object wants to update
     */
    private void updateUserPassword(final User user) {
        System.out.println("Do you want to update user password press yes(y) else press no(n)");

        if (AMAZON_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
            user.setPassword(getUserPassword());
        }
    }

    /**
     * <p>
     * Updates the user address in user object
     * </p>
     *
     * @param user {@link User} User object wants to update
     */
    private void updateUserAddress(final User user) {
        System.out.println("Do you want to update user address press yes(y) else press no(n)");

        if (AMAZON_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
            user.setAddress(getUserAddress());
        }
    }

    /**
     * <p>
     * Updates the phone no in user object
     * </p>
     *
     * @param user Represents {@link User} wants to update
     */
    private void updateUserPhoneNo(final User user) {
        System.out.println("Do you want to update user phone number press yes(y) else press no(n)");

        if (AMAZON_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
            user.setPhoneNumber(getUserPhoneNumber());
        }
    }

    /**
     * <p>
     * Deletes the user object from user list
     * </p>
     *
     * @param user Represents {@link User}  to be delete
     */
    private void deleteUser(final User user) {
        if (AMAZON_USER_CONTROLLER.delete(user)) {
            System.out.println("User Account deleted");
        } else {
            System.out.println("User account not deleted");
        }
    }

    /**
     * <p>
     * Gets and validate email value from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    private String getUserEmail() {
        try {
            System.out.println("Enter the email id\t(press # for logout to menu)");
            final String emailId = SCANNER.nextLine().trim();

            if (AMAZON_USER_VALIDATION.isReturnToMenu(emailId)) {
                displayMenu();
            }

            if (AMAZON_USER_VALIDATION.validateEmail(emailId)) {
                return emailId;
            } else {
                System.out.println("Invalid email id enter the correct email id");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserEmail();
    }

    /**
     * <p>
     * Gets and validate the user password from the user
     * </p>
     *
     * @return Represents password that the user entered
     */
    private String getUserPassword() {
        try {
            System.out.println(String.join(" ", "Enter the password\t(password must contain",
                    "one capital letter small letter, number and a symbol)\t(press # for logout to menu)"));
            final String password = SCANNER.nextLine().trim();

            if (AMAZON_USER_VALIDATION.isReturnToMenu(password)) {
                displayMenu();
            }

            if (AMAZON_USER_VALIDATION.validatePassword(password)) {
                return password;
            }
            System.out.println("Your password is no strong enough");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserPassword();
    }

    /**
     * <p>
     * Gets address from the user object
     * </p>
     *
     * @return Represents address user entered
     */
    private String getUserAddress() {
        try {
            System.out.println(String.join("", "Enter the address\n", "(first enter the door number and enter the address ", "eg: 2/34 new bus stand, tirunelveli tamil nadu 627007)\t(press # for logout to menu)"));
            final String address = SCANNER.nextLine().trim();

            if (AMAZON_USER_VALIDATION.isReturnToMenu(address)) {
                displayMenu();
            }

            if (AMAZON_USER_VALIDATION.validateAddress(address)) {
                return address;
            } else {
                System.out.println("invalid address");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserAddress();
    }

    /**
     * <p>
     * Gets and validate the username from the user
     * </p>
     *
     * @return Represents username the user entered
     */
    private String getUserName() {
        try {
            System.out.println("Enter the user name\t(press # for logout to menu)");
            final String userName = SCANNER.nextLine().trim();

            if (AMAZON_USER_VALIDATION.isReturnToMenu(userName)) {
                displayMenu();
            }

            if (AMAZON_USER_VALIDATION.validateUserName(userName)) {
                return userName;
            }
            System.out.println("Invalid username");
        } catch (final IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserName();
    }

    /**
     * <p>
     * Gets and validate phone number from user
     * </p>
     *
     * @return Represents phone number the user entered
     */
    private String getUserPhoneNumber() {
        try {
            System.out.println("Enter the phone no\t(press # for logout)");
            final String phoneNo = SCANNER.nextLine().trim();

            if (AMAZON_USER_VALIDATION.isReturnToMenu(phoneNo)) {
                displayMenu();
            }

            if (AMAZON_USER_VALIDATION.validatePhone(phoneNo)) {
                return phoneNo;
            }
            System.out.println("Invalid phone number");
        } catch (final IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserPhoneNumber();
    }

    /**
     * <p>
     * Gets and validate the user's choice
     * </p>
     *
     * @return Represents value that the user entered
     */
    private int getUserChoice() {
        System.out.println("Enter the choice");
        final String userChoice = SCANNER.nextLine().trim();

        try {
            return Integer.parseInt(userChoice);
        } catch (final NumberFormatException exception) {
            System.out.println("Invalid input enter the number input");
        }
        return getUserChoice();
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

            if (AMAZON_USER_VALIDATION.isReturnToMenu(String.valueOf(productPrice))) {
                accessProduct(userId);
            }
            return productPrice;
        } catch (final NumberFormatException Exception) {
            System.out.println("Enter the value is number");
        }
        System.out.println("Do you want to enter again press yes(y) else press any letter key");

        try {
            if ('y' == SCANNER.nextLine().charAt(0)) {
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

        if (AMAZON_PRODUCT_CONTROLLER.add(product)) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("Product adding is unsuccessful");
        }
        System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");
        final String userChoice = SCANNER.nextLine().trim();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            addProduct(userId);
        } else {
            getAdminOptions();
        }
    }

    /**
     * <p>
     * Gets the product category from the user
     * </p>
     *
     * @param userId Represents {@link User} id
     * @return Represent {@link Category}
     */
    private Category getProductCategory(final Long userId) {
        System.out.println(String.join("", "Enter the product category in words\nmobile_phones\n",
                "footwear\nelectronics\nclothing\nkitchen_appliances\nsports\nbooks\ntoys\t",
                "(press # to return to product menu)"));
        final String productChoice = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.isReturnToMenu(productChoice)) {
            accessProduct(userId);
        }

        try {
            return AMAZON_PRODUCT_VALIDATION.validateCategory(productChoice.toUpperCase());
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
    private void viewProducts() {
        if (AMAZON_PRODUCT_CONTROLLER.getUserProduct().isEmpty()) {
            System.out.println("The product list is empty");
            getUserOptions();
        }
        System.out.println(AMAZON_PRODUCT_CONTROLLER.getUserProduct());
    }

    /**
     * <p>
     * Gets the products from the product list
     * </p>
     *
     * @param userId Represents user userId
     */
    private void viewProducts(final Long userId) {
        if (AMAZON_PRODUCT_CONTROLLER.getUserProduct().isEmpty()) {
            System.out.println("The product list is empty");
            accessProduct(userId);
        }
        System.out.println(AMAZON_PRODUCT_CONTROLLER.getUserProduct(userId));
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

            if (AMAZON_PRODUCT_VALIDATION.isReturnToMenu(productName)) {
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

            if (AMAZON_PRODUCT_VALIDATION.isReturnToMenu(description)) {
                accessProduct(userId);
            }

            return description;
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getProductDescription(userId);
    }

    public static void main(final String[] args) {
        System.out.println("Welcome to amazon");
        AMAZON_VIEW.displayMenu();
        System.out.println("Do you want to continue yes(y) else press n to exit");
        final String exitChoice = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.toContinueValidation(exitChoice)) {
            AMAZON_VIEW.displayMenu();
        } else {
            SCANNER.close();
        }
    }
}