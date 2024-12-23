# hotels-booking-service

Создать заказ
```sh
curl --location --request POST 'localhost:8080/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "hotel_id": "reddison",
    "room_ids": ["lux", "deluxe"],
    "email": "guest@mail.ru",
    "from": "2024-01-02T00:00:00Z",
    "to": "2024-01-04T00:00:00Z"
}'
```
Получить все заказы
```sh
curl --location --request GET 'localhost:8080/orders'
```