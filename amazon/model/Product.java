package com.amazon.model;

/**
 * Represents the product
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
                    return Category.MOBILE_PHONES;
                case 2:
                    return Category.FOOTWEAR;
                case 3:
                    return Category.ELECTRONICS;
                case 4:
                    return Category.CLOTHING;
                case 5:
                    return Category.KITCHEN_APPLIANCES;
                case 6:
                    return Category.SPORTS;
                case 7:
                    return Category.BOOKS;
                case 8:
                    return Category.TOYS;
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

    public void setId(final Long id) {
        this.id = id;
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

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setUpdatedTime(final String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String toString() {
        return String.format("product id : %d\nproduct category:%s\nproduct name : %s\ndescription : %s\nprice : %f\nupdated time :%s\n",
                id, category, name, description, price, updatedTime);
    }
}