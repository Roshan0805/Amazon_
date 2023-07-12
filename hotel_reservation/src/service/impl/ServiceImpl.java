package service.impl;

import model.*;
import service.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceImpl implements Service {

    private static final ServiceImpl SERVICE = new ServiceImpl();
    private static final Map<Long, Admin> ADMIN_LIST = new HashMap<>();
    private static final Map<Long, HotelRoom> SINGLE_ROOM_LIST = new HashMap<>();
    private static final Map<Long, HotelRoom> DOUBLE_ROOM_LIST = new HashMap<>();
    private static final Map<Long, HotelRoom> SUITE_LIST = new HashMap<>();

    private ServiceImpl() {}

    public static Service getInstance() {
        return SERVICE;
    }
    public boolean signUp(final Admin admin) {
        try {
            ADMIN_LIST.put(admin.getId(), admin);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean signIn(String email, String password) {
        for(Admin admin: ADMIN_LIST.values()) {

            if(admin.getEmail().equals(email) && admin.getPassWord().equals(password)) {
                return true;
            }
        }
    }

    public boolean addSingleRoom(final SingleRoom room) {
        try {
            SINGLE_ROOM_LIST.put(room.getRoomId(), room);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean addDoubleRoom(final DoubleRoom room) {
        try {
            DOUBLE_ROOM_LIST.put(room.getRoomId(), room);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean addSuite(final Suite suite) {
        try {
            SUITE_LIST.put(suite.getSuiteId(), suite);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Map<Long, HotelRoom> getAvailableRoom(final int choice) {
        switch (choice) {
            case 1:
                return SINGLE_ROOM_LIST;
            case 2:
                return DOUBLE_ROOM_LIST;
            case 3:
                return SUITE_LIST;
            default:
                return null;
        }
    }

    public boolean registerSingleRoom(final HotelRoom room) {

    }

    public boolean registerDoubleRoom(final HotelRoom room) {
        final RegisteredRoom registered = new RegisteredRoom();
        registered.setId(room.getRoomId());
        final List<HotelRoom> registeredRooms = new ArrayList<>();
        registeredRooms.add(room);
        registered.setRegisteredRoom(registeredRooms);

    }

    public boolean registerSuite(final HotelRoom room) {



}
