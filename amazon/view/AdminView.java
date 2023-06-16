package com.amazon.view;

import com.amazon.controller.UserController;
import com.amazon.model.User;

import java.util.Collection;

/**
 * <p>
 * Represents the admin view of the application
 * </p>
 *
 * @version 1.0
 * @auther Roshan
 */
public class AdminView {

    private static final AdminView ADMIN_VIEW = new AdminView();
    private static final LoginView LOGIN_VIEW = LoginView.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final ProductView PRODUCT_VIEW = ProductView.getInstance();
    private static final UserController USER_CONTROLLER = UserController.getInstance();

    private AdminView() {
    }

    public static AdminView getInstance() {
        return ADMIN_VIEW;
    }

    /**
     * <p>
     * Provides option for access admin details and the products details and logout
     * </p>
     */
    public void getAdminOptions() {
        System.out.println("Choose from the options\n1.admin details\t2.product details\t3.get all admin details");
        final int adminOption = USER_VIEW.getUserChoice();

        if (4 == adminOption) {
            LOGIN_VIEW.displayMenu();
        }
        final User user = USER_VIEW.getsUserDetails();

        switch (adminOption) {
            case 1:
                USER_VIEW.accessUser(user);
                break;
            case 2:
                PRODUCT_VIEW.accessProduct(user.getId());
                break;
            case 3:
                getAllAdmin();
                break;
            default:
                System.out.println("Enter the correct option");
                getAdminOptions();
        }
    }

    /**
     * <p>
     *     Get all the Admin details from the user list
     * </p>
     */
    public void getAllAdmin() {
        Collection<User> admins = USER_CONTROLLER.getAllAdmin();

        if(admins.isEmpty()) {
            System.out.println("The admin list is empty");
        } else {
            System.out.println(admins);
        }
        getAdminOptions();
    }
}
