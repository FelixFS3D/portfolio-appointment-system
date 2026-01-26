package dev.romero.portfolio_api.DTO;

import lombok.Data;


import java.time.ZonedDateTime;

@Data
public class SlotDTO {
    private Long id;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Boolean isBooked;

}
