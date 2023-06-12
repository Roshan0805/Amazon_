package com.amazon.model;

/**
 * Represents the user provides services like set and get the user details
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private Boolean adminStatus = false;

    public void setId(final int id) {
        this.id = id;
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

    public String getEmailId() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setAsAdmin() {
        this.adminStatus = true;
    }

    public boolean getAdminStatus() {
        return adminStatus;
    }
    public String toString() {
        return String.format("user id -%d\tusername - %s\temail -%s\taddress - %s\tphone no - %s\n", id, name, email, address, phoneNumber);
    }
}
