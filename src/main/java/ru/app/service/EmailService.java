package ru.app.service;

import ru.app.model.Order;

public interface EmailService {
    void sendConfirmationEmail(Order order);
}
