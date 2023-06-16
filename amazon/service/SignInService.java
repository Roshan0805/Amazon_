package com.amazon.service;

import com.amazon.model.User;
import com.amazon.service.impl.SignInServiceImpl;

/**
 * <p>
 * Provides Services for {@link User} sign in
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface SignInService {

    static SignInService getInstance() {
        return SignInServiceImpl.getInstance();
    }

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
}
