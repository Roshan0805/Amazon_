package com.amazon.view;

import com.amazon.controller.UserController;
import com.amazon.model.User;
import com.amazon.view.validation.UserValidator;

import java.util.Collection;

/**
 * <p>
 * Represents the view of {@link User}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserView extends View {

    private static UserView USER_VIEW = null;
    private final UserValidator userValidator;
    private final AuthenticationView authenticationView;
    private final UserController userController;
    private final ProductView productView ;

    private UserView() {
        userValidator = UserValidator.getInstance();
        authenticationView = AuthenticationView.getInstance();
        userController = UserController.getInstance();
        productView = ProductView.getInstance();
    }

    /**
     * <p>
     * Represents the {@link UserView} class object can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link UserView}
     */
    public static UserView getInstance() {
        return USER_VIEW == null ? USER_VIEW = new UserView() : USER_VIEW;
    }

    /**
     * <p>
     * Provide user option for user to get, update and delete details
     * </p>
     *
     * @param userId Represents id of {@link User}
     */
    public void accessUser(final Long userId) {
        System.out.println("Choose from the options\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
        final UserChoice userOption = obtainUserChoice();

        switch (userOption) {
            case ONE:
                System.out.println(get(userId));
                break;
            case TWO:
                update(userId);
                break;
            case THREE:
                delete(userId);
                authenticationView.displayMenu();
                break;
            case FOUR:
                obtainUserOptions();
                break;
            default:
                System.out.println("Enter the correct option");
                accessUser(userId);
        }
        System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

        if (userValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            accessUser(userId);
        } else {
            obtainUserOptions();
        }
    }

    /**
     * <p>
     * Gets the user details in user list by using user id
     * </p>
     *
     * @return Represents the id of {@link User}
     */
    public User get(final Long userId) {

        final User user = userController.getDetail(userId);

        if (user == null) {
            System.out.println("The user is not exists");

            return null;
        } else {
            return user;
        }
    }

    /**
     * <p>
     * Updates the Admin details in user list using user object
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void update(final Long userId) {
        final User user = get(userId);

        if (user == null) {
            System.out.println("User not found");
            obtainUserOptions();
        }
        updateName(user);
        updateEmail(user);
        updatePassword(user);
        updateAddress(user);
        updatePhoneNo(user);
        userController.update(user, userId);
        obtainUserOptions();

    }

    /**
     * <p>
     * Updates the username in user object
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void updateName(final User user) {
        System.out.println("Do you want to update user name press yes(y) else press no(n)");

        if (userValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            user.setName(obtainName());
        }
    }

    /**
     * <p>
     * Updates the user email in user object
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void updateEmail(final User user) {
        System.out.println("Do you want to update user email press yes(y) else press no(n)");

        if (userValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            user.setEmail(obtainEmail());
        }
    }

    /**
     * <p>
     * Updates the user password in user object
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void updatePassword(final User user) {
        System.out.println("Do you want to update user password press yes(y) else press no(n)");

        if (userValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            user.setPassword(obtainPassword());
        }
    }

    /**
     * <p>
     * Updates the user address in user object
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void updateAddress(final User user) {
        System.out.println("Do you want to update user address press yes(y) else press no(n)");

        if (userValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            user.setAddress(obtainAddress());
        }
    }

    /**
     * <p>
     * Updates the phone no in user object
     * </p>
     *
     * @param user Represents {@link User}
     */
    private void updatePhoneNo(final User user) {
        System.out.println("Do you want to update user phone number press yes(y) else press no(n)");

        if (userValidator.toContinueValidation(SCANNER.nextLine().trim())) {
            user.setPhoneNumber(obtainPhoneNumber());
        }
    }

    /**
     * <p>
     * Deletes the user object from user list
     * </p>
     *
     * @param user_id Represents id of {@link User}
     */
    private void delete(final Long user_id) {
        if (userController.delete(user_id)) {
            System.out.println("User Account deleted");
        } else {
            System.out.println("User account not deleted");
        }
    }

    /**
     * <p>
     * Gets and validates email from user
     * </p>
     *
     * @return Represents email the user entered
     */
    public String obtainEmail() {
        try {
            System.out.println("Enter the email id\t(press # for logout to menu)");
            final String email = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(email)) {
                authenticationView.displayMenu();
            }

            if (userValidator.validateEmail(email)) {
                return email;
            } else {
                System.out.println("Invalid email id enter the correct email id");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return obtainEmail();
    }

    /**
     * <p>
     * Gets and validates the id from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public Long obtainId() {
        try {
            System.out.println("Enter the user id\t(press # for logout to menu)");
            final String userChoice = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(userChoice)) {
                authenticationView.displayMenu();
            }
            final Long id = Long.parseLong(userChoice);

            if (userValidator.validateId(String.valueOf(id))) {
                return id;
            } else {
                System.out.println("Invalid id enter the correct id");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return obtainId();
    }

    /**
     * <p>
     * Gets and validates user password from the user
     * </p>
     *
     * @return Represents password that the user entered
     */
    public String obtainPassword() {
        try {
            System.out.println(String.join(" ", "Enter the password\t(password must contain",
                    "one capital letter small letter, number and a symbol)\t(press # for logout to menu)"));
            final String password = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(password)) {
                authenticationView.displayMenu();
            }

            if (userValidator.validatePassword(password)) {
                return password;
            }
            System.out.println("Your password is no strong enough");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return obtainPassword();
    }

    /**
     * <p>
     * Gets the address from the user
     * </p>
     *
     * @return Represents address user entered
     */
    public String obtainAddress() {
        try {
            System.out.println(("Enter the address with door no\t (press # for logout to menu)"));
            final String address = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(address)) {
                authenticationView.displayMenu();
            }

            if (userValidator.validateAddress(address)) {
                return address;
            } else {
                System.out.println("Invalid address");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return obtainAddress();
    }

    /**
     * <p>
     * Gets and validates the username from the user
     * </p>
     *
     * @return Represents username the user entered
     */
    public String obtainName() {
        try {
            System.out.println("Enter the name\t(press # for logout to menu)");
            final String userName = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(userName)) {
                authenticationView.displayMenu();
            }

            if (userValidator.validateUserName(userName)) {
                return userName;
            }
            System.out.println("Invalid username");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return obtainName();
    }

    /**
     * <p>
     * Gets and validates the phone number from user
     * </p>
     *
     * @return Represents phone number the user entered
     */
    public String obtainPhoneNumber() {
        try {
            System.out.println("Enter the phone no\t(press # for logout)");
            final String phoneNo = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(phoneNo)) {
                authenticationView.displayMenu();
            }

            if (userValidator.validatePhone(phoneNo)) {
                return phoneNo;
            }
            System.out.println("Invalid phone number");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return obtainPhoneNumber();
    }

    /**
     * <p>
     * obtain user's choice
     * </p>
     *
     * @return Represents value that the user entered
     */
    public UserChoice obtainUserChoice() {
        System.out.println("Enter the choice\t(press # for return to menu)");

        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (userValidator.isReturnToMenu(userChoice)) {
                obtainUserOptions();
            }

            return UserChoice.getValue(Integer.parseInt(userChoice));
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input enter the number input");
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("Enter valid input");
        }

        return obtainUserChoice();
    }

    /**
     * <p>
     * Provides option for access user details and the products details and logout
     * </p>
     */
    public void obtainUserOptions() {
        System.out.println(String.join("", "Choose from the options\n1.admin details\n",
                "2.sell product\n3.view products\n4.cart details\n5.order details\n6.get all user\n7.logout"));
        final UserChoice userOption = obtainUserChoice();

        if (userOption.equals(UserChoice.SEVEN)) {
            authenticationView.displayMenu();
        }
        final Long userId = USER_VIEW.obtainId();

        switch (userOption) {
            case ONE:
                accessUser(userId);
                break;
            case TWO:
                productView.accessProduct(userId);
                break;
            case THREE:
                productView.viewProduct(userId);
                break;
            case FOUR:
                productView.accessCart(userId);
                break;
            case FIVE:
                productView.accessOrder(userId);
                break;
            case SIX:
                getAllUsers();
                break;
            default:
                System.out.println("Enter the correct option");
                obtainUserOptions();
        }
    }

    /**
     * <p>
     * Represents all the {@link User} details in the usersList
     * </p>
     */
    public void getAllUsers() {
        final Collection<User> users = userController.getAllUser();

        if (users.isEmpty()) {
            System.out.println("The user list is empty");
        } else {
            System.out.println(users);
        }
        obtainUserOptions();
    }
}