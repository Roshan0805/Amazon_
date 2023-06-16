package com.amazon.view;

import com.amazon.controller.UserController;
import com.amazon.model.User;
import com.amazon.view.validation.UserValidation;

import java.util.Collection;
import java.util.Scanner;

/**
 * <p>
 * Represents the {@link User} view, update and delete
 * </p>
 *
 * @version 1.0
 * @auther Roshan
 */
public class UserView {

    private static final UserView USER_VIEW = new UserView();
    private static final LoginView LOGIN_VIEW = LoginView.getInstance();
    private static final UserValidation USER_VALIDATION = UserValidation.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserController USER_CONTROLLER = UserController.getInstance();

    private UserView() {
    }

    public static UserView getInstance() {
        return USER_VIEW;
    }

    /**
     * <p>
     * Retrieves the admin details using email ,update the admin details and delete admin details
     * </p>
     *
     * @param user Represents {@link User}
     */
    public void accessUser(final User user) {
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
                checkAdmin(user);
                break;
            default:
                System.out.println("Enter the correct option");
                accessUser(user);
        }
        System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

        if (USER_VALIDATION.toContinueValidation(SCANNER.nextLine())) {
            accessUser(user);
        } else {
            checkAdmin(user);
        }
    }

    /**
     * <p>
     * Gets the user details in user list by using user id
     * </p>
     *
     * @return Represents {@link User}
     */
    public User getsUserDetails() {
        final Long userId = getUserId();

        final User user = USER_CONTROLLER.getDetail(userId);

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
        checkAdmin(user);
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

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
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

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
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

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
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

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
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

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine())) {
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
        if (USER_CONTROLLER.delete(user)) {
            System.out.println("User Account deleted");
        } else {
            System.out.println("User account not deleted");
        }
    }

    /**
     * <p>
     * Gets the admin key while admin registration
     * </p>
     *
     * @return Represents admin key entered by the user
     */
    public String getAdminKey() {
        try {
            System.out.println("Enter the admin key for verification\t(press # for back to menu)");
            final String adminKey = SCANNER.nextLine();

            if (USER_VALIDATION.isReturnToMenu(adminKey)) {
                LOGIN_VIEW.displayMenu();
            }

            return adminKey;
        } catch (final StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getAdminKey();
    }

    /**
     * <p>
     * Gets and validate email value from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public String getUserEmail() {
        try {
            System.out.println("Enter the email id\t(press # for logout to menu)");
            final String email = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(email)) {
                LOGIN_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateEmail(email)) {
                return email;
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
     * Gets and validate id value from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public Long getUserId() {
        try {
            System.out.println("Enter the id\t(press # for logout to menu)");
            final Long id = Long.parseLong(SCANNER.nextLine().trim());

            if (USER_VALIDATION.isReturnToMenu(String.valueOf(id))) {
                LOGIN_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateId(String.valueOf(id))) {
                return id;
            } else {
                System.out.println("Invalid id enter the correct id");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserId();
    }

    /**
     * <p>
     * Gets and validate the user password from the user
     * </p>
     *
     * @return Represents password that the user entered
     */
    public String getUserPassword() {
        try {
            System.out.println(String.join(" ", "Enter the password\t(password must contain",
                    "one capital letter small letter, number and a symbol)\t(press # for logout to menu)"));
            final String password = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(password)) {
                LOGIN_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validatePassword(password)) {
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
    public String getUserAddress() {
        try {
            System.out.println(("Enter the address with door no\t (press # for logout to menu)"));
            final String address = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(address)) {
                LOGIN_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateAddress(address)) {
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
    public String getUserName() {
        try {
            System.out.println("Enter the user name\t(press # for logout to menu)");
            final String userName = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(userName)) {
                LOGIN_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateUserName(userName)) {
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
    public String getUserPhoneNumber() {
        try {
            System.out.println("Enter the phone no\t(press # for logout)");
            final String phoneNo = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(phoneNo)) {
                LOGIN_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validatePhone(phoneNo)) {
                return phoneNo;
            }
            System.out.println("Invalid phone number");
        } catch (final IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getUserPhoneNumber();
    }

    private void checkAdmin(final User user) {
        if (user.getIsAdmin()) {
            final AdminView adminView = AdminView.getInstance();
            adminView.getAdminOptions();
        } else {
            final CustomerView customerView = CustomerView.getInstance();
            customerView.getUserOptions();
        }
    }

    /**
     * <p>
     * Gets and validate the user's choice
     * </p>
     *
     * @return Represents value that the user entered
     */
    public int getUserChoice() {
        System.out.println("Enter the choice");
        final String userChoice = SCANNER.nextLine().trim();

        try {
            return Integer.parseInt(userChoice);
        } catch (final NumberFormatException exception) {
            System.out.println("Invalid input enter the number input");
        }
        return getUserChoice();
    }
}