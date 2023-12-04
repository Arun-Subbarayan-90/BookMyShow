package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.BookTicketRequestDTO;
import com.scaler.bookmyshow.dto.BookTicketResponseDTO;
import com.scaler.bookmyshow.dto.ResponseStatus;
import com.scaler.bookmyshow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.scaler.bookmyshow.models.*;
@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;
    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO bookTicketRequestDTO){
        Booking booking;
        BookTicketResponseDTO bookTicketResponseDTO = new BookTicketResponseDTO();
        try{
            booking = bookingService.bookTicket(bookTicketRequestDTO.getUserId(),bookTicketRequestDTO.getShowSeatIds(),bookTicketRequestDTO.getShowId());
        }
        catch (Exception e){
            bookTicketResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            return bookTicketResponseDTO;
        }
        bookTicketResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        bookTicketResponseDTO.setBookingId(booking.getId());
        bookTicketResponseDTO.setPrice(booking.getAmount());
        return bookTicketResponseDTO;

    }
}
