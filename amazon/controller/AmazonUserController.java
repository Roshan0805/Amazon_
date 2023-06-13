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

    private AmazonUserController() {
    }

    /**
     * <p>
     * Represents the object of AmazonUserController class can be created for only one time
     * </p>
     *
     * @return Represents {@link AmazonUserController}
     */
    public static AmazonUserController getAmazonUserController() {
        if (amazonUserController == null) {
            amazonUserController = new AmazonUserController();
        }
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
     * Provides user sign in for user
     * </p>
     *
     * @param email    Represents user email for verification
     * @param password Represents user password for verification
     * @param key      Represent admin key for verification
     * @return
     */
    public boolean signIn(final String email, final String password, final String key) {
        return AMAZON_USER_SERVICE.signIn(email, password, key);
    }

    /**
     * <p>
     * Provides user sign up for user
     * </p>
     *
     * @param user Represents {@link User} object
     */
    public void signUp(final User user) {
        AMAZON_USER_SERVICE.signUp(user);
    }

    /**
     * <p>
     * Gets the user and key then verify the admin key and provide admin signup
     * </p>
     *
     * @param user Represents {@link User} object
     * @return True if the signup is successful
     */
    public boolean signUp(final User user, final String key) {
        return AMAZON_USER_SERVICE.signUp(user, key);
    }

    /**
     * <p>
     * Check whether the email is exists or not
     * </p>
     *
     * @param email The User's email id
     * @return The boolean value from the isEmailAlreadyExists method from service
     */
    public boolean isUserEmailExists(final String email) {
        return AMAZON_USER_SERVICE.isUserEmailExists(email);
    }


    /**
     * <p>
     * Gets the user details from the user list using user email
     * </p>
     *
     * @param email User's email
     * @return {@link User} from the amazon service
     */
    public User getDetail(final String email) {
        return AMAZON_USER_SERVICE.getAdminDetails(email);
    }

    /**
     * <p>
     * Deletes the user from the user list
     * </p>
     *
     * @param user Represents {@link User}
     * @return boolean value from the deleteUser method
     */
    public boolean delete(final User user) {
        return AMAZON_USER_SERVICE.deleteUser(user);
    }
}

