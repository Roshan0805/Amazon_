package com.amazon.model;

/**
 * <p> Represents the Product </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;
    private String updatedTime;
    private Long adminId;

    public enum Category {

        MOBILE_PHONES(1), FOOTWEAR(2), ELECTRONICS(3), CLOTHING(4), KITCHEN_APPLIANCES(5),
        SPORTS(6), BOOKS(7), TOYS(8);

        private int index;

        Category(int index) {
            this.index = index;
        }

        public static Category getCategory(final int index) {

            switch (index) {
                case 1:
                    return MOBILE_PHONES;
                case 2:
                    return FOOTWEAR;
                case 3:
                    return ELECTRONICS;
                case 4:
                    return CLOTHING;
                case 5:
                    return KITCHEN_APPLIANCES;
                case 6:
                    return SPORTS;
                case 7:
                    return BOOKS;
                case 8:
                    return TOYS;
                default:
                    return null;
            }
        }
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() { return this.name;}

    public void setId(final Long id) {
        this.id = id;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
    public Double getPrice() { return this.price;}

    public Long getId() {
        return this.id;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return this.adminId;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(final String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String toString() {
        return String.format("product id : %d\nproduct category:%s\nproduct name : %s\ndescription : %s\nprice : %f\nupdated time :%s\n",
                id, category, name, description, price, updatedTime);
    }
}