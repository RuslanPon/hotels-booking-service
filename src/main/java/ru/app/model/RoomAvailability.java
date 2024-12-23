package ru.app.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RoomAvailability {
    private String hotelId;
    private String roomId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private int quota;
}
