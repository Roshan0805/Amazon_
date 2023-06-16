package com.amazon.service.impl;

import com.amazon.service.UserService;
import com.amazon.model.User;

import java.util.*;

/**
 * <p>
 * Implements the {@link UserService} to provide services for {@link  User} get and delete
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    public static final Map<Long, User> USERS_LIST = new HashMap<>();
    public static final String SECRET_KEY = "Amazon@1994";
    public static Long adminId = 1L;
    public static Long userId = 1L;
    private static final UserServiceImpl AMAZON_USER_SERVICE = new UserServiceImpl();

    private UserServiceImpl() {
    }

    /**
     * Represents the object of AmazonUserServiceImpl class can be created for only one time
     *
     * @return Represents {@link UserServiceImpl}
     */
    public static UserServiceImpl getInstance() {
        return AMAZON_USER_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param email Represents user email
     * @return Represents {@link User}
     */
    public User getDetails(final Long id) {
        return USERS_LIST.get(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param user User object wants to delete
     * @return True is the user is deleted successfully
     */
    public boolean deleteUser(final User user) {
        if (null == user) {
            return false;
        }
        USERS_LIST.remove(user.getId());

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return Represents Collection of Admin{@link User}
     */
    public Collection<User> getAllAdmin() {
        Collection<User> adminList = new HashSet<>();
        for (User user : USERS_LIST.values()) {
            if (user.getIsAdmin()) {
                adminList.add(user);
            }
        }
        return adminList;
    }

    /**
     * {@inheritDoc}
     *
     * @param email {@link User} email
     * @return True If the email is already exists
     */
    public boolean isUserEmailExists(String email) {
        for (final User user : USERS_LIST.values()) {

            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }
}