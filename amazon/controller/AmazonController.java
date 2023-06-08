package com.amazon.controller;

import com.amazon.model.Product;
import com.amazon.model.User;
import com.amazon.service.serviceimpl.AmazonProductServiceImpl;
import com.amazon.service.AmazonProductService;
import com.amazon.service.AmazonUserService;
import com.amazon.service.serviceimpl.AmazonUserServiceImpl;

import java.util.Collection;
import java.util.Set;

/**
 * Controller between service and the view
 *
 * @author Roshan
 * @version 1.0
 */
public class AmazonController {

    private static final AmazonUserService AMAZON_USER_SERVICE = new AmazonUserServiceImpl();

    private static final AmazonProductService AMAZON_PRODUCT_SERVICE = new AmazonProductServiceImpl();

    /**
     * Returns the user's list method from amazon service for getting the user's list
     *
     * @return Collection view of User's list
     */
    public Collection<User> getUsersList() {
        return AMAZON_USER_SERVICE.getUsersList();
    }

    /**
     * Returns the user sign in method from amazon service for user sign in
     *
     * @param email    User's email
     * @param password User's password
     * @return Boolean value from the validateUser method on service
     */
    public boolean userSignIn(final String email, final String password) {
        return AMAZON_USER_SERVICE.userSignIn(email, password);
    }

    public boolean adminSignIn(final String email, final String password) {
        return AMAZON_USER_SERVICE.adminSignIn(email, password);
    }

    /**
     * Returns the UserSignUp method from amazon service for user sign up
     *
     * @param user User object
     */
    public void userSignUp(final User user) {
        AMAZON_USER_SERVICE.userSignUp(user);
    }

    public void adminSignUp(final User user) {
        AMAZON_USER_SERVICE.adminSignUp(user);
    }

    /**
     * Returns the IsUserEmailAlreadyExists method from amazon service for check the email is already exists or not
     *
     * @param emailId The User's email id
     * @return The boolean value from the isEmailAlreadyExists method from service
     */
    public boolean isUserEmailAlreadyExists(final String emailId) {
        return AMAZON_USER_SERVICE.isUserEmailAlreadyExists(emailId);
    }

    /**
     * Returns the IsAdminEmailAlreadyExists method from amazon service for check the email is already exists or not
     *
     * @param emailId The User's email id
     * @return The boolean value from the isEmailAlreadyExists method from service
     */
    public boolean isAdminEmailAlreadyExists(final String emailId) {
        return AMAZON_USER_SERVICE.isAdminEmailAlreadyExists(emailId);
    }

    /**
     * Returns the getUserDetails method from the amazon service for getting the user details
     *
     * @param email User's email
     * @return User Object from the amazon service
     */
    public User getUserDetails(final String email) {
        return AMAZON_USER_SERVICE.getUserDetails(email);
    }

    /**
     * Returns the getAdminDetails method from the amazon service for getting the user details
     *
     * @param email User's email
     * @return User Object from the amazon service
     */
    public User getAdminDetails(final String email) {
        return AMAZON_USER_SERVICE.getAdminDetails(email);
    }

    /**
     * Returns the updateUserEmail method from amazon service for update user email
     *
     * @param user  User object
     * @param email User's email for update
     * @return Boolean value from the updateUserEmail method
     */
    public boolean updateUserEmail(final User user, final String email) {
        return AMAZON_USER_SERVICE.updateUserEmail(user, email);
    }

    /**
     * Returns the updateUserName method from the amazon service for update username
     *
     * @param user     user object
     * @param userName username
     * @return Boolean value from updateUserName method
     */
    public boolean updateUserName(final User user, final String userName) {
        return AMAZON_USER_SERVICE.updateUserName(user, userName);
    }

    /**
     * Returns the updateUserPassword method from amazon service for update user password
     *
     * @param user     user object
     * @param password user password
     * @return Boolean value from the updateUserPassWord method
     */
    public boolean updateUserPassword(final User user, final String password) {
        return AMAZON_USER_SERVICE.updateUserPassword(user, password);
    }

    /**
     * Returns the updateUserAddress method from amazon service for update user address
     *
     * @param user    user object
     * @param address user address
     * @return Boolean value of updateUserAddress methods
     */
    public boolean updateUserAddress(final User user, final String address) {
        return AMAZON_USER_SERVICE.updateUserAddress(user, address);
    }

    /**
     * Returns the updateUserPhoneNumber method from amazon service for update phone number
     *
     * @param user    user object
     * @param phoneNo user phone number
     * @return Boolean value of updateUserPhoneNumber method
     */
    public boolean updateUserPhoneNumber(final User user, final String phoneNo) {
        return AMAZON_USER_SERVICE.updateUserPhoneNumber(user, phoneNo);
    }

    /**
     * Returns the deleteUser method from the amazon service for delete user from the user's list
     *
     * @param user user object
     * @return boolean value from the deleteUser method
     */
    public boolean deleteUser(final User user) {
        return AMAZON_USER_SERVICE.deleteUser(user);
    }

    /**
     * Returns the addProduct method from the amazon service for add the product to product list
     *
     * @param product Product object
     * @return boolean value from addProduct method
     */
    public boolean addProduct(final Product product) {
        return AMAZON_PRODUCT_SERVICE.addProduct(product);
    }

    /**
     * Returns the viewProducts method from amazon service for view product details
     *
     * @return Collection view of products from the viewProduct method
     */
    public Collection<Product> viewProduct() {
        return AMAZON_PRODUCT_SERVICE.viewProducts();
    }

    /**
     * Returns the updatedProductName method from amazon service for update product name
     *
     * @param id          ProductId
     * @param productName Product Name
     * @return boolean Value from updateProductName method
     */
    public boolean updateProductName(final int id, final String productName) {
        return AMAZON_PRODUCT_SERVICE.updateProductName(id, productName);
    }

    /**
     * Returns the updateProductDescription method from amazon service for update product description
     *
     * @param id                 product id
     * @param productDescription product description for update
     * @return boolean value from the updateProductDescription method
     */
    public boolean updateProductDescription(final int id, final String productDescription) {
        return AMAZON_PRODUCT_SERVICE.updateProductDescription(id, productDescription);
    }

    /**
     * Returns the updateProductPrice method from amazon service for update product price
     *
     * @param id           Product id
     * @param productPrice Product price
     * @return Boolean value from the updateProductPrice method
     */
    public boolean updateProductPrice(final int id, final double productPrice) {
        return AMAZON_PRODUCT_SERVICE.updateProductPrice(id, productPrice);
    }

    /**
     * Returns the deleteProductPrice method for delete the product
     *
     * @param id Product id of the product object
     * @return Boolean value from the deleteProduct method
     */
    public boolean deleteProduct(final int id) {
        return AMAZON_PRODUCT_SERVICE.deleteProduct(id);
    }

    /**
     * Returns the getProductsId method for getting set of product id's
     *
     * @return Set of product id's
     */
    public Set<Integer> getProductsId() {
        return AMAZON_PRODUCT_SERVICE.getProductsId();
    }

    /**
     * Returns the adminKeyVerification method for validating key from service
     * @param key user key for validate
     * @return boolean value returned from adminKeyVerification method
     */
    public boolean adminKeyVerification(final String key) {
        return AMAZON_USER_SERVICE.adminKeyVerification(key);
    }
}