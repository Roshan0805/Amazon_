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

    /**
     * <p>
     * Represents the object for {@link AdminView} class can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link AdminView}
     */
    public static AdminView getInstance() {
        return ADMIN_VIEW;
    }

    /**
     * <p>
     * Provides option for access admin details and the products details and logout
     * </p>
     */
    public void getAdminOptions() {
        System.out.println(String.join("", "Choose from the options\n1.admin details\n",
                "2.product details\n3.get all admin details\n4.get all user\n5.logout"));
        final int adminOption = USER_VIEW.getUserChoice();

        if (5 == adminOption) {
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
            case 4:
                getAllUsers();
                break;
            default:
                System.out.println("Enter the correct option");
                getAdminOptions();
        }
    }

    /**
     * <p>
     * Get all the Admin details from the user list
     * </p>
     */
    public void getAllAdmin() {
        Collection<User> admins = USER_CONTROLLER.getAllAdmin();

        if (admins.isEmpty()) {
            System.out.println("The admin list is empty");
        } else {
            System.out.println(admins);
        }
        getAdminOptions();
    }

    /**
     * Represents all the {@link User} details in the usersList
     *
     * @return Represents collection of {@link User}
     */
    public void getAllUsers() {
        Collection<User> users = USER_CONTROLLER.getAllUser();

        if (users.isEmpty()) {
            System.out.println("The user list is empty");
        } else {
            System.out.println(users);
        }
        getAdminOptions();
    }

}
