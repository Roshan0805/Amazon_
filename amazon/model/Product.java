package com.amazon.model;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;
    private String category;

    public enum ProductCategories {
        ELECTRONICS, CLOTHING, KITCHEN_APPLIANCES, SPORTS, BOOKS, TOYS
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void setId(final int id) {
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

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("product id : %d\nproduct category:%s\nproduct name : %s\ndescription : %s\nprice : %f\n",
                id, name, description, price);
    }
}
