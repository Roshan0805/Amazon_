package controller;

import model.*;
import service.Service;
import service.impl.ServiceImpl;

import java.util.List;

public class Controller {

    private static final Controller CONTROLLER = new Controller();
    private static final Service SERVICE = ServiceImpl.getInstance();

    private Controller() {}

    public static Controller getInstance() {
        return CONTROLLER;
    }

    public boolean signUp (final Admin admin) {
        return
    }

    public boolean signIn(final String email, final String password) {
       return SERVICE.signIn(email,password);
    }

    public boolean addSingleRoom(final SingleRoom room) {
        return SERVICE.addSingleRoom(room);
    }

    public boolean addDoubleRoom(final DoubleRoom room) {
        return SERVICE.addDoubleRoom(room);
    }

    public boolean addSuite(final Suite suite {
        return SERVICE.addSuite(suite);
    }

    public List<SingleRoom> getAvailableRoom(final int choice) {
        return SERVICE.getAvailableRoom(choice);
    }

    public boolean registerSingleRoom (final HotelRoom room) {
        SERVICE.registerSingleRoom(room);
    }

    public boolean registerDoubleRoom (final HotelRoom room) {
        SERVICE.registerDoubleRoom(room);
    }

    public boolean registerSuite (final HotelRoom room) {
        SERVICE.registerSuite(room);
    }
}
