package com.amazon.model;

/**
 * Represents the user
 */
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Boolean isAdmin = false;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPhoneNumber(final String phoneNo) {
        this.phoneNumber = phoneNo;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setIsAdmin(final boolean value) {
        this.isAdmin = value;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public String toString() {
        return String.format("user id -%d\nusername - %s\nemail -%s\naddress - %s\nphone no - %s\n", id, name, email, address, phoneNumber);
    }
}