package com.amazon.model;

public class User {

    private String name;
    private String email;
    private String password;
    private String address;
    private int id;
    private String phoneNo;

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

    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
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

    public String toString() {
        return String.format("user id -%d\tusername - %s\temail -%s\taddress - %s\tphone no - %s\n", id, name, email, address, phoneNo);
    }
}
