package com.amazon.service.impl;

import com.amazon.model.User;
import com.amazon.service.SignInService;

/**
 * <p>
 * Implements the {@link SignInService} to provide services for {@link User} sign in
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class SignInServiceImpl implements SignInService {

    private static final SignInService SIGN_IN_SERVICE = new SignInServiceImpl();

    private SignInServiceImpl() {
    }

    /**
     * <p>
     * Represents the object of {@link SignInServiceImpl} class can be created for only one time
     * </p>
     *
     * @return Represents {@link SignInServiceImpl}
     */
    public static SignInService getInstance() {
        return SIGN_IN_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param email    Represents user's email
     * @param password Represents user's password
     * @return True if email and password match the user from the users list
     */
    public boolean signIn(final String email, final String password) {
        for (final User existingUser : UserServiceImpl.USERS_LIST.values()) {

            if ((existingUser.getEmail().equals(email)) && (existingUser.getPassword().equals(password))) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param email    Represents user's email
     * @param password Represents user's password
     * @param key      Represents user's key
     * @return True if email, password and key match the admin user from the users list
     */
    public boolean signIn(final String email, final String password, final String key) {
        for (final User user : UserServiceImpl.USERS_LIST.values()) {

            if ((user.getEmail().equals(email)) && (user.getPassword().equals(password) &&
                    (UserServiceImpl.SECRET_KEY.equals(key)))) {

                return true;
            }
        }

        return false;
    }

}
