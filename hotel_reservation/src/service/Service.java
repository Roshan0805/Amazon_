package service;

import model.*;

import java.util.List;

public interface Service {

    boolean signUp(final Admin admin);

    boolean signIn(final String email, final String password);

    boolean addSingleRoom(final SingleRoom room);

    boolean addDoubleRoom(final DoubleRoom room);

    boolean addSuite(final Suite suite);

    List<SingleRoom> getAvailableRoom(final int choice);

    boolean registerSingleRoom (final HotelRoom room);

    boolean registerDoubleRoom (final HotelRoom room);

    boolean registerSuite (final HotelRoom room);
}
