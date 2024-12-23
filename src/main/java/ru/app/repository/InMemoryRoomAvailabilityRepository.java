package ru.app.repository;

import ru.app.model.RoomAvailability;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryRoomAvailabilityRepository implements RoomAvailabilityRepository {

    public static final String DELUXE = "deluxe";
    public static final String LUX = "lux";
    public static final String REDDISON = "reddison";

    private final List<RoomAvailability> availabilities = new ArrayList<>(List.of(
            new RoomAvailability(REDDISON, LUX, LocalDate.of(2024, 1, 1), 1),
            new RoomAvailability(REDDISON, LUX, LocalDate.of(2024, 1, 2), 1),
            new RoomAvailability(REDDISON, LUX, LocalDate.of(2024, 1, 3), 1),
            new RoomAvailability(REDDISON, LUX, LocalDate.of(2024, 1, 4), 1),
            new RoomAvailability(REDDISON, LUX, LocalDate.of(2024, 1, 5), 0),
            new RoomAvailability(REDDISON, DELUXE, LocalDate.of(2024, 1, 2), 1),
            new RoomAvailability(REDDISON, DELUXE, LocalDate.of(2024, 1, 3), 1),
            new RoomAvailability(REDDISON, DELUXE, LocalDate.of(2024, 1, 4), 1)
    ));

    @Override
    public RoomAvailability findByDateAndRoom(String hotelId, String roomId, LocalDate date) {
        return availabilities.stream()
                .filter(a -> Objects.equals(a.getHotelId(), hotelId) && Objects.equals(a.getRoomId(), roomId) && Objects.equals(a.getDate(), date))
                .findFirst().orElse(null);
    }

    @Override
    public void save(RoomAvailability availability) {
        availabilities.removeIf(a -> a.getHotelId().equals(availability.getHotelId()) && a.getRoomId().equals(availability.getRoomId()) && a.getDate().equals(availability.getDate()));
        availabilities.add(availability);
    }
}
