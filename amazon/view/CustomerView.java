package com.amazon.view;

import com.amazon.model.User;

/**
 * <p>
 * Represents customer view of the application
 * </p>
 *
 * @version 1.0
 * @auther Roshan
 */
public class CustomerView {

    private static final CustomerView CUSTOMER_VIEW = new CustomerView();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final ProductView PRODUCT_VIEW = ProductView.getInstance();
    private static final LoginView LOGIN_VIEW = LoginView.getInstance();

    private CustomerView() {
    }

    /**
     * <p>
     * Represents the object for {@link CustomerView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link CustomerView}
     */
    public static CustomerView getInstance() {
        return CUSTOMER_VIEW;
    }

    /**
     * <p>
     * Provides navigation for admin details and the products details and logout
     * </p>
     */
    public void getUserOptions() {
        System.out.println("Choose from the options\n1.usersDetail\n2.viewProducts\n3.logout");
        final int userOption = USER_VIEW.getUserChoice();

        switch (userOption) {
            case 1:
                final User user = USER_VIEW.getsUserDetails();
                USER_VIEW.accessUser(user);
                break;
            case 2:
                PRODUCT_VIEW.viewProduct();
                break;
            case 3:
                LOGIN_VIEW.displayMenu();
                break;
            default:
                System.out.println("Enter the correct option");
                getUserOptions();
        }
    }
}
