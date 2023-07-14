package com.amazon.service.impl;

import com.amazon.model.User;
import com.amazon.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final AuthenticationServiceImpl AUTHENTICATION_SERVICE = new AuthenticationServiceImpl();
    private static Long userId = 1L;


    private AuthenticationServiceImpl() {}

    /**
     * <p>
     *     Represents the object of {@link AuthenticationServiceImpl} can be created only once
     * </p>
     * @return Represents {@link AuthenticationServiceImpl}
     */
    public static AuthenticationService getInstance() {
        return AUTHENTICATION_SERVICE;
    }

    /**
     * <p>
     * Provides {@link User} sign up
     * </p>
     *
     * @param user User object is passed as a parameter
     */
    public boolean signUp(User user) {
        try {
            final Long id = generateId();
            user.setId(id);
            UserServiceImpl.USERS_LIST.put(id, user);

            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
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
     * @param email {@link User} email
     * @return True If the email is already exists
     */
    public boolean isUserEmailExists(String email) {
        for (final User user : UserServiceImpl.USERS_LIST.values()) {

            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param phoneNumber {@link User} email
     * @return True If the email is already exists
     */
    public boolean isNumberExists(String phoneNumber) {
        for (final User user : UserServiceImpl.USERS_LIST.values()) {

            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    private Long generateId() {
        return userId++;
    }
}
