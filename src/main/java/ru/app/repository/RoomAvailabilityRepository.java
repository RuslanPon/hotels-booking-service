package ru.app.repository;

import ru.app.model.RoomAvailability;

import java.time.LocalDate;

public interface RoomAvailabilityRepository {
    RoomAvailability findByDateAndRoom(String hotelId, String roomId, LocalDate date);
    void save(RoomAvailability availability);
}
