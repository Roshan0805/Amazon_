package com.amazon.service;

import com.amazon.model.User;
import com.amazon.service.impl.UserServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Provides Services for {@link User} get and delete
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface UserService {

    static UserService getInstance() {
        return UserServiceImpl.getInstance();
    }

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
    User getDetails(final Long id);

    /**
     * <p>
     * Delete the user from the user list
     * </p>
     *
     * @param user {@link User} wants to delete
     * @return Boolean true is the user is deleted successfully
     */
    boolean deleteUser(final User user);

    /**
     * Represents all the admin details in user list
     * @return Represents Collection of Admin{@link User}
     */
    Collection<User> getAllAdmin();
}