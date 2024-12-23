package ru.app.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private String hotelId;
    private List<String> roomId;
    private String email;
    private LocalDateTime from;
    private LocalDateTime to;
}
