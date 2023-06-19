package com.amazon.view;

import com.amazon.controller.SignInController;
import com.amazon.model.User;

/**
 * <p>
 * Represents {@link User} sign in
 * </p>
 *
 * @version 1.0
 * @auther Roshan
 */
public class SignInView {

    private static final SignInView SIGN_IN_VIEW = new SignInView();
    private static final LoginView LOGIN_VIEW = LoginView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final AdminView ADMIN_VIEW = AdminView.getInstance();
    private static final SignInController SIGN_IN_CONTROLLER = SignInController.getInstance();
    private static final CustomerView CUSTOMER_VIEW = CustomerView.getInstance();

    private SignInView() {
    }

    /**
     * <p>
     *     Represents the object for {@link SignInView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link SignInView}
     */
    public static SignInView getInstance() {
        return SIGN_IN_VIEW;
    }

    /**
     * <p>
     * Gets user type from the user and providing sign in
     * </p>
     */
    public void signIn() {
        System.out.println("Enter the user type\n1.admin user\n2.customer\t3.back to menu");
        final int userChoice = USER_VIEW.getUserChoice();

        switch (userChoice) {
            case 1:
                signIn(USER_VIEW.getUserEmail(), USER_VIEW.getUserPassword(), USER_VIEW.getAdminKey());
                break;
            case 2:
                signIn(USER_VIEW.getUserEmail(), USER_VIEW.getUserPassword());
                break;
            case 3:
                LOGIN_VIEW.displayMenu();
                break;
            default:
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
        if (SIGN_IN_CONTROLLER.signIn(email, password)) {
            System.out.println("User sign in successful");
            CUSTOMER_VIEW.getUserOptions();
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
        if (SIGN_IN_CONTROLLER.signIn(email, password, key)) {
            System.out.println("Admin singIn successful");
            ADMIN_VIEW.getAdminOptions();
        } else {
            System.out.println("Invalid details");
            signIn();
        }
    }
}
