package com.amazon.model;

/**
 * <p> Represents the Order </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class Order {

    private Long id;
    private Long productId;
    private Long cartId;
    private String productName;
    private Long productCount;
    private Double totalPrice;
    private Long userId;
    private Payment paymentType;

    public Order() {}

    public Order(final Cart cart, final Payment paymentType) {
        this.cartId = cart.getId();
        this.productId = cart.getProductId();
        this.productName = cart.getProductName();
        this.productCount = cart.getProductCount();
        this.totalPrice = cart.getTotalPrice();
        this.userId = cart.getUserId();
        this.paymentType = paymentType;
    }

    public enum Payment {
        PAY_ON_DELIVERY(1), CREDIT_OR_DEBIT_CART(2), NET_BANKING(3), OTHER_UPI_PAYMENTS(4);

        private final int index;

        Payment(final int index) {
            this.index = index;
        }

        public static Payment getPayment(final int index) {

            switch (index) {
                case 1:
                    return PAY_ON_DELIVERY;
                case 2:
                    return CREDIT_OR_DEBIT_CART;
                case 3:
                    return NET_BANKING;
                case 4:
                    return OTHER_UPI_PAYMENTS;
                default:
                    return null;
            }
        }
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCount() {
        return productCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getUserId() {
        return userId;
    }

    public Payment getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Payment paymentType) {
        this.paymentType = paymentType;
    }

    public void setProductId(final Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductCount(final Long count) {
        this.productCount = count;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setTotalPrice(final Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return String.format("Order id :%d\nproduct name : %s\nproduct quantity : %d\ntotal amount : %f",
               id,productName, productCount, totalPrice);
    }
}
