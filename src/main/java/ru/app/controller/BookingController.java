package ru.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.app.model.Order;
import ru.app.service.AbstractBookingService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class BookingController {
    private final AbstractBookingService bookingService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        return ResponseEntity.ok(bookingService.processOrder(newOrder));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(bookingService.getAllOrders());
    }
}
