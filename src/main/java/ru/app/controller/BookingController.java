package ru.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.app.model.Order;
import ru.app.model.dto.OrderDTO;
import ru.app.service.AbstractBookingService;
import ru.app.service.IBookingService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class BookingController {
    private final IBookingService bookingService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody Order newOrder) {
        return bookingService.processOrder(newOrder);
    }

    @GetMapping
    public List<OrderDTO> getOrders() {
        return bookingService.getAllOrders();
    }
}
