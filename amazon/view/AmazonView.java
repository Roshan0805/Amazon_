package com.amazon.view;

import com.amazon.controller.AmazonProductController;
import com.amazon.controller.AmazonUserController;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.validation.AmazonProductValidation;
import com.amazon.view.validation.AmazonUserValidation;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides methods for {@link User} sign in, sign up, update, delete and {@link Product} product add, update, delete
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AmazonUserController AMAZON_USER_CONTROLLER = new AmazonUserController();
    private static final AmazonProductController AMAZON_PRODUCT_CONTROLLER = new AmazonProductController();
    private static final AmazonUserValidation AMAZON_USER_VALIDATION = new AmazonUserValidation();
    private static final AmazonProductValidation AMAZON_PRODUCT_VALIDATION = new AmazonProductValidation();

    /**
     * Provides Methods for {@link User} sign in, sign up
     */
    private static void userMenu() {
        System.out.println("1.signUp\n2.signIp\n3.exit");
        final int option = getUserChoice();

        switch (option) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                userMenu();
        }
    }

    /**
     * Gets user type for sign in
     */
    private static void signIn() {
        System.out.println("User type\n1.admin user\n2.customer(press # for back to menu)");
        final int userChoice = getUserChoice();

        if (AMAZON_USER_VALIDATION.returnToMenu(String.valueOf(userChoice))) {
            userMenu();
        }

        if (1 == userChoice) {
            adminSignIn();
        } else if (2 == userChoice) {
            userSignIn();
        } else {
            System.out.println("Enter the valid option");
            signIn();
        }
    }

    /**
     * Gets the email and password provides customer sign in
     */
    private static void userSignIn() {
        final String userEmail = getUserEmail();

        if (AMAZON_USER_CONTROLLER.signInUser(userEmail, getUserPassword())) {
            System.out.println("User sign in successful");
            userDashBoard();
        } else {
            System.out.println("Invalid details");
            userSignIn();
        }
    }

    /**
     * Gets the email and password provides admin sign in
     */
    private static void adminSignIn() {
        if (AMAZON_USER_CONTROLLER.signInAdmin(getUserEmail(), getUserPassword())) {
            System.out.println("Admin singIn successful");
            adminDashBoard();
        } else {
            System.out.println("Invalid details");
            adminSignIn();
        }
    }

    /**
     * Gets the details from user and provides signup
     */
    private static void signUp() {
        final String userEmail = getUserEmail();

        if (AMAZON_USER_CONTROLLER.isUserEmailExists(userEmail)
                || AMAZON_USER_CONTROLLER.isAdminEmailExists(userEmail)) {
            System.out.println("The email id is already exists");
            getUserEmail();
        }
        final User user = new User();

        user.setEmail(userEmail);
        user.setPassword(getUserPassword());
        user.setName(getUserName());
        user.setAddress(getUserAddress());
        user.setPhoneNo(getUserPhoneNo());

        if (adminCheck()) {

            if (adminKeyVerification()) {
                AMAZON_USER_CONTROLLER.signUpAdmin(user);
                System.out.println("Admin sign up successful");
                adminDashBoard();
            } else {
                System.out.println("Admin Verification failed");
                signUp();
            }
        } else {
            AMAZON_USER_CONTROLLER.signUpUser(user);
            userDashBoard();
        }
    }

    /**
     * Check the user for sign up as admin
     *
     * @return Boolean true if the user choose admin user for sign up
     */
    private static boolean adminCheck() {
        System.out.println("Are you an Admin User yes(y) or no");
        final String userChoice = SCANNER.nextLine();

        return ('y' == userChoice.charAt(0) || 'Y' == userChoice.charAt(0));
    }

    /**
     * Verifies the admin key while admin registration
     *
     * @return Boolean true if the user key matches the admin key
     */
    private static boolean adminKeyVerification() {
        System.out.println("Enter the admin key for verification\t(press # for back to menu)");
        final String adminKey = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.returnToMenu(adminKey)) {
            userMenu();
        }
        return AMAZON_USER_CONTROLLER.adminKeyVerification(adminKey);
    }

    /**
     * Provides dashboard between admin details and the products
     */
    private static void adminDashBoard() {
        System.out.println("1.admin details\t2.product details\t3.logout");
        final int adminOption = getUserChoice();

        switch (adminOption) {
            case 1:
                accessAdmin();
                break;
            case 2:
                accessProduct();
                break;
            case 3:
                userMenu();
                break;
            default:
                System.out.println("Enter the correct option");
                adminDashBoard();
        }
    }

    /**
     * Provides dashboard between user details and the products
     */
    private static void userDashBoard() {
        System.out.println("1.usersDetail\n2.viewProducts\n3.logout");
        final int userOption = getUserChoice();

        switch (userOption) {
            case 1:
                accessUser();
                break;
            case 2:
                getAllProducts();
                break;
            case 3:
                userMenu();
                break;
            default:
                System.out.println("Enter the correct option");
                userDashBoard();
        }
    }

    /**
     * Provides operations on product add, view ,update, delete
     */
    private static void accessProduct() {
        System.out.println(String.join(" ", "Enter the Choice\n1.view product\t2.add product",
                "\t3.update product\t4.delete product\t5.Back to dashboard"));
        final int userOption = getUserChoice();

        switch (userOption) {
            case 1:
                System.out.println(getAllProducts());
                break;
            case 2:
                addProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                adminDashBoard();
                break;
            default:
                System.out.println("Enter the correct option");
                accessProduct();
                break;
        }
        System.out.println("Do you want to continue press yes(y) else press any letter key");

        if (AMAZON_USER_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            accessProduct();
        } else {
            adminDashBoard();
        }
    }

    /**
     * Provides operation on admin view, update, delete
     */
    private static void accessAdmin() {
        final User user = getAdminDetails();

        System.out.println("Enter the option\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
        final int userOption = getUserChoice();


        switch (userOption) {
            case 1:
                System.out.println(user);
                break;
            case 2:
                updateAdminDetails(user);
                break;
            case 3:
                deleteUser(user);
                break;
            case 4:
                userDashBoard();
                break;
            default:
                System.out.println("Enter the correct option");
                accessAdmin();
        }
        System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

        if (AMAZON_USER_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            accessAdmin();
        } else {
            adminDashBoard();
        }
    }

    /**
     * Retrieves the user details, update details and delete user using email
     */
    private static void accessUser() {
        final User user = getUsersDetail();

        System.out.println("Enter the option\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
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
                userDashBoard();
                break;
            default:
                System.out.println("Enter the correct option");
                accessUser();
        }
        System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

        if (AMAZON_USER_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            accessUser();
        } else {
            userDashBoard();
        }
    }

    /**
     * Updates the product details by getting product key from the user
     */
    private static void updateProduct() {
        System.out.println(getAllProducts());
        System.out.println("Enter the product id for update product information");

        final int productId = getUserChoice();

        final Product product = AMAZON_PRODUCT_CONTROLLER.getProduct(productId);

        updateProductName(product);
        updateProductDescription(product);
        updateProductPrice(product);
        AMAZON_PRODUCT_CONTROLLER.updateProduct(productId, product);
        accessProduct();
    }

    /**
     * Updates the product name in product object
     *
     * @param product Represents {@link Product}
     */
    private static void updateProductName(final Product product) {
        System.out.println("Do you want to update product name press yes(y) else press any letter key");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product name for update");
            final String productName = SCANNER.nextLine();

            product.setName(productName);
        }
    }

    /**
     * Updates the product description using product id
     *
     * @param product Represents {@link Product}
     */
    private static void updateProductDescription(final Product product) {
        System.out.println("Do you want to update product description");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product description for update");
            final String description = SCANNER.nextLine();

            product.setDescription(description);
        }
    }

    /**
     * Updates the product price using product id
     *
     * @param product Represents {@link Product}
     */
    private static void updateProductPrice(final Product product) {
        System.out.println("Do you want to update product price");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product price for update");
            try {
                final double productPrice = SCANNER.nextDouble();
                product.setPrice(productPrice);
            } catch (final InputMismatchException exception) {
                System.out.println(exception.getMessage());
            }
            updateProductPrice(product);
        }
    }

    /**
     * Gets all the product id from the product list
     *
     * @return Collections of product id's
     */
    private static Collection<Long> getProductsId() {
        return AMAZON_PRODUCT_CONTROLLER.getProductsId();
    }

    /**
     * Deletes the product from the products list
     */
    private static void deleteProduct() {
        System.out.println(getAllProducts());
        System.out.println("Enter the product id to delete the product details");
        final int productId = getUserChoice();

        if (getProductsId().contains(productId)) {

            if (AMAZON_PRODUCT_CONTROLLER.deleteProduct(productId)) {
                System.out.println("product deleted successfully");
            } else {
                System.out.println("deleted unsuccessful");
            }
        } else {
            System.out.println("enter the correct product id");
        }
    }

    /**
     * Gets the user detail in user list by using email
     *
     * @return {@link User}
     */
    private static User getUsersDetail() {
        System.out.println("Enter the email of the user");
        final String email = SCANNER.nextLine();

        if (AMAZON_USER_CONTROLLER.getUserDetail(email) == null) {
            System.out.println("The admin is not exists");

            return getAdminDetails();
        } else {
            return AMAZON_USER_CONTROLLER.getAdminDetail(email);
        }
    }

    /**
     * Gets the admin details in user list by using email
     *
     * @return Represent {@link User}
     */
    private static User getAdminDetails() {
        System.out.println("Enter the email of the user");
        final String email = SCANNER.nextLine();

        if (AMAZON_USER_CONTROLLER.getAdminDetail(email) == null) {
            System.out.println("The admin is not exists");

            return getAdminDetails();
        } else {
            return AMAZON_USER_CONTROLLER.getAdminDetail(email);
        }
    }

    /**
     * Updates the Admin details in user list using user object
     *
     * @param user {@link User} Admin object want to update
     */
    private static void updateAdminDetails(final User user) {
        updateUserName(user);
        updateUserEmail(user);
        updateUserPassword(user);
        updateUserAddress(user);
        updateUserPhoneNo(user);
        adminDashBoard();
    }

    /**
     * Updates the user details in user list using user object
     *
     * @param user {@link User} User object want to update
     */
    private static void updateUserDetails(final User user) {
        updateUserName(user);
        updateUserEmail(user);
        updateUserPassword(user);
        updateUserAddress(user);
        updateUserPhoneNo(user);
        userDashBoard();
    }

    /**
     * Updates the username in user object
     *
     * @param user {@link User} User object wants to update
     */
    private static void updateUserName(final User user) {
        System.out.println("Do you want to update user name press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.toContinueValidation(userChoice)) {
            final String userName = getUserName();

            user.setName(userName);
        }
    }

    /**
     * Updates the user email in user object
     *
     * @param user {@link User} User object want's to update
     */
    private static void updateUserEmail(final User user) {
        System.out.println("Do you want to update user email press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String email = getUserEmail();

            user.setEmail(email);
        }
    }

    /**
     * Updates the user password in user object
     *
     * @param user {@link User} User object wants to update
     */
    private static void updateUserPassword(final User user) {
        System.out.println("Do you want to update user password press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String password = getUserPassword();

            user.setPassword(password);
        }
    }

    /**
     * Updates the user address in user object
     *
     * @param user {@link User} User object wants to update
     */
    private static void updateUserAddress(final User user) {
        System.out.println("Do you want to update user address press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String address = getUserAddress();

            user.setAddress(address);
        }
    }

    /**
     * Updates the phone no in user object
     *
     * @param user {@link User} User object wants to update
     */
    private static void updateUserPhoneNo(final User user) {
        System.out.println("Do you want to update user phoneNo press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String phoneNo = getUserPhoneNo();

            user.setPhoneNo(phoneNo);
        }
    }

    /**
     * Deletes the user object from user list
     *
     * @param user {@link User} User object to be delete
     */
    private static void deleteUser(final User user) {
        if (AMAZON_USER_CONTROLLER.deleteUser(user)) {
            System.out.println("User Account deleted");
        } else {
            System.out.println("User account not deleted");
        }
    }

    /**
     * Gets and validate email value from user
     *
     * @return Email id the user entered
     */
    private static String getUserEmail() {
        System.out.println("Enter the email id\t(press # for back to menu)");
        final String emailId = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.returnToMenu(emailId)) {
            userMenu();
        }

        if (AMAZON_USER_VALIDATION.validateEmail(emailId)) {
            return emailId;
        } else {
            System.out.println("Invalid email id enter the correct email id");

            return getUserEmail();
        }
    }

    /**
     * Gets and validate the user password from the user
     *
     * @return String password that the user entered
     */
    private static String getUserPassword() {
        System.out.println(String.join(" ", "Enter the password\t(password must contain",
                "one capital letter small letter, number and a symbol)\t(press # for back to menu)"));
        final String password = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.returnToMenu(password)) {
            userMenu();
        }

        if (AMAZON_USER_VALIDATION.validatePassword(password)) {
            return password;
        }
        System.out.println("Your password is no strong enough");

        return getUserPassword();
    }

    /**
     * Gets address from the user object
     *
     * @return String address user entered
     */
    private static String getUserAddress() {
        System.out.println(String.join("", "Enter the address with pin code\n",
                "(first enter the door number and enter the address ",
                "eg: 2/34 new bus stand, tirunelveli tamil nadu 627007)\t(press # for back to menu)"));
        final String address = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.returnToMenu(address)) {
            userMenu();
        }

        if (AMAZON_USER_VALIDATION.validateAddress(address)) {
            return address;
        } else {
            System.out.println("invalid address");

            return getUserAddress();
        }
    }

    /**
     * Gets and validate the username from the user
     *
     * @return String username the user entered
     */
    private static String getUserName() {
        System.out.println("Enter the user name\t(press # for back to menu)");
        final String userName = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.returnToMenu(userName)) {
            userMenu();
        }

        if (AMAZON_USER_VALIDATION.validateUserName(userName)) {
            return userName;
        }
        System.out.println("Invalid username number");

        return getUserName();
    }

    /**
     * Gets and validate phone number from user
     *
     * @return String Phone number the user entered
     */
    private static String getUserPhoneNo() {
        System.out.println("Enter the phone no\t(press # for logout)");
        final String phoneNo = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.returnToMenu(phoneNo)) {
            userMenu();
        }

        if (AMAZON_USER_VALIDATION.validatePhone(phoneNo)) {
            return phoneNo;
        }
        System.out.println("Invalid phone number");

        return getUserPhoneNo();
    }

    /**
     * Gets and validate the user's choice
     *
     * @return Integer value that the user entered
     */
    private static int getUserChoice() {
        System.out.println("Enter the choice");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.validateAsNumber(userChoice)) {
            return Integer.parseInt(userChoice);
        }
        System.out.println("Enter the number input");

        return getUserChoice();
    }

    /**
     * Gets and validate price that is entered by the user
     *
     * @return double price value entered by user
     */
    private static double getProductPrice() {
        System.out.println("Enter the Product price\t(press # for back to product menu)");

        try {
            final double productPrice = SCANNER.nextDouble();

            if (AMAZON_USER_VALIDATION.returnToMenu(String.valueOf(productPrice))) {
                accessProduct();
            }

            return productPrice;
        } catch (final InputMismatchException Exception) {
            System.out.println("Enter the value is number");
        }

        return getProductPrice();
    }

    /**
     * validate the category of the product object
     */
    private static Product.ProductCategories getCategory() {

        try {
            final Product.ProductCategories category = Product.ProductCategories.valueOf(getProductCategory());

            switch (category) {
                case MOBILE_PHONES:
                case FOOTWEAR:
                case ELECTRONICS:
                case CLOTHING:
                case KITCHEN_APPLIANCES:
                case SPORTS:
                case BOOKS:
                case TOYS:
                    return category;
                default:
                    System.out.println("Enter from above category");
                    getCategory();
                    break;
            }
        } catch (final IllegalArgumentException exception) {
            System.out.println("Invalid input");
        }
        return getCategory();
    }

    /**
     * Gets the product category from the user
     *
     * @return String category type
     */
    private static String getProductCategory() {
        System.out.println(String.join("", "Enter the product category in words\n1.mobile_phones\n",
                "2.footwear\n3.electronics\n4.clothing\n5.kitchen_appliances\n6.sports\n6.books\n8.toys\t",
                "(press # to return to product menu)"));
        final String productChoice = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.returnToMenu(productChoice)) {
            accessProduct();
        }

        if (AMAZON_PRODUCT_VALIDATION.validateCategory(productChoice)) {
            return productChoice.toUpperCase();
        } else {
            System.out.println("Enter a valid category in words");
            return getProductCategory();
        }
    }

    /**
     * Create a product object and add the product object to the product list
     */
    private static void addProduct() {
        final Product product = new Product();

        product.setCategory(getCategory());
        product.setName(getProductName());
        product.setDescription(getProductDescription());
        product.setPrice(getProductPrice());

        if (AMAZON_PRODUCT_CONTROLLER.addProduct(product)) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("product adding is unsuccessful");
        }
        System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");
        final String userChoice = SCANNER.next().trim();

        SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            getCategory();
        } else {
            adminDashBoard();
        }
    }

    /**
     * Represent the collection view of the products
     *
     * @return Products object in product list
     */
    private static Collection<Product> getAllProducts() {
        return AMAZON_PRODUCT_CONTROLLER.getAllProducts();
    }

    /**
     * Gets the product name from user
     *
     * @return Product name
     */
    private static String getProductName() {
        System.out.println("Enter the product name\t(press # for back to menu)");
        final String productName = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.returnToMenu(productName)) {
            accessProduct();
        }
        return productName;
    }

    /**
     * Get the product description from the user
     *
     * @return Product description
     */
    private static String getProductDescription() {
        System.out.println("Enter the product description\t(press # for back to menu)");
        final String description = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.returnToMenu(description)) {
            accessProduct();
        }
        return description;
    }

    public static void main(final String[] args) {
        System.out.println("Welcome to amazon");
        userMenu();
        System.out.println("Do you want to continue yes(y) else press n to exit");
        final String exitChoice = SCANNER.nextLine().trim().toLowerCase();

        if (AMAZON_USER_VALIDATION.toContinueValidation(exitChoice)) {
            userMenu();
        } else {
            SCANNER.close();
        }
    }
}