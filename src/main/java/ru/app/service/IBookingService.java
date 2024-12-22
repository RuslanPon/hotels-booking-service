package ru.app.service;

import org.springframework.scheduling.annotation.Async;
import ru.app.model.Order;
import ru.app.model.dto.OrderDTO;

import java.util.List;

public interface IBookingService {
    OrderDTO processOrder(Order order);

    void reserveRooms(Order order);

    void validateOrder(Order order);

    void saveOrder(Order order);

    @Async
    void sendConfirmationEmail(Order order);

    List<OrderDTO> getAllOrders();
}
