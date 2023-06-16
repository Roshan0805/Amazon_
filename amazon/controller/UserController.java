package com.amazon.controller;

import com.amazon.model.User;
import com.amazon.service.UserService;
import com.amazon.service.impl.UserServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Provides Control between service and the view to provide {@link User} sign in, sign up, update and delete
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserController {

    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();
    private static final UserController AMAZON_USER_CONTROLLER = new UserController();

    private UserController() {
    }

    /**
     * <p>
     * Represents the object of AmazonUserController class can be created for only one time
     * </p>
     *
     * @return Represents {@link UserController}
     */
    public static UserController getInstance() {
        return AMAZON_USER_CONTROLLER;
    }

    /**
     * <p>
     * Gets the user details from the user list using user email
     * </p>
     *
     * @param email Represents user's email
     * @return {@link User} from the amazon service
     */
    public User getDetail(final Long id) {
        return USER_SERVICE.getDetails(id);
    }

    /**
     * <p>
     * Deletes the user from the user list
     * </p>
     *
     * @param user Represents {@link User}
     * @return True if the user is deleted successfully otherwise return false
     */
    public boolean delete(final User user) {
        return USER_SERVICE.deleteUser(user);
    }

    /**
     * Represents all the admin details in user list
     * @return Represents Collection of Admin{@link User}
     */
    public Collection<User> getAllAdmin() {return USER_SERVICE.getAllAdmin();
    }

}

