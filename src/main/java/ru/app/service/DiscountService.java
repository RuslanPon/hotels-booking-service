package ru.app.service;

import ru.app.model.Order;

public interface DiscountService {
    double calculatePriceWithDiscount(Order order, double basePrice); // Пример логики: скидка 10%, если указана программа лояльности либо бронирование от 3х ночей
}
