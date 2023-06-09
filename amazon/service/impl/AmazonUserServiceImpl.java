package com.amazon.service.impl;

import com.amazon.model.User;
import com.amazon.service.AmazonUserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Provides service
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonUserServiceImpl implements AmazonUserService {

    private static final Map<Integer, User> USERS_LIST = new HashMap<>();
    private static final Map<Integer, User> ADMIN_LIST = new HashMap<>();
    private static final String ADMIN_KEY = "Amazon@1994";
    private static int adminId = 1;
    private static int userId = 1;

    /**
     * {@inheritDoc}
     *
     * @param user User object
     */
    public void userSignUp(final User user) {
        user.setId(userId);
        USERS_LIST.put(userId++, user);
    }

    public void adminSignUp(final User user) {
        user.setId(adminId);
        ADMIN_LIST.put(adminId++, user);
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

    public Set<String> getAdminsEmailIds() {
        final Set<String> emailIds = new HashSet<>();

        for (final User user : ADMIN_LIST.values()) {
            emailIds.add(user.getEmailId());
        }

        return emailIds;
    }

    /**
     * {@inheritDoc}
     *
     * @param email User's email
     * @return Boolean true if email is already registered
     */
    public boolean isUserEmailExists(final String email) {
        return getUsersEmailIds().contains(email);
    }

    /**
     * {@inheritDoc}
     *
     * @param email User's email
     * @return Boolean true if email is already registered
     */
    public boolean isAdminEmailExists(final String email) {
        return getAdminsEmailIds().contains(email);
    }

    /**
     * {@inheritDoc}
     *
     * @param email    User's email
     * @param password User's password
     * @return Boolean true if email and password matches from user list
     */
    public boolean userSignIn(final String email, final String password) {
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
     * @return Boolean true if email and password matches from user list
     */
    public boolean adminSignIn(final String email, final String password) {
        for (final User user : ADMIN_LIST.values()) {

            if ((user.getEmailId().equals(email)) && (user.getPassword().equals(password))) {

                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param email User email
     * @return User object from the user list
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

    public User getAdminDetails(final String email) {
        final User user = new User();

        for (final User existingUser : ADMIN_LIST.values()) {

            if (existingUser.getEmailId().equals(email)) {

                return existingUser;
            }
        }
        return user;
    }


    /**
     * {@inheritDoc}
     *
     * @param user  User object wants to change the email
     * @param email User email to update
     * @return Boolean true if the user email is  updated is successfully
     */
    public boolean updateEmail(final User user, final String email) {
        if (user == null || email == null) {
            return false;
        }
        user.setEmail(email);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user User object wants to change the username
     * @param name Username for update
     * @return Boolean true if the username is updated successfully
     */
    public boolean updateName(final User user, final String name) {
        if (user == null || name == null) {
            return false;
        }
        user.setName(name);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user     User object wants to change the username
     * @param password Username for update
     * @return Boolean true if the username is updated successfully
     */
    public boolean updatePassword(final User user, final String password) {
        if (user == null || password == null) {
            return false;
        }
        user.setPassword(password);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user    User object wants to change the address
     * @param address User address for update
     * @return Boolean true if address is updated successfully
     */
    public boolean updateAddress(final User user, final String address) {
        if (user == null || address == null) {
            return false;
        }
        user.setAddress(address);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user    User object wants to change the phone number
     * @param phoneNo User's phone number for update
     * @return Boolean true if the phone number is updated successfully
     */
    public boolean updatePhoneNumber(final User user, final String phoneNo) {
        if (user == null || phoneNo == null) {
            return false;
        }
        user.setPhoneNo(phoneNo);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user User object wants to delete
     * @return Boolean true is the user is deleted successfully
     */
    public boolean deleteUser(final User user) {
        if (user == null) {
            return false;
        }
        USERS_LIST.remove(user);
        return true;
    }

    /**
     * Verify the user key with verification
     *
     * @param key user key
     * @return boolean true if user key is equal to the verification key
     */
    public boolean adminKeyVerification(String key) {
        return ADMIN_KEY.equals(key);
    }
}
