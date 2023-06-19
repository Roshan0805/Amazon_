package com.amazon.service;

import com.amazon.model.User;
import com.amazon.service.impl.SignUpServiceImpl;

/**
 * <p>
 * Provides Services for {@link User}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public interface SignUpService {

    /**
     * <p>
     * Represents the {@link SignUpService} interface implemented class object can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link SignUpServiceImpl}
     */
    static SignUpService getInstance() {
        return SignUpServiceImpl.getInstance();
    }

    /**
     * <p>
     * Provides {@link User} sign up
     * </p>
     *
     * @param user User object is passed as a parameter
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Provides {@link User} sign up
     * </p>
     *
     * @param user User object is passed as a parameter
     * @param key  user entered ket for admin key verification
     * @return True if signUp successful otherwise return false
     */
    boolean signUp(final User user, final String key);
}
