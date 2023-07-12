package model;

import java.util.ArrayList;
import java.util.List;

public class RegisteredRoom {

    private Long id;
    private Long customerId;
    private List<HotelRoom> registeredRoom;
    private double totalPrice;

    public RegisteredRoom() {
        registeredRoom = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<HotelRoom> getRegisteredRoom() {
        return registeredRoom;
    }

    public void setRegisteredRoom(List<HotelRoom> registeredRoom) {
        this.registeredRoom = registeredRoom;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
