package com.amazon.service.impl;

import com.amazon.model.User;
import com.amazon.service.AmazonUserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Implements the AmazonUserService to provide services for {@link  User} for signIn, signUp, getUser, delete User
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonUserServiceImpl implements AmazonUserService {

    private static final Map<Integer, User> USERS_LIST = new HashMap<>();
    private static final String SECRET_KEY = "Amazon@1994";
    private static int adminId = 1;
    private static int userId = 1;
    private static AmazonUserServiceImpl amazonUserService = null;

    private AmazonUserServiceImpl() {
    }

    /**
     * Represents the object of AmazonUserServiceImpl class can be created for only one time
     *
     * @return Represents {@link AmazonUserServiceImpl}
     */
    public static AmazonUserServiceImpl getAmazonUserService() {
        if (amazonUserService == null) {
            return new AmazonUserServiceImpl();
        } else {
            return amazonUserService;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User}
     */
    public boolean signUp(final User user) {
        try {
            user.setId(userId);
            USERS_LIST.put(userId++, user);
            return true;
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents {@link User}
     */
    public boolean signUp(final User user, final String key) {
        if (SECRET_KEY.equals(key)) {
            user.setId(adminId);
            user.setAsAdmin();
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
            emailIds.add(user.getEmailId());
        }

        return emailIds;
    }

    /**
     * {@inheritDoc}
     *
     * @param email User's email
     * @return True if email is already registered
     */
    public boolean isUserEmailExists(final String email) {
        return getUsersEmailIds().contains(email);
    }

    /**
     * {@inheritDoc}
     *
     * @param email    User's email
     * @param password User's password
     * @return True if email and password matches from user list
     */
    public boolean signIn(final String email, final String password) {
        for (final User existingUser : USERS_LIST.values()) {

            if ((existingUser.getEmailId().equals(email)) && (existingUser.getPassword().equals(password))) {

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param email    User's email
     * @param password User's password
     * @param key
     * @return True if email and password matches from user list
     */
    public boolean signIn(final String email, final String password, final String key) {
        for (final User user : USERS_LIST.values()) {

            if ((user.getEmailId().equals(email)) && (user.getPassword().equals(password) && (SECRET_KEY.equals(key)))) {

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param email User email
     * @return Represents {@link User}
     */
    public User getUserDetails(final String email) {
        User user = new User();

        for (final User existingUser : USERS_LIST.values()) {

            if (existingUser.getEmailId().equals(email)) {

                return existingUser;
            }
        }

        return user;
    }

    /**
     * {@inheritDoc}
     *
     * @param email User email
     * @return Represents {@link User}
     */
    public User getAdminDetails(final String email) {

        for (final User existingUser : USERS_LIST.values()) {

            if (existingUser.getEmailId().equals(email)) {

                return existingUser;
            }
        }

        return null;
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
        USERS_LIST.remove(user);

        return true;
    }
}
