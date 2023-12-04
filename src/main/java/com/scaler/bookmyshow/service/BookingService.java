package com.scaler.bookmyshow.service;

import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repository.ShowRepository;
import com.scaler.bookmyshow.repository.ShowSeatRepository;
import com.scaler.bookmyshow.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private PriceService priceService;
    @Transactional(isolation= Isolation.SERIALIZABLE)
    public Booking bookTicket(Long userId, List<Long> showSeatIds, Long showId){
              /*
        ------ START lock here for this lecture
        * 1. Get the user with user id from DB
        * 2. Get the show details from DB
        * ------------ Start LOCK here ------------------
        * 3. get the show seats with the given show seats ids from DB
        * 4. check if the show seats are available
        * 5. If they are not available, throw an error
        * 6. If they are available, update the status to blocked and update the timestamp
        * 7. Update the show seats in DB
        * --------- Release lock here -------------------
        * 8. return
        -------- END lock here for this lecture ------------
        * */
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
            throw new RuntimeException();
        User bookedBy = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty())
            throw new RuntimeException();
        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat: showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), LocalDateTime.now()).toMinutes() < 15))){
                throw new RuntimeException();
            }
        }
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat: showSeats){
           showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
           showSeat.setBlockedAt(new Date());
           savedShowSeats.add(showSeatRepository.save(showSeat));
        }
        Booking booking = new Booking();
        booking.setUser(bookedBy);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceService.calculatePrice(savedShowSeats,show));
        booking.setShowSeats(savedShowSeats);
        return booking;
    }

}
