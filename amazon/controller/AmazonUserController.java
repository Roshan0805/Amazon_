package com.amazon.controller;

import com.amazon.model.User;
import com.amazon.service.AmazonUserService;
import com.amazon.service.impl.AmazonUserServiceImpl;

/**
 * Provides Control between service and the view to provide user sign in, sign up and update
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonUserController {

    private static final AmazonUserService AMAZON_USER_SERVICE = AmazonUserServiceImpl.getAmazonUserService();
    private static AmazonUserController amazonUserController = null;

    private AmazonUserController() {}

    public static AmazonUserController getAmazonUserController() {
        if(amazonUserController == null) {
            amazonUserController = new AmazonUserController();
        }
        return amazonUserController;
    }
    /**
     * Provides the user sign in for user
     *
     * @param email    User's email
     * @param password User's password
     * @return Boolean value from the validateUser method on service
     */
    public boolean signIn(final String email, final String password) {
        return AMAZON_USER_SERVICE.signIn(email, password);
    }

    public boolean signIn(final String email, final String password, final String key) {
        return AMAZON_USER_SERVICE.signIn(email, password, key);
    }

    /**
     * Provides user sign up for user
     *
     * @param user Represents {@link User} object
     */
    public void signUp(final User user) {
        AMAZON_USER_SERVICE.signUp(user);
    }

    /**
     * Gets the user and key then verify the admin key and provide admin signup
     *
     * @param user Represents {@link User} object
     */
    public boolean signUp(final User user, final String key) {
        return AMAZON_USER_SERVICE.signUp(user, key);
    }

    /**
     * Check whether the email is exists or not
     *
     * @param email The User's email id
     * @return The boolean value from the isEmailAlreadyExists method from service
     */
    public boolean isUserEmailExists(final String email) {
        return AMAZON_USER_SERVICE.isUserEmailExists(email);
    }


    /**
     * Gets the user details from the user list using user email
     *
     * @param email User's email
     * @return {@link User} from the amazon service
     */
    public User getDetail(final String email) {
        return AMAZON_USER_SERVICE.getAdminDetails(email);
    }

    /**
     * Deletes the user from the user list
     *
     * @param user Represents {@link User}
     * @return boolean value from the deleteUser method
     */
    public boolean delete(final User user) {
        return AMAZON_USER_SERVICE.deleteUser(user);
    }
}

