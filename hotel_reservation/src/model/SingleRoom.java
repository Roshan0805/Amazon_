package model;

public class SingleRoom extends HotelRoom {

    private Long roomId;
    private boolean isAvailable;
    private Double price;

    public SingleRoom(Long id) {
        super(id);
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
