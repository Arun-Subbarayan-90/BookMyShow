package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDTO {

    private Long bookingId;
    private int price;

    private ResponseStatus  responseStatus;
}
