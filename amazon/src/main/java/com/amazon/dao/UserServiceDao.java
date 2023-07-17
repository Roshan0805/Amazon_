package com.amazon.dao;

import com.amazon.model.User;

import java.util.Collection;

/**
 * <p>
 * Represents the {@link User} service
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface UserServiceDao {


    /**
     * <p>
     * Gets the user details from the user list
     * </p>
     *
     * @param id User email
     * @return User object from the user list
     */
    User getDetails(final Long id);

    /**
     * <p>
     * Delete the user from the user list
     * </p>
     *
     * @param user_id Represents the id of {@link User}
     * @return Boolean true is the user is deleted successfully
     */
    boolean deleteUser(final Long user_id);

    /**
     * Represents all the {@link User} details in the usersList
     * @return Represents collection of {@link User}
     */
    Collection<User> getAllUser();

    /**
     * <p>
     *     Represents the {@link User}update
     * </p>
     * @param user Represents {@link User}
     * @param userId Represents the id of user
     * @return True if the user object is updated successfully
     */
    boolean update(final User user, final Long userId);
}
