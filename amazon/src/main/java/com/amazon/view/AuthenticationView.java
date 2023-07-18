package com.amazon.view;

import com.amazon.controller.AuthenticationController;
import com.amazon.exception.DBException;
import com.amazon.model.User;

/**
 * <p>
 * Represents the authentication view of {@link User}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AuthenticationView extends View {

    private static final UserView USER_VIEW = UserView.getInstance();
    private static AuthenticationView AUTHENTICATION_VIEW ;
    private final AuthenticationController authenticationController;

    public AuthenticationView() {
        authenticationController = AuthenticationController.getInstance();
    }

    /**
     * <p>
     * Represents the object of {@link AuthenticationView} class can be created for only one time
     * </p>
     *
     * @return Represents {@link AuthenticationView}
     */
    public static AuthenticationView getInstance() {
        return AUTHENTICATION_VIEW == null ? AUTHENTICATION_VIEW = new AuthenticationView() :AUTHENTICATION_VIEW ;
    }

    /**
     * <p>
     * Provides Methods for {@link User} sign in, sign up and exit
     * </p>
     */
    public void displayMenu() {
        System.out.println("1.signUp\n2.signIn\n3.exit");
        final UserChoice option = USER_VIEW.obtainUserChoice();

        switch (option) {
            case ONE:
                signUp();
                break;
            case TWO:
                signIn();
                break;
            case THREE:
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
        try {
            if (authenticationController.signIn(USER_VIEW.obtainEmail(), USER_VIEW.obtainPassword())) {
                System.out.println("Sign in successful");
                USER_VIEW.obtainUserOptions();
            } else {
                System.out.println("Sign in unsuccessful");
                signIn();
            }
        } catch (DBException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * <p>
     * Gets the user details and provides signup for the user
     * </p>
     */
    public void signUp() {
        try {
            final String userEmail = USER_VIEW.obtainEmail();
            final String phoneNumber = USER_VIEW.obtainPhoneNumber();

            if (authenticationController.isUserEmailExists(userEmail) || authenticationController.isNumberExists(phoneNumber)) {
                System.out.println("The email or phone number is already exists");
                signUp();
            }
            final User user = new User();

            user.setEmail(userEmail);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(USER_VIEW.obtainPassword());
            user.setName(USER_VIEW.obtainName());
            user.setAddress(USER_VIEW.obtainAddress());

            if (authenticationController.signUp(user)) {
                System.out.println("Sing up successful");
                USER_VIEW.obtainUserOptions();
            } else {
                System.out.println("Sign up unsuccessful");
                displayMenu();
            }
        } catch (final StringIndexOutOfBoundsException | DBException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
