package dev.romero.portfolio_api.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SlotDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isBooked;

}
