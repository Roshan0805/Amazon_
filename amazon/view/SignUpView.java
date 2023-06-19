package com.amazon.view;

import com.amazon.controller.SignUpController;
import com.amazon.model.User;

import java.util.Scanner;

/**
 * <p>
 * Represents the {@link User} sign up
 * </p>
 *
 * @version 1.0
 * @auther Roshan
 */
public class SignUpView {

    private static final SignUpView SIGN_UP_VIEW = new SignUpView();
    private static final LoginView LOGIN_VIEW = LoginView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final AdminView ADMIN_VIEW = AdminView.getInstance();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CustomerView CUSTOMER_VIEW = CustomerView.getInstance();
    private static final SignUpController SIGN_UP_CONTROLLER = SignUpController.getInstance();

    private SignUpView() {
    }

    /**
     * <p>
     *     Represents the object for {@link SignUpView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link SignUpView}
     */
    public static SignUpView getInstance() {
        return SIGN_UP_VIEW;
    }

    /**
     * <p>
     * Gets the user details and provides signup for the user
     * </p>
     */
    public void signUp() {
        try {
            final String userEmail = USER_VIEW.getUserEmail();

            if (SIGN_UP_CONTROLLER.isUserEmailExists(userEmail)) {
                System.out.println("The email is already exists");
                USER_VIEW.getUserEmail();
            }
            final User user = new User();

            user.setEmail(userEmail);
            user.setPassword(USER_VIEW.getUserPassword());
            user.setName(USER_VIEW.getUserName());
            user.setAddress(USER_VIEW.getUserAddress());
            user.setPhoneNumber(USER_VIEW.getUserPhoneNumber());
            System.out.println("Are you an admin user press yes(y) or no(n)");
            final String userChoice = SCANNER.nextLine().trim();

            if ("y".equalsIgnoreCase(userChoice)) {
                verifyAdmin(user);
            }
            if (SIGN_UP_CONTROLLER.signUp(user)) {
                System.out.println("Sing up successful");
                CUSTOMER_VIEW.getUserOptions();
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
        if (SIGN_UP_CONTROLLER.signUp(user, USER_VIEW.getAdminKey())) {
            System.out.println("Admin sign up successful");
            ADMIN_VIEW.getAdminOptions();
        } else {
            System.out.println("Do you want to enter admin key again press yes(y) else press no(n)");

            if ('y' == SCANNER.nextLine().charAt(0)) {
                verifyAdmin(user);
            } else {
                System.out.println("Verification failed");
                LOGIN_VIEW.displayMenu();
            }
        }
    }

}
