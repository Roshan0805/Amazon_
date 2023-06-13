package com.amazon.model;

/**
 * Represents the product provides services like set and get the product details
 */
public class Product {

    private long id;
    private String name;
    private String description;
    private double price;
    private Category category;
    private int adminId;

    public enum Category {
        MOBILE_PHONES, FOOTWEAR, ELECTRONICS, CLOTHING, KITCHEN_APPLIANCES, SPORTS, BOOKS, TOYS
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setAdminId(final int adminId) {
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public String toString() {
        return String.format("product id : %d\nproduct category:%s\nproduct name : %s\ndescription : %s\nprice : %f\n",
                id, category, name, description, price);
    }
}
