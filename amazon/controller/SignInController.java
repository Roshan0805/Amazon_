package com.amazon.controller;

import com.amazon.service.SignInService;

public class SignInController {

    private static final SignInController SIGN_IN_CONTROLLER = new SignInController();
    private static final SignInService SIGN_IN_SERVICE = SignInService.getInstance();

    private SignInController() {}

    public static SignInController getInstance() {
        return SIGN_IN_CONTROLLER;
    }
    /**
     * <p>
     * Provides user sign in for user
     * </p>
     *
     * @param email    User's email
     * @param password User's password
     * @return Boolean value from the validateUser method on service
     */
    public boolean signIn(final String email, final String password) {
        return SIGN_IN_SERVICE.signIn(email, password);
    }

    /**
     * <p>
     * Provides user sign in
     * </p>
     *
     * @param email    Represents user email for verification
     * @param password Represents user password for verification
     * @param key      Represent admin key for verification
     * @return True if sign in successful
     */
    public boolean signIn(final String email, final String password, final String key) {
        return SIGN_IN_SERVICE.signIn(email, password, key);
    }
}
