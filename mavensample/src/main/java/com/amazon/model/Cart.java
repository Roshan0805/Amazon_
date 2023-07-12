package com.amazon.model;

/**
 * <p> Represents the cart </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class Cart {

    private Long id;
    private Long productId;
    private String productName;
    private Long productCount;
    private Double totalPrice;
    private Long userId;

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getProductCount() {
        return productCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductCount(final Long count) {
        this.productCount = count;
    }

    public void setTotalPrice(final Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return String.format("Cart id : %d\nproduct name : %s product quantity : %d\ntotal amount : %f",
                id, productName, productCount, totalPrice);
    }
}
