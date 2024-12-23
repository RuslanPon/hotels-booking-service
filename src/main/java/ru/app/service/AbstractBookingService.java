package ru.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.app.model.Order;
import ru.app.model.dto.OrderDTO;
import ru.app.repository.OrderRepository;
import ru.app.repository.RoomAvailabilityRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractBookingService implements BookingService {
    protected final RoomAvailabilityRepository roomAvailabilityRepository;
    protected final OrderRepository orderRepository;
    protected final EmailService emailService;

    @Override
    public final OrderDTO processOrder(Order order) {
        validateOrder(order);
        reserveRooms(order);
        saveOrder(order);
        sendConfirmationEmail(order);

        return OrderDTO.builder()
                .hotelId(order.getHotelId())
                .roomId(order.getRoomId())
                .email(order.getEmail())
                .from(order.getFrom())
                .to(order.getTo())
                .build();
    }

    @Override
    public void validateOrder(Order order) {
        if (order.getFrom() == null || order.getTo() == null || order.getFrom().isAfter(order.getTo())) {
            throw new IllegalArgumentException("Invalid booking request");
        }
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void sendConfirmationEmail(Order order) {
        emailService.sendConfirmationEmail(order);
        log.info("Confirmation email sent to: {}", order.getEmail());
    }

    @Override
    public List<OrderDTO> getAllOrders() { //В идеале затащить MapStruct библиотечку.
        return orderRepository.findAll().stream()
                .map(order -> OrderDTO.builder()
                        .hotelId(order.getHotelId())
                        .roomId(order.getRoomId())
                        .email(order.getEmail())
                        .from(order.getFrom())
                        .to(order.getTo())
                        .build())
                .toList();
    }
}
