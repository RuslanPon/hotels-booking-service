package ru.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.app.model.Order;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Async("taskExecutor")
    @Override
    public void sendConfirmationEmail(Order order) {
        // Просто заглушка
        log.info("Booking details: {}", order);
    }
}
