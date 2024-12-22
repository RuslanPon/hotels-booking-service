package ru.app.service;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.app.model.Order;
import ru.app.model.RoomAvailability;
import ru.app.repository.OrderRepository;
import ru.app.repository.RoomAvailabilityRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService extends AbstractBookingService{

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    public BookingService(@NonNull RoomAvailabilityRepository roomAvailabilityRepository, @NonNull OrderRepository orderRepository, @NonNull EmailService emailService) {
        super(roomAvailabilityRepository, orderRepository, emailService);
    }

    @Override
    protected void reserveRooms(Order order) {
        validateOrder(order);
        List<LocalDate> bookingDates = order.getBookingDates();

        for (LocalDate date : bookingDates) {
            for (String roomId : order.getRoomId()) {
                RoomAvailability availability = roomAvailabilityRepository.findByDateAndRoom(order.getHotelId(), roomId, date);
                if (availability == null || availability.getQuota() < 1) {
                    throw new IllegalStateException("No rooms with id '" + roomId + "' available for date: " + date);
                }
                availability.setQuota(availability.getQuota() - 1);
                roomAvailabilityRepository.save(availability);
            }
        }
        sendConfirmationEmail(order);
        logger.info("Order successfully created: {}", order);
    }
}
