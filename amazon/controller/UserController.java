package com.amazon.controller;

import com.amazon.model.User;
import com.amazon.service.UserService;
import com.amazon.service.impl.UserServiceImpl;

/**
 * <p>
 * Provides Control between service and the view to provide {@link User} sign in, sign up, update and delete
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserController {

    private static final UserService AMAZON_USER_SERVICE = UserServiceImpl.getAmazonUserService();
    private static final UserController amazonUserController = new UserController();

    private UserController() {
    }

    /**
     * <p>
     * Represents the object of AmazonUserController class can be created for only one time
     * </p>
     *
     * @return Represents {@link UserController}
     */
    public static UserController getAmazonUserController() {
        return amazonUserController;
    }

    /**
     * <p>
     * Provides user sign in for user
     * </p>
     *
     * @param email    User's email
     * @param password User's password
     * @return Boolean value from the validateUser method on service
     */
    public boolean signIn(final String email, final String password) {
        return AMAZON_USER_SERVICE.signIn(email, password);
    }

    /**
     * <p>
     * Provides user sign in
     * </p>
     *
     * @param email    Represents user email for verification
     * @param password Represents user password for verification
     * @param key      Represent admin key for verification
     * @return True if sign in successful
     */
    public boolean signIn(final String email, final String password, final String key) {
        return AMAZON_USER_SERVICE.signIn(email, password, key);
    }

    /**
     * <p>
     * Provides user sign up
     * </p>
     *
     * @param user Represents {@link User} object
     */
    public boolean signUp(final User user) {
        return AMAZON_USER_SERVICE.signUp(user);
    }

    /**
     * <p>
     * Gets the user and key then verify the admin key and provide admin signup
     * </p>
     *
     * @param user Represents {@link User} object
     * @return True if signup is successful
     */
    public boolean signUp(final User user, final String key) {
        return AMAZON_USER_SERVICE.signUp(user, key);
    }

    /**
     * <p>
     * Check whether the email is exists or not
     * </p>
     *
     * @param email Represents user's email id
     * @return True if the email is exits otherwise return false
     */
    public boolean isUserEmailExists(final String email) {
        return AMAZON_USER_SERVICE.isUserEmailExists(email);
    }


    /**
     * <p>
     * Gets the user details from the user list using user email
     * </p>
     *
     * @param email Represents user's email
     * @return {@link User} from the amazon service
     */
    public User getDetail(final String email) {
        return AMAZON_USER_SERVICE.getDetails(email);
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
        return AMAZON_USER_SERVICE.deleteUser(user);
    }
}

