package com.scaler.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketRequestDTO {
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;

}
