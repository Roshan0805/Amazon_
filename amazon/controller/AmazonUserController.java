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

    private static final AmazonUserService AMAZON_USER_SERVICE = new AmazonUserServiceImpl();


    /**
     * Provides the user sign in for user
     *
     * @param email    User's email
     * @param password User's password
     * @return Boolean value from the validateUser method on service
     */
    public boolean signInUser(final String email, final String password) {
        return AMAZON_USER_SERVICE.userSignIn(email, password);
    }

    public boolean signInAdmin(final String email, final String password) {
        return AMAZON_USER_SERVICE.adminSignIn(email, password);
    }

    /**
     * Provides user sign up for user
     *
     * @param user Represents {@link User} object
     */
    public void signUpUser(final User user) {
        AMAZON_USER_SERVICE.userSignUp(user);
    }

    /**
     * Provides user sign up for user
     *
     * @param user Represents {@link User} object
     */
    public void signUpAdmin(final User user) {
        AMAZON_USER_SERVICE.adminSignUp(user);
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
     * Check whether the email is exists or not
     *
     * @param email The User's email id
     * @return The boolean value from the isEmailAlreadyExists method from service
     */
    public boolean isAdminEmailExists(final String email) {
        return AMAZON_USER_SERVICE.isAdminEmailExists(email);
    }

    /**
     * Returns the getUserDetails method from the amazon service for getting the user details
     *
     * @param email User's email
     * @return User Object from the amazon service
     */
    public User getUserDetail(final String email) {
        return AMAZON_USER_SERVICE.getUserDetails(email);
    }

    /**
     * Returns the getAdminDetails method from the amazon service for getting the user details
     *
     * @param email User's email
     * @return User Object from the amazon service
     */
    public User getAdminDetail(final String email) {
        return AMAZON_USER_SERVICE.getAdminDetails(email);
    }

    /**
     * Returns the deleteUser method from the amazon service for delete user from the user's list
     *
     * @param user user object
     * @return boolean value from the deleteUser method
     */
    public boolean deleteUser(final User user) {
        return AMAZON_USER_SERVICE.deleteUser(user);
    }

    /**
     * Returns the adminKeyVerification method for validating key from service
     *
     * @param key user key for validate
     * @return boolean value returned from adminKeyVerification method
     */
    public boolean adminKeyVerification(final String key) {
        return AMAZON_USER_SERVICE.adminKeyVerification(key);
    }
}

