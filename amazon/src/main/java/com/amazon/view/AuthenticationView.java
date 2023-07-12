package com.amazon.view;

import com.amazon.controller.AuthenticationController;
import com.amazon.model.User;

/**
 * <p>
 * Represents the authentication view of {@link User}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AuthenticationView extends ScannerInstance {

    private static AuthenticationView AUTHENTICATION_VIEW = null;
    private final AuthenticationController AUTHENTICATION_CONTROLLER = AuthenticationController.getInstance();
    private final UserView USER_VIEW = UserView.getInstance();
    protected static Long userId = 1L;

    private AuthenticationView() {
    }

    public static AuthenticationView getInstance() {
        return AUTHENTICATION_VIEW == null ? AUTHENTICATION_VIEW = new AuthenticationView() : AUTHENTICATION_VIEW;
    }

    /**
     * <p>
     * Provides Methods for {@link User} sign in, sign up and exit
     * </p>
     */
    public void displayMenu() {
        System.out.println("1.signUp\n2.signIn\n3.exit");
        final int option = USER_VIEW.getUserChoice();

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
     * <P> Gets user type for sign in </P>
     */
    public void signIn() {
        if (AUTHENTICATION_CONTROLLER.signIn(USER_VIEW.getEmail(), USER_VIEW.getPassword())) {
            System.out.println("Sign in successful");
            USER_VIEW.getUserOptions();
        } else {
            System.out.println("Sign in unsuccessful");
            signIn();
        }
    }

    /**
     * <p>
     * Gets the user details and provides signup for the user
     * </p>
     */
    public void signUp() {
        try {
            final String userEmail = USER_VIEW.getEmail();
            final String phoneNumber = USER_VIEW.getPhoneNumber();

            if (AUTHENTICATION_CONTROLLER.isUserEmailExists(userEmail) || AUTHENTICATION_CONTROLLER.isNumberExists(phoneNumber)) {
                System.out.println("The email or phone number is already exists");
                signUp();
            }
            final User user = new User();

            user.setId(userId++);
            user.setEmail(userEmail);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(USER_VIEW.getPassword());
            user.setName(USER_VIEW.getName());
            user.setAddress(USER_VIEW.getAddress());

            if (AUTHENTICATION_CONTROLLER.signUp(user)) {
                System.out.println("Sing up successful");
                USER_VIEW.getUserOptions();
            } else {
                System.out.println("Sign up unsuccessful");
                displayMenu();
            }
        } catch (final StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
