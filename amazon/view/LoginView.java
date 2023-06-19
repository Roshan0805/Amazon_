package com.amazon.view;

import com.amazon.model.User;
import com.amazon.view.validation.UserValidation;

import java.util.Scanner;

/**
 * <p>
 * Represents the login view of the application
 * </P>
 *
 * @author Roshan
 * @version 1.0
 */
public class LoginView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LoginView LOGIN_VIEW = new LoginView();
    private static final SignInView SIGN_IN_VIEW = SignInView.getInstance();
    private static final SignUpView SIGN_UP_VIEW = SignUpView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final UserValidation AMAZON_USER_VALIDATION = UserValidation.getInstance();

    private LoginView() {
    }

    /**
     * <p>
     * Represents the object of {@link LoginView} class be created for only one time
     * </p>
     *
     * @return Represents {@link LoginView}
     */
    public static LoginView getInstance() {
        return LOGIN_VIEW;
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
                SIGN_UP_VIEW.signUp();
                break;
            case 2:
                SIGN_IN_VIEW.signIn();
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

    public static void main(final String[] args) {
        System.out.println("Welcome to amazon");
        LOGIN_VIEW.displayMenu();
        System.out.println("Do you want to continue yes(y) else press n to exit");
        final String exitChoice = SCANNER.nextLine().trim();

        if (AMAZON_USER_VALIDATION.toContinueValidation(exitChoice)) {
            LOGIN_VIEW.displayMenu();
        } else {
            SCANNER.close();
        }
    }
}