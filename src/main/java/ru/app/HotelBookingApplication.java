package ru.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.app.repository.InMemoryOrderRepository;
import ru.app.repository.InMemoryRoomAvailabilityRepository;
import ru.app.repository.OrderRepository;
import ru.app.repository.RoomAvailabilityRepository;

@SpringBootApplication
public class HotelBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelBookingApplication.class, args);
    }
}