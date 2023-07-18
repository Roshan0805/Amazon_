package com.amazon.view;

import com.amazon.controller.AuthenticationController;
import com.amazon.model.User;

public class AuthenticationView extends View {

    private static final AuthenticationView AUTHENTICATION = new AuthenticationView();
    private final AuthenticationController authenticationController = AuthenticationController.getInstance();
    private final static UserView USER_VIEW = UserView.getInstance();
    protected static Long userId = 1L;

    private AuthenticationView() {}

    public static AuthenticationView getInstance() {
        return AUTHENTICATION;
    }

    /**
     * <p>
     * Provides Methods for {@link User} sign in, sign up and exit
     * </p>
     */
    public void displayMenu() {
        System.out.println("1.signUp\n2.signIn\n3.exit");
        final int option = USER_VIEW.obtainUserChoice();

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
                displayMenu();
        }
    }

    /**
     * <P> Gets user type for sign in </P>
     */
    public void signIn() {
        System.out.println("Enter the email and password");
        if (authenticationController.signIn(USER_VIEW.getEmail(), USER_VIEW.getPassword())) {
            System.out.println("Sign in successful");
            USER_VIEW.obtainUserOptions();
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
            final String phoneNumber = USER_VIEW.obtainPhoneNumber();

            if (authenticationController.isUserEmailExists(userEmail) || authenticationController.isNumberExists(phoneNumber)) {
                System.out.println("The email or phone number is already exists");
                signUp();
            }
            final User user = new User();

            user.setEmail(userEmail);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(USER_VIEW.getPassword());
            user.setName(USER_VIEW.obtainName());
            user.setAddress(USER_VIEW.getAddress());

            if (authenticationController.signUp(user)) {
                System.out.println("Sing up successful");
                USER_VIEW.obtainUserOptions();
            } else {
                System.out.println("Sign up unsuccessful");
                displayMenu();
            }
        } catch (final StringIndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
