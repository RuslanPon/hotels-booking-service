package ru.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Version;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Data
public class Order {
    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("room_ids")
    private List<String> roomId;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime from;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime to;

    @Version
    private Long version;

    public List<LocalDate> getBookingDates() {
        return Stream.iterate(from.toLocalDate(), date -> date.plusDays(1))
                .limit(to.toLocalDate().toEpochDay() - from.toLocalDate().toEpochDay() + 1)
                .toList();
    }
}
