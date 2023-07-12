package com.amazon.service.Impl2;

import com.amazon.dao.UserServiceDao;
import com.amazon.dao.impl.UserServiceDaoImpl;
import com.amazon.model.User;
import com.amazon.service.UserService;

import java.util.Collection;

/**
 * <p>
 * Implements the {@link UserServiceDaoImpl} to provide services for {@link  User}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserServiceImpl2 implements UserService {

    private static final UserService USER_SERVICE = new UserServiceImpl2();
    private static final UserServiceDao USER_SERVICE_DAO = UserServiceDaoImpl.getInstance();

    private UserServiceImpl2() {}

    public static UserService getInstance() {
        return USER_SERVICE;
    }

    /**
     * <p>
     * Gets the user details from the user list
     * </p>
     *
     * @param id User email
     * @return User object from the user list
     */
    public User get(Long id) {
        return USER_SERVICE_DAO.getDetails(id);
    }

    /**
     * <p>
     * Delete the user from the user list
     * </p>
     *
     * @param userId Represents the id of {@link User}
     * @return Boolean true is the user is deleted successfully
     */
    public boolean delete(Long userId) {
        return USER_SERVICE_DAO.deleteUser(userId);
    }

    /**
     * Represents all the {@link User} details in the usersList
     *
     * @return Represents collection of {@link User}
     */
    public Collection<User> getAllUser() {
        return USER_SERVICE_DAO.getAllUser();
    }

    /**
     * Represents the update of {@link User}
     *
     * @param user Represents {@link User}
     * @param id   Represents the id of user
     * @return True if the user is updated successfully
     */
    public boolean update(User user, Long id) {
        return USER_SERVICE_DAO.update(user, id);
    }
}
