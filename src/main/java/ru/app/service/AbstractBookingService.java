package ru.app.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import ru.app.model.Order;
import ru.app.repository.OrderRepository;
import ru.app.repository.RoomAvailabilityRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractBookingService {
    protected final RoomAvailabilityRepository roomAvailabilityRepository;
    protected final OrderRepository orderRepository;
    protected final EmailService emailService;

    public final Order processOrder(Order order) {
        validateOrder(order);
        reserveRooms(order);
        saveOrder(order);
        sendConfirmationEmail(order);
        return order;
    }

    protected abstract void reserveRooms(Order order);

    protected void validateOrder(Order order) {
        if (order.getFrom() == null || order.getTo() == null || order.getFrom().isAfter(order.getTo())) {
            throw new IllegalArgumentException("Invalid booking request");
        }
    }

    protected void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Async
    public void sendConfirmationEmail(Order order) {
        emailService.sendConfirmationEmail(order);
        log.info("Confirmation email sent to: {}", order.getEmail());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
