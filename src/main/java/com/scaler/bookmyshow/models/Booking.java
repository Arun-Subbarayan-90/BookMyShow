package com.scaler.bookmyshow.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<Payment> payments;
    private int amount;

}
