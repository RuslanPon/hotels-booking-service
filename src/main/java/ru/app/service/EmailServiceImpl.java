package ru.app.service;

import org.springframework.stereotype.Service;
import ru.app.model.Order;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendConfirmationEmail(Order order) {
        // Просто заглушка
        System.out.println("Booking details: " + order);
    }
}
