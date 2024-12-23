package ru.app.service;

import jakarta.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.app.exception.BusinessException;
import ru.app.model.Order;
import ru.app.model.RoomAvailability;
import ru.app.repository.OrderRepository;
import ru.app.repository.RoomAvailabilityRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl extends AbstractBookingService {

    private final Object lock = new Object();

    public BookingServiceImpl(RoomAvailabilityRepository roomAvailabilityRepository, OrderRepository orderRepository, EmailService emailService) {
        super(roomAvailabilityRepository, orderRepository, emailService);
    }

    @Override
    public void reserveRooms(Order order) {
        synchronized (lock) {
            validateOrder(order);
            List<LocalDate> bookingDates = order.getBookingDates();

            for (LocalDate date : bookingDates) {
                for (String roomId : order.getRoomId()) {
                    RoomAvailability availability = roomAvailabilityRepository.findByDateAndRoom(order.getHotelId(), roomId, date);
                    if (availability == null || availability.getQuota() < 1) {
                        throw new BusinessException("No rooms with id '" + roomId + "' available for date: " + date);
                    }
                    availability.setQuota(availability.getQuota() - 1);
                    try {
                        roomAvailabilityRepository.save(availability);
                    } catch (OptimisticLockException e) {
                        throw new BusinessException("Room was already booked by another user for date: " + date);
                    }
                }
            }
            log.info("Order successfully created: {}", order);
        }
    }
}