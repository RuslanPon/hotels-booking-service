package ru.app.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import ru.app.model.Order;
import ru.app.model.dto.OrderDTO;
import ru.app.repository.OrderRepository;
import ru.app.repository.RoomAvailabilityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractBookingService implements IBookingService {
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

    @Async
    @Override
    public void sendConfirmationEmail(Order order) {
        emailService.sendConfirmationEmail(order);
        log.info("Confirmation email sent to: {}", order.getEmail());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
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
