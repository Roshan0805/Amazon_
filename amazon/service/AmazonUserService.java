package com.amazon.service;

import com.amazon.model.User;

import java.util.Collection;
import java.util.Set;


/**
 * Service provider
 *
 * @author Roshan
 * @version 1.0
 */
public interface AmazonUserService {

    /**
     * Provides User sign up
     *
     * @param user User object is passed as a parameter
     */
    void userSignUp(final User user);

    /**
     * Provides admin sign up
     *
     * @param user User object is passed as a parameter
     */
    void adminSignUp(final User user);

    /**
     * Gets email id's from the user list
     *
     * @return Set of email id's
     */
    Set<String> getUsersEmailIds();

    /**
     * Provides User sign in
     *
     * @param email    User's email id
     * @param password User's password
     * @return true if the sign in successful otherwise return false
     */
    boolean userSignIn(final String email, final String password);

    /**
     * Provides User sign in
     *
     * @param email    User's email id
     * @param password User's password
     * @return true if the sign in successful otherwise return false
     */
    boolean adminSignIn(final String email, final String password);

    /**
     * Check whether the  user email is already exist in user list
     *
     * @param emailId User's email
     * @return Boolean true if the email id is already present on the user list
     */
    boolean isUserEmailExists(final String emailId);

    /**
     * Check whether the admin email is already exist in user list
     *
     * @param email User's email
     * @return Boolean true if the email id is already present on the user list
     */
    boolean isAdminEmailExists(final String email);

    /**
     * Gets the user details from the user list
     *
     * @param email User email
     * @return User object from the user list
     */
    User getUserDetails(final String email);

    /**
     * Gets the user details from the user list
     *
     * @param email User email
     * @return User object from the user list
     */
    User getAdminDetails(final String email);

    /**
     * Updates the user email
     *
     * @param user  User object wants to change the email
     * @param email User email to update
     * @return Boolean true if the user email is  updated is successfully
     */
    boolean updateEmail(final User user, final String email);

    /**
     * Updates the username
     *
     * @param user     User object wants to change the username
     * @param userName Username for update
     * @return Boolean true if the username is updated successfully
     */
    boolean updateName(final User user, final String userName);

    /**
     * Updates the username
     *
     * @param user     User object wants to change the username
     * @param password User's password for update
     * @return Boolean true if the username is updated successfully
     */
    boolean updatePassword(final User user, final String password);

    /**
     * Updates the user address
     *
     * @param user    User object wants to change the address
     * @param address User address for update
     * @return Boolean true if address is updated successfully
     */
    boolean updateAddress(final User user, final String address);

    /**
     * Updates the user phone number
     *
     * @param user    User object wants to change the phone number
     * @param phoneNo User's phone number for update
     * @return Boolean true if the phone number is updated successfully
     */
    boolean updatePhoneNumber(final User user, final String phoneNo);

    /**
     * Delete the user from the user list
     *
     * @param user User object wants to delete
     * @return Boolean true is the user is deleted successfully
     */
    boolean deleteUser(final User user);

    /**
     * Verify the user key with verification
     *
     * @param key user key
     * @return boolean true if user key is equal to the verification key
     */
    boolean adminKeyVerification(final String key);
}


