package com.amazon.service.impl;

import com.amazon.model.User;
import com.amazon.service.AmazonUserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Implements the {@link AmazonUserService} to provide services for {@link  User} signIn, signUp, getUser, delete User
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonUserServiceImpl implements AmazonUserService {

    private static final Map<Long, User> USERS_LIST = new HashMap<>();
    private static final String SECRET_KEY = "Amazon@1994";
    private static Long adminId = 1L;
    private static Long userId = 1L;
    private static final AmazonUserServiceImpl amazonUserService = new AmazonUserServiceImpl();

    private AmazonUserServiceImpl() {
    }

    /**
     * Represents the object of AmazonUserServiceImpl class can be created for only one time
     *
     * @return Represents {@link AmazonUserServiceImpl}
     */
    public static AmazonUserServiceImpl getAmazonUserService() {
        return amazonUserService;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User}
     * @return True if signup is successful otherwise return false
     */
    public boolean signUp(final User user) {
        try {
            user.setId(userId);
            USERS_LIST.put(userId++, user);

            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User}
     * @return True if signup is successful otherwise return false
     */
    public boolean signUp(final User user, final String key) {
        if (SECRET_KEY.equals(key)) {
            user.setId(adminId);
            user.setIsAdmin();
            USERS_LIST.put(adminId++, user);

            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return email id's from the user list
     */
    public Set<String> getUsersEmailIds() {
        final Set<String> emailIds = new HashSet<>();

        for (final User user : USERS_LIST.values()) {
            emailIds.add(user.getEmail());
        }

        return emailIds;
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user's email
     * @return True if email is already registered
     */
    public boolean isUserEmailExists(final String email) {
        return getUsersEmailIds().contains(email);
    }

    /**
     * {@inheritDoc}
     *
     * @param email    Represents user's email
     * @param password Represents user's password
     * @return True if email and password match the user from the users list
     */
    public boolean signIn(final String email, final String password) {
        for (final User existingUser : USERS_LIST.values()) {

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
        for (final User user : USERS_LIST.values()) {

            if ((user.getEmail().equals(email)) && (user.getPassword().equals(password) && (SECRET_KEY.equals(key)))) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user email
     * @return Represents {@link User}
     */
    public User getUserDetails(final String email) {
        User user = new User();

        for (final User existingUser : USERS_LIST.values()) {

            if (existingUser.getEmail().equals(email)) {
                return existingUser;
            }
        }

        return user;
    }

    /**
     * {@inheritDoc}
     *
     * @param user User object wants to delete
     * @return True is the user is deleted successfully
     */
    public boolean deleteUser(final User user) {
        if (user == null) {
            return false;
        }
        USERS_LIST.remove(user.getId());

        return true;
    }
}
