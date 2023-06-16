package com.amazon.controller;

import com.amazon.model.User;
import com.amazon.service.SignUpService;
import com.amazon.service.UserService;

public class SignUpController {

    private static final SignUpController SIGN_UP_CONTROLLER = new SignUpController();
    private static final UserService USER_SERVICE = UserService.getInstance();
    private static final SignUpService SIGN_UP_SERVICE = SignUpService.getInstance();

    private SignUpController() {}

    public static SignUpController getInstance() {
        return SIGN_UP_CONTROLLER;
    }

    /**
     * <p>
     * Provides user sign up
     * </p>
     *
     * @param user Represents {@link User} object
     */
    public boolean signUp(final User user) {
        return SIGN_UP_SERVICE.signUp(user);
    }

    /**
     * <p>
     * Gets the user and key then verify the admin key and provide admin signup
     * </p>
     *
     * @param user Represents {@link User} object
     * @return True if signup is successful
     */
    public boolean signUp(final User user, final String key) {
        return SIGN_UP_SERVICE.signUp(user, key);
    }

    /**
     * <p>
     * Check whether the email is exists or not
     * </p>
     *
     * @param email Represents user's email id
     * @return True if the email is exits otherwise return false
     */
    public boolean isUserEmailExists(final String email) {
        return USER_SERVICE.isUserEmailExists(email);
    }

}
