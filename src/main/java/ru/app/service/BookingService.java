package ru.app.service;

import ru.app.model.Order;
import ru.app.model.dto.OrderDTO;

import java.util.List;

public interface BookingService {
    OrderDTO processOrder(Order order);

    void reserveRooms(Order order);

    void validateOrder(Order order);

    void saveOrder(Order order);

    void sendConfirmationEmail(Order order);

    List<OrderDTO> getAllOrders();
}
