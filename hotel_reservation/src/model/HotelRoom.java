package model;

public class HotelRoom {
    private Long roomId;

    public HotelRoom(final Long id) {
        this.roomId = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
