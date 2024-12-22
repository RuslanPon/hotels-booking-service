package ru.app.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Data
@Builder
public class OrderDTO {
    private String hotelId;
    private List<String> roomId;
    private String email;
    private LocalDateTime from;
    private LocalDateTime to;
}
