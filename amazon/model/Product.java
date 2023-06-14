package com.amazon.model;

/**
 * Represents the products, provides the methods for get and set the product details
 */
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private Long adminId;

    public enum Category {
        MOBILE_PHONES, FOOTWEAR, ELECTRONICS, CLOTHING, KITCHEN_APPLIANCES, SPORTS, BOOKS, TOYS
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setAdminId(final Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String toString() {
        return String.format("product id : %d\nproduct category:%s\nproduct name : %s\ndescription : %s\nprice : %f\n",
                id, category, name, description, price);
    }
}
