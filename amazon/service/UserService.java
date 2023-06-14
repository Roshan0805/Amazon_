package com.amazon.service;

import com.amazon.model.User;

import java.util.Set;

/**
 * <p>
 * Provides Services for {@link User} signIn, signUp, get and delete user
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Provides {@link User} sign up
     * </p>
     *
     * @param user User object is passed as a parameter
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Provides {@link User} sign up
     * </p>
     *
     * @param user User object is passed as a parameter
     * @param key  user entered ket for admin key verification
     * @return True if signUp successful otherwise return false
     */
    boolean signUp(final User user, final String key);

    /**
     * <p>
     * Gets email id's from the user list
     * </p>
     *
     * @return Set of email id's
     */
    Set<String> getUsersEmailIds();

    /**
     * <p>
     * Provides {@link User} sign in
     * </p>
     *
     * @param email    User's email id
     * @param password User's password
     * @return True if email and password match the user from the users list
     */
    boolean signIn(final String email, final String password);

    /**
     * <p>
     * Provides {@link User} sign in
     * </p>
     *
     * @param email    User's email id
     * @param password User's password
     * @param key      User's admin key
     * @return True if email, password and key match the user from the users list
     */
    boolean signIn(final String email, final String password, final String key);

    /**
     * <p>
     * Check whether the  user email is already exist in user list
     * </p>
     *
     * @param emailId User's email
     * @return True if the email id is already present on the user list
     */
    boolean isUserEmailExists(final String emailId);

    /**
     * <p>
     * Gets the user details from the user list
     * </p>
     *
     * @param email User email
     * @return User object from the user list
     */
    User getDetails(final String email);

    /**
     * <p>
     * Delete the user from the user list
     * </p>
     *
     * @param user {@link User} wants to delete
     * @return Boolean true is the user is deleted successfully
     */
    boolean deleteUser(final User user);
}