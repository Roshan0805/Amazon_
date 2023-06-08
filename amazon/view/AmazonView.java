package com.amazon.view;

import com.amazon.controller.AmazonController;
import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.view.validation.AmazonProductValidation;
import com.amazon.view.validation.AmazonUserValidation;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Provides methods for sign in, sign up, user's update, product update
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AmazonController AMAZON_CONTROLLER = new AmazonController();
    private static final AmazonUserValidation AMAZON_USER_VALIDATION = new AmazonUserValidation();
    private static final AmazonProductValidation AMAZON_PRODUCT_VALIDATION = new AmazonProductValidation();

    /**
     * Provides option for sign in, sign up
     */
    private static void userMenu() {
        System.out.println("1.signUp\n2.signUp\n3.exit");
        final int option = getUserChoiceInInt();

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
        System.out.println("User type\n1.admin user\n2.customer(press # to return menu)");
        final int userChoice = getUserChoiceInInt();

        AMAZON_USER_VALIDATION.returnToMenu(String.valueOf(userChoice));

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
     * Gets user type for signup
     */
    private static void signUp() {
        System.out.println("User type\n1.admin user\n2.customer");
        final int userChoice = getUserChoiceInInt();

        AMAZON_USER_VALIDATION.returnToMenu(String.valueOf(userChoice));

        if (1 == userChoice) {
            adminSignUp();
        } else if (2 == userChoice) {
            userSignUp();
        } else {
            System.out.println("Enter the valid option");
            signUp();
        }
    }

    /**
     * Gets the email and password provides user sign in
     */
    private static void userSignIn() {
        final String userEmail = getUserEmail();

        if (AMAZON_CONTROLLER.userSignIn(userEmail, getUserPassword())) {
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

        if (AMAZON_CONTROLLER.adminSignIn(getUserEmail(), getUserPassword())) {
            System.out.println("Admin singIn successful");
            adminDashBoard();
        } else {
            System.out.println("Invalid details");
            adminSignIn();
        }
    }

    /**
     * Gets the user details provides user signup
     */
    private static void userSignUp() {
        final String userEmail = getUserEmail();

        if (AMAZON_CONTROLLER.isUserEmailAlreadyExists(userEmail)) {
            System.out.println("The email id is already exists");
            getUserEmail();
        }
        final User user = new User();

        user.setEmail(userEmail);
        user.setPassword(getUserPassword());
        user.setName(getUserName());
        user.setAddress(getUserAddress());
        user.setPhoneNo(getUserPhoneNo());
        AMAZON_CONTROLLER.userSignUp(user);
        userDashBoard();
    }

    /**
     * Gets the details provides admin signup
     */
    private static void adminSignUp() {
        final String userEmail = getUserEmail();

        if (AMAZON_CONTROLLER.isUserEmailAlreadyExists(userEmail)) {
            System.out.println("The email id is already exists");
            getUserEmail();
        }
        final User user = new User();

        user.setEmail(userEmail);
        user.setPassword(getUserPassword());
        user.setName(getUserName());
        user.setAddress(getUserAddress());
        user.setPhoneNo(getUserPhoneNo());

        if (adminKeyVerification()) {
            AMAZON_CONTROLLER.adminSignUp(user);
            System.out.println("Admin sign up successful");
            adminDashBoard();
        } else {
            System.out.println("Admin Verification failed");
            adminKeyVerification();
        }
    }

    /**
     * Verifies the admin key while admin registration
     *
     * @return boolean true if the user key matches the admin key
     */
    private static boolean adminKeyVerification() {
        System.out.println("Enter the admin key for verification\t(press # for back to menu)");
        final String adminKey = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.returnToMenu(adminKey)) {
            userMenu();
        }

        return AMAZON_CONTROLLER.adminKeyVerification(adminKey);
    }

    /**
     * Provides dashboard between admin details and the products
     */
    private static void adminDashBoard() {
        System.out.println("1.admin details\t2.product details\t3.logout");
        final int adminOption = getUserChoiceInInt();

        switch (adminOption) {
            case 1:
                adminAccess();
                break;
            case 2:
                productAccess();
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
        final int userOption = getUserChoiceInInt();

        switch (userOption) {
            case 1:
                userAccess();
                break;
            case 2:
                getProducts();
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
    private static void productAccess() {
        System.out.println(String.join(" ", "Enter the Choice\n1.view product\t2.add product" +
                "\t3.update product\t4.delete product\t5.Back to user dashboard"));
        final int userOption = getUserChoiceInInt();

        switch (userOption) {
            case 1:
                System.out.println(getProducts());
                productAccess();
                break;
            case 2:
                validateCategory();
                productAccess();
                break;
            case 3:
                updateProduct();
                productAccess();
                break;
            case 4:
                deleteProduct();
                productAccess();
                break;
            case 5:
                adminDashBoard();
                break;
            default:
                System.out.println("Enter the correct option");
        }
    }

    /**
     * Provides operation on admin view, update, delete
     */
    private static void adminAccess() {
        System.out.println("Enter the email id of the user");
        final User user = getAdminDetails(SCANNER.nextLine());

        System.out.println("Enter the option\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
        final int userOption = getUserChoiceInInt();


        switch (userOption) {
            case 1:
                System.out.println(user);
                System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

                if (AMAZON_USER_VALIDATION.exitChoiceValidation(SCANNER.nextLine())) {
                    adminAccess();
                }
                adminDashBoard();
                break;
            case 2:
                updateAdminDetails(user);
                System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

                if (AMAZON_USER_VALIDATION.exitChoiceValidation(SCANNER.nextLine())) {
                    adminAccess();
                }
                adminDashBoard();
                break;
            case 3:
                deleteUser(user);
                System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

                if (AMAZON_USER_VALIDATION.exitChoiceValidation(SCANNER.nextLine())) {
                    adminAccess();
                }
                adminDashBoard();
                break;
            case 4:
                userDashBoard();
                break;
            default:
                System.out.println("Enter the correct option");
                adminAccess();
        }
    }

    /**
     * Retrieves the user details,update details and delete user using email
     *
     * @return User object
     */
    private static void userAccess() {
        System.out.println("Enter the option\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
        final int userOption = getUserChoiceInInt();

        System.out.println("Enter the email id of the user");
        final User user = getUsersDetail(SCANNER.nextLine());

        switch (userOption) {
            case 1:
                System.out.println(user);
                System.out.println(String.join("", "Do you want to continue on user menu press yes(y)",
                        " or return back to main menu press no(n)"));

                if (AMAZON_USER_VALIDATION.exitChoiceValidation(SCANNER.nextLine())) {
                    userAccess();
                }
                userDashBoard();
                break;
            case 2:
                updateUserDetails(user);
                System.out.println(String.join("", "Do you want to continue on user menu press yes(y)",
                        " or return back to main menu press no(n)"));

                if (AMAZON_USER_VALIDATION.exitChoiceValidation(SCANNER.nextLine())) {
                    userAccess();
                }
                userDashBoard();
                break;
            case 3:
                deleteUser(user);
                System.out.println(String.join("", "Do you want to continue on user menu press yes(y)",
                        " or return back to main menu press no(n)"));

                if (AMAZON_USER_VALIDATION.exitChoiceValidation(SCANNER.nextLine())) {
                    userAccess();
                }
                userDashBoard();
                break;
            case 4:
                userDashBoard();
                break;
            default:
                System.out.println("Enter the correct option");
                adminAccess();
        }
    }

    /**
     * Updates the product details
     */
    private static void updateProduct() {
        System.out.println(getProducts());
        System.out.println("Enter the product id for update product information");
        final int productId = getIdOfProduct();

        updateProductName(productId);
        updateProductDescription(productId);
        updateProductPrice(productId);
        productAccess();
    }

    /**
     * Updates the product name using product id
     *
     * @param productId ProductId of the product wants to update product details
     */
    private static void updateProductName(final int productId) {
        System.out.println("Do you want to update product name");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product name for update");
            final String productName = SCANNER.nextLine();

            updateCheck(AMAZON_CONTROLLER.updateProductName(productId, productName));
        }
    }

    /**
     * Updates the product description using product id
     *
     * @param productId ProductId of the product wants to update product details
     */
    private static void updateProductDescription(final int productId) {
        System.out.println("Do you want to update product description");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product description for update");
            final String productPrice = SCANNER.nextLine();

            updateCheck(AMAZON_CONTROLLER.updateProductDescription(productId, productPrice));
        }
    }

    /**
     * Updates the product price using product id
     *
     * @param productId ProductId of the product wants to update product details
     */
    private static void updateProductPrice(final int productId) {
        System.out.println("Do you want to update product price");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            System.out.println("Enter the product price for update");
            final String productPrice = SCANNER.nextLine();

            if (AMAZON_PRODUCT_VALIDATION.validateAsNumber(productPrice)) {
                AMAZON_CONTROLLER.updateProductPrice(productId, Double.parseDouble(productPrice));
            }
        }
    }

    /**
     * Gets all the product id from the product list
     *
     * @return Set of product id's
     */
    private static Set<Integer> getProductsId() {
        return AMAZON_CONTROLLER.getProductsId();
    }

    /**
     * Deletes the product from the products list
     */
    private static void deleteProduct() {
        System.out.println(getProducts());
        System.out.println("Enter the product id to delete the product details");
        final int productId = getUserChoiceInInt();

        if (getProductsId().contains(productId)) {

            if (AMAZON_CONTROLLER.deleteProduct(productId)) {
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
     * @param email The email of the user
     * @return {@link User}
     */
    private static User getUsersDetail(final String email) {
        if (AMAZON_CONTROLLER.isUserEmailAlreadyExists(email)) {

            return AMAZON_CONTROLLER.getUserDetails(email);
        } else {
            System.out.println("The email id is not exists");

            return getUsersDetail(getUserEmail());
        }
    }

    /**
     * Gets the admin details in user list by using email
     *
     * @param email The email of the user
     * @return User object
     */
    private static User getAdminDetails(final String email) {
        if (AMAZON_CONTROLLER.isAdminEmailAlreadyExists(email)) {

            return AMAZON_CONTROLLER.getAdminDetails(email);
        } else {
            System.out.println("The email id is not exists");

            return getUsersDetail(getUserEmail());
        }
    }

    /**
     * Updates the Admin details in user list using user object
     *
     * @param user Admin object want to update
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
     * @param user User object want to update
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
     * @param user User object wants to update
     */
    private static void updateUserName(final User user) {
        System.out.println("Do you want to update user name press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.exitChoiceValidation(userChoice)) {
            final String userName = getUserName();

            updateCheck(AMAZON_CONTROLLER.updateUserName(user, userName));
        }
    }

    /**
     * Updates the user email in user object
     *
     * @param user User object want's to update
     */
    private static void updateUserEmail(final User user) {
        System.out.println("Do you want to update user email press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String userName = getUserEmail();

            updateCheck(AMAZON_CONTROLLER.updateUserEmail(user, userName));
        }
    }

    /**
     * Updates the user password in user object
     *
     * @param user User object wants to update
     */
    private static void updateUserPassword(final User user) {
        System.out.println("Do you want to update user password press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String userName = getUserPassword();

            updateCheck(AMAZON_CONTROLLER.updateUserPassword(user, userName));
        }
    }

    /**
     * Updates the user address in user object
     *
     * @param user User object wants to update
     */
    private static void updateUserAddress(final User user) {
        System.out.println("Do you want to update user address press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String userName = getUserAddress();

            updateCheck(AMAZON_CONTROLLER.updateUserName(user, userName));
        }
    }

    /**
     * Updates the phone no in user object
     *
     * @param user User object wants to update
     */
    private static void updateUserPhoneNo(final User user) {
        System.out.println("Do you want to update user phoneNo press yes(y) else press any other letters");
        final String userChoice = SCANNER.nextLine().trim();

        if ('y' == userChoice.charAt(0)) {
            final String userName = getUserPhoneNo();

            updateCheck(AMAZON_CONTROLLER.updateUserName(user, userName));
        }
    }

    /**
     * Deletes the user object from user list
     *
     * @param user User object to be delete
     */
    private static void deleteUser(final User user) {
        if (AMAZON_CONTROLLER.deleteUser(user)) {
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
     * @return String username that the user entered
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
     * @return String Phone number that the user entered
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
     * Return's the user's list
     *
     * @return The collection of user from user list
     */
    private static Collection<User> getUsersList() {
        return AMAZON_CONTROLLER.getUsersList();
    }

    /**
     * Gets and validate the user's choice
     *
     * @return Integer value that the user entered
     */
    private static int getUserChoiceInInt() {
        System.out.println("Enter the choice");
        final String userChoice = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.validateAsNumber(userChoice)) {
            return Integer.parseInt(userChoice);
        }
        System.out.println("Enter the number input");

        return getUserChoiceInInt();
    }

    /**
     * Gets and validate price that is entered by the user
     *
     * @return double price value entered by user
     */
    private static double getUserPrice() {
        System.out.println("Enter the Product price\t(press # for back to product menu)");
        final String productPrice = SCANNER.nextLine();

        if (AMAZON_USER_VALIDATION.returnToMenu(productPrice)) {
            productAccess();
        }

        if (AMAZON_PRODUCT_VALIDATION.validateAsNumber(productPrice)) {
            return Double.parseDouble(productPrice);
        }
        System.out.println("Enter the product price in numbers");

        return getUserPrice();
    }

    /**
     * Gets product id from the user
     *
     * @return product id entered by the user
     */
    private static int getIdOfProduct() {
        System.out.println("Enter the number of product");
        final String numberOfProducts = SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.validateAsNumber(numberOfProducts)) {
            return Integer.parseInt(numberOfProducts);
        }

        return getIdOfProduct();
    }

    /**
     * validate the category of the product object
     */
    private static void validateCategory() {

        try {
            final Product.ProductCategories categories = Product.ProductCategories.valueOf(getProductCategory());

            switch (categories) {
                case ELECTRONICS:
                    addProduct("electronics");
                    break;
                case CLOTHING:
                    addProduct("clothing");
                    break;
                case KITCHEN_APPLIANCES:
                    addProduct("kitchen_appliances");
                    break;
                case SPORTS:
                    addProduct("sports");
                    break;
                case BOOKS:
                    addProduct("books");
                    break;
                case TOYS:
                    addProduct("toys");
                    break;
                default:
                    System.out.println("Enter from above category");
                    validateCategory();
                    break;
            }
        } catch (final InputMismatchException exception) {
            System.out.println("Invalid input");
        }
        System.out.println("Do you want to continue");

        if (AMAZON_USER_VALIDATION.returnToMenu(SCANNER.nextLine())) {
            validateCategory();
        }

    }

    /**
     * Gets the product category from the user
     *
     * @return String category type
     */
    private static String getProductCategory() {
        System.out.println(String.join(" ", "Enter the product category\n1.electronics\n2.clothing\n",
                "3.kitchen_appliances\n4.sports\n5.books\n6.toys"));
        final int productChoice = getUserChoiceInInt();

        switch (productChoice) {
            case 1:
                return "ELECTRONICS";
            case 2:
                return "CLOTHING";
            case 3:
                return "KITCHEN_APPLIANCES";
            case 4:
                return "SPORTS";
            case 5:
                return "BOOKS";
            case 6:
                return "TYPES";
            default:
                System.out.println("Enter the category from above");
                break;
        }
        return getProductCategory();
    }

    /**
     * Create a product object and add the product object to the product list
     *
     * @param category
     */
    private static void addProduct(final String category) {
        final Product product = new Product();

        product.setCategory(category);
        product.setName(getProductName());
        product.setDescription(getProductDescription());
        product.setPrice(getProductPrice());

        if (AMAZON_CONTROLLER.addProduct(product)) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("product adding is unsuccessful");
        }
        System.out.println("Do you want to add more products yes(y) otherwise press n for back to menu");
        final String userChoice = SCANNER.next().trim();

        SCANNER.nextLine();

        if (AMAZON_PRODUCT_VALIDATION.updateChoiceValidation(userChoice)) {
            validateCategory();
        } else {
            userDashBoard();
        }
    }

    /**
     * Represent the collection view of the products
     *
     * @return Products object in product list
     */
    private static Collection<Product> getProducts() {
        return AMAZON_CONTROLLER.viewProduct();
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
            productAccess();
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
            productAccess();
        }
        return description;
    }

    /**
     * Gets the product price from the user
     *
     * @return Product price
     */
    private static double getProductPrice() {
        return getUserPrice();
    }

    /**
     * conveys the update is successful or not
     *
     * @param value boolean true or false
     */
    private static void updateCheck(final boolean value) {
        if (value) {
            System.out.println("Updated successfully");
        } else {
            System.out.println("Updated unsuccessful");
        }
    }

    public static void main(final String[] args) {
        System.out.println("Welcome to amazon");
        userMenu();
        System.out.println("Do you want to continue yes(y) else press n to exit");
        final String exitChoice = SCANNER.nextLine().trim().toLowerCase();

        if (AMAZON_USER_VALIDATION.exitChoiceValidation(exitChoice)) {
            userMenu();
        } else {
            SCANNER.close();
        }
    }
}